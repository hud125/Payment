package com.aurfy.haze.core.model.tools;

import java.io.Serializable;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

@UseEnumTypeHandler
public enum CipherAlgorithmEnum implements Serializable {
	/**
	 * simple md5 hash
	 */
	MD5,

	/**
	 * sha-1 hash
	 */
	SHA1,

	/**
	 * sha-3 hash
	 */
	SHA3,

	/**
	 * DES
	 */
	DES,

	/**
	 * 3DES
	 */
	ThreeDES,

	/**
	 * standard RSA
	 */
	RSA,

	/**
	 * standard AES
	 */
	AES

}
