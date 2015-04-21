package com.aurfy.haze.dao.infrastructure;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.infra.mer.MerchantStatusEnum;
import com.aurfy.haze.core.model.tools.CipherAlgorithmEnum;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.mer.MerchantMapper;
import com.aurfy.haze.dao.security.KeyStoreMapper;
import com.aurfy.haze.entity.infra.mer.MerchantEntity;
import com.aurfy.haze.entity.security.KeyStoreEntity;
import com.aurfy.haze.utils.DateUtils;
import com.aurfy.haze.utils.SecurityUtils;

/**
 * 由于职能不同，故这里不考虑addressbook的生成，直接将其置为null
 * 
 * @author rocket
 *
 */
public class KeyStoreMapperTest extends PersistUnitTest {

	@Autowired
	private MerchantMapper merMapper;
	
	@Autowired
	private KeyStoreMapper mapper;

	public MerchantEntity getNewMerchant() {
		MerchantEntity mer = MerchantMapperTest.newMerchantWithNullAddr();
		String oldId = mer.getID();
		int count = merMapper.insert(mer);
		String newId = mer.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		return mer;
	}
	
	public KeyStoreEntity newKeyStoreEntity(){
		KeyStoreEntity ks = new KeyStoreEntity();
		ks.setID(SecurityUtils.UUID());
		ks.setMerchant(getNewMerchant());
		ks.setEncryptedKey("1");
		ks.setCipherAlgorithm(CipherAlgorithmEnum.MD5);
		ks.setSalt(null);
		ks.setExpiryDate(DateUtils.addDays(new Date(), 5));
		ks.setCreateDate(new Date());
		ks.setUpdateDate(ks.getCreateDate());
		System.out.println(ks.getExpiryDate());
		String oldId = ks.getID();
		int count = mapper.insert(ks);
		String newId = ks.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		
		return ks;
	}
	
	@Test
	@Transactional
	public void TestInsertKeyStore(){
		newKeyStoreEntity();
	}
	
	@Test
	@Transactional
	public void TestSelectKeyStore(){
		KeyStoreEntity ks = newKeyStoreEntity();
		KeyStoreEntity ks2 = mapper.selectOne(ks.getID());
		assertTrue(ks.equals(ks2));
	}
	
	@Test
	@Transactional
	public void TestCountBy(){
		KeyStoreEntity ks = newKeyStoreEntity();
		int count = mapper.countBy(ks);
		assertTrue(count > 0);
	}
	
	@Test
	@Transactional
	public void TestSelectBy(){
		KeyStoreEntity ks = newKeyStoreEntity();
		List<KeyStoreEntity> kss = mapper.selectBy(ks, 0, 10);
		assertTrue(kss.size() > 0);
	}
	
	@Test
	@Transactional
	public void TestUpdateKeyStore(){
		KeyStoreEntity ks = newKeyStoreEntity();
		ks.setEncryptedKey("2");
		int count = mapper.update(ks);
		assertTrue(count == 1);
	}
	
	@Test
	@Transactional
	public void TestDeleteKeyStore(){
		KeyStoreEntity ks = newKeyStoreEntity();
		int count = mapper.delete(ks.getID());
		assertTrue(count == 1);
	}

}
