package com.unionpay.upop.sdk.common;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unionpay.upop.sdk.SdkConf;
import com.unionpay.upop.sdk.enums.Service;
import com.unionpay.upop.sdk.util.PayUtils;
import com.unionpay.upop.sdk.util.PropManager;

/**
 * 支付通知类，商户可以根据需要自己实现。一般分为三步： 1. 解析返回报文 2. 验证签名 3. 具体业务逻辑
 * 
 * @author Malcolm
 *
 */
public class PayResponseServLet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6247574514957274823L;

	public void init() {

	}

	public void service(HttpServletRequest request, HttpServletResponse response) {
		String charset = PropManager.getMerInstance().getProperty("charset");
		try {
			request.setCharacterEncoding(charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		response.setContentType("text/html;charset=" + charset);
		response.setCharacterEncoding(charset);

		String signature = request.getParameter("signature");
		String signMethod = request.getParameter("signMethod");

		Map<String, String> map = new TreeMap<String, String>();
		for (int i = 0; i < SdkConf.notifyVo.length; i++) {
			map.put(SdkConf.notifyVo[i], request.getParameter(SdkConf.notifyVo[i]));
		}
		map.put("signMethod", signMethod);
		map.put("signature", signature);

		boolean isTrusted = PayUtils.verifySignature(map, "88888888", "UTF-8");
		if (isTrusted) {
			int status = PayUtils.getFrontUpSupport(Service.NOTIFY).handleResponse(response, map);
			response.setStatus(status);
		} else {
			System.out.println("验证签名失败：" + map);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}
}
