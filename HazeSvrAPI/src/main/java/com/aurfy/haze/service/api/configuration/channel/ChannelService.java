package com.aurfy.haze.service.api.configuration.channel;

import java.util.List;
import java.util.Set;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.MerchantKey;
import com.aurfy.haze.core.model.page.Pagination;
import com.aurfy.haze.service.api.HazeService;
import com.aurfy.haze.service.bean.configuration.channel.ChannelBean;
import com.aurfy.haze.service.bean.configuration.channel.ChannelParameterBean;
import com.aurfy.haze.service.exceptions.ObjectNotFoundException;
import com.aurfy.haze.service.exceptions.ParameterValidationException;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;

public interface ChannelService extends HazeService {

	/**
	 * get the supported currencies for the given merchant
	 * 
	 * @param merchantKey
	 * @throws ObjectNotFoundException
	 *             if the merchant by the given key is not found
	 * @return
	 */
	public Set<Currency> getSupportedCurrencies(MerchantKey merchantKey) throws ObjectNotFoundException;

	/**
	 * 创建channel以及channelParameter
	 * 
	 * @param bean
	 * @return
	 */
	ChannelBean create(ChannelBean bean) throws ServiceException, RuntimeServiceException;

	/**
	 * 获取channel
	 * 
	 * @param channelId
	 * @return
	 */
	ChannelBean retrieve(String channelId) throws ServiceException, RuntimeServiceException;

	/**
	 * 获取channel 当needParams为true时获取channelParameter
	 * 
	 * @param channelId
	 * @param includeParams
	 * @return
	 */
	ChannelBean retrieve(String channelId, boolean includeParams) throws ServiceException, RuntimeServiceException;

	/**
	 * 获取通道列表 带分页
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @throws RuntimeServiceException
	 */
	public Pagination<ChannelBean> retrieve(int pageNum, int pageSize) throws ServiceException, RuntimeServiceException;

	/**
	 * 获取所有的通道
	 * 
	 * @return
	 * @throws ServiceException
	 * @throws RuntimeServiceException
	 */
	public List<ChannelBean> retrieveAll() throws ServiceException, RuntimeServiceException;

	/**
	 * 更新ChannelBean 并不更新ChannelParameter
	 * 
	 * @param bean
	 * @return
	 * @throws ObjectNotFoundException
	 * @throws ParameterValidationException
	 */
	ChannelBean update(ChannelBean bean) throws ServiceException, RuntimeServiceException;

	/**
	 * 根据channelId先删除掉channelParameter,再插入新的ChannelParameter
	 * 
	 * @param channelId
	 * @param params
	 * @return
	 * @throws ParameterValidationException
	 */
	ChannelBean updateParams(String channelId, List<ChannelParameterBean> params) throws ServiceException,
			RuntimeServiceException;

	/**
	 * 根据channelId删除通道的同时会删掉对应的通道参数
	 * 
	 * @param channelId
	 * @throws ObjectNotFoundException
	 * @throws ParameterValidationException
	 */
	void delete(String channelId) throws ServiceException, RuntimeServiceException;
}
