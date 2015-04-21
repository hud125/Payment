package com.aurfy.haze.dao.security;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.aurfy.haze.core.model.tools.CipherAlgorithmEnum;
import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.security.KeyStoreEntity;

@Component(AOP_MAPPER.KEY_STORE_MAPPER)
@MapperEntity(value = KeyStoreEntity.class, CRUDBeanRequired = false)
public interface KeyStoreMapper extends CRUDMapper {

	/**
	 * count keyStores associated with the given merchant's id.
	 * 
	 * @param keyStore
	 * @return
	 */
	int countKeyStoresByMerchantID(@Param("merchantID") String merchantID);

	/**
	 * Get keyStores associated with the given merchant's id, and do with pagination.
	 * 
	 * @param param
	 * @return
	 */
	List<KeyStoreEntity> selectKeyStoresByMerchantID(Map<String, Object> param);

	/**
	 * Get merchant's keyStore by the given policy.<br />
	 * The result is only less than one.
	 * 
	 * @param merchantID
	 * @param policy
	 * @return
	 */
	KeyStoreEntity selectKeyStoreByPolicy(@Param("merchantID") String merchantID, @Param("merchantCode") String merchantCode,
			@Param("policy") CipherAlgorithmEnum policy);

}
