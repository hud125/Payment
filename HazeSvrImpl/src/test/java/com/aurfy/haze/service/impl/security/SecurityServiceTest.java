package com.aurfy.haze.service.impl.security;

import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.tools.CipherAlgorithmEnum;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.api.security.SecurityService;
import com.aurfy.haze.service.bean.infra.mer.MerchantBean;
import com.aurfy.haze.service.bean.security.KeyStoreBean;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.ServiceUnitTest;
import com.aurfy.haze.utils.EncodingUtils;
import com.aurfy.haze.utils.SecurityUtils;

public class SecurityServiceTest extends ServiceUnitTest {

	@Autowired
	private SecurityService service;

	@Autowired
	private CRUDService crudService;

	/**
	 * create retrieve update remove
	 */
	@Test
	@Transactional
	public void testEncryptUserPassword() {
		String plainPasswd = "123456";
		String salt = RandomStringUtils.randomAlphanumeric(12);
		String entryptedPw = service.encryptUserPassword(plainPasswd, salt);
		assertTrue(!plainPasswd.equals(entryptedPw));
	}

	/**
	 * 这里RSA加解密是双向的，但签名是单向的，只能私钥做签名，公钥验证签名
	 * @throws ServiceException
	 * @throws UnsupportedEncodingException
	 */
	@Test
	@Transactional
	public void testSecurityService() throws ServiceException, UnsupportedEncodingException {
		// 1.get keyPair by the RSA algorithm
		KeyPair keyPair = (KeyPair) service.generateKey(CipherAlgorithmEnum.RSA);

		// 2.encrypt the public key by the AES algorithm
		Key priKey = keyPair.getPrivate();
		Key pubKey = keyPair.getPublic();

		// use base64 policy and utf8 charset to encode the key
		String pubKeyBase64 = EncodingUtils.encodeBase64(pubKey.getEncoded(), EncodingUtils.UTF_8);// 需要发送给用户

		String encryptedPriKey = service.encryptKey(CipherAlgorithmEnum.AES,
				EncodingUtils.encodeBase64(priKey.getEncoded(), EncodingUtils.UTF_8), null);// 需要存到数据库
		// 3.store the encrypted public key in database
		KeyStoreBean ksBean = new KeyStoreBean();
		ksBean.setID(SecurityUtils.UUID());
		MerchantBean mer = new MerchantBean();
		mer.setID("00000000-0000-0000-0000-000000000000");
		ksBean.setMerchant(mer);
		ksBean.setCipherAlgorithm(CipherAlgorithmEnum.RSA);
		ksBean.setEncryptedKey(encryptedPriKey);
		ksBean.setSalt(null);
		ksBean.setExpiryDate(null);
		ksBean.setCreateDate(new Date());
		ksBean.setUpdateDate(ksBean.getCreateDate());
		KeyStoreBean ksBean2 = (KeyStoreBean) crudService.create(ksBean);
		assertTrue(ksBean.equals(ksBean2));

		// 4.decrypt the encrypted public key and use it to generate the signature
		byte[] encryptedPriKey2 = EncodingUtils.decodeBase64(ksBean2.getEncryptedKey(), EncodingUtils.UTF_8);
		byte[] decryptPriKeyArray = SecurityUtils.decryptByAES(encryptedPriKey2, SecurityServiceImpl.AES_PASSWORD,
				EncodingUtils.UTF_16);
		PrivateKey priKey2 = (PrivateKey) SecurityUtils.convertRSAKey(decryptPriKeyArray, SecurityUtils.PRIVATEKEY);
		String content = "hello world!你好，世界！";// 需要发送给用户的内容
		String signedBase64 = service.calculateSignature(content, ksBean);// 需要发送给用户RSA的签名

		// 5.通过私钥加密内容，并将加密内容通过公钥进行解密
		byte[] contentEncryptRSA = SecurityUtils.encryptByRSA(content.getBytes(EncodingUtils.UTF_8), priKey2);// 通过私钥加密内容
		byte[] contentDecryptRSA = SecurityUtils.decryptByRSA(contentEncryptRSA, pubKey);// 通过公钥解密内容
		String newContent = new String(contentDecryptRSA, EncodingUtils.UTF_8);
		assertTrue(content.equals(newContent));
		
		// 6.merchant needs validate the signature to know if the content is complete or not.
		// 这个方法是aurfy验证merchant的签名是否有效用的
		// 这里由于是测试，故将公钥先加密，再解密
		String decryptedPubKey = service.encryptKey(CipherAlgorithmEnum.AES, pubKeyBase64, null);// 需要存到数据库

		ksBean.setEncryptedKey(decryptedPubKey);
		boolean valid = service.validateSignature(newContent, ksBean, signedBase64);// 用户需要传过来pubKeyBase64
		assertTrue(valid);
		
		
		// 7.通过公钥加密内容，并将加密内容通过私钥进行解密
		byte[] contentEncryptRSA2 = SecurityUtils.encryptByRSA(content.getBytes(EncodingUtils.UTF_8), pubKey);// 通过私钥加密内容
		byte[] contentDecryptRSA2 = SecurityUtils.decryptByRSA(contentEncryptRSA2, priKey2);// 通过公钥解密内容
		String newContent2 = new String(contentDecryptRSA2, EncodingUtils.UTF_8);
		assertTrue(content.equals(newContent2));
		
		
	}

	@Test
	@Transactional
	public void testSecurityServiceByMD5() throws ServiceException, UnsupportedEncodingException {
		// 1.get key by the MD5 algorithm
		String secretkey = (String) service.generateKey(CipherAlgorithmEnum.MD5);

		// 2.encrypt the public key by the AES algorithm.
		//	use base64 policy and utf8 charset to encode the key
		String encryptedSecretKey = service.encryptKey(CipherAlgorithmEnum.AES,
				EncodingUtils.encodeBase64(secretkey.getBytes(), EncodingUtils.UTF_8), null);// 需要存到数据库
		// 3.store the encrypted public key in database
		KeyStoreBean ksBean = new KeyStoreBean();
		ksBean.setID(SecurityUtils.UUID());
		MerchantBean mer = new MerchantBean();
		mer.setID("1246dfc2-519c-4cf0-83fe-605addf36b1f");
		ksBean.setMerchant(mer);
		ksBean.setCipherAlgorithm(CipherAlgorithmEnum.MD5);
		ksBean.setEncryptedKey(encryptedSecretKey);
		ksBean.setSalt(null);
		ksBean.setExpiryDate(null);
		ksBean.setCreateDate(new Date());
		ksBean.setUpdateDate(ksBean.getCreateDate());
		KeyStoreBean ksBean2 = (KeyStoreBean) crudService.create(ksBean);
		assertTrue(ksBean.equals(ksBean2));

		// 4.decrypt the encrypted public key and use it to generate the signature
		byte[] encryptedSecretKey2 = EncodingUtils.decodeBase64(ksBean2.getEncryptedKey(), EncodingUtils.UTF_8);
		byte[] decryptSecretKeyArray = SecurityUtils.decryptByAES(encryptedSecretKey2, SecurityServiceImpl.AES_PASSWORD,
				EncodingUtils.UTF_16);
		String content = "hello world!你好，世界！";// 需要发送给用户
		String decryptSecretKey = new String(decryptSecretKeyArray, EncodingUtils.UTF_8);
		
		String encryptContent = SecurityUtils.MD5(SecurityUtils.MD5(decryptSecretKey + content) + decryptSecretKey);

		ksBean.setEncryptedKey(decryptSecretKey);
		boolean valid = service.validateSignature(content, ksBean, null);
		assertTrue(valid);
	}
	
}
