package com.aurfy.haze.service.api;

import java.util.List;

import com.aurfy.haze.core.model.page.Pagination;
import com.aurfy.haze.service.bean.CRUDBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;

/**
 * generic CRUD service for beans that allowed.
 * 
 * @author hermano
 *
 */
public interface CRUDService extends HazeService {

	/**
	 * create a new bean
	 * 
	 * @param bean
	 * @return
	 * @throws ServiceException
	 * @throws RuntimeServiceException
	 */
	public CRUDBean create(CRUDBean bean) throws ServiceException, RuntimeServiceException;

	/**
	 * retrieve a Bean from database by the primary key.
	 * 
	 * @param beanClass
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @throws RuntimeServiceException
	 */
	public CRUDBean retrieve(Class<? extends CRUDBean> beanClass, String id) throws ServiceException,
			RuntimeServiceException;

	/**
	 * search the beans in page
	 * 
	 * @param beanClass
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @throws RuntimeServiceException
	 */
	public Pagination<CRUDBean> retrieve(Class<? extends CRUDBean> beanClass, int pageNum, int pageSize)
			throws ServiceException, RuntimeServiceException;

	/**
	 * retrieve all the data of this bean (without any pagination).
	 * 
	 * @param beanClass
	 * @return
	 * @throws ServiceException
	 * @throws RuntimeServiceException
	 */
	public List<CRUDBean> retrieveAll(Class<? extends CRUDBean> beanClass) throws ServiceException,
			RuntimeServiceException;

	/**
	 * search the beans in page by params.
	 * 
	 * @param beanClass
	 * @return
	 * @throws ServiceException
	 * @throws RuntimeServiceException
	 */
	public Pagination<CRUDBean> retrieveBy(Class<? extends CRUDBean> beanClass, CRUDBean bean, 
			int pageNum, int pageSize) throws ServiceException,
			RuntimeServiceException;
	
	/**
	 * update the bean info
	 * 
	 * @param bean
	 * @return
	 * @throws ServiceException
	 * @throws RuntimeServiceException
	 */
	public CRUDBean update(CRUDBean bean) throws ServiceException, RuntimeServiceException;

	/**
	 * remove a bean from database by the primary key.
	 * 
	 * @param beanClass
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @throws RuntimeServiceException
	 */
	public boolean delete(Class<? extends CRUDBean> beanClass, String id) throws ServiceException,
			RuntimeServiceException;

}
