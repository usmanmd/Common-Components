package com.redn.connect.logger;

import java.sql.SQLException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.mule.api.MuleContext;
import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.Payload;
import org.mule.api.annotations.param.SessionHeaders;
import org.mule.api.registry.Registry;

import com.redn.connect.logger.config.ConnectLoggerConstants;
import com.redn.connect.logger.config.ConnectorConfig;
import com.redn.connect.logger.config.IConnectLogger;



@Connector(name="connectloggerconnector", friendlyName="Connectloggerconnector")
public class ConnectloggerconnectorConnector {

/*	*//** The connect logger. *//*
	IConnectLogger cLogger = null;

	*//**
	 * Configurable Service Name
	 *//*
	@org.mule.api.annotations.Configurable
	protected String serviceName;

	*//**
	 * Configurable Logger Name
	 *//*
	@org.mule.api.annotations.Configurable
	protected String loggerName;

	
    @Config
    ConnectorConfig config;

    *//**
     * Custom processor
     *
     * @param friend Name to be used to generate a greeting message.
     * @return A greeting message
     *//*
    @Processor
    public String greet(String friend) {
        
         * MESSAGE PROCESSOR CODE GOES HERE
         
        return config.getGreeting() + " " + friend + ". " + config.getReply();
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }*/

	

	/** The connect logger. */
	IConnectLogger cLogger = null;

	/**
	 * Configurable Service Name
	 */
	@org.mule.api.annotations.Configurable
	protected String serviceName;

	/**
	 * Configurable Logger Name
	 */
	@org.mule.api.annotations.Configurable
	protected String loggerName;

	/**
	 * Gets the logger name.
	 *
	 * @return the logger name
	 */
	public String getLoggerName() {
		return loggerName;
	}

	/**
	 * Sets the logger name.
	 *
	 * @param loggerName            the logger name
	 */
	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}

	/**
	 * Gets the service name.
	 *
	 * @return the service name
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * Sets the service name.
	 *
	 * @param serviceName            the service name
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * Injecting Mule Context for future use
	 */
	@Inject
	protected MuleContext muleContext;
	/**
	 * Injecting Registry for future use
	 */
	@Inject
	protected Registry registry;

	/**
	 * Gets the mule context.
	 *
	 * @return the mule context
	 */
	public MuleContext getMuleContext() {
		return muleContext;
	}

	/**
	 * Sets the mule context.
	 *
	 * @param muleContext            the mule context
	 */
	public void setMuleContext(MuleContext muleContext) {
		this.muleContext = muleContext;
	}

	/**
	 * Gets the registry.
	 *
	 * @return the registry
	 */
	public Registry getRegistry() {
		return registry;
	}

	/**
	 * Sets the registry.
	 *
	 * @param registry            the registry
	 */
	public void setRegistry(Registry registry) {
		this.registry = registry;
	}

	/**
	 * Initializes the logger configuration
	 * 
	 * @throws SQLException
	 *             if connection to database is configured and not possible
	 */
	@PostConstruct
	public void initializeLoggerConfiguration() throws SQLException {


		//EnterpriseServiceConfig serviceConfig = new EnterpriseServiceConfig();
		// Read values from environment variables, typically from wrapper.conf
		// or
		// command line arguments using -Denv.xxx
		String configRoot = System.getProperty(ConnectLoggerConstants.ENV_CONFIGROOT);
		String decryptionAlgorithm = System.getProperty(ConnectLoggerConstants.ENV_DECRYPTION_ALGORITHM);
		String decryptionPassword = System.getProperty(ConnectLoggerConstants.ENV_DECRYPTION_PASSWORD);
		String location = (System.getProperty(ConnectLoggerConstants.ENV_LOCATION) == null
				? ConnectLoggerConstants.LOG_CONFIG_NAME
				: System.getProperty(ConnectLoggerConstants.ENV_LOCATION));

		if (null != location) {

			//serviceConfig = new EnterpriseServiceConfig();
			if (decryptionAlgorithm != null) {
			//	serviceConfig.setDecryptionAlgorithm(decryptionAlgorithm);
			}

			if (decryptionPassword != null) {
			//	serviceConfig.setDecryptionPassword(decryptionPassword);
			}

		//	serviceConfig.setConfigRoot(configRoot);
		//	serviceConfig.setLocation(new ClassPathResource(location));
		}
		String loggerImplementation = null;
	/*	if (serviceConfig != null) {
		//	loggerImplementation = serviceConfig
		//			.get(ConnectLoggerConstants.ES_LOGGER_SERVICE_NAME_LOGGER_IMPL_PREFIX + serviceName);
		}*/

		/*boolean shouldUseLogger2 = loggerImplementation != null
				&& ConnectLoggerConstants.ES_LOGGER_ENTERPRISELOGGER2.equals(loggerImplementation);


		cLogger = shouldUseLogger2 ? new EnterpriseLogger2(loggerName, serviceName)
				: new ConnectLogger1(loggerName, serviceName);
*/
	}
	
	/**
	 * 
	 */
	@PreDestroy
	public void tearDown() {
		cLogger.shutDown();
	}

	/**
	 * {@sample.xml ../../../doc/enterpriselogger-connector.xml.sample
	 * enterpriselogger:debug}
	 * 
	 * @param payloadObject
	 *            is payload refernce injected to pass the enterprise message
	 *            and string´
	 * @param headers
	 *            the context information
	 * @param processState
	 *            is process State passed for current state of process
	 * @param logMessage
	 *            is log message passed to populate log message
	 */
	@Processor
	
	public void debug(@Payload Object payloadObject,
			@SessionHeaders(ConnectLoggerConstants.SESSIONHEADERS_VARS_INJECTION) Map<String, String> headers,
			String processState, String logMessage) {
		cLogger.debug(payloadObject, headers, processState, logMessage);
	}

	/**
	 * {@sample.xml ../../../doc/enterpriselogger-connector.xml.sample
	 * enterpriselogger:error}
	 * 
	 * @param payloadObject
	 *            is payload refernce injected to pass the enterprise message
	 *            and string
	 * @param headers
	 *            the context information
	 * @param processState
	 *            is process State passed for current state of process
	 * @param logMessage
	 *            is log message passed to populate log message
	 */
	@Processor
	
	public void error(@Payload Object payloadObject,
			@SessionHeaders(ConnectLoggerConstants.SESSIONHEADERS_VARS_INJECTION) Map<String, String> headers,
			String processState, String logMessage) {
		cLogger.error(payloadObject, headers, processState, logMessage);
	}

	/**
	 * {@sample.xml ../../../doc/enterpriselogger-connector.xml.sample
	 * enterpriselogger:errorWithThrowable}
	 * 
	 * @param payloadObject
	 *            is payload refernce injected to pass the enterprise message
	 *            and string
	 * @param headers
	 *            the context information
	 * @param processState
	 *            is process State passed for current state of process
	 * @param logMessage
	 *            is log message passed to populate log message
	 * @param error
	 *            is Throwable parameter pass error details
	 */
	@Processor
	
	public void errorWithThrowable(@Payload Object payloadObject,
			@SessionHeaders(ConnectLoggerConstants.SESSIONHEADERS_VARS_INJECTION) Map<String, String> headers,
			String processState, String logMessage, Throwable error) {
		cLogger.error(payloadObject, headers, processState, logMessage, error);
	}

	/**
	 * {@sample.xml ../../../doc/enterpriselogger-connector.xml.sample
	 * enterpriselogger:fatal}
	 * 
	 * @param payloadObject
	 *            is payload refernce injected to pass the enterprise message
	 *            and string
	 * @param headers
	 *            the context information
	 * @param processState
	 *            is process State passed for current state of process
	 * @param logMessage
	 *            is log message passed to populate log message
	 */
	@Processor
	
	public void fatal(@Payload Object payloadObject,
			@SessionHeaders(ConnectLoggerConstants.SESSIONHEADERS_VARS_INJECTION) Map<String, String> headers,
			String processState, String logMessage) {
		cLogger.fatal(payloadObject, headers, processState, logMessage);
	}

	/**
	 * {@sample.xml ../../../doc/enterpriselogger-connector.xml.sample
	 * enterpriselogger:fatalWithThrowable}
	 * 
	 * @param payloadObject
	 *            is payload refernce injected to pass the enterprise message
	 *            and string
	 * @param headers
	 *            the context information
	 * @param processState
	 *            is process State passed for current state of process
	 * @param logMessage
	 *            is log message passed to populate log message
	 * @param error
	 *            is Throwable parameter pass error details
	 */
	@Processor
	
	public void fatalWithThrowable(@Payload Object payloadObject,
			@SessionHeaders(ConnectLoggerConstants.SESSIONHEADERS_VARS_INJECTION) Map<String, String> headers,
			String processState, String logMessage, Throwable error) {
		cLogger.fatal(payloadObject, headers, processState, logMessage, error);
	}

	/**
	 * {@sample.xml ../../../doc/enterpriselogger-connector.xml.sample
	 * enterpriselogger:info}
	 * 
	 * @param payloadObject
	 *            is payload refernce injected to pass the enterprise message
	 *            and string
	 * @param headers
	 *            the context information
	 * @param processState
	 *            is process State passed for current state of process
	 * @param logMessage
	 *            is log message passed to populate log message
	 */
	@Processor
	
	public void info(@Payload Object payloadObject,
			@SessionHeaders(ConnectLoggerConstants.SESSIONHEADERS_VARS_INJECTION) Map<String, String> headers,
			String processState, String logMessage) {
		cLogger.info(payloadObject, headers, processState, logMessage);
	}

	/**
	 * {@sample.xml ../../../doc/enterpriselogger-connector.xml.sample
	 * enterpriselogger:trace}
	 * 
	 * @param payloadObject
	 *            is payload refernce injected to pass the enterprise message
	 *            and string
	 * @param headers
	 *            the context information
	 * @param processState
	 *            is process State passed for current state of process
	 * @param logMessage
	 *            is log message passed to populate log message
	 */
	@Processor
	
	public void trace(@Payload Object payloadObject,
			@SessionHeaders(ConnectLoggerConstants.SESSIONHEADERS_VARS_INJECTION) Map<String, String> headers,
			String processState, String logMessage) {
		cLogger.trace(payloadObject, headers, processState, logMessage);
	}

	/**
	 * {@sample.xml ../../../doc/enterpriselogger-connector.xml.sample
	 * enterpriselogger:warn}
	 * 
	 * @param payloadObject
	 *            is payload refernce injected to pass the enterprise message
	 *            and string
	 * @param headers
	 *            the context information
	 * @param processState
	 *            is process State passed for current state of process
	 * @param logMessage
	 *            is log message passed to populate log message
	 */
	@Processor
	public void warn(@Payload Object payloadObject,
			@SessionHeaders(ConnectLoggerConstants.SESSIONHEADERS_VARS_INJECTION) Map<String, String> headers,
			String processState, String logMessage) {
		cLogger.warn(payloadObject, headers, processState, logMessage);
	}

	/**
	 * {@sample.xml ../../../doc/enterpriselogger-connector.xml.sample
	 * enterpriselogger:warnWithThrowable}
	 * 
	 * @param payloadObject
	 *            is payload refernce injected to pass the enterprise message
	 *            and string
	 * @param headers
	 *            the context information
	 * @param processState
	 *            is process State passed for current state of process
	 * @param logMessage
	 *            is log message passed to populate log message
	 * @param error
	 *            is Throwable parameter pass error details
	 */
	@Processor
	
	public void warnWithThrowable(@Payload Object payloadObject,
			@SessionHeaders(ConnectLoggerConstants.SESSIONHEADERS_VARS_INJECTION) Map<String, String> headers,
			String processState, String logMessage, Throwable error) {
		cLogger.warn(payloadObject, headers, processState, logMessage, error);
	}

	/**
	 * {@sample.xml ../../../doc/enterpriselogger-connector.xml.sample
	 * enterpriselogger:all}
	 * 
	 * @param payloadObject
	 *            is payload refernce injected to pass the enterprise message
	 *            and string
	 * @param headers
	 *            the context information
	 * @param processState
	 *            is process State passed for current state of process
	 * @param logMessage
	 *            is log message passed to populate log message
	 */

	@Processor
	public void all(@Payload Object payloadObject,
			@SessionHeaders(ConnectLoggerConstants.SESSIONHEADERS_VARS_INJECTION) Map<String, String> headers,
			String processState, String logMessage) {
		cLogger.all(payloadObject, headers, processState, logMessage);
	}

	/**
	 * {@sample.xml ../../../doc/enterpriselogger-connector.xml.sample
	 * enterpriselogger:warnWithThrowable}
	 */

	@Processor
	
	public void off() {
		cLogger.off();
	}


}