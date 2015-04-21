package com.unionpay.upop.sdk.common;

import java.util.Map;

import com.unionpay.upop.sdk.PayRequest;
import com.unionpay.upop.sdk.enums.Service;
import com.unionpay.upop.sdk.util.PayUtils;

/**
 * 后台交易类，商户可以根据需要自己实现。一般分为三步： 1. 创建PayRequest请求对象 2. 提交请求到相应的服务 3. 验证签名 4. 具体业务逻辑
 * 
 * @author Malcolm
 *
 */
public class BackStageCase {

	public void postRequest(Service service) {
		postRequest(service, null);
	}

	/**
	 * 向银联发送后续交易请求
	 * 
	 */
	public void postRequest(Service service, PayRequest payRequest) {
		BackStageUPSupport upSupport = PayUtils.getBackStageUpSupport(service);
		if (payRequest == null) {
			payRequest = upSupport.createRequest();
		}

		String res = PayUtils.doPostQueryCmd(service.getUrl(),
				PayUtils.createPostDataForBackTrans(service.getParamArray(), payRequest), payRequest.getCharset());

		if (PayUtils.isNotBlank(res)) {
			Map<String, String> response = PayUtils.getResponse(res);
			if (PayUtils.verifySignature(response, payRequest.getSecurityKey(), payRequest.getCharset())) {// 验证签名
				upSupport.handleResponse(response);// 商户业务逻辑
			} else {
				System.out.println("验证签名失败：" + response);
			}
		} else {
			System.out.println("报文格式为空");
		}
	}
}
