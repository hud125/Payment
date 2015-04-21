package com.aurfy.haze.service.api.payment;

import com.aurfy.haze.core.model.payment.PayCredential;
import com.aurfy.haze.service.api.HazeService;
import com.aurfy.haze.service.bean.bank.ProcessReference;
import com.aurfy.haze.service.bean.payment.PayResponse;
import com.aurfy.haze.service.bean.txn.MerTxnBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;

public interface PaymentService extends HazeService {

	/**
	 * <p>
	 * create or update merchant transaction.<br/>
	 * if the same transaction (identified by merId, subMerId, mer order id, order date) already exists and it's status
	 * is already paid, then the operation will be denied. otherwise, just update and overwrite non-empty properties of
	 * the transaction.<br/>
	 * </p>
	 * 
	 * @param txn
	 * @return
	 */
	public MerTxnBean saveMerTxn(MerTxnBean txn) throws ServiceException, RuntimeServiceException;

	/**
	 * do an express pay (async way).
	 * 
	 * @param txn
	 * @param payCredential
	 * @return
	 */
	public ProcessReference doExpressPay(MerTxnBean txn, PayCredential payCredential) throws ServiceException,
			RuntimeServiceException;

	/**
	 * do necessary process upon pay response from {@link com.aurfy.haze.service.api.bank.BankPostProcessService
	 * BankPostProcessService}
	 * 
	 * @param payResponse
	 * @throws ServiceException
	 * @throws RuntimeServiceException
	 */
	public void notifyPayResponse(PayResponse payResponse) throws ServiceException, RuntimeServiceException;

}
