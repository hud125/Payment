package com.aurfy.haze.service.conf;

public final class ServiceImplConstant {

	private ServiceImplConstant() {
	}

	public static final class AOP_NAME {
		public static final String CRUD_SERVICE = "crudService";
		public static final String CACHE_SERVICE = "cacheService";

		public static final String MERCHANT_SERVICE = "merchantService";
		public static final String PERMISSION_SERVICE = "permissionService";
		public static final String ADDRESS_SERVICE = "addressService";
		public static final String CURRENCY_SERVICE = "currencyService";
		public static final String SECURITY_SERVICE = "securityService";
		public static final String USER_SERVICE = "userService";
		public static final String PAYMENT_SERVICE = "paymentService";

		public static final String BANK_SERVICE = "bankService";
		public static final String PAYMENT_PRODUCT_SERVICE = "paymentProductService";
		public static final String CHANNEL_SERVICE = "channelService";

		public static final String MAIL_SERVICE = "mailService";
		
		public static final String BANK_POST_PROCESS_SERVICE = "bankPostProcessService";
		
	}

	public static final class COMPONENT_NAME {
		public static final String MAPPER_REGISTRY = "mapperHelper";
		public static final String DEFAULT_CACHE_PROVIDER = "defaultMemCacheProvider";
		public static final String INMEM_CACHE_PROVIDER = "inMemCacheProvider";
		public static final String REDIS_CACHE_PROVIDER = "redisCacheProvider";
	}

}
