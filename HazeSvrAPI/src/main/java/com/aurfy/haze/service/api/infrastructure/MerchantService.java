package com.aurfy.haze.service.api.infrastructure;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.service.api.HazeService;
import com.aurfy.haze.service.bean.configuration.channel.ChannelBean;
import com.aurfy.haze.service.bean.infra.VariableMapBean;
import com.aurfy.haze.service.bean.infra.mer.MerchantBean;
import com.aurfy.haze.service.bean.settlement.MerSettleConfigBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;

/**
 * Merchant Service
 * 
 * @author hermano
 *
 */
public interface MerchantService extends HazeService {

	/**
	 * create a merchant by given params
	 * @param merBean
	 * @param varMapBean
	 * @return
	 * @throws ServiceException
	 */
	public MerchantBean insertMerchant(MerchantBean merBean, VariableMapBean varMapBean) throws ServiceException;

	/**
	 * add channel mapping for merchant.
	 * 
	 * @param merchantId
	 * @param channelId
	 * @param isDefault
	 * @return <code>false</code> if the specified channel is already set as desired
	 * @throws ServiceException
	 * @throws RuntimeServiceException
	 */
	public boolean addChannelMapping(String merchantId, String channelId, boolean isDefault) throws ServiceException,
			RuntimeServiceException;

	/**
	 * <p>
	 * get merchant channel for the specified transaction currency.<br/>
	 * default channel will be used if no channel is associated with that currency.<br/>
	 * a list of {@link com.aurfy.haze.service.bean.configuration.channel.ChannelParameterBean ChannelParameterBean}
	 * will also be included if any.
	 * </p>
	 * 
	 * @param merchantId
	 * @param txnCurrency
	 * @return
	 */
	public ChannelBean getChannel(String merchantId, Currency txnCurrency) throws ServiceException,
			RuntimeServiceException;

	/**
	 * get merchant settlement configuration.
	 * 
	 * @param merchantId
	 * @return
	 */
	public MerSettleConfigBean getSettleConfig(String merchantId) throws ServiceException, RuntimeServiceException;

}
