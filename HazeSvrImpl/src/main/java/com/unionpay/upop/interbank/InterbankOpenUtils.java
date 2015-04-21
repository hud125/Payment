package com.unionpay.upop.interbank;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Enumeration;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

import com.unionpay.upop.sdk.util.PayUtils;
import com.unionpay.upop.sdk.util.PropManager;

public class InterbankOpenUtils {

	public static String decryptByPrivateKey(String cipherInfo,
			String serialNumber) {

		try {
			String fileName = serialNumber + ".pfx";
			String passwd = PropManager.getMerInstance().getProperty(
					"mer_cert_passwd");
			PrivateKey privateKey = getPrivateKey(fileName, passwd);

			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);

			byte[] result = cipher.doFinal(Base64.decodeBase64(cipherInfo.getBytes("UTF-8")));
			
			return new String(result);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static PrivateKey getPrivateKey(String certName, String passwd) {

		try {
			KeyStore ks = KeyStore.getInstance("pkcs12");
			InputStream is = PayUtils.class.getResourceAsStream("/" + certName);

			char[] nPassword = null;
			if ((passwd == null) || passwd.trim().equals("")) {
				nPassword = null;
			} else {
				nPassword = passwd.toCharArray();
			}
			ks.load(is, nPassword);
			is.close();

			Enumeration<String> e = ks.aliases();
			String keyAlias = null;
			if (e.hasMoreElements()) // we are reading just one certificate.
			{
				keyAlias = (String) e.nextElement();
			}

			PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
			// Certificate cert = ks.getCertificate(keyAlias);

			return prikey;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public static String entryptByPrivateKey(String cipherInfo,
			String serialNumber) {

		try {
			String fileName = serialNumber + ".pfx";
			String passwd = PropManager.getMerInstance().getProperty(
					"mer_cert_passwd");
			PrivateKey privateKey = getPrivateKey(fileName, passwd);

			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);

			byte[] result = cipher.doFinal(cipherInfo.getBytes("UTF-8"));
			
			return Base64.encodeBase64String(result);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String entryptByPublicKey(String planData,
			String serialNumber) {

		try {
			InputStream is = PayUtils.class.getResourceAsStream("/"+serialNumber);
			CertificateFactory factory = CertificateFactory.getInstance("X.509");
			
			Certificate cert = factory.generateCertificate(is);
			PublicKey publicKey = cert.getPublicKey();

			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);

			byte[] result = cipher.doFinal(planData.getBytes("UTF-8"));
			
			return Base64.encodeBase64String(result);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
