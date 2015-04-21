package com.unionpay.upop.sdk.cases;

import static com.unionpay.upop.sdk.util.PayUtils.isNotBlank;

import com.unionpay.upop.sdk.PayRequest;
import com.unionpay.upop.sdk.common.BackStageCase;
import com.unionpay.upop.sdk.common.BackStageUPSupport;
import com.unionpay.upop.sdk.enums.Service;
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

public class PayResultQuery extends BackStageCase {

    private String transType;
    private String orderNumber;
    private String orderTime;
    
	public static void main(String[] args){
	    PayResultQuery query = new PayResultQuery();
	    if (args != null && args.length == 3) {
	        query.transType = args[0];
	        query.orderNumber = args[1];
	        query.orderTime = args[2];
	    }
	    query.queryTrans();
	}
	
	/**
	 * 查询交易入口
	 * @param transType 交易类型
	 * @param orderNumber 商户订单号
	 * @param orderTime  商户订单时间
	 */
	public void queryTrans() {
	    BackStageUPSupport upSupport = PayUtils.getBackStageUpSupport(Service.QUERY);
	    PayRequest payRequest = upSupport.createRequest();
	    
        if (isNotBlank(transType)) {
            payRequest.setTransType(transType);
        }
        if (isNotBlank(orderNumber)) {
            payRequest.setOrderNumber(orderNumber);
        }
        if (isNotBlank(orderTime)) {
            payRequest.setOrderTime(orderTime);
        }
        
        postRequest(Service.QUERY, payRequest);
	}
}
	