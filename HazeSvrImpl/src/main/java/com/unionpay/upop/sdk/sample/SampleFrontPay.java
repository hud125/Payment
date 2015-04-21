package com.unionpay.upop.sdk.sample;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unionpay.upop.sdk.PayRequest;
import com.unionpay.upop.sdk.common.FrontUPSupport;
import com.unionpay.upop.sdk.util.PayUtils;

/**
 * 名称：支付请求类
 * 功能：servlet方式支付类请求
 * 类属性：
 * 版本：1.0
 * 作者：中国银联UPOP团队
 * 版权：中国银联
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。该代码仅供参考。
 * */
public class SampleFrontPay implements FrontUPSupport {
    
    @Override
    public PayRequest createRequest(HttpServletRequest request) {
        return PayUtils.mockRequest(getClass());
    }

    @Override
    public int handleResponse(HttpServletResponse response, Map<String, String> responseMap) {
        throw new UnsupportedOperationException("Method createRequest() is unsupported");
    }
}
