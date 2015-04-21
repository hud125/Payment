package com.aurfy.haze.core.model.system;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

/**
 * System Module Name.
 * 
 * @author hermano
 *
 */
@UseEnumTypeHandler
public enum SystemModuleEnum {

	/**
	 * Infrastructure
	 */
	INFRASTRUCTURE,

	/**
	 * Configuration
	 */
	CONFIGURATION,

	/**
	 * Security
	 */
	SECURITY,

	/**
	 * Transaction
	 */
	TRANSACTION,

	/**
	 * Settlement
	 */
	SETTLEMENT,

	/**
	 * Report
	 */
	REPORT,

	/**
	 * Risk
	 */
	RISK,

	/**
	 * Sender
	 */
	SENDER,

	/**
	 * Receiver
	 */
	RECEIVER,

	/**
	 * Notification
	 */
	NOTIFICATION

}
