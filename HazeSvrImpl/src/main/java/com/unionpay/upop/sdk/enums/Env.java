package com.unionpay.upop.sdk.enums;

import com.unionpay.upop.sdk.SdkConf;
import com.unionpay.upop.sdk.util.PropManager;

public enum Env {
    TEST, PM, PROD;
    
    private String url;
    
    private Env() {
        url = PropManager.getSdkInstance().getProperty(SdkConf.KEY_SERVER_URL + "_" + this.name().toLowerCase());
    }
    
    public String getUrl() {
        return url;
    }
}