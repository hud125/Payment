package com.aurfy.haze.service.api.bank;

import java.util.Map;

import com.aurfy.haze.service.api.HazeService;
import com.aurfy.haze.service.bean.bank.BankResponse;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;

/**
 * post-processor for banking response.
 * 
 * @author hermano
 *
 */
public interface BankPostProcessService extends HazeService {

	/**
	 * <p>
	 * do some work after the response has been sent to the bank<br/>
	 * note: this action does not related to bank callback.<br/>
	 * i.e.: update send date, etc.
	 * </p>
	 * 
	 * @param response
	 * @throws ServiceException
	 * @throws RuntimeServiceException
	 */
	public void postSend(BankResponse response) throws ServiceException, RuntimeServiceException;

	/**
	 * <ol>
	 * <li>signature validation</li>
	 * <li>check status (from cache if any)</li>
	 * <li>exception handle (duplicate/rollback)</li>
	 * <li>update pay flow/pay summary</li>
	 * <li>update order status</li>
	 * <li>create merchant notification item (with signature)</li>
	 * <li>update status cache</li>
	 * <li>notify risk system</li>
	 * </ol>
	 * 
	 * @param bankOrderId
	 * @param data
	 * @throws ServiceException
	 * @throws RuntimeServiceException
	 */
	public void onCallback(String bankOrderId, Map<String, String> data) throws ServiceException,
			RuntimeServiceException;

}
