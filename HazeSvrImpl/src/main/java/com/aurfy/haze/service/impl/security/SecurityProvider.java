package com.aurfy.haze.service.impl.security;

import com.aurfy.haze.service.bean.security.KeyStoreBean;


/**
 * 策略模式，不同的加密方案需要用不同的实现类来实现，比如RSASecurityProviderImpl、MD5SecurityProviderImpl等
 * 
 * @author rocket
 *
 */
public interface SecurityProvider {
	
	/**
	 * Generate key for different encryption policy.
	 * In general, it is used by aurfy.
	 * 
	 * @return
	 */
	public Object generateKey();
	
	
	/**
	 * Calculate the signature of the merchant's message by the given key store object and message.<br />
	 * It is just encrypt message.
	 * 
	 * @param message
	 * @param keyStore
	 * @return
	 */
	public String calculateSignature(String message, KeyStoreBean keyStore);
	
	/**
	 * Validate the message's signature.
	 * 
	 * @param message
	 * @param keyStore
	 * @param signature
	 * @return
	 */
	public boolean validateSignature(String message, KeyStoreBean keyStore, String signature);
	
}
