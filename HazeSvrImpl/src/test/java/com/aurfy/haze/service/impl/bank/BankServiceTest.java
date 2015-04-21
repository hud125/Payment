package com.aurfy.haze.service.impl.bank;

import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.core.model.infra.mer.MerchantReference;
import com.aurfy.haze.core.model.payment.CredentialTypeEnum;
import com.aurfy.haze.core.model.payment.ExpiryDate;
import com.aurfy.haze.core.model.payment.PayCredential;
import com.aurfy.haze.core.model.payment.PayStatusEnum;
import com.aurfy.haze.core.model.txn.MerchantOrder;
import com.aurfy.haze.core.model.txn.SrcAppTypeEnum;
import com.aurfy.haze.core.model.txn.SrcPayOrder;
import com.aurfy.haze.core.model.txn.TxnTypeEnum;
import com.aurfy.haze.dao.payment.PaymentFlowMapper;
import com.aurfy.haze.dao.payment.PaymentSummaryMapper;
import com.aurfy.haze.entity.payment.PaymentFlowEntity;
import com.aurfy.haze.entity.payment.PaymentSummaryEntity;
import com.aurfy.haze.service.api.bank.BankService;
import com.aurfy.haze.service.api.configuration.channel.ChannelService;
import com.aurfy.haze.service.bean.bank.BankRequest;
import com.aurfy.haze.service.bean.bank.ExpressPayRequest;
import com.aurfy.haze.service.bean.bank.ProcessReference;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.ServiceUnitTest;
import com.aurfy.haze.utils.SecurityUtils;
import com.aurfy.haze.utils.StringUtils;

public class BankServiceTest extends ServiceUnitTest {

	@Autowired
	private BankService bankService;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private PaymentFlowMapper paymentFlowMapper;
	@Autowired
	private PaymentSummaryMapper paymentSummaryMapper;

	@Test
	@Rollback(false)
	public void testDoExpressPay() throws ServiceException, InterruptedException {
		ProcessReference processReference = bankService.doExpressPay(
				(ExpressPayRequest) newBankRequest("123", "6221558812340000"),
				channelService.retrieve("cc346097-9089-4f75-95c6-3ddc41267a40", true));

		Thread thread = new InnerThread(processReference);
		thread.start();
		thread.join();
	}

	class InnerThread extends Thread {
		private ProcessReference processReference;

		public InnerThread(ProcessReference processReference) {
			this.processReference = processReference;
		}

		@Override
		public void run() {
			while (true) {
				PaymentFlowEntity paymentFlow = paymentFlowMapper.selectOne(processReference.getProcessId());
				Assert.notNull(paymentFlow);
				if ((paymentFlow.getStatus().equals(PayStatusEnum.INITIAL))) {
					PaymentSummaryEntity paymentSuammary = paymentSummaryMapper
							.selectOne(paymentFlow.getPaySummaryId());
					Assert.notNull(paymentSuammary);
					Assert.isNull(paymentFlow.getSendDate());
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					paymentFlow = paymentFlowMapper.selectOne(processReference.getProcessId());
					Assert.notNull(paymentFlow.getSendDate());
					System.out.println("success send");
					return;
				}
			}
		}
	}

	public BankRequest newBankRequest(String cvv, String cardNo) {

		MerchantReference merRef = new MerchantReference();
		merRef.setMerId("1246dfc2-519c-4cf0-83fe-605addf36b1d");

		MerchantOrder merOrder = new MerchantOrder();
		merOrder.setOrderId(RandomStringUtils.randomAlphanumeric(20));
		merOrder.setMerRef(merRef);
		merOrder.setCurrency(Currency.CNY);
		merOrder.setAmount(new BigInteger("100000"));
		merOrder.setTxnType(TxnTypeEnum.PURCHASE);
		merOrder.setServerNotifyUrl("http://localhost:8080/UpopSDK/payBackResServlet");

		SrcPayOrder payOrder = new SrcPayOrder();
		payOrder.setSrcRefId(SecurityUtils.UUID());
		payOrder.setSrcTxnDay(new Date());
		payOrder.setSrcCurrency(Currency.CNY);
		payOrder.setSrcAmount(new BigInteger("100000"));
		payOrder.setSrcTxnType(TxnTypeEnum.PURCHASE);
		payOrder.setSrcAppType(SrcAppTypeEnum.PGW);
		payOrder.setSrcTxnId(SecurityUtils.UUID());
		payOrder.setSrcOrderId(SecurityUtils.UUID());

		PayCredential payCredential = new PayCredential();
		payCredential.setVirtualAccount(RandomStringUtils.randomAlphanumeric(18));
		payCredential.setCardOrg(CardOrgEnum.UNIONPAY);
		payCredential.setToken(RandomStringUtils.randomAlphanumeric(100));
		payCredential.setEncryptedCardNo(RandomStringUtils.randomAlphanumeric(10));
		payCredential.setMaskCardNo(StringUtils.maskCardNumber(payCredential.getCardNo()));
		payCredential.setCardNo(cardNo);
		payCredential.setCvv(cvv);
		payCredential.setExpiryDate(new ExpiryDate("11", "17"));
		payCredential.setCellphone("13552535506");
		payCredential.setCardHolderFullName("赵骏");
		payCredential.setCardHolderFirstName("赵");
		payCredential.setCardHolderMiddleName("");
		payCredential.setCardHolderLastName("骏");
		payCredential.setCredentialType(CredentialTypeEnum.IDCard);
		payCredential.setCredentialNo(RandomStringUtils.randomNumeric(10));

		ExpressPayRequest request = new ExpressPayRequest();
		request.setPayOrder(payOrder);
		request.setPayCredential(payCredential);
		request.setSrcTxnId(SecurityUtils.UUID());
		return request;
	}

}
