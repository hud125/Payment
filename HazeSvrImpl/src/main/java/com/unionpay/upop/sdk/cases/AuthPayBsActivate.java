package com.unionpay.upop.sdk.cases;

import com.unionpay.upop.sdk.common.BackStageCase;
import com.unionpay.upop.sdk.enums.Service;

public class AuthPayBsActivate extends BackStageCase {
	public void doTrans() {
		postRequest(Service.BS_ACTIVATE);
	}

	public static void main(String[] args) {
		AuthPayBsActivate bsActivate = new AuthPayBsActivate();
		bsActivate.doTrans();
	}
}
