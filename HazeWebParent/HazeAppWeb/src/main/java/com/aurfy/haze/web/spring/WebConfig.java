package com.aurfy.haze.web.spring;

import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.BANK_POST_PROCESS_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.BANK_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.CHANNEL_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.CRUD_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.MAIL_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.MERCHANT_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.PAYMENT_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.PERMISSION_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.SECURITY_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.TEST_SERVICE_URL;
import static com.aurfy.haze.service.conf.ServiceConstant.Hessian.USER_SERVICE_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.api.HazeService;
import com.aurfy.haze.service.api.bank.BankPostProcessService;
import com.aurfy.haze.service.api.bank.BankService;
import com.aurfy.haze.service.api.configuration.channel.ChannelService;
import com.aurfy.haze.service.api.infrastructure.MerchantService;
import com.aurfy.haze.service.api.infrastructure.UserService;
import com.aurfy.haze.service.api.mail.MailService;
import com.aurfy.haze.service.api.payment.PaymentService;
import com.aurfy.haze.service.api.perm.PermissionService;
import com.aurfy.haze.service.api.security.SecurityService;
import com.aurfy.haze.service.api.test.TestService;
import com.aurfy.haze.web.controller.HomeController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = { HomeController.class })
public class WebConfig extends WebConfigTemplate {

	// private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

	@Autowired
	@Bean(name = CRUD_SERVICE_URL)
	public HessianServiceExporter exportCRUDService( /* @Qualifier("crudService") */CRUDService service) {
		return getHessianServiceExporter(CRUDService.class, service);
	}

	@Autowired
	@Bean(name = BANK_POST_PROCESS_SERVICE_URL)
	public HessianServiceExporter exportBankPostProcessService(
	/* @Qualifier("crudService") */BankPostProcessService service) {
		return getHessianServiceExporter(BankPostProcessService.class, service);
	}

	@Autowired
	@Bean(name = MERCHANT_SERVICE_URL)
	public HessianServiceExporter exportMerchantService( /* @Qualifier("merchantService") */MerchantService service) {
		return getHessianServiceExporter(MerchantService.class, service);
	}

	@Autowired
	@Bean(name = USER_SERVICE_URL)
	public HessianServiceExporter exportUserService( /* @Qualifier("userService") */UserService service) {
		return getHessianServiceExporter(UserService.class, service);
	}

	@Autowired
	@Bean(name = TEST_SERVICE_URL)
	public HessianServiceExporter exportTestService( /* @Qualifier("merchantService") */TestService service) {
		return getHessianServiceExporter(TestService.class, service);
	}

	@Autowired
	@Bean(name = PERMISSION_SERVICE_URL)
	public HessianServiceExporter exportPermissionService(PermissionService service) {
		return getHessianServiceExporter(PermissionService.class, service);
	}

	@Autowired
	@Bean(name = BANK_SERVICE_URL)
	public HessianServiceExporter exportBankService(BankService service) {
		return getHessianServiceExporter(BankService.class, service);
	}

	// @Autowired
	// @Bean(name = PAYMENT_SERVICE_URL)
	// public HessianServiceExporter exportBankService(PaymentService service) {
	// return getHessianServiceExporter(PaymentService.class, service);
	// }

	@Autowired
	@Bean(name = SECURITY_SERVICE_URL)
	public HessianServiceExporter exportSecurityService(SecurityService service) {
		return getHessianServiceExporter(SecurityService.class, service);
	}

	@Autowired
	@Bean(name = MAIL_SERVICE_URL)
	public HessianServiceExporter exportMailService(MailService service) {
		return getHessianServiceExporter(MailService.class, service);
	}

	@Autowired
	@Bean(name = CHANNEL_SERVICE_URL)
	public HessianServiceExporter exportChannelService(ChannelService service) {
		return getHessianServiceExporter(ChannelService.class, service);
	}

	@Autowired
	@Bean(name = PAYMENT_SERVICE_URL)
	public HessianServiceExporter exportPaymentService(PaymentService service) {
		return getHessianServiceExporter(PaymentService.class, service);
	}

	private HessianServiceExporter getHessianServiceExporter(Class<?> clz, HazeService service) {
		HessianServiceExporter exporter = new HessianServiceExporter();
		exporter.setServiceInterface(clz);
		exporter.setService(service);
		exporter.setAllowNonSerializable(true);
		exporter.setDebug(true);
		exporter.afterPropertiesSet();
		return exporter;
	}

}
