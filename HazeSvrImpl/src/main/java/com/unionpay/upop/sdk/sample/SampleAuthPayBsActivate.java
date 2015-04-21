package com.unionpay.upop.sdk.sample;

import java.util.Map;

import com.unionpay.upop.sdk.PayRequest;
import com.unionpay.upop.sdk.common.BackStageUPSupport;
import com.unionpay.upop.sdk.util.PayUtils;

public class SampleAuthPayBsActivate implements BackStageUPSupport {

	@Override
	public PayRequest createRequest() {
		return PayUtils.mockRequest(getClass());
	}

	@Override
	public void handleResponse(Map<String, String> response) {
		String respCode = response.get("respCode");
		String respMsg = response.get("respMsg");

		System.out.println(">>>respCode : " + respCode);
		System.out.println(">>>respMsg : " + respMsg);
	}

}
