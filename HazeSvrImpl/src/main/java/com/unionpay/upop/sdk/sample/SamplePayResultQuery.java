package com.unionpay.upop.sdk.sample;

import java.util.Map;

import com.unionpay.upop.sdk.PayRequest;
import com.unionpay.upop.sdk.common.BackStageUPSupport;
import com.unionpay.upop.sdk.util.PayUtils;


/**
 * 名称：商户查询类
 * 功能：
 * 类属性：方法调用
 * 版本：1.0
 * 作者：中国银联UPOP团队
 * 版权：中国银联
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。该代码仅供参考。
 * */

public class SamplePayResultQuery implements BackStageUPSupport {
    
    @Override
    public PayRequest createRequest() {
        return PayUtils.mockRequest(getClass());
    }
    
    // 商户的业务逻辑
    /**
     * queryResult=0或者2时 respCode为00，其余情况下respCode为非全零的两位错误码
     * queryResult为空时报文格式错误
     * queryResult：
     * 0：成功（响应码respCode为00）
     * 1：失败（响应码respCode非00）
     * 2：处理中（响应码respCode为00）
     * 3：无此交易（响应码respCode非00）
    */
    @Override
    public void handleResponse(Map<String, String> response) {
        // 以下是商户业务处理
        String respCode = response.get("respCode");
        String respMsg = response.get("respMsg");
        String queryResult = response.get("queryResult");
        
        System.out.println(">>>respCode : " + respCode);
        System.out.println(">>>respMsg : " + respMsg);
        System.out.println(">>>queryResult : " + queryResult);
        
        if (queryResult != "") {
            if ("0".equals(queryResult)) {
                System.out.println("交易成功");
            }
            if ("1".equals(queryResult)) {
                System.out.println("交易失败");
            }
            if ("2".equals(queryResult)) {
                System.out.println("交易处理中");
            }
            if ("3".equals(queryResult)) {
                System.out.println("无此交易");
            }
        } else {
            System.out.println("报文格式错误");
        }
    }
}
	