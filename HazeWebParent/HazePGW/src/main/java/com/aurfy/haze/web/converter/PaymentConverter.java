package com.aurfy.haze.web.converter;

import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.core.model.infra.ProductClassifierEnum;
import com.aurfy.haze.core.model.infra.mer.MerchantReference;
import com.aurfy.haze.core.model.payment.CredentialTypeEnum;
import com.aurfy.haze.core.model.payment.ExpiryDate;
import com.aurfy.haze.core.model.payment.PayCredential;
import com.aurfy.haze.core.model.txn.MerchantOrder;
import com.aurfy.haze.core.model.txn.SignMethod;
import com.aurfy.haze.core.model.txn.TxnStatusEnum;
import com.aurfy.haze.core.model.txn.TxnTypeEnum;
import com.aurfy.haze.service.bean.txn.MerTxnBean;
import com.aurfy.haze.utils.SecurityUtils;
import com.aurfy.haze.utils.StringUtils;
import com.aurfy.haze.web.vo.txn.ExpressPayReqVO;

/**
 * TODO 很多字段还没set，别漏了
 * 
 * 
 * 
 * @author rocket
 *
 */
public class PaymentConverter extends DefaultConverter {

	public PaymentConverter() {
	}
	
	public static MerTxnBean VO2MerBean(ExpressPayReqVO payReq, String merId){
		MerTxnBean merTxnBean = new MerTxnBean();
		MerchantReference merRef = new MerchantReference();
		merRef.setMerId(merId);
		merRef.setMerCode(payReq.getMerCode());
		MerchantOrder merOrder = new MerchantOrder();
		merOrder.setMerRef(merRef);
		merOrder.setTxnType(TxnTypeEnum.PURCHASE);
		merOrder.setCurrency(Currency.parseByName(payReq.getCurrency()));
		merOrder.setAmount(new BigInteger(payReq.getAmount()));
		merOrder.setOrderId(payReq.getOrderId());
		merOrder.setBillDesc(payReq.getBillDesc());
		merOrder.setOrderDetail(payReq.getOrderDetail());
		// merOrder.setOrderDate(DateUtils.parseDateConcrete(merTxn.getOrderDate()));
		merOrder.setOrderDate(new Date(Long.parseLong(payReq.getOrderDate())));
		merOrder.setOrderTimezone("08");
		merOrder.setOrderTimeout(Integer.parseInt(payReq.getOrderTimeout()));
		merOrder.setCustomerIp(payReq.getCustomerIp());
		merOrder.setPayTimeout(Integer.parseInt(payReq.getPayTimeout()));
		merOrder.setPreferredBank("ICBC");
		merOrder.setBrowserNotifyUrl(payReq.getBrowserNotifyUrl());
		merOrder.setServerNotifyUrl(payReq.getServerNotifyUrl());
		merTxnBean.setMerOrder(merOrder);
		merTxnBean.setTxnStatus(TxnStatusEnum.NEW);
		merTxnBean.setOwnerId(SecurityUtils.UUID());
		merTxnBean.setProductClassifier(ProductClassifierEnum.ExpressPay);
		merTxnBean.setTerminalId(payReq.getTerminalId());
		merTxnBean.setProtocolVer(payReq.getProtocolVer());
		merTxnBean.setCharsetEncoding(payReq.getCharsetEncoding());
		merTxnBean.setSignMethod(SignMethod.parseByName(payReq.getSignMethod()));
		merTxnBean.setMerOrder(merOrder);
		return merTxnBean;
	}

	public static PayCredential VO2PayCredential(ExpressPayReqVO payReq){
		PayCredential payCredential = new PayCredential();
		payCredential.setVirtualAccount(RandomStringUtils.randomAlphanumeric(18));
		payCredential.setCardOrg(CardOrgEnum.UNIONPAY);
		payCredential.setToken(RandomStringUtils.randomAlphanumeric(100));
		payCredential.setEncryptedCardNo(RandomStringUtils.randomAlphanumeric(10));
		payCredential.setMaskCardNo(StringUtils.maskCardNumber(payCredential.getCardNo()));

		payCredential.setCardNo(payReq.getCardNo());
		payCredential.setCvv(payReq.getCvv());
		payCredential.setExpiryDate(ExpiryDate.parseByDateStr(payReq.getExpiryDate()));
		payCredential.setCellphone(payReq.getCellphone());

		payCredential.setCardHolderFullName(payReq.getCardHolderFullName());
		payCredential.setCardHolderFirstName(payReq.getCardHolderFirstName());
		payCredential.setCardHolderMiddleName(payReq.getCardHolderMiddleName());
		payCredential.setCardHolderLastName(payReq.getCardHolderLastName());
		payCredential.setCredentialType(CredentialTypeEnum.IDCard);
		payCredential.setCredentialNo(RandomStringUtils.randomNumeric(10));
		return payCredential;
	}
	
	
}
