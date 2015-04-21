package com.aurfy.haze.service.impl.security;

import static com.aurfy.haze.conf.HazeDefaultConfig.getSecurityProps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aurfy.haze.core.model.tools.CipherAlgorithmEnum;
import com.aurfy.haze.dao.security.KeyStoreMapper;
import com.aurfy.haze.entity.security.KeyStoreEntity;
import com.aurfy.haze.service.api.infrastructure.MerchantService;
import com.aurfy.haze.service.api.security.SecurityService;
import com.aurfy.haze.service.bean.infra.mer.MerchantBean;
import com.aurfy.haze.service.bean.security.KeyStoreBean;
import com.aurfy.haze.service.conf.ServiceImplConstant.AOP_NAME;
import com.aurfy.haze.service.exceptions.ParameterValidationException;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.BaseHazeService;
import com.aurfy.haze.service.impl.converter.Converter;
import com.aurfy.haze.service.impl.converter.ConverterHelper;
import com.aurfy.haze.utils.EncodingUtils;
import com.aurfy.haze.utils.SecurityUtils;

/**
 * 签名和加密不同，签名主要是保证信息传输的完整性、发送者的身份认证、防止交易中的抵赖发生。<br />
 * Aurfy用私钥对数据做签名，商户用公钥和数据来验证，即判断此信息有否被篡改。<br />
 * 反之，商户用公钥钥对数据做签名，Aurfy用私钥和数据来验证，即判断此信息有否被篡改。<br />
 * 
 * Aurfy用私钥对数据做加密，商户用公钥对加密数据进行解密。<br />
 * 反之，商户用公钥对数据做加密，Aurfy用私钥对加密数据进行解密。<br />
 * 
 * 
 * 由于RSA算法是第一个能同时用于加密和数字签名的算法，故对于加密的情况，可以是公钥加密，私钥解密；私钥加密，公钥解密。
 * 1.对于商户生成的密钥或签名，Aurfy需要有验证机制。 若是RSA加解密，则需要有验证签名的方法；<br />
 * 2.对于Aurfy生成的密钥或签名，商户需要自行编写接口进行验证。若是RSA加解密，则需要有生成密钥、生成签名的方法。
 * 
 * @author rocket
 *
 */
@Service(AOP_NAME.SECURITY_SERVICE)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SecurityServiceImpl extends BaseHazeService implements SecurityService {

	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

	public static final String AES_PASSWORD = getSecurityProps().getProperty("AES_PASSWORD");

	public static final String AURFY_MERCHANT_ID = getSecurityProps().getProperty("AURFY_MERCHANT_ID");

	@Autowired
	private MerchantService merService;

	@Autowired
	private KeyStoreMapper ksMapper;

	/**
	 * Use the SHA3 policy to encrypt the plainPasswd with a random salt.<br />
	 * Perhaps modify it later by other entryption policy.
	 */
	@Override
	public String encryptUserPassword(String plainPasswd, String salt) {
		return SecurityUtils.SHA3(SecurityUtils.SHA3(plainPasswd) + salt);
	}

	/**
	 * Aurfy validates the signature provided by the merchant's policy,such as public key of RSA.
	 */
	@Override
	public boolean validateSignature(String message, KeyStoreBean keyStore, String signature)
			throws RuntimeServiceException, ServiceException {
		if (null == keyStore.getMerchant()) {
			logger.error("merchant is empty.");
			throw new ParameterValidationException("merchant is empty.");
		}

		// MerchantBean merBean = merService.retrieve(merchant.getID());//等他们写完
		MerchantBean merBean = (MerchantBean) keyStore.getMerchant();
		KeyStoreEntity ksEntity = ksMapper.selectKeyStoreByPolicy(merBean.getID(), merBean.getMerchantCode(),
				keyStore.getCipherAlgorithm());
		if (ksEntity == null) {
			logger.error("Can't find the cipherPolicy of this merchant.");
			throw new ParameterValidationException("Can't find the cipherPolicy of this merchant.");
		}
		Converter converter = ConverterHelper.getConverter(KeyStoreBean.class);
		KeyStoreBean ksBean = (KeyStoreBean) converter.entity2Bean(KeyStoreBean.class, ksEntity);

		SecurityProviderHelper help = new SecurityProviderHelper();
		SecurityProvider provider = help.getProvider(ksBean.getCipherAlgorithm());
		return provider.validateSignature(message, keyStore, signature);
	}

	/**
	 * Aurfy generates the key of the different policy, such as RSA, MD5
	 * 
	 * @throws ServiceException
	 */
	@Override
	public Object generateKey(CipherAlgorithmEnum cipherPolicy) throws ServiceException {
		SecurityProviderHelper help = new SecurityProviderHelper();
		SecurityProvider provider = help.getProvider(cipherPolicy);
		return provider.generateKey();
	}

	/**
	 * Aurfy calculates the signature provided by the aurfy's policy
	 */
	@Override
	public String calculateSignature(String message, KeyStoreBean keyStore) throws RuntimeServiceException,
			ServiceException {
		if (null == keyStore.getMerchant()) {
			logger.error("merchant is empty.");
			throw new ParameterValidationException("merchant is empty.");
		}

		// this function can also be used by merchant's MD5 policy.
		// if (!AURFY_MERCHANT_ID.equals(keyStore.getMerchant().getID())) {
		// logger.error("This function is only designed for aurfy self.");
		// throw new ParameterValidationException("This function is only designed for aurfy self.");
		// }

		// MerchantBean merBean = merService.retrieve(merchant.getID());//等他们写完
		MerchantBean merBean = (MerchantBean) keyStore.getMerchant();

		KeyStoreEntity ksEntity = ksMapper.selectKeyStoreByPolicy(merBean.getID(), merBean.getMerchantCode(),
				keyStore.getCipherAlgorithm());
		if (ksEntity == null) {
			logger.error("Can't find the cipherPolicy of this merchant.");
			throw new ParameterValidationException("Can't find the cipherPolicy of this merchant.");
		}
		Converter converter = ConverterHelper.getConverter(KeyStoreBean.class);
		KeyStoreBean ksBean = (KeyStoreBean) converter.entity2Bean(KeyStoreBean.class, ksEntity);

		SecurityProviderHelper help = new SecurityProviderHelper();
		SecurityProvider provider = help.getProvider(ksBean.getCipherAlgorithm());
		return provider.calculateSignature(message, ksBean);
	}

	/**
	 * base64 and ASE
	 */
	@Override
	public String encryptKey(CipherAlgorithmEnum cipherPolicy, String plainKey, String salt) {
		byte[] plainKeyArray = EncodingUtils.decodeBase64(plainKey, EncodingUtils.UTF_8);
		byte[] encryptKeyArray = SecurityUtils.encryptByAES(plainKeyArray, SecurityServiceImpl.AES_PASSWORD,
				EncodingUtils.UTF_16);
		String encryptKey = EncodingUtils.encodeBase64(encryptKeyArray, EncodingUtils.UTF_8);
		return encryptKey;
	}

}
