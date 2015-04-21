package com.unionpay.upop.sdk;


/**
 * 名称：支付配置类
 * 功能：配置类
 * 类属性：公共类
 * 版本：1.0
 * 作者：中国银联UPOP团队
 * 版权：中国银联
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。该代码仅供参考。
 * */
public class SdkConf {
    
    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";
    public static final String RESP_CODE_SUCCESS = "00";
    
    public static final String KEY_SERVER_URL = "server_url";
    public static final String KEY_ENV = "env";
    
	//组装消费请求包
	public final static String[] reqVo = new String[]{
			"version",
            "charset",
            "transType",
            "origQid",
            "merId",
            "merAbbr",
            "acqCode",
            "merCode",
            "commodityUrl",
            "commodityName",
            "commodityUnitPrice",
            "commodityQuantity",
            "commodityDiscount",
            "transferFee",
            "orderNumber",
            "orderAmount",
            "orderCurrency",
            "orderTime",
            "customerIp",
            "customerName",
            "defaultPayType",
            "defaultBankNumber",
            "transTimeout",
            "frontEndUrl",
            "backEndUrl",
            "merReserved"
	};

	public final static String[] notifyVo = new String[]{
            "charset",
            "cupReserved",
            "exchangeDate",
            "exchangeRate",
            "merAbbr",
            "merId",
            "orderAmount",
            "orderCurrency",
            "orderNumber",
            "qid",
            "respCode",
            "respMsg",
            "respTime",
            "settleAmount",
            "settleCurrency",
            "settleDate",
            "traceNumber",
            "traceTime",
            "transType",
            "version"
	};
	
	public final static String[] activateNotifyVo = new String[] {
		"charset",
		"activateStatus",
        "cupReserved",
        "respCode",
        "respMsg",
        "version"
	};

	public final static String[] queryVo = new String[]{
		"version",
		"charset",
		"transType",
		"merId",
		"orderNumber",
		"orderTime",
		"merReserved"
	};
	
	public final static String[] smsVo = new String[]{
		"version",
		"charset",
		"acqCode",
		"merId",
		"merAbbr",
		"orderNumber",
		"orderAmount",
        "orderCurrency",
		"merReserved"
	};
	
	public static final String[] activationQueryVo = new String[] {
		"version",
		"charset",
		"acqCode",
		"merId",
		"merReserved"
	};
	
	public static final String[] authPayFrontActivateVo = new String[] {
		"version",
		"charset",
		"acqCode",
		"merId",
		"merReserved"
	};
	
	public static final String[] authPayBsActivateVo = new String[] {
		"version",
		"charset",
		"acqCode",
		"merId",
		"merReserved"
	};
}
