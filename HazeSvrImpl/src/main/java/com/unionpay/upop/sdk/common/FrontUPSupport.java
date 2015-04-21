package com.unionpay.upop.sdk.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unionpay.upop.sdk.PayRequest;

/**
 * 商户需要实现这个类，来发送前台交易请求
 * 
 * @author Malcolm
 *
 */
public interface FrontUPSupport {
    
    /**
     * 构造请求对象
     * @param request
     * @return
     */
    PayRequest createRequest(HttpServletRequest request);
    
    /**
     * 处理返回的业务逻辑
     * @param response
     * @param responseMap 已经封装好的map实例，包含返回参数
     * @return HTTP response status: please refer to HttpServletResponse
     */
    int handleResponse(HttpServletResponse response, Map<String, String> responseMap);
}
