package com.aurfy.haze.web.controller;

import static com.aurfy.haze.web.spring.rest.RestResponseEntityExceptionHandler.wrapException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aurfy.haze.web.exceptions.WebException;
import com.aurfy.haze.web.model.SampleRequest;
import com.aurfy.haze.web.model.SampleResponse;

@RestController
@RequestMapping(value = "/rest/validate")
public class ValidationController {

	private static final Logger logger = LoggerFactory.getLogger(ValidationController.class);

	public ValidationController() {
	}

	@RequestMapping(value = "/"/*, method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE*/)
	public SampleResponse test(@RequestBody @Validated SampleRequest request) throws WebException {

		logger.debug(request.toString());

		try {
			// do something here
			SampleResponse response = new SampleResponse();
			response.setResult(true);
			return response;
		} catch (Exception e) {
			final String msg = "error while doing test";
			logger.error(msg, e);
			throw wrapException(e, msg);
		}
	}

}
