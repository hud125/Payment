package com.unionpay.upop.sdk.common;

import java.util.Map;

import com.unionpay.upop.sdk.PayRequest;

/**
 * 商户需要实现这个类，来发送后台交易请求和处理响应。
 * 
 * @author Malcolm
 *
 */
public interface BackStageUPSupport {
    
    /**
     * 构造请求对象
     * @return
     */
    PayRequest createRequest();
    
    /**
     * 处理返回的业务逻辑
     * @param response
     */
    void handleResponse(Map<String, String> response);
}
