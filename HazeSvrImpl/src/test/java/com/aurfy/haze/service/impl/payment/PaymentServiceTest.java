package com.aurfy.haze.service.impl.payment;

import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.core.model.infra.ProductClassifierEnum;
import com.aurfy.haze.core.model.infra.mer.MerchantReference;
import com.aurfy.haze.core.model.payment.CredentialTypeEnum;
import com.aurfy.haze.core.model.payment.ExpiryDate;
import com.aurfy.haze.core.model.payment.PayCredential;
import com.aurfy.haze.core.model.txn.MerchantOrder;
import com.aurfy.haze.core.model.txn.TxnStatusEnum;
import com.aurfy.haze.core.model.txn.SignMethod;
import com.aurfy.haze.core.model.txn.TxnTypeEnum;
import com.aurfy.haze.service.api.payment.PaymentService;
import com.aurfy.haze.service.bean.payment.PayResponse;
import com.aurfy.haze.service.bean.txn.MerTxnBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.ServiceUnitTest;
import com.aurfy.haze.utils.SecurityUtils;
import com.aurfy.haze.utils.StringUtils;

public class PaymentServiceTest extends ServiceUnitTest {

	@Autowired
	private PaymentService paymentService;
	
	public MerTxnBean newMerTxnBean() {
		MerTxnBean merTxn = new MerTxnBean();
		MerchantReference merRef = new MerchantReference();
		merRef.setMerId("1246dfc2-519c-4cf0-83fe-605addf36b1d");
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

		return merTxn;
	}
	
	public PayCredential newPayCredential(){
		PayCredential payCredential = new PayCredential();
		payCredential.setVirtualAccount(RandomStringUtils.randomAlphanumeric(18));
		payCredential.setCardOrg(CardOrgEnum.UNIONPAY);
		payCredential.setToken(RandomStringUtils.randomAlphanumeric(100));
		payCredential.setEncryptedCardNo(RandomStringUtils.randomAlphanumeric(10));
		payCredential.setMaskCardNo(StringUtils.maskCardNumber(payCredential.getCardNo()));
		payCredential.setCardNo("6221558812340000");
		payCredential.setCvv("123");
		payCredential.setExpiryDate(new ExpiryDate("11", "17"));
		payCredential.setCellphone("13552535506");
		payCredential.setCardHolderFullName("赵骏");
		payCredential.setCardHolderFirstName("赵");
		payCredential.setCardHolderMiddleName("");
		payCredential.setCardHolderLastName("骏");
		payCredential.setCredentialType(CredentialTypeEnum.IDCard);
		payCredential.setCredentialNo(RandomStringUtils.randomNumeric(10));
		
		return payCredential;
	}
	
	@Test
	@Transactional
	public void testCreateMerTxn() throws RuntimeServiceException, ServiceException{
		MerTxnBean txn = newMerTxnBean();
		paymentService.saveMerTxn(txn);
	}
	
	@Test
	@Transactional
	public void testUpdateMerTxn() throws RuntimeServiceException, ServiceException{
		MerTxnBean txn = newMerTxnBean();
		//create merTxn
		paymentService.saveMerTxn(txn);
		
		//update merTxn
		paymentService.saveMerTxn(txn);
		
	}
	
	@Test
	@Transactional
//	@Rollback(false)
	public void testDoExpressPay() throws RuntimeServiceException, ServiceException{
		MerTxnBean txn = newMerTxnBean();
		//create merTxn
		txn = paymentService.saveMerTxn(txn);
		
		PayCredential payCredential = newPayCredential();
		paymentService.doExpressPay(txn, payCredential); //TODO
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	public void testNotifyPayResponse() throws RuntimeServiceException, ServiceException{
		MerTxnBean txn = newMerTxnBean();
		txn = paymentService.saveMerTxn(txn);
		PayResponse payResponse = new PayResponse();
		payResponse.setSrcTxnId(txn.getID());
		paymentService.notifyPayResponse(payResponse);
	}
	
	
}
