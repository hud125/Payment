package com.unionpay.upop.sdk.enums;

import com.unionpay.upop.sdk.SdkConf;
import com.unionpay.upop.sdk.sample.SampleActivationQuery;
import com.unionpay.upop.sdk.sample.SampleAuthPay;
import com.unionpay.upop.sdk.sample.SampleAuthPayBsActivate;
import com.unionpay.upop.sdk.sample.SampleAuthPayFrontActivate;
import com.unionpay.upop.sdk.sample.SampleAuthPaySms;
import com.unionpay.upop.sdk.sample.SampleBackStageTrans;
import com.unionpay.upop.sdk.sample.SampleFrontPay;
import com.unionpay.upop.sdk.sample.SamplePayResponse;
import com.unionpay.upop.sdk.sample.SamplePayResultQuery;
import com.unionpay.upop.sdk.util.PayUtils;
import com.unionpay.upop.sdk.util.PropManager;

public enum Service {
    AUTH_PAY(SdkConf.reqVo, SampleAuthPay.class), 
    FRONT_PAY(SdkConf.reqVo, SampleFrontPay.class), 
    NOTIFY(SdkConf.notifyVo, SamplePayResponse.class), 
    BS_PAY(SdkConf.reqVo, SampleBackStageTrans.class),  
    QUERY(SdkConf.queryVo, SamplePayResultQuery.class), 
    SMS(SdkConf.smsVo, SampleAuthPaySms.class),
    ACTIVATION_QUERY(SdkConf.activationQueryVo, SampleActivationQuery.class),
    FRONT_ACTIVATE(SdkConf.authPayFrontActivateVo, SampleAuthPayFrontActivate.class),
    BS_ACTIVATE(SdkConf.authPayBsActivateVo, SampleAuthPayBsActivate.class);
    
    private String url;
    private String[] paramArray;
    private Class<?> implClassName;
    
    private Service(String[] paramArray, Class<?> clazz) {
        this.paramArray = paramArray;
        
        String envString = PropManager.getSdkInstance().getProperty(SdkConf.KEY_ENV);
        Env env = Env.valueOf(envString.toUpperCase());
        String servicePath = PropManager.getSdkInstance().getProperty(this.name().toLowerCase() + "_url");
        if (PayUtils.isNotBlank(servicePath)) {
            url = env.getUrl() +  servicePath;
        }
        
//        String classname = PropManager.getMerInstance().getProperty(this.name().toLowerCase() + "_implClass");
//        if (PayUtils.isNotBlank(classname)) {
//            try {
//                implClassName = Class.forName(classname);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//                implClassName = clazz;
//            }
//        } else {
//            implClassName = clazz;
//        }
    }
    
    public String getUrl() {
        return url;
    }
    
    public Class<?> getImplClassName() {
        return implClassName;
    }

    public String[] getParamArray() {
        return this.paramArray;
    }
}