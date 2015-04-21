package com.aurfy.haze.service.client;

import static com.aurfy.haze.conf.HazeDefaultConfig.getAppWebProps;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.BANK_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.CHANNEL_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.CRUD_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.MAIL_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.MERCHANT_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.PAYMENT_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.PERMISSION_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.SECURITY_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.USER_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.BANK_POST_PROCESS_SERVICE_URL;
import static com.aurfy.haze.utils.StringUtils.formatMessage;

import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.api.bank.BankPostProcessService;
import com.aurfy.haze.service.api.bank.BankService;
import com.aurfy.haze.service.api.configuration.channel.ChannelService;
import com.aurfy.haze.service.api.infrastructure.MerchantService;
import com.aurfy.haze.service.api.infrastructure.UserService;
import com.aurfy.haze.service.api.mail.MailService;
import com.aurfy.haze.service.api.payment.PaymentService;
import com.aurfy.haze.service.api.perm.PermissionService;
import com.aurfy.haze.service.api.security.SecurityService;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.caucho.hessian.client.HessianProxyFactory;

public class ServiceClient<T> {

	private static final Logger logger = LoggerFactory.getLogger(ServiceClient.class);
	private static HessianProxyFactory factory = createProxyFactory();
	public static final String SVR_BASE_URL = getAppWebProps().getProperty("HessianBaseURL");

	private ServiceClient() {
	}

	private static HessianProxyFactory createProxyFactory() {
		HessianProxyFactory factory = new HessianProxyFactory();
		factory.setHessian2Request(true);
		factory.setHessian2Reply(true);
		factory.setOverloadEnabled(true);
		factory.setDebug(true);
		factory.setReadTimeout(30000);
		factory.getSerializerFactory().setAllowNonSerializable(true);
		return factory;
	}

	public static CRUDService createCRUDService() throws ServiceException {
		return createServiceClient(CRUDService.class, SVR_BASE_URL, CRUD_SERVICE_URL);
	}
	
	public static BankPostProcessService createBankPostProcessService() throws ServiceException {
		return createServiceClient(BankPostProcessService.class, SVR_BASE_URL, BANK_POST_PROCESS_SERVICE_URL);
	}

	public static MerchantService createMerchantService() throws ServiceException {
		return createServiceClient(MerchantService.class, SVR_BASE_URL, MERCHANT_SERVICE_URL);
	}

	public static UserService createUserService() throws ServiceException {
		return createServiceClient(UserService.class, SVR_BASE_URL, USER_SERVICE_URL);
	}

	public static BankService createBankService() throws ServiceException {
		return createServiceClient(BankService.class, SVR_BASE_URL, BANK_SERVICE_URL);
	}

	public static PaymentService createPaymentService() throws ServiceException {
		return createServiceClient(PaymentService.class, SVR_BASE_URL, PAYMENT_SERVICE_URL);
	}

	public static ChannelService createChannelService() throws ServiceException {
		return createServiceClient(ChannelService.class, SVR_BASE_URL, CHANNEL_SERVICE_URL);
	}

	public static PermissionService createPermissionService() throws ServiceException {
		return createServiceClient(PermissionService.class, SVR_BASE_URL, PERMISSION_SERVICE_URL);
	}

	public static SecurityService createSecurityService() throws ServiceException {
		return createServiceClient(SecurityService.class, SVR_BASE_URL, SECURITY_SERVICE_URL);
	}

	public static MailService createMailService() throws ServiceException {
		return createServiceClient(MailService.class, SVR_BASE_URL, MAIL_SERVICE_URL);
	}

	@SuppressWarnings("unchecked")
	public static <T> T createServiceClient(Class<T> clazz, final String baseUrl, String service_url)
			throws ServiceException {
		try {
			return (T) factory.create(clazz, baseUrl + service_url);
		} catch (MalformedURLException e) {
			final String msg = formatMessage("create client for ''{0}'' failed", clazz.getSimpleName());
			logger.error(msg, e);
			throw new ServiceException(msg, e);
		}
	}

}
