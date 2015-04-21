package com.aurfy.haze.service.impl.security;

import org.apache.commons.lang3.RandomStringUtils;

import com.aurfy.haze.service.bean.security.KeyStoreBean;
import com.aurfy.haze.utils.EncodingUtils;
import com.aurfy.haze.utils.SecurityUtils;

public class MD5SecurityProviderImpl implements SecurityProvider {

	private static final int SALT_LENGTH = 32;

	@Override
	public Object generateKey() {
		return RandomStringUtils.randomAlphanumeric(SALT_LENGTH);
	}

	@Override
	public String calculateSignature(String message, KeyStoreBean keyStore) {
		String encryptedSecretKey = keyStore.getEncryptedKey();
		// 1.decode encryptedPriKey by base64
		byte[] encryptedSecretKeyArray = EncodingUtils.decodeBase64(encryptedSecretKey, EncodingUtils.UTF_8);
		// 2.decrypt encryptedPriKeyArray by AES
		byte[] decryptSecretKeyArray = SecurityUtils.decryptByAES(encryptedSecretKeyArray,
				SecurityServiceImpl.AES_PASSWORD, EncodingUtils.UTF_16);
		// 3.encode signed by base64
		String signedBase64 = EncodingUtils.encodeBase64(decryptSecretKeyArray, EncodingUtils.UTF_8);
		return SecurityUtils.MD5(SecurityUtils.MD5(message + signedBase64) + signedBase64);
	}

	/**
	 * MD5 need not validate
	 */
	@Override
	public boolean validateSignature(String message, KeyStoreBean keyStore, String signature) {
		return true;
	}

}
