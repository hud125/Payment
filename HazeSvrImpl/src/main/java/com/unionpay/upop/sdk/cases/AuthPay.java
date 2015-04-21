package com.unionpay.upop.sdk.cases;

import com.unionpay.upop.sdk.common.BackStageCase;
import com.unionpay.upop.sdk.enums.Service;

/**
 * 名称：认证支付2.0交易
 *
 */
public class AuthPay extends BackStageCase {

	/**
	 * 认证支付2.0交易入口
	 * 
	 */
	public void doTrans() {
		postRequest(Service.AUTH_PAY);
	}

    public static void main(String[] args) {
        AuthPay authPay = new AuthPay();
        authPay.doTrans();
    }
}
