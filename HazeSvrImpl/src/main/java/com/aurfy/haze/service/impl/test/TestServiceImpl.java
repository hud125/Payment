package com.aurfy.haze.service.impl.test;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aurfy.haze.core.model.tools.CipherAlgorithmEnum;
import com.aurfy.haze.service.api.test.TestService;
import com.aurfy.haze.service.impl.BaseHazeService;

@Service("testService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestServiceImpl extends BaseHazeService implements TestService {

	public TestServiceImpl() {
	}

	@Override
	public String testString(String str) {
		return str + " replied";
	}

	@Override
	public CipherAlgorithmEnum testEnum(CipherAlgorithmEnum policy) {
		return CipherAlgorithmEnum.SHA3;
	}

}
