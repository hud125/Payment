package com.aurfy.haze.service.impl.bank.handler;

import java.util.Map;

import com.aurfy.haze.core.model.txn.BankOrder;
import com.aurfy.haze.service.bean.bank.BankRequest;
import com.aurfy.haze.service.bean.bank.BankResponse;
import com.aurfy.haze.service.bean.configuration.channel.ChannelBean;

/**
 * for each channel provider, we need to setup a concrete implementation class.
 * 
 * @author hermano
 *
 */
public interface BankHandler {

	/**
	 * init this handler
	 * 
	 * @param request
	 * @param channel
	 * @param paySummaryId
	 */
	public void init(BankRequest request, ChannelBean channel, String paySummaryId);

	/**
	 * <p>
	 * necessary steps: <br/>
	 * parameter replacement<br/>
	 * signature<br/>
	 * create bank order<br/>
	 * etc
	 * </p>
	 */
	public BankOrder compose();

	/**
	 * get the final parameters (key and value) that will be sent to bank.
	 * 
	 * @return
	 */
	public String getBankParmeters();

	/**
	 * process the request (send to bank)
	 */
	public BankResponse process();

	/**
	 * 
	 * asseble the response with the key-value in map ,used for oncallback in post process
	 * 
	 * @return
	 */
	public BankResponse assembleResponse(Map<String, String> map);

	/**
	 * 验证签名
	 * 
	 * @param channelId
	 * @param map
	 * @return
	 */
	public boolean verifySignature(String channelId, Map<String, String> map);

}
