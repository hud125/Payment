package com.aurfy.haze.dao.txn;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.infra.ProductClassifierEnum;
import com.aurfy.haze.core.model.infra.mer.MerchantReference;
import com.aurfy.haze.core.model.txn.MerchantOrder;
import com.aurfy.haze.core.model.txn.TxnStatusEnum;
import com.aurfy.haze.core.model.txn.SignMethod;
import com.aurfy.haze.core.model.txn.TxnTypeEnum;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.txn.MerTxnEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class MerTxnMapperTest extends PersistUnitTest {

	@Autowired
	private MerTxnMapper merTxnMapper;

	public MerTxnEntity newMerTxnEntity() {
		MerTxnEntity merTxn = new MerTxnEntity();
		merTxn.setID(SecurityUtils.UUID());
		MerchantReference merRef = new MerchantReference();
		merRef.setMerId("1246dfc2-519c-4cf0-83fe-605addf36b1f");
		merRef.setMerCode("99999999999999999999999999999999");
		MerchantOrder merOrder = new MerchantOrder();
		merOrder.setMerRef(merRef);
		merOrder.setTxnType(TxnTypeEnum.PURCHASE);
		merOrder.setCurrency(Currency.CNY);
		merOrder.setAmount(new BigInteger("100000"));
		merOrder.setOrderId(RandomStringUtils.randomAlphanumeric(20));
		merOrder.setBillDesc(RandomStringUtils.randomAlphanumeric(50));
		merOrder.setOrderDetail(RandomStringUtils.randomAlphanumeric(10));
		merOrder.setOrderDate(new Date());
		merOrder.setOrderTimezone("08");
		merOrder.setOrderTimeout(2000);
		merOrder.setCustomerIp("127.0.0.1");
		merOrder.setPayTimeout(1000);
		merOrder.setPreferredBank("ICBC");
		merOrder.setBrowserNotifyUrl("www.baidu.com");
		merOrder.setServerNotifyUrl("www.baidu.com");
		merTxn.setMerOrder(merOrder);
		merTxn.setTxnStatus(TxnStatusEnum.NEW);
		merTxn.setOwnerId(SecurityUtils.UUID());
		merTxn.setProductClassifier(ProductClassifierEnum.ExpressPay);
		merTxn.setTerminalId(SecurityUtils.UUID());
		merTxn.setProtocolVer("1.0.0");
		merTxn.setCharsetEncoding(null);
		merTxn.setSignMethod(SignMethod.SHA3);

		merTxn.setSettleRefId(SecurityUtils.UUID());
		merTxn.setPayRefId(SecurityUtils.UUID());
		merTxn.setCreateDate(new Date());
		merTxn.setUpdateDate(merTxn.getCreateDate());
		return merTxn;
	}

	public MerTxnEntity getMerTxnEntity() {
		MerTxnEntity merTxn = newMerTxnEntity();
		int count = merTxnMapper.insert(merTxn);
		assertTrue(count == 1);
		return merTxn;
	}

	@Test
	@Transactional
	public void TestInsert() {
		getMerTxnEntity();
	}

	@Test
	@Transactional
	public void TestSelectOne() {
		MerTxnEntity merTxn = getMerTxnEntity();
		MerTxnEntity merTxn2 = merTxnMapper.selectOne(merTxn.getID());
		assertTrue(merTxn.equals(merTxn2));
	}

	@Test
	@Transactional
	public void TestRetrieveTxnWithinSameDay() {
		MerTxnEntity merTxn = getMerTxnEntity();
		MerTxnEntity merTxn2 = merTxnMapper.selectOne(merTxn.getID());
		assertTrue(merTxn.equals(merTxn2));
		
//		MerTxnEntity merTxn3 = merTxnMapper.retrieveTxnWithinSameDay(merTxn2.getMerOrder().getMerRef().getMerId(), null, null, null);
//		assertTrue(merTxn.equals(merTxn3));
		
		MerTxnEntity merTxn3 = merTxnMapper.retrieveTxnWithinSameDay(merTxn2.getMerOrder().getMerRef().getMerId(), 
				merTxn2.getMerOrder().getMerRef().getSubMerId(), merTxn2.getMerOrder().getOrderId(), merTxn2.getMerOrder().getOrderDate());
		assertTrue(merTxn.equals(merTxn3));
	}


}
