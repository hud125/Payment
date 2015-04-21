package com.aurfy.haze.service.impl.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.core.model.tools.CipherAlgorithmEnum;
import com.aurfy.haze.service.exceptions.ServiceException;

public class SecurityProviderHelper {

	private static final Logger logger = LoggerFactory.getLogger(SecurityProviderHelper.class);
	
	public SecurityProvider getProvider(CipherAlgorithmEnum hashPolicy) throws ServiceException{
		if(CipherAlgorithmEnum.RSA.equals(hashPolicy)){
			SecurityProvider provider = new RSASecurityProviderImpl();
			return provider;
		}else if(CipherAlgorithmEnum.MD5.equals(hashPolicy)){
			SecurityProvider provider = new MD5SecurityProviderImpl();
			return provider;
		}else{
			logger.error("Don't have solution for this hashPolicy");
			throw new ServiceException("Don't have solution for this hashPolicy");
		}
	}
	
}
