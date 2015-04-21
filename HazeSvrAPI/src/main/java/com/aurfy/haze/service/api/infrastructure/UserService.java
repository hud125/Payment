package com.aurfy.haze.service.api.infrastructure;

import com.aurfy.haze.service.api.HazeService;
import com.aurfy.haze.service.bean.infra.UserBean;
import com.aurfy.haze.service.bean.infra.UserLoginRequest;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;

public interface UserService extends HazeService {

	public UserBean selectUserByName(String userName) throws ServiceException, RuntimeServiceException;

	public UserBean selectUserByNameAndPasswd(String loginName, String passwdDigest) throws ServiceException,
			RuntimeServiceException;

	public UserBean login(UserLoginRequest request) throws ServiceException, RuntimeServiceException;

	/**
	 * send email for password recovery
	 * @param loginName
	 * @throws ServiceException
	 * invalid email format is fail
	 * @throws RuntimeServiceException
	 * if select user by loginName is null
	 */
	public void sendRecoveryEmail(String loginName) throws ServiceException, RuntimeServiceException;
	/**
	 * validate the password recovery auth key
	 * 
	 * @param authKey
	 * @return <code>true</code> if the key is valid, <code>false</code> for invalid keys.
	 * @throws ServiceException
	 *             if the key is valid but already timeout
	 * @throws RuntimeServiceException
	 */
	public boolean validateRecoveryAuthKey(String userName, String authKey) throws ServiceException, RuntimeServiceException;

	/**
	 * reset password 
	 * @param userName
	 * @param newPassword
	 * @return <code>true</code> if reset is success, <code>false</code> reset is fail.
	 * @throws ServiceException
	 * @throws RuntimeServiceException
	 */
	public boolean resetPassword(String userName, String newPassword) throws ServiceException, RuntimeServiceException;

}
