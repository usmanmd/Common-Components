package com.redn.connect.logger.config;

import java.io.Serializable;
import java.util.Map;

public interface IConnectLogger extends Serializable {
	
	/**
	 * Debug.
	 *
	 * @param payloadobj the payloadobj
	 * @param headers the headers
	 * @param processState the process state
	 * @param logMsg the log msg
	 */
	void debug(Object payloadobj, Map<String, String> headers, String processState, String logMsg);

	/**
	 * Error.
	 *
	 * @param payloadobj the payloadobj
	 * @param headers the headers
	 * @param processState the process state
	 * @param logMsg the log msg
	 */
	void error(Object payloadobj, Map<String, String> headers, String processState, String logMsg);

	/**
	 * Error.
	 *
	 * @param payloadobj the payloadobj
	 * @param headers the headers
	 * @param processState the process state
	 * @param logMsg the log msg
	 * @param error the error
	 */
	void error(Object payloadobj, Map<String, String> headers, String processState, String logMsg, Throwable error);

	/**
	 * Fatal.
	 *
	 * @param payloadobj the payloadobj
	 * @param headers the headers
	 * @param processState the process state
	 * @param logMsg the log msg
	 */
	void fatal(Object payloadobj, Map<String, String> headers, String processState, String logMsg);

	/**
	 * Fatal.
	 *
	 * @param payloadobj the payloadobj
	 * @param headers the headers
	 * @param processState the process state
	 * @param logMsg the log msg
	 * @param error the error
	 */
	void fatal(Object payloadobj, Map<String, String> headers, String processState, String logMsg, Throwable error);

	/**
	 * Info.
	 *
	 * @param payloadobj the payloadobj
	 * @param headers the headers
	 * @param processState the process state
	 * @param logMsg the log msg
	 */
	void info(Object payloadobj, Map<String, String> headers, String processState, String logMsg);

	/**
	 * Trace.
	 *
	 * @param payloadobj the payloadobj
	 * @param headers the headers
	 * @param processState the process state
	 * @param logMsg the log msg
	 */
	void trace(Object payloadobj, Map<String, String> headers, String processState, String logMsg);

	/**
	 * Warn.
	 *
	 * @param payloadobj the payloadobj
	 * @param headers the headers
	 * @param processState the process state
	 * @param logMsg the log msg
	 */
	void warn(Object payloadobj, Map<String, String> headers, String processState, String logMsg);

	/**
	 * Warn.
	 *
	 * @param payloadobj the payload object
	 * @param headers the headers
	 * @param processState the process state
	 * @param logMsg the log msg
	 * @param error the error
	 */
	void warn(Object payloadobj, Map<String, String> headers, String processState, String logMsg, Throwable error);

	/**
	 * All.
	 *
	 * @param payloadobj the payload object
	 * @param headers the headers
	 * @param processState the process state
	 * @param logMsg the log msg
	 */
	void all(Object payloadobj, Map<String, String> headers, String processState, String logMsg);

	/**
	 * All.
	 *
	 * @param payloadobj the payload object
	 * @param headers the headers
	 * @param processState the process state
	 * @param logMsg the log msg
	 * @param error the error
	 */
	void all(Object payloadobj, Map<String, String> headers, String processState, String logMsg, Throwable error);

	/**
	 * To turn off logging
	 */
	void off();

	/**
	 * Gets the config root.
	 *
	 * @return the config root
	 */
	String getConfigRoot();

	/**
	 * Sets the config root.
	 *
	 * @param configRoot the new config root
	 */
	void setConfigRoot(String configRoot);

	/**
	 * Gets the decryption algorithm.
	 *
	 * @return the decryption algorithm
	 */
	String getDecryptionAlgorithm();

	/**
	 * Sets the decryption algorithm.
	 *
	 * @param decryptionAlgorithm the new decryption algorithm
	 */
	void setDecryptionAlgorithm(String decryptionAlgorithm);

	/**
	 * Gets the decryption password.
	 *
	 * @return the decryption password
	 */
	String getDecryptionPassword();

	/**
	 * Sets the decryption password.
	 *
	 * @param decryptionPassword the new decryption password
	 */
	void setDecryptionPassword(String decryptionPassword);

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	String getLocation();

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	void setLocation(String location);

	/**
	 * 
	 */
	void shutDown();

}
