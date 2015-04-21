package com.aurfy.haze.service.api.bank;

import com.aurfy.haze.core.model.bank.EBankForm;
import com.aurfy.haze.core.model.bank.ThreeDInfo;
import com.aurfy.haze.core.model.bank.Token;
import com.aurfy.haze.service.api.HazeService;
import com.aurfy.haze.service.bean.bank.ExpressPayRequest;
import com.aurfy.haze.service.bean.bank.ProcessReference;
import com.aurfy.haze.service.bean.configuration.channel.ChannelBean;
import com.aurfy.haze.service.bean.payment.PayResponse;
import com.aurfy.haze.service.bean.todo.CreateFormRequest;
import com.aurfy.haze.service.bean.todo.CreateTokenRequest;
import com.aurfy.haze.service.bean.todo.ThreeDInfoRequest;
import com.aurfy.haze.service.bean.todo.ThreeDRequest;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;

/**
 * for e-bank and express-pay, etc.
 * 
 * @author hermano
 *
 */
public interface BankService extends HazeService {

	/**
	 * <p>
	 * create a bank token for further usage.<br />
	 * some e-bank (i.e.: ABC - Agriculture Bank of China) needs two steps for processing: <br/>
	 * <ol>
	 * <li>create a token</li>
	 * <li>use the token to process the order</li>
	 * </ol>
	 * </p>
	 * 
	 * @param request
	 *            request to create the token in the bank
	 * @return
	 */
	public Token createToken(CreateTokenRequest request);

	/**
	 * create a e-bank form with everything the bank need inside (for browser redirection usage)
	 * 
	 * @param request
	 * @return
	 */
	public EBankForm createForm(CreateFormRequest request);

	// #####################################################################

	/**
	 * first step to make a 3D request: call the bank (visa/master/JCB) to get the 3D server information, etc.
	 * 
	 * @param request
	 * @return
	 */
	public ThreeDInfo create3D(ThreeDInfoRequest request);

	/**
	 * async method to send 3D request to the bank. <br/>
	 * internal listeners will be triggered to handle the response.<br />
	 * use the returned reference object to trace the process result.
	 * 
	 * @param request
	 * @return
	 */
	public ProcessReference do3D(ThreeDRequest request);

	/**
	 * <p>
	 * process the express pay request.<br/>
	 * internally create the bank order via different channel properties, and sent to queue for further processing.<br/>
	 * use the returned reference object to trace the process result.
	 * </p>
	 * 
	 * @param channel
	 * @param request
	 * @return
	 */
	public ProcessReference doExpressPay(ExpressPayRequest request, ChannelBean channel) throws ServiceException,
			RuntimeServiceException;

	// #####################################################################

	/**
	 * query the bank process result.
	 * 
	 * @param reference
	 * @return
	 */
	public PayResponse queryResult(ProcessReference reference) throws ServiceException, RuntimeServiceException;

	
	/**
	 * query the bank process result by order id.
	 * @param orderId
	 * @return
	 */
	public PayResponse queryResultByOrderId(String orderId);

}
