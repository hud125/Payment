package com.aurfy.haze.service.conf;

public final class ServiceConstant {

	private ServiceConstant() {
	}

	public static final class Hessian {
		public static final String BASE_URL = "/remoting";
		public static final String CRUD_SERVICE_URL = BASE_URL + "/CRUDService";
		public static final String TEST_SERVICE_URL = BASE_URL + "/TestService";
		// public static final String _SERVICE_URL = BASE_URL + "/";

		public static final String MERCHANT_SERVICE_URL = BASE_URL + "/MerchantService";
		public static final String USER_SERVICE_URL = BASE_URL + "/UserService";
		public static final String CURRENCY_SERVICE_URL = BASE_URL + "/CurrencyService";
		public static final String ORDER_SERVICE_URL = BASE_URL + "/OrderService";
		public static final String RISK_SERVICE_URL = BASE_URL + "/RiskService";
		public static final String SECURITY_SERVICE_URL = BASE_URL + "/SecurityService";
		public static final String SENDER_SERVICE_URL = BASE_URL + "/SenderService";
		public static final String PERMISSION_SERVICE_URL = BASE_URL + "/PermissionService";
		public static final String CHANNEL_SERVICE_URL = BASE_URL + "/ChannelService";
		public static final String PAYMENT_PRODUCT_SERVICE_URL = BASE_URL + "/PaymentProductService";
		public static final String ACQUIRER_SERVICE_URL = BASE_URL + "/AcquirerService";
		public static final String BANK_SERVICE_URL = BASE_URL + "/BankService";
		public static final String PAYMENT_SERVICE_URL = BASE_URL + "/PaymentService";

		public static final String MAIL_SERVICE_URL = BASE_URL + "/MailService";

		public static final String BANK_POST_PROCESS_SERVICE_URL = BASE_URL + "/bankPostProcessService";

	}

}
