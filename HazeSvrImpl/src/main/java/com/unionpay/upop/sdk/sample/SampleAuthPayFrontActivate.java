package com.unionpay.upop.sdk.sample;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unionpay.upop.sdk.PayRequest;
import com.unionpay.upop.sdk.common.FrontUPSupport;
import com.unionpay.upop.sdk.util.PayUtils;

public class SampleAuthPayFrontActivate implements FrontUPSupport {

	@Override
	public PayRequest createRequest(HttpServletRequest request) {
		return PayUtils.mockRequest(getClass());
	}

	@Override
	public int handleResponse(HttpServletResponse response, Map<String, String> responseMap) {
		throw new UnsupportedOperationException("Method createRequest() is unsupported");
	}
}
