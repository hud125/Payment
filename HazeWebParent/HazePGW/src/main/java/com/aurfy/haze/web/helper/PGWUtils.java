package com.aurfy.haze.web.helper;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.core.model.tools.CipherAlgorithmEnum;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.api.security.SecurityService;
import com.aurfy.haze.service.bean.CRUDBean;
import com.aurfy.haze.service.bean.infra.mer.MerchantBean;
import com.aurfy.haze.service.bean.security.KeyStoreBean;
import com.aurfy.haze.service.client.ServiceClient;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.utils.StringUtils;

public class PGWUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(PGWUtils.class);
	
	public static String generateSignature(Object instance, String merCode, String signMethod) {
		try {	
			logger.debug("1.validate merchant's encrypted policy.");
			SecurityService securityService;
			
			securityService = ServiceClient.createSecurityService();
			
			CRUDService crudService = ServiceClient.createCRUDService();

			KeyStoreBean ksBean = new KeyStoreBean();
			
			//get merchant
			MerchantBean mer = new MerchantBean();
			mer.setMerchantCode(merCode);
			List<CRUDBean> list = crudService.retrieveBy(mer.getClass(), mer, 0, 0).getPageData();
			if(list == null || list.size() == 0){
				throw new RuntimeException("Not found this merchant in system!");
			}
			mer = (MerchantBean) list.get(0);
			
			ksBean.setMerchant(mer);
			if ("MD5".equals(signMethod)) {
				ksBean.setCipherAlgorithm(CipherAlgorithmEnum.MD5);
			} else if ("RSA".equals(signMethod)) {
				ksBean.setCipherAlgorithm(CipherAlgorithmEnum.RSA);
			}
			// to MD5,only confirm if the cipher algorithm is in the db or not.
			securityService.validateSignature(null, ksBean, null);

			logger.debug("2.sort the keys of params.");
			Map<String, String> mp = BeanUtils.describe(instance);
			TreeMap<String, String> treeMap = new TreeMap<String, String>();
			treeMap.putAll(mp);

			logger.debug("3.object to string.");
			String message = StringUtils.joinMapValue(treeMap);

			logger.debug("4.encrypt message by the given policy, e.g., MD5, RSA");
			ksBean.setEncryptedKey(securityService.calculateSignature(null, ksBean));
			return securityService.calculateSignature(message, ksBean);
		} catch (ServiceException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			logger.error("generate signature faild", e);
			throw new RuntimeException("generate signature faild", e);
		}
	}
	
	public static <T> T encodeUrl(T instance, String encoding, List<String> columns){
		Method methodGet = null;
		Method methodSet = null;
		String methodName = null;
		for(String column : columns){
			try {
				methodName = "get" + StringUtils.capitalize(column);
				methodGet = instance.getClass().getMethod(methodName);
				String cValue = String.class.cast(methodGet.invoke(instance));
				
				methodName = "set" + StringUtils.capitalize(column);
				methodSet = instance.getClass().getMethod(methodName, String.class);
				methodSet.invoke(instance, URLEncoder.encode(cValue, encoding));
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | UnsupportedEncodingException e) {
				logger.error("encode url failed!", e);
				throw new RuntimeException("encode url failed!", e);
			} 
		}
		return instance;
		
	}

}
