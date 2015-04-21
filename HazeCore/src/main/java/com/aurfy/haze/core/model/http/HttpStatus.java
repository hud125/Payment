package com.aurfy.haze.core.model.http;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

@UseEnumTypeHandler(value = "com.aurfy.haze.dao.handler.HttpStatusTypeHandler")
public class HttpStatus implements Serializable {

	private static Map<String, HttpStatus> nameMap = new HashMap<String, HttpStatus>(53);
	private static Map<String, HttpStatus> codeMap = new HashMap<String, HttpStatus>(53);

	public static final HttpStatus CONTINUE = new HttpStatus("CONTINUE", "100", "Continue");
	public static final HttpStatus SWITCHING_PROTOCOLS = new HttpStatus("SWITCHING_PROTOCOLS", "101",
			"Switching Protocols");
	public static final HttpStatus PROCESSING = new HttpStatus("PROCESSING", "102", "Processing");
	public static final HttpStatus CHECKPOINT = new HttpStatus("CHECKPOINT", "103", "Checkpoint");
	public static final HttpStatus OK = new HttpStatus("OK", "200", "OK");
	public static final HttpStatus CREATED = new HttpStatus("CREATED", "201", "Created");
	public static final HttpStatus ACCEPTED = new HttpStatus("ACCEPTED", "202", "Accepted");
	public static final HttpStatus NON_AUTHORITATIVE_INFORMATION = new HttpStatus("NON_AUTHORITATIVE_INFORMATION", "",
			"Non-Authoritative Information");
	public static final HttpStatus NO_CONTENT = new HttpStatus("NO_CONTENT", "", "204");
	public static final HttpStatus RESET_CONTENT = new HttpStatus("RESET_CONTENT", "205", "Reset Content");
	public static final HttpStatus PARTIAL_CONTENT = new HttpStatus("PARTIAL_CONTENT", "206", "Partial Content");
	public static final HttpStatus MULTI_STATUS = new HttpStatus("MULTI_STATUS", "207", "Multi-Status");
	public static final HttpStatus ALREADY_REPORTED = new HttpStatus("ALREADY_REPORTED", "208", "Already Reported");
	public static final HttpStatus IM_USED = new HttpStatus("IM_USED", "226", "IM Used");
	public static final HttpStatus MULTIPLE_CHOICES = new HttpStatus("MULTIPLE_CHOICES", "300", "Multiple Choices");
	public static final HttpStatus MOVED_PERMANENTLY = new HttpStatus("MOVED_PERMANENTLY", "301", "Moved Permanently");
	public static final HttpStatus FOUND = new HttpStatus("FOUND", "302", "Found");
	public static final HttpStatus SEE_OTHER = new HttpStatus("SEE_OTHER", "303", "See Other");
	public static final HttpStatus NOT_MODIFIED = new HttpStatus("NOT_MODIFIED", "304", "Not Modified");
	public static final HttpStatus USE_PROXY = new HttpStatus("USE_PROXY", "305", "Use Proxy");
	public static final HttpStatus TEMPORARY_REDIRECT = new HttpStatus("TEMPORARY_REDIRECT", "307",
			"Temporary Redirect");
	public static final HttpStatus PERMANENT_REDIRECT = new HttpStatus("PERMANENT_REDIRECT", "308",
			"Permanent Redirect");
	public static final HttpStatus BAD_REQUEST = new HttpStatus("BAD_REQUEST", "400", "Bad Request");
	public static final HttpStatus UNAUTHORIZED = new HttpStatus("UNAUTHORIZED", "401", "Unauthorized");
	public static final HttpStatus PAYMENT_REQUIRED = new HttpStatus("PAYMENT_REQUIRED", "402", "Payment Required");
	public static final HttpStatus FORBIDDEN = new HttpStatus("FORBIDDEN", "403", "Forbidden");
	public static final HttpStatus NOT_FOUND = new HttpStatus("NOT_FOUND", "404", "Not Found");
	public static final HttpStatus METHOD_NOT_ALLOWED = new HttpStatus("METHOD_NOT_ALLOWED", "405",
			"Method Not Allowed");
	public static final HttpStatus NOT_ACCEPTABLE = new HttpStatus("NOT_ACCEPTABLE", "406", "Not Acceptable");
	public static final HttpStatus PROXY_AUTHENTICATION_REQUIRED = new HttpStatus("PROXY_AUTHENTICATION_REQUIRED",
			"407", "Proxy Authentication Required");
	public static final HttpStatus REQUEST_TIMEOUT = new HttpStatus("REQUEST_TIMEOUT", "408", "Request Timeout");
	public static final HttpStatus CONFLICT = new HttpStatus("CONFLICT", "409", "Conflict");
	public static final HttpStatus GONE = new HttpStatus("GONE", "410", "Gone");
	public static final HttpStatus LENGTH_REQUIRED = new HttpStatus("LENGTH_REQUIRED", "411", "Length Required");
	public static final HttpStatus PRECONDITION_FAILED = new HttpStatus("PRECONDITION_FAILED", "412",
			"Precondition Failed");
	public static final HttpStatus PAYLOAD_TOO_LARGE = new HttpStatus("PAYLOAD_TOO_LARGE", "413", "Payload Too Large");
	public static final HttpStatus URI_TOO_LONG = new HttpStatus("URI_TOO_LONG", "414", "URI Too Long");
	public static final HttpStatus UNSUPPORTED_MEDIA_TYPE = new HttpStatus("HttpStatus", "415",
			"Unsupported Media Type");
	public static final HttpStatus REQUESTED_RANGE_NOT_SATISFIABLE = new HttpStatus("REQUESTED_RANGE_NOT_SATISFIABLE",
			"416", "Requested range not satisfiable");
	public static final HttpStatus EXPECTATION_FAILED = new HttpStatus("EXPECTATION_FAILED", "417",
			"Expectation Failed");
	public static final HttpStatus I_AM_A_TEAPOT = new HttpStatus("I_AM_A_TEAPOT", "418", "I'm a teapot");
	public static final HttpStatus INSUFFICIENT_SPACE_ON_RESOURCE = new HttpStatus("INSUFFICIENT_SPACE_ON_RESOURCE",
			"419", "Insufficient Space On Resource");
	public static final HttpStatus METHOD_FAILURE = new HttpStatus("METHOD_FAILURE", "420", "Method Failure");
	public static final HttpStatus DESTINATION_LOCKED = new HttpStatus("DESTINATION_LOCKED", "421",
			"Destination Locked");
	public static final HttpStatus UNPROCESSABLE_ENTITY = new HttpStatus("UNPROCESSABLE_ENTITY", "422",
			"Unprocessable Entity");
	public static final HttpStatus LOCKED = new HttpStatus("LOCKED", "423", "Locked");
	public static final HttpStatus FAILED_DEPENDENCY = new HttpStatus("FAILED_DEPENDENCY", "424", "Failed Dependency");
	public static final HttpStatus UPGRADE_REQUIRED = new HttpStatus("UPGRADE_REQUIRED", "426", "Upgrade Required");
	public static final HttpStatus PRECONDITION_REQUIRED = new HttpStatus("PRECONDITION_REQUIRED", "428",
			"Precondition Required");
	public static final HttpStatus TOO_MANY_REQUESTS = new HttpStatus("TOO_MANY_REQUESTS", "429", "Too Many Requests");
	public static final HttpStatus REQUEST_HEADER_FIELDS_TOO_LARGE = new HttpStatus("REQUEST_HEADER_FIELDS_TOO_LARGE",
			"431", "Request Header Fields Too Large");
	public static final HttpStatus INTERNAL_SERVER_ERROR = new HttpStatus("INTERNAL_SERVER_ERROR", "500",
			"Internal Server Error");
	public static final HttpStatus NOT_IMPLEMENTED = new HttpStatus("NOT_IMPLEMENTED", "501", "Not Implemented");
	public static final HttpStatus BAD_GATEWAY = new HttpStatus("BAD_GATEWAY", "502", "Bad Gateway");
	public static final HttpStatus SERVICE_UNAVAILABLE = new HttpStatus("SERVICE_UNAVAILABLE", "503",
			"Service Unavailable");
	public static final HttpStatus GATEWAY_TIMEOUT = new HttpStatus("GATEWAY_TIMEOUT", "504", "Gateway Timeout");
	public static final HttpStatus HTTP_VERSION_NOT_SUPPORTED = new HttpStatus("HTTP_VERSION_NOT_SUPPORTED", "505",
			"HTTP Version not supported");
	public static final HttpStatus VARIANT_ALSO_NEGOTIATES = new HttpStatus("VARIANT_ALSO_NEGOTIATES", "506",
			"Variant Also Negotiates");
	public static final HttpStatus INSUFFICIENT_STORAGE = new HttpStatus("INSUFFICIENT_STORAGE", "507",
			"Insufficient Storage");
	public static final HttpStatus LOOP_DETECTED = new HttpStatus("LOOP_DETECTED", "508", "Loop Detected");
	public static final HttpStatus BANDWIDTH_LIMIT_EXCEEDED = new HttpStatus("BANDWIDTH_LIMIT_EXCEEDED", "509",
			"Bandwidth Limit Exceeded");
	public static final HttpStatus NOT_EXTENDED = new HttpStatus("NOT_EXTENDED", "510", "Not Extended");
	public static final HttpStatus NETWORK_AUTHENTICATION_REQUIRED = new HttpStatus("NETWORK_AUTHENTICATION_REQUIRED",
			"511", "Network Authentication Required");

	private String name;

	private String code;

	private String description;

	public HttpStatus(String name, String code, String description) {
		this.name = name;
		this.code = code;
		this.description = description;
		nameMap.put(name, this);
		codeMap.put(code, this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static HttpStatus parseByName(String name) {
		return nameMap.get(name);
	}

	public static HttpStatus parseByCode(String code) {
		return codeMap.get(code);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HttpStatus other = (HttpStatus) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getCode();
	}

}
