package com.aurfy.haze.service.impl.bank.handler;

import static com.aurfy.haze.utils.StringUtils.joinMapValue;

import java.util.HashMap;
import java.util.Map;

import com.aurfy.haze.core.model.txn.BankOrder;
import com.aurfy.haze.service.bean.bank.BankRequest;
import com.aurfy.haze.service.bean.bank.BankResponse;
import com.aurfy.haze.service.bean.configuration.channel.ChannelBean;
import com.aurfy.haze.service.exceptions.NotSupportedException;
import com.aurfy.haze.service.exceptions.UnimplementedException;

public abstract class BaseBankHandler implements BankHandler {

	protected static final String KEY_ACQ_CODE = "acqCode";
	protected static final String KEY_MER_ID = "merId";
	protected static final String KEY_SUB_MER_ID = "subMerId";
	protected static final String KEY_MCC = "merCode";
	// protected static final String KEY_ORDER_ID = "orderId";
	protected static final String KEY_TERMINAL = "terminalId";
	protected static final String KEY_PORT = "port";
	// protected static final String KEY_ADDRESS = "address";
	protected static final String KEY_CURRENCY = "currency";
	// protected static final String KEY_AMOUT = "amount";
	// protected static final String KEY_EXCHANGE_RATE = "exchangeRate";
	// protected static final String KEY_SIGNATURE = "signature";

	private BankRequest request;
	private ChannelBean channel;
	private String paySummaryId;

	/**
	 * contains all the final parameters that will be sent to bank
	 */
	protected Map<String, String> parameters;

	public BaseBankHandler() {
		parameters = new HashMap<String, String>();
	}

	public void init(BankRequest request, ChannelBean channel, String paySummaryId) {
		this.request = request;
		this.channel = channel;
		this.paySummaryId = paySummaryId;
	}

	protected abstract String getSignatureFieldName();

	public ChannelBean getChannel() {
		return channel;
	}

	public void setChannel(ChannelBean channel) {
		this.channel = channel;
	}

	public BankRequest getRequest() {
		return request;
	}

	public void setRequest(BankRequest request) {
		this.request = request;
	}

	protected Map<String, String> getParameters() {
		return parameters;
	}

	protected String getPaySummaryId() {
		return paySummaryId;
	}

	@Override
	public BankOrder compose() {
		fillParameters();
		parameters.put(getSignatureFieldName(), calculateSignature());
		return createBankOrder();
	}

	/**
	 * replace all channel template variables with input value, and store it in parameters map
	 */
	protected abstract void fillParameters();

	protected abstract String calculateSignature();

	protected abstract BankOrder createBankOrder();

	/**
	 * default implementation, joint by '&'
	 */
	@Override
	public String getBankParmeters() {
		return joinMapValue(parameters);
	}

	@Override
	public BankResponse process() {
		BankResponse bankResponse = null;
		switch (this.request.getPayOrder().getSrcTxnType()) {
		case PURCHASE: {
			bankResponse = processPurchase();
			break;
		}
		case AUTH: {
			bankResponse = processAuth();
			break;
		}
		case AUTH_CAPTURE: {
			bankResponse = processAuthCapture();
			break;
		}
		case AUTH_CANCEL: {
			bankResponse = processAuthCancel();
			break;
		}
		case VOID: {
			bankResponse = processVoid();
			break;
		}
		case REFUND: {
			bankResponse = processRefund();
			break;
		}
		case REVERSAL: {
			bankResponse = processReversal();
			break;
		}
		case CHARGEBACK:
		default: {
			throw new NotSupportedException(this.request.getPayOrder().getSrcTxnType().toString());
		}
		}
		return bankResponse;
	}

	protected BankResponse processPurchase() {
		throw new UnimplementedException("Purchase");
	}

	protected BankResponse processAuth() {
		throw new UnimplementedException("Auth");
	}

	protected BankResponse processAuthCapture() {
		throw new UnimplementedException("AuthCapture");
	}

	protected BankResponse processAuthCancel() {
		throw new UnimplementedException("AuthCancel");
	}

	protected BankResponse processVoid() {
		throw new UnimplementedException("Void");
	}

	protected BankResponse processRefund() {
		throw new UnimplementedException("Refund");
	}

	protected BankResponse processReversal() {
		throw new UnimplementedException("Reversal");
	}

	protected abstract String generateBankOrderId();

}
