package com.unionpay.upop.sdk.cases;

import com.unionpay.upop.sdk.common.BackStageCase;
import com.unionpay.upop.sdk.enums.Service;

public class AuthPaySms extends BackStageCase {
    
	public void doTrans() {
		postRequest(Service.SMS);
	}
	
    public static void main(String[] args) {
        AuthPaySms authPaySms = new AuthPaySms();
        authPaySms.doTrans();
    }
}
