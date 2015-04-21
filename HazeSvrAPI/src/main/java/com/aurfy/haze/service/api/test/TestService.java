package com.aurfy.haze.service.api.test;

import com.aurfy.haze.core.model.tools.CipherAlgorithmEnum;
import com.aurfy.haze.service.api.HazeService;

public interface TestService extends HazeService {

	public String testString(String str);

	public CipherAlgorithmEnum testEnum(CipherAlgorithmEnum policy);
}
