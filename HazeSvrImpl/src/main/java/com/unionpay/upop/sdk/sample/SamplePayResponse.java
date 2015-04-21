package com.unionpay.upop.sdk.sample;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unionpay.upop.sdk.PayRequest;
import com.unionpay.upop.sdk.SdkConf;
import com.unionpay.upop.sdk.common.FrontUPSupport;

/**
 * 名称：支付应答类
 * 功能：servlet方式，建议前台通知和后台通知用两个类实现，后台通知进行商户的数据库等处理,前台通知实现客户浏览器跳转
 * 类属性：
 * 版本：1.0
 * 作者：中国银联UPOP团队
 * 版权：中国银联
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。该代码仅供参考。
 * */
public class SamplePayResponse implements FrontUPSupport {
    
    @Override
    public PayRequest createRequest(HttpServletRequest request) {
        throw new UnsupportedOperationException("Method createRequest() is unsupported");
    }

    @Override
    public int handleResponse(HttpServletResponse response, Map<String, String> responseMap) {
        boolean isSucceed = SdkConf.RESP_CODE_SUCCESS.equals(responseMap.get("respCode"));
        try {
            response.getWriter().append("建议前台通知和后台通知用两个类实现，后台通知进行商户的数据库等处理,前台通知实现客户浏览器跳转")
                    .append("<br>验证签名成功")        
                    .append("<br>签名方式：" + responseMap.get("signMethod"))
                    .append("<br>签名信息：" + responseMap.get("signature"))
                    .append("<br>交易是否成功：" + isSucceed);
            
            if (!isSucceed) {
                response.getWriter()
                    .append("<br>错误响应码: " + responseMap.get("respCode"))
                    .append("<br>错误消息： " + responseMap.get("respMsg"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }
        
        return HttpServletResponse.SC_OK;
    }
}
