package com.aurfy.haze.service.impl.infra;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aurfy.haze.service.api.infrastructure.AddressService;
import com.aurfy.haze.service.bean.infra.CityBean;
import com.aurfy.haze.service.conf.ServiceImplConstant.AOP_NAME;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.BaseHazeService;

@Service(AOP_NAME.ADDRESS_SERVICE)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AddressServiceImpl extends BaseHazeService implements AddressService {

	@Override
	public CityBean createCity(CityBean city) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
