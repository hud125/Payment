package com.unionpay.upop.sdk.sample;

import java.util.Map;

import com.unionpay.upop.sdk.PayRequest;
import com.unionpay.upop.sdk.common.BackStageUPSupport;
import com.unionpay.upop.sdk.util.PayUtils;

/**
 * 名称：商户后续类交易（包括退货、消费撤销等后台交易） 功能： 类属性：方法调用 版本：1.0 日期：2011-03-11 作者：中国银联UPOP团队 版权：中国银联
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。该代码仅供参考。
 * */

public class SampleBackStageTrans implements BackStageUPSupport {
    
    @Override
    public PayRequest createRequest() {
        return PayUtils.mockRequest(getClass());
    }
    
    public PayRequest createRequest(Map<String, String> map) {
       
    	 PayRequest payRequest = new PayRequest();
//    	  payRequest.setAcqCode(acqCode);
//    		 payRequest.setDefaultPayType();
//    		 payRequest.setDefaultBankNumber();
//    		 payRequest.setOrderNumber();
//    		 payRequest.setOrderTime();
//    		 payRequest.setCommodityUrl();
//    		 payRequest.setCommodityName();
//
//
//    		 payRequest.setCommodityUnitPrice();
//
//    		 payRequest.setCommodityQuantity();
//
//    		 payRequest.setCommodityDiscount();
//
//
//    		 payRequest.setTransferFee();
//
//    		 payRequest.setMerCode();
//    		 payRequest.setTransType();
//
//
//    		 payRequest.setOrigQid();
//    		 payRequest.setAcqCode();
//
//    		 payRequest.setOrderAmount(
//
//
//    		 payRequest.setOrderCurrency();
//
//    		 payRequest.setCustomerIp();
//
//    		 payRequest.setCustomerName();
//
//    		 payRequest.setTransTimeout();
//
//    		 payRequest.setFrontEndUrl();
//
//    		 payRequest.setBackEndUrl();
//
//    		 payRequest.setSignMethod();
//
//    		 payRequest.setMerReserved();
//    		 payRequest.setVersion();
//
//
//    		 payRequest.setCharset();
//
//    		 payRequest.setMerId();
//
//
//    		 payRequest.setMerAbbr();
    		 
    	return payRequest;
    }
  
    
    // 商户的业务逻辑
    @Override
    public void handleResponse(Map<String, String> response) {
        // 以下是商户业务处理
        String respCode = response.get("respCode");
        String respMsg = response.get("respMsg");
        
        System.out.println(">>>respCode : " + respCode);
        System.out.println(">>>respMsg : " + respMsg);
        
        if ("00".equals(respCode)) {
            System.out.println("银联开始受理请求，但是并不表示处理成功。交易类型为（01，31，04等）等成功后有后台通知发到报文的后台通知地址。交易类型71，商户自己发查询确定状态");
        } else if ("30".equals(respCode)) {
            System.out.println(respCode + ":报文格式错误");
        } else if ("94".equals(respCode)) {
            System.out.println(respCode + ":重复交易");
        } else if ("25".equals(respCode)) {
            System.out.println(respCode + ":查询原交易失败");
        } else if ("36".equals(respCode)) {
            System.out.println(respCode + ":交易金额超限");
        } else {
            System.out.println(respCode + ":其他错误");
        }
    }
}
