package com.unionpay.upop.sdk.cases;

import static com.unionpay.upop.sdk.util.PayUtils.isNotBlank;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

import com.aurfy.haze.utils.DateUtils;
import com.unionpay.upop.sdk.PayRequest;
import com.unionpay.upop.sdk.common.BackStageCase;
import com.unionpay.upop.sdk.common.BackStageUPSupport;
import com.unionpay.upop.sdk.enums.Service;
import com.unionpay.upop.sdk.util.PayUtils;

/**
 * 名称：商户后续类交易（包括退货、消费撤销等后台交易） 功能： 类属性：方法调用 版本：1.0 日期：2011-03-11 作者：中国银联UPOP团队 版权：中国银联
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。该代码仅供参考。
 * */
public class BackStageTrans extends BackStageCase {
	private String transType;
	private String orderNumber;
	private String orderTime;

	public static void main(String[] args) {
		BackStageTrans back = new BackStageTrans();
		if (args != null && args.length == 3) {
			back.transType = args[0];
			back.orderNumber = args[1];
			back.orderTime = args[2];
		}
		back.orderNumber = RandomStringUtils.randomAlphanumeric(10);
		back.orderTime = DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
		back.doBackTrans();
	}

	/**
	 * 后续交易入口
	 * 
	 */
	public void doBackTrans() {
		BackStageUPSupport upSupport = PayUtils.getBackStageUpSupport(Service.BS_PAY);
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

		postRequest(Service.BS_PAY, payRequest);
	}
}
