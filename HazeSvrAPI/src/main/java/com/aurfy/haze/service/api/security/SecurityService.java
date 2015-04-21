package com.aurfy.haze.service.api.security;

import com.aurfy.haze.core.model.tools.CipherAlgorithmEnum;
import com.aurfy.haze.service.api.HazeService;
import com.aurfy.haze.service.bean.security.KeyStoreBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;

/**
 * All security related service API, including signature, key store, encryption and decryption, etc.
 * 
 * @author hermano
 *
 */
public interface SecurityService extends HazeService {

	/**
	 * encrypt user password. the encryption algorithm is internal decided and not exploded to client. use it when user
	 * login or user register.
	 * 
	 * @param plainPasswd
	 * @param salt
	 * @return
	 */
	public String encryptUserPassword(String plainPasswd, String salt);

	/**
	 * 
	 * validate whether merchant signature is valid or not.
	 * 
	 * @param message
	 * @param keyStore
	 *            it must contain merchant id and cipherPolicy
	 * @param signature
	 *            it must be encoded by base64 and utf-8
	 * @return <code>true</code> if the signature is valid, <code>false</code> otherwise.
	 * @throws RuntimeServiceException
	 * @throws ServiceException
	 */
	public boolean validateSignature(String message, KeyStoreBean keyStore, String signature)
			throws RuntimeServiceException, ServiceException;

	/**
	 * This function is only designed for aurfy self. calculate the signature for aurfy with given hash policy.
	 * 
	 * @param keyStore
	 *            must contain merchant id and cipherPolicy
	 * @param message
	 * @return
	 * @throws RuntimeServiceException
	 * @throws ServiceException
	 */
	public String calculateSignature(String message, KeyStoreBean keyStore) throws RuntimeServiceException,
			ServiceException;

	/**
	 * generate key according to different cipherPolicy, such as an RSA key pair, a MD5 string.
	 * 
	 * @param cipherPolicy
	 * @return
	 * @throws ServiceException
	 */
	public Object generateKey(CipherAlgorithmEnum cipherPolicy) throws ServiceException;

	/**
	 * encrypt the merchant key which could be one of the supported algorithm (MD5, SHA3, RSA, etc). <br />
	 * the encryption algorithm is internal decided and not exploded to client.
	 * 
	 * @param cipherPolicy
	 * @param plainKey
	 *            the key used for merchant signature. convert RSA private key to string representative if necessary.
	 *            for example: new String(Base64.encodeBase64(publicKey.getEncoded()), "UTF-8")
	 * @param salt
	 * @return
	 */
	public String encryptKey(CipherAlgorithmEnum cipherPolicy, String plainKey, String salt);

}
