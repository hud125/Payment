package com.unionpay.upop.interbank;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unionpay.upop.sdk.util.PropManager;

public class InterbankOpenServlet extends HttpServlet{

	private static final long serialVersionUID = 6449836020074467628L;

	private static final String CERT_INDEX = "certIdx";
	private static final String  UPOP_OPEN_REQ = "UPOPOpenReq";
	
	public void init() {
		
	}

	public void service(HttpServletRequest request, HttpServletResponse response) {
	    
		try {
			String certIndex = request.getParameter(CERT_INDEX);
			
			request.setAttribute("certIdx", certIndex);
			
			String encryptInfo = request.getParameter(UPOP_OPEN_REQ);
			
			RequestVO vo = parse(certIndex, encryptInfo);
			request.setAttribute("requestVo", vo);
			
			String cup_certificate = PropManager.getSdkInstance().getProperty("cup_certificate");
			request.setAttribute("successRes", getSuccessInfo(cup_certificate, vo));
			request.setAttribute("failRes", getFailInfo(cup_certificate, vo));
			
			String cupSerialNumber = PropManager.getSdkInstance().getProperty("cup_serialNumber");
			request.setAttribute("cupSerialNumber", cupSerialNumber);
			
			String url = PropManager.getSdkInstance().getProperty("interank_notify_url");
			request.setAttribute("notifyUrl", url);
			
			request.getRequestDispatcher("interbank.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private RequestVO parse(String certIndex, String encryptInfo){
		String result = InterbankOpenUtils.decryptByPrivateKey(encryptInfo, certIndex);
		
		RequestVO vo = new RequestVO();
		
		if(result != null){
			String [] arr = result.split("&");
			
			for(String temp : arr){
				String [] request = temp.split("=");
				
				if("msgID".equalsIgnoreCase(request[0])){
					vo.setMsgID(request[1]);
				}
				if("pan".equalsIgnoreCase(request[0])){
					vo.setPan(request[1]);
				}
				if("frontUrl".equalsIgnoreCase(request[0])){
					vo.setFrontUrl(request[1]);
				}
				if("backUrl".equalsIgnoreCase(request[0])){
					vo.setBackUrl( request[1]);
				}
			}
		}
		return vo;
	}
	
	private String getSuccessInfo(String certName, RequestVO vo){
		String str = "msgID="+vo.getMsgID()+"&pan="+vo.getPan()+"&phone=13564747474&respCode=00";
		return InterbankOpenUtils.entryptByPublicKey(str, certName);
	}
	
	private String getFailInfo(String certName, RequestVO vo){
		String str = "msgID="+vo.getMsgID()+"&pan="+vo.getPan()+"&phone=13564747474&respCode=P1";
		return InterbankOpenUtils.entryptByPublicKey(str, certName);
	}


}
