package com.aurfy.haze.web.velocity;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.tools.view.ViewToolContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.service.api.perm.PermissionService;
import com.aurfy.haze.service.bean.infra.UserBean;
import com.aurfy.haze.service.bean.perm.PermAssignmentBean;
import com.aurfy.haze.service.bean.perm.PermEntryBean;
import com.aurfy.haze.service.client.ServiceClient;

/**
 * 
 * 获取工程左侧栏目
 * 
 * @author zhangcheng
 *
 */
public class PermissionDirective extends Directive {

	private static final Logger logger = LoggerFactory.getLogger(PermissionDirective.class);

	public PermissionDirective() {
	}

	public String getName() {
		return "perm";
	}

	public int getType() {
		return BLOCK;
	}

	public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException,
			ResourceNotFoundException, ParseErrorException, MethodInvocationException {

		ViewToolContext viewToolContext = (ViewToolContext) context.getInternalUserContext();
		HttpServletRequest request = viewToolContext.getRequest();

		PermissionService permService = null;
		List<PermAssignmentBean> permAssignments = null;
		try {
			HttpSession session = request.getSession();
			UserBean userContext = (UserBean) session.getAttribute("userContext");
			permService = ServiceClient.createPermissionService();
			permAssignments = permService.listPermission(userContext.getID());
		} catch (Exception e) {
			logger.error("can not retrive the permission", e);
			return false;
		}

		List<String> entryKeys = new ArrayList<String>();

		for (PermAssignmentBean permAssignment : permAssignments) {
			PermEntryBean permEntryBean = permAssignment.getPermEntryBean();
			entryKeys.add(permEntryBean.getEntryKey());
		}

		String entryKeyText = Arrays.toString(entryKeys.toArray());
		logger.debug(entryKeyText);

		// 获取权限Key
		SimpleNode sn_key = (SimpleNode) node.jjtGetChild(0);
		String key = (String) sn_key.value(context);

		Node body = node.jjtGetChild(1);
		if (entryKeyText.indexOf(key) != -1) {
			StringWriter sw = new StringWriter();
			body.render(context, sw);
			writer.write(sw.toString());
		}

		return true;
	}
}
