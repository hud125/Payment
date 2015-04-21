package com.unionpay.upop.sdk.common;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unionpay.upop.sdk.PayRequest;
import com.unionpay.upop.sdk.enums.Service;
import com.unionpay.upop.sdk.util.PayUtils;

/**
 * 前台支付类，商户可以根据需要自己实现。一般分为三步：
 * 1. 创建PayRequest请求对象
 * 2. 调用PayUtils.createPayHtml创建自动提交表单
 * 3. 提交表单
 * 
 * @author Malcolm
 *
 */
public class FrontPayServLet extends HttpServlet {
    
	private static final long serialVersionUID = -6247574514957274823L;
	
	public void init() {
		
	}

	public void service(HttpServletRequest request, HttpServletResponse response) {
	    
	    PayRequest payRequest = PayUtils.getFrontUpSupport(Service.FRONT_PAY).createRequest(request);

		String html = PayUtils.createPayHtml(payRequest);//跳转到银联页面支付
		
		response.setContentType("text/html;charset=" + payRequest.getCharset());   
		response.setCharacterEncoding(payRequest.getCharset());
		
		int status = HttpServletResponse.SC_OK;
		try {
			response.getWriter().write(html);
		} catch (IOException e) {
			e.printStackTrace();
			status = HttpServletResponse.SC_REQUEST_TIMEOUT;
		}
		response.setStatus(status);
	}
}
