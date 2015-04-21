/**
 * 
 */
package com.aurfy.haze.service.api.infrastructure;

import com.aurfy.haze.service.api.HazeService;
import com.aurfy.haze.service.bean.infra.CityBean;
import com.aurfy.haze.service.exceptions.ServiceException;

public interface AddressService extends HazeService {
	
	public CityBean createCity(CityBean city) throws ServiceException;
}
