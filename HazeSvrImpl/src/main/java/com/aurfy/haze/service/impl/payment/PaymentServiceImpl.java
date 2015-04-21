package com.aurfy.haze.service.impl.payment;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.configuration.channel.ChannelProvider;
import com.aurfy.haze.core.model.infra.DeliveryStatusEnum;
import com.aurfy.haze.core.model.infra.mer.MerchantReference;
import com.aurfy.haze.core.model.payment.PayCredential;
import com.aurfy.haze.core.model.txn.MerTxn;
import com.aurfy.haze.core.model.txn.MerchantOrder;
import com.aurfy.haze.core.model.txn.SrcAppTypeEnum;
import com.aurfy.haze.core.model.txn.SrcPayOrder;
import com.aurfy.haze.core.model.txn.TxnStatusEnum;
import com.aurfy.haze.dao.configuration.channel.ChannelProviderMapper;
import com.aurfy.haze.dao.infra.NotificationMapper;
import com.aurfy.haze.dao.txn.MerTxnMapper;
import com.aurfy.haze.entity.infra.NotificationEntity;
import com.aurfy.haze.entity.txn.MerTxnEntity;
import com.aurfy.haze.service.api.bank.BankService;
import com.aurfy.haze.service.api.infrastructure.MerchantService;
import com.aurfy.haze.service.api.payment.PaymentService;
import com.aurfy.haze.service.bean.bank.ExpressPayRequest;
import com.aurfy.haze.service.bean.bank.ProcessReference;
import com.aurfy.haze.service.bean.configuration.channel.ChannelBean;
import com.aurfy.haze.service.bean.infra.NotificationBean;
import com.aurfy.haze.service.bean.payment.PayResponse;
import com.aurfy.haze.service.bean.txn.MerTxnBean;
import com.aurfy.haze.service.conf.ServiceImplConstant.AOP_NAME;
import com.aurfy.haze.service.exceptions.DuplicateException;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.BaseHazeService;
import com.aurfy.haze.service.impl.CRUDServiceImpl;
import com.aurfy.haze.service.impl.converter.Converter;
import com.aurfy.haze.service.impl.converter.ConverterHelper;

@Service(AOP_NAME.PAYMENT_SERVICE)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PaymentServiceImpl extends BaseHazeService implements PaymentService {

	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private MerTxnMapper merTxnMapper;

	@Autowired
	private ChannelProviderMapper channelProviderMapper;

	@Autowired
	private BankService bankSvr;

	@Autowired
	private MerchantService merSvr;

	@Autowired
	private NotificationMapper ntMapper;

	public PaymentServiceImpl() {
	}

	@Override
	@Transactional
	public MerTxnBean saveMerTxn(MerTxnBean txn) throws ServiceException, RuntimeServiceException {

		// TODO parameter check

		MerchantOrder mo = txn.getMerOrder();
		MerchantReference merRef = mo.getMerRef();
		MerTxn oldTxn = merTxnMapper.retrieveTxnWithinSameDay(merRef.getMerId(), merRef.getSubMerId(), mo.getOrderId(),
				mo.getOrderDate());

		Converter converter = ConverterHelper.getConverter(MerTxnBean.class);
		MerTxnEntity entity = (MerTxnEntity) converter.bean2Entity(MerTxnEntity.class, txn);
		String action;
		int row;

		// new transaction
		if (oldTxn == null) {
			action = "create";
			CRUDServiceImpl.updateStdProperties(entity);
			row = merTxnMapper.insert(entity);
		}
		// duplicate, check status
		else {
			if (TxnStatusEnum.PAID.equals(oldTxn.getTxnStatus())) {
				throw new DuplicateException("the same transaction already exists and is paid successfully.");
			}
			// update
			action = "update";
			entity.setID(oldTxn.getID()); // this is important for overwrite
			entity.setTxnStatus(TxnStatusEnum.NEW);
			CRUDServiceImpl.updateProperty(entity, "createDate", new Date(), Date.class);
			entity.setUpdateDate(new Date());
			row = merTxnMapper.update(entity);
		}
		logger.info("{} MerTxn: merId={}, subMerId={}, type={}, orderId={}, date={}, result={}", action,
				merRef.getMerId(), merRef.getSubMerId(), mo.getTxnType(), mo.getOrderId(), mo.getOrderDate(), row);
		return (MerTxnBean) converter.entity2Bean(MerTxnBean.class, entity);
	}

	@Override
	@Transactional
	public ProcessReference doExpressPay(MerTxnBean txn, PayCredential payCredential) throws ServiceException,
			RuntimeServiceException {
		ExpressPayRequest request = new ExpressPayRequest();
		MerchantOrder merchantOrder = txn.getMerOrder();

		SrcPayOrder srcPayOrder = new SrcPayOrder();
		srcPayOrder.setSrcAppType(SrcAppTypeEnum.PGW);
		srcPayOrder.setSrcRefId(merchantOrder.getMerRef().getMerId());
		srcPayOrder.setSrcTxnId(txn.getID());
		srcPayOrder.setSrcTxnType(merchantOrder.getTxnType());
		srcPayOrder.setSrcOrderId(merchantOrder.getOrderId());
		srcPayOrder.setSrcCurrency(merchantOrder.getCurrency());
		srcPayOrder.setSrcAmount(merchantOrder.getAmount());
		srcPayOrder.setSrcTxnDate(merchantOrder.getOrderDate());
		srcPayOrder.setSrcTxnDay(merchantOrder.getOrderDate());// TODO

		request.setPayOrder(srcPayOrder);
		request.setPayCredential(payCredential);
		ChannelBean channel = merSvr.getChannel(txn.getMerOrder().getMerRef().getMerId(), txn.getMerOrder()
				.getCurrency());
		ChannelProvider provider = channelProviderMapper.selectOne(channel.getChannelProviderId());
		if (provider != null) {
			channel.setSupport3D(provider.isSupport3D());
			channel.setSupportCardNoTrasmit(provider.isSupportCardNoTrasmit());
			channel.setSupportDCC(provider.isSupportDCC());
		}
		return bankSvr.doExpressPay(request, channel);
	}

	@Override
	@Transactional
	public void notifyPayResponse(PayResponse payResponse) throws ServiceException, RuntimeServiceException {
		// private String srcTxnId;
		// private String paySummaryId;
		// private RespCode respCode;
		// private BigInteger payAmount;
		// private Currency payCurrency;
		// private PayStatusEnum status;// 当需要自动或人工处理某些流水时（如需退货等），将此标志位置为特殊条件，方便查询

		// `notification_id` VARCHAR(36) NOT NULL,
		// `target_url` VARCHAR(256) NOT NULL,
		// `http_method` VARCHAR(16) NOT NULL,
		// `json_msg` TEXT NOT NULL,
		// `max_counter` TINYINT NOT NULL,
		// `retry_counter` TINYINT NOT NULL,
		// `delivery_status` TINYINT NOT NULL,
		// `http_status` CHAR(3),
		// `create_date` TIMESTAMP(3) NOT NULL,
		// `update_date` TIMESTAMP(3) NOT NULL,

		MerTxnEntity merTxnEntity = merTxnMapper.selectOne(payResponse.getSrcTxnId());
		if (merTxnEntity == null) {
			throw new RuntimeServiceException("Not found the merTxn object with the given id of "
					+ payResponse.getSrcTxnId());
		}
		Converter merTxnConverter = ConverterHelper.getConverter(MerTxnEntity.class);
		MerTxnBean merTxnBean = (MerTxnBean) merTxnConverter.entity2Bean(MerTxnBean.class, merTxnEntity);

		NotificationBean ntBean = new NotificationBean();
		ntBean.setMaxCounter(5);
		ntBean.setRetryCounter(0);
		ntBean.setTargetURL(merTxnBean.getMerOrder().getServerNotifyUrl());
		ntBean.setHttpMethod("POST");

		// TODO
		ntBean.setJsonMsg("{'value':'test'}");
		ntBean.setDeliveryStatus(DeliveryStatusEnum.FAILED);

		Converter notifyConverter = ConverterHelper.getConverter(NotificationEntity.class);
		NotificationEntity ntEntity = (NotificationEntity) notifyConverter
				.bean2Entity(NotificationEntity.class, ntBean);
		CRUDServiceImpl.updateStdProperties(ntEntity);
		ntMapper.insert(ntEntity);
	}

}
