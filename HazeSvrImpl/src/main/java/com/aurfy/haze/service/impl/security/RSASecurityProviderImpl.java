package com.aurfy.haze.service.impl.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.service.bean.security.KeyStoreBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.utils.EncodingUtils;
import com.aurfy.haze.utils.SecurityUtils;

public class RSASecurityProviderImpl implements SecurityProvider {

	private static final Logger logger = LoggerFactory.getLogger(RSASecurityProviderImpl.class);
	
	/**
	 * 通过RSA生成公钥和私钥对，一般公钥发送给商户，而私钥存到数据库。
	 * 由于Aurfy需要将私钥存到商户表中，故需要在商户表中为Aurfy单独建一个特别的账户，即mer_id很特别。
	 * 下次要用时，直接通过此mer_id取即可。
	 */
	@Override
	public Object generateKey() {
		KeyPairGenerator keyGen = null;
		try {
			keyGen = KeyPairGenerator.getInstance(SecurityUtils.ALGORITHM_RSA);
			keyGen.initialize(SecurityUtils.BUFFER_SIZE);
			KeyPair keyPair = keyGen.genKeyPair();
			return keyPair;
		} catch (NoSuchAlgorithmException e) {
			logger.error("Generate a keyPair by RSA failed", e);
			throw new RuntimeServiceException("Generate a keyPair by RSA failed", e);
		}
	}

	/**
	 * 用私钥对信息生成数字签名，这里不支持用公钥对信息生成数字签名
	 */
	@Override
	public String calculateSignature(String message, KeyStoreBean keyStore) {
		String encryptedPriKey = keyStore.getEncryptedKey();
		//1.decode encryptedPriKey by base64
		byte[] encryptedPriKeyArray = EncodingUtils.decodeBase64(encryptedPriKey, EncodingUtils.UTF_8);
		//2.decrypt encryptedPriKeyArray by AES
		byte[] decryptPriKeyArray = SecurityUtils.decryptByAES(encryptedPriKeyArray, SecurityServiceImpl.AES_PASSWORD, EncodingUtils.UTF_16);
		//3.convert decryptPriKeyArray by RSA
		PrivateKey priKey = (PrivateKey) SecurityUtils.convertRSAKey(decryptPriKeyArray, SecurityUtils.PRIVATEKEY);
		//4.calculate signature
		byte[] signed = SecurityUtils.calculateSignature(message, priKey, EncodingUtils.UTF_8);
		//5.encode signed by base64
		String signedBase64 = EncodingUtils.encodeBase64(signed, EncodingUtils.UTF_8);
		return signedBase64;
	}

	/**
	 * 用公钥对信息生成数字签名，这里不支持用私钥对信息生成数字签名
	 */
	@Override
	public boolean validateSignature(String message, KeyStoreBean keyStore, String signature) {
		String encryptedPubKey = keyStore.getEncryptedKey();
		//1.decode encryptedPriKey by base64
		byte[] encryptedPubKeyArray = EncodingUtils.decodeBase64(encryptedPubKey, EncodingUtils.UTF_8);
		//2.decrypt encryptedPriKeyArray by AES
		byte[] decryptPubKeyArray = SecurityUtils.decryptByAES(encryptedPubKeyArray, SecurityServiceImpl.AES_PASSWORD, EncodingUtils.UTF_16);
		//3.convert decryptPriKeyArray by RSA
		PublicKey pubKey = (PublicKey) SecurityUtils.convertRSAKey(decryptPubKeyArray, SecurityUtils.PUBLICKEY);
		//4.decode signed by base64
		byte[] signed = EncodingUtils.decodeBase64(signature, EncodingUtils.UTF_8);
		//5.validate the signature by public key
		return SecurityUtils.validateSignature(pubKey, message, signed, EncodingUtils.UTF_8);
	}
	
}
