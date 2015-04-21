package com.unionpay.upop.sdk.cases;

import com.unionpay.upop.sdk.common.BackStageCase;
import com.unionpay.upop.sdk.enums.Service;

public class ActivationQuery extends BackStageCase {

	public void doTrans() {
		postRequest(Service.ACTIVATION_QUERY);
	}
	
    public static void main(String[] args) {
		ActivationQuery activationQuery = new ActivationQuery();
        activationQuery.doTrans();
    }
}
