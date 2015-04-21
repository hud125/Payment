package com.aurfy.haze.service.api.risk;

import com.aurfy.haze.core.model.risk.InspectionResult;
import com.aurfy.haze.core.model.risk.RiskRequest;
import com.aurfy.haze.service.api.HazeService;

public interface RiskService extends HazeService {

	/**
	 * run through all the risk rule to evaluate the request and give the risk index
	 * 
	 * @return
	 */
	public InspectionResult check(RiskRequest request);
}
