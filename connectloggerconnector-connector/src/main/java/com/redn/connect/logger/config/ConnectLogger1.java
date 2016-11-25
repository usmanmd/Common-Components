package com.redn.connect.logger.config;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.PatternLayout;
import org.mule.api.annotations.param.Payload;
import org.springframework.core.io.ClassPathResource;


public class ConnectLogger1 implements IConnectLogger{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logger enterpriseLogger = Logger.getRootLogger();

	/** The service level. */
	private Level serviceLevel = Level.INFO;

	/** The set url. */
	public static String setURL;

	/** The set user. */
	public static String setUser;

	/** The set password. */
	public static String setPassword;

	// Read values from environment variables, typically from wrapper.conf or
	/** The config root. */
	// command line arguments using -Denv.xxx
	String configRoot = System.getProperty(ConnectLoggerConstants.ENV_CONFIGROOT);

	/** The decryption algorithm. */
	String decryptionAlgorithm = System.getProperty(ConnectLoggerConstants.ENV_DECRYPTION_ALGORITHM);

	/** The decryption password. */
	String decryptionPassword = System.getProperty(ConnectLoggerConstants.ENV_DECRYPTION_PASSWORD);

	/** The location. */
	String location = (System.getProperty(ConnectLoggerConstants.ENV_LOCATION) == null
			? ConnectLoggerConstants.LOG_CONFIG_NAME : System.getProperty(ConnectLoggerConstants.ENV_LOCATION));

	/** The common_logger_code. */
	// this logger code will be used for mule common logging
	public static String common_logger_code = null;

	/**
	 * This is EnterpriseLogger constructor which takes logger Name, service
	 * name as inputs.
	 *
	 * @param loggerName
	 *            the logger name
	 * @param serviceName
	 *            the service name
	 */
	public ConnectLogger1(String loggerName, String serviceName) {
		//EnterpriseServiceConfig serviceConfig = null;
		if (null != loggerName) {
			MDC.put(ConnectLoggerConstants.LOGGER_NAME, loggerName);
		}
		if (null != serviceName) {
			MDC.put(ConnectLoggerConstants.SERVICE_NAME, serviceName);
		}

		try {
			MDC.put(ConnectLoggerConstants.NODE_NAME, InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {

			enterpriseLogger.error("Error while getting the NodeName in EnterpriseLogger.java Class", e);
		}
		// Amjad: 20/11/2014
		// Changed to remove null check for decryption properties
		// as service config will function even without these.
	/*	if (null != location) {

		//	serviceConfig = new EnterpriseServiceConfig();
			if (decryptionAlgorithm != null) {
				serviceConfig.setDecryptionAlgorithm(decryptionAlgorithm);
			}

			if (decryptionPassword != null) {
				serviceConfig.setDecryptionPassword(decryptionPassword);
			}

			serviceConfig.setConfigRoot(configRoot);
			serviceConfig.setLocation(new ClassPathResource(location));
		}
		if (null != serviceConfig) {
			Logger.getRootLogger().getLoggerRepository().resetConfiguration();
			String IS_CONSOLE_APPENDER_ENABLE = serviceConfig.get("es.common.logger.enable.appender.console");
			String IS_FILE_APPENDER_ENABLE = serviceConfig.get("es.common.logger.enable.appender.file");
			String IS_DATABASE_APPENDER_ENABLE = serviceConfig.get("es.common.logger.enable.appender.db");
			String SERVICE_LEVEL = serviceConfig.get("es.common.logger.log.level." + serviceName);

			// This default logger code will be used for mule log statements.
			// This can be configured from service config properties.
			common_logger_code = serviceConfig.get(ConnectLoggerConstants.ES_DEFAULT_MULE_LOGGING_CODE);
			if (null != common_logger_code) {
				MDC.put(ConnectLoggerConstants.PROCESS_STATE, common_logger_code);
			}

			if (null != SERVICE_LEVEL) {
				// setting log level for the interface from zuul properties
				serviceLevel = Level.toLevel(serviceConfig.get("es.common.logger.log.level." + serviceName));
			}

			// Set log level to specified packages
			// Set All Log level packages
			String allLevelPackages = serviceConfig.get(ConnectLoggerConstants.ALL_LOG_LEVEL_PACKAGES);
			if (null != allLevelPackages) {
				String strKeyArray[] = allLevelPackages.split(",");
				for (int i = 0; i < strKeyArray.length; i++) {
					Logger.getLogger(strKeyArray[i]).setLevel(Level.ALL);
				}
			}

			// Set Trace Log level packages
			String traceLevelPackages = serviceConfig.get(ConnectLoggerConstants.TRACE_LOG_LEVEL_PACKAGES);
			if (null != traceLevelPackages) {
				String strKeyArray[] = traceLevelPackages.split(",");
				for (int i = 0; i < strKeyArray.length; i++) {
					Logger.getLogger(strKeyArray[i]).setLevel(Level.TRACE);
				}
			}

			// Set Debug Log level packages
			String debugLevelPackages = serviceConfig.get(ConnectLoggerConstants.DEBUG_LOG_LEVEL_PACKAGES);
			if (null != debugLevelPackages) {
				String strKeyArray[] = debugLevelPackages.split(",");
				for (int i = 0; i < strKeyArray.length; i++) {
					Logger.getLogger(strKeyArray[i]).setLevel(Level.DEBUG);
				}
			}

			// Set INFO Log level packages
			String infoLevelPackages = serviceConfig.get(ConnectLoggerConstants.INFO_LOG_LEVEL_PACKAGES);
			if (null != infoLevelPackages) {
				String strKeyArray[] = infoLevelPackages.split(",");
				for (int i = 0; i < strKeyArray.length; i++) {
					Logger.getLogger(strKeyArray[i]).setLevel(Level.INFO);
				}
			}

			// Set Warn Log level packages
			String warnLevelPackages = serviceConfig.get(ConnectLoggerConstants.WARN_LOG_LEVEL_PACKAGES);
			if (null != warnLevelPackages) {
				String strKeyArray[] = warnLevelPackages.split(",");
				for (int i = 0; i < strKeyArray.length; i++) {
					Logger.getLogger(strKeyArray[i]).setLevel(Level.WARN);
				}
			}

			// Set Error Log level packages
			String errorLevelPackages = serviceConfig.get(ConnectLoggerConstants.ERROR_LOG_LEVEL_PACKAGES);
			if (null != errorLevelPackages) {
				String strKeyArray[] = errorLevelPackages.split(",");
				for (int i = 0; i < strKeyArray.length; i++) {
					Logger.getLogger(strKeyArray[i]).setLevel(Level.ERROR);
				}
			}

			// Set Fatal Log level packages
			String fatalLevelPackages = serviceConfig.get(ConnectLoggerConstants.FATAL_LOG_LEVEL_PACKAGES);
			if (null != fatalLevelPackages) {
				String strKeyArray[] = fatalLevelPackages.split(",");
				for (int i = 0; i < strKeyArray.length; i++) {
					Logger.getLogger(strKeyArray[i]).setLevel(Level.FATAL);
				}
			}

			// Set Off Log level packages
			String offLevelPackages = serviceConfig.get(ConnectLoggerConstants.OFF_LOG_LEVEL_PACKAGES);
			if (null != offLevelPackages) {
				String strKeyArray[] = offLevelPackages.split(",");
				for (int i = 0; i < strKeyArray.length; i++) {
					Logger.getLogger(strKeyArray[i]).setLevel(Level.OFF);
				}
			}

			if (null != IS_CONSOLE_APPENDER_ENABLE && IS_CONSOLE_APPENDER_ENABLE.equalsIgnoreCase("true")) {

				String setConsoleConversionPattern = serviceConfig.get("es.common.logger.enable.appender.console.conversion.pattern");
				if (null != setConsoleConversionPattern) {
					setConsoleAppender(setConsoleConversionPattern, serviceLevel);

				}
			}
			if (null != IS_FILE_APPENDER_ENABLE && IS_FILE_APPENDER_ENABLE.equalsIgnoreCase("true")) {
				String setFileConversionPattern = serviceConfig.get("es.common.logger.enable.appender.file.conversion.pattern");
				String setFileDatePattern = serviceConfig.get("es.common.logger.enable.appender.file.date.pattern");
				String setMaxLogFileSize = serviceConfig.get("es.common.logger.enable.appender.file.max.size");
				String maxBackupIndex = serviceConfig.get("es.common.logger.enable.appender.file.max.backup");
				// setting the default number of log file backups to 100, this
				// can be configured from zuul otherwise
				int setMaxBackupIndex = 100;
				if (maxBackupIndex.length() > 0) {
					try {
						setMaxBackupIndex = Integer.parseInt(maxBackupIndex);
					} catch (NumberFormatException numFormatException) {
						enterpriseLogger.log(Level.toLevel(ConnectLoggerConstants.ERROR),
								numFormatException.getMessage());
					}
				}
				String fileLocation = serviceConfig.get("es.common.logger.enable.appender.file.location") + serviceName
						+ ".log";

				if (null != fileLocation && null != setFileDatePattern) {
					setDailyFileAsAppender(fileLocation, setFileConversionPattern, setFileDatePattern,
							setMaxLogFileSize, setMaxBackupIndex, serviceLevel);
				}
			}
			if (null != IS_DATABASE_APPENDER_ENABLE && IS_DATABASE_APPENDER_ENABLE.equalsIgnoreCase("true")) {

				setURL = serviceConfig.get("es.common.logger.enable.appender.db.url");
				String setDriver = serviceConfig.get("es.common.logger.enable.appender.db.driver");
				setUser = serviceConfig.get("es.common.logger.enable.appender.db.user");
				setPassword = serviceConfig.get("es.common.logger.enable.appender.db.password");
				String setpreparedSQLQuery = serviceConfig.get("es.common.logger.enable.appender.db.sql.query");
				String setpreparedSQLValues = serviceConfig.get("es.common.logger.enable.appender.db.sql.query.values");

				if (null != setURL && null != setDriver && null != setUser && null != setPassword
						&& null != setpreparedSQLQuery && null != setpreparedSQLValues) {
					setDatabaseAsAppender(setURL, setDriver, setUser, setPassword, setpreparedSQLQuery,
							setpreparedSQLValues, serviceLevel);
				}

			}
		}
	}*/

	/**
	 * Sets the console appender.
	 *
	 * @param setConsoleAppenderPattern
	 *            the set console appender pattern
	 * @param serviceLevel
	 *            the service level
	 */
	
/*	 * (non-Javadoc)
	 * 
	 * @see com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#
	 * setConsoleAppender(java.lang.String, org.apache.log4j.Level)*/
	 
	private void setConsoleAppender(String setConsoleAppenderPattern, Level serviceLevel) {
		enterpriseLogger.setLevel(Level.TRACE);
		if (enterpriseLogger.getAppender(ConnectLoggerConstants.ENTERPRISE_CONSOLE) == null) {
			// Creating Pattern Layout
			PatternLayout patternLayout = new PatternLayout(setConsoleAppenderPattern);
			ConsoleAppender consoleAppender = new ConsoleAppender(patternLayout);
			consoleAppender.setThreshold(serviceLevel);
			consoleAppender.activateOptions();
			enterpriseLogger.addAppender(consoleAppender);
		}

	}

	/**
	 * Sets the daily file as appender.
	 *
	 * @param fileLocation
	 *            the file location
	 * @param fileConversionPattern
	 *            the file conversion pattern
	 * @param fileDatePattern
	 *            the file date pattern
	 * @param maxLogFileSize
	 *            the max log file size
	 * @param maxBackupIndex
	 *            the max backup index
	 * @param serviceLevel
	 *            the service level
	 */
/*	
	 * (non-Javadoc)
	 * 
	 * @see com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#
	 * setDailyFileAsAppender(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, int, org.apache.log4j.Level)*/
	 
	private void setDailyFileAsAppender(String fileLocation, String fileConversionPattern, String fileDatePattern,
			String maxLogFileSize, int maxBackupIndex, Level serviceLevel) {
		enterpriseLogger.setLevel(Level.TRACE);
		if (enterpriseLogger.getAppender(ConnectLoggerConstants.ENTERPRISE_FILE) == null) {
			PatternLayout patternLayout = new PatternLayout(fileConversionPattern);
			try {
				TimeSizeRollingFileAppender timeSizeRollingFileAppender = new TimeSizeRollingFileAppender(patternLayout,
						fileLocation, fileDatePattern);
				if (maxLogFileSize.length() > 0)
					timeSizeRollingFileAppender.setMaxFileSize(maxLogFileSize);
				timeSizeRollingFileAppender.setThreshold(serviceLevel);
				timeSizeRollingFileAppender.setMaxBackupIndex(maxBackupIndex);
				timeSizeRollingFileAppender.activateOptions();
				enterpriseLogger.addAppender(timeSizeRollingFileAppender);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Sets the database as appender.
	 *
	 * @param dbURL
	 *            the db url
	 * @param dbDriveClass
	 *            the db drive class
	 * @param dbUserName
	 *            the db user name
	 * @param dbPassword
	 *            the db password
	 * @param preparedSQLQuery
	 *            the prepared sql query
	 * @param preparedSQLValues
	 *            the prepared sql values
	 * @param serviceLevel
	 *            the service level
	 */
	
/*	 * (non-Javadoc)
	 * 
	 * @see com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#
	 * setDatabaseAsAppender(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * org.apache.log4j.Level)*/
	 
	private void setDatabaseAsAppender(String dbURL, String dbDriveClass, String dbUserName, String dbPassword,
			String preparedSQLQuery, String preparedSQLValues, Level serviceLevel) {
		enterpriseLogger.setLevel(Level.TRACE);
		if (enterpriseLogger.getAppender(ConnectLoggerConstants.ENTERPRISE_DATABASE) == null) {
			CustomJDBCAppender jdbcAppender = new CustomJDBCAppender();
			jdbcAppender.setURL(dbURL);
			jdbcAppender.setDriver(dbDriveClass);
			jdbcAppender.setUser(dbUserName);
			jdbcAppender.setPassword(dbPassword);
			jdbcAppender.setPreparedSQL(preparedSQLQuery);
			jdbcAppender.setThreshold(serviceLevel);

			String[] values = preparedSQLValues.split(",");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < values.length; i++) {
				sb.append(values[i]);
				sb.append(" ");
				if (i != values.length - 1)
					sb.append("#$#$#$#");
			}
			jdbcAppender.setValues(sb.toString());
			jdbcAppender.activateOptions();
			// setting up enterpriseLogger to DB
			enterpriseLogger.addAppender(jdbcAppender);
		}
	}

	
/*	 * (non-Javadoc)
	 * 
	 * @see
	 * com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#debug(java.
	 * lang.Object, java.util.Map, java.lang.String, java.lang.String)*/
	 
	@Override
	public void debug(Object payloadobj, Map<String, String> headers, String processState, String logMsg) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg) {
			enterpriseLogger.log(Level.toLevel(ConnectLoggerConstants.DEBUG), logMsg);
		}

		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(ConnectLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	
/*	 * (non-Javadoc)
	 * 
	 * @see
	 * com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#error(java.
	 * lang.Object, java.util.Map, java.lang.String, java.lang.String)*/
	 
	@Override
	public void error(Object payloadobj, Map<String, String> headers, String processState, String logMsg) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg) {
			enterpriseLogger.log(Level.toLevel(ConnectLoggerConstants.ERROR), logMsg);
		}

		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(ConnectLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	
/*	 * (non-Javadoc)
	 * 
	 * @see
	 * com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#error(java.
	 * lang.Object, java.util.Map, java.lang.String, java.lang.String,
	 * java.lang.Throwable)*/
	 
	@Override
	public void error(Object payloadobj, Map<String, String> headers, String processState, String logMsg,
			Throwable error) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg && null != error) {
			boolean isErrorThrowable = error instanceof Throwable;
			if (isErrorThrowable) {
				MDC.put(ConnectLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(Level.toLevel(ConnectLoggerConstants.ERROR), logMsg, error);
			} else {
				MDC.put(ConnectLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(Level.toLevel(ConnectLoggerConstants.ERROR), logMsg);
			}
		}
		// to clear the MDC after logging
		// Remove the current error message from MDC. SO that this error message
		// will not be carry forward from next log statements.
		MDC.remove(ConnectLoggerConstants.ERROR_MESSAGE);

		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(ConnectLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

/*	
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#fatal(java.
	 * lang.Object, java.util.Map, java.lang.String, java.lang.String)*/
	 
	@Override
	public void fatal(Object payloadobj, Map<String, String> headers, String processState, String logMsg) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg) {
			enterpriseLogger.log(Level.toLevel(ConnectLoggerConstants.FATAL), logMsg);
		}

		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(ConnectLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	
/*	 * (non-Javadoc)
	 * 
	 * @see
	 * com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#fatal(java.
	 * lang.Object, java.util.Map, java.lang.String, java.lang.String,
	 * java.lang.Throwable)*/
	 
	@Override
	public void fatal(Object payloadobj, Map<String, String> headers, String processState, String logMsg,
			Throwable error) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg && null != error) {
			boolean isErrorThrowable = error instanceof Throwable;
			if (isErrorThrowable) {
				MDC.put(ConnectLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(Level.toLevel(ConnectLoggerConstants.FATAL), logMsg, error);
			} else {
				MDC.put(ConnectLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(Level.toLevel(ConnectLoggerConstants.FATAL), logMsg);
			}
		}
		// to clear the MDC after logging
		// Remove the current error message from MDC. SO that this error message
		// will not be carry forward from next log statements.
		MDC.remove(ConnectLoggerConstants.ERROR_MESSAGE);

		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(ConnectLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	
/*	 * (non-Javadoc)
	 * 
	 * @see
	 * com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#info(java.
	 * lang.Object, java.util.Map, java.lang.String, java.lang.String)
	 */
	@Override
	public void info(Object payloadobj, Map<String, String> headers, String processState, String logMsg) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg) {
			enterpriseLogger.log(Level.toLevel(ConnectLoggerConstants.INFO), logMsg);
		}

		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(ConnectLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	
/*	 * (non-Javadoc)
	 * 
	 * @see
	 * com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#trace(java.
	 * lang.Object, java.util.Map, java.lang.String, java.lang.String)*/
	 
	@Override
	public void trace(Object payloadobj, Map<String, String> headers, String processState, String logMsg) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg) {
			enterpriseLogger.log(Level.toLevel(ConnectLoggerConstants.TRACE), logMsg);
		}

		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(ConnectLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

/*	
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#warn(java.
	 * lang.Object, java.util.Map, java.lang.String, java.lang.String)*/
	 
	@Override
	public void warn(Object payloadobj, Map<String, String> headers, String processState, String logMsg) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg) {
			enterpriseLogger.log(Level.toLevel(ConnectLoggerConstants.WARN), logMsg);
		}

		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(ConnectLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

/*	
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#warn(java.
	 * lang.Object, java.lang.String, java.lang.String, java.lang.Throwable)*/
	 

	
/*	 * (non-Javadoc)
	 * 
	 * @see
	 * com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#warn(java.
	 * lang.Object, java.util.Map, java.lang.String, java.lang.String,
	 * java.lang.Throwable)
	 */
	@Override
	public void warn(Object payloadobj, Map<String, String> headers, String processState, String logMsg,
			Throwable error) {
		putMDCParams(payloadobj, headers, processState);

		if (null != logMsg && null != error) {
			boolean isErrorThrowable = error instanceof Throwable;
			if (isErrorThrowable) {
				MDC.put(ConnectLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(Level.toLevel(ConnectLoggerConstants.WARN), logMsg, error);
			} else {
				MDC.put(ConnectLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(Level.toLevel(ConnectLoggerConstants.WARN), logMsg);
			}
		}
		// to clear the MDC after logging
		// Remove the current error message from MDC. SO that this error message
		// will not be carry forward from next log statements.
		MDC.remove(ConnectLoggerConstants.ERROR_MESSAGE);

		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(ConnectLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	
/*	 * (non-Javadoc)
	 * 
	 * @see
	 * com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#all(java.
	 * lang.Object, java.util.Map, java.lang.String, java.lang.String)*/
	 
	@Override
	public void all(Object payloadobj, Map<String, String> headers, String processState, String logMsg) {
		putMDCParams(payloadobj, headers, processState);
		if (null != logMsg) {
			enterpriseLogger.log(Level.toLevel(ConnectLoggerConstants.ALL), logMsg);
		}

		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(ConnectLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	
/*	 * (non-Javadoc)
	 * 
	 * @see
	 * com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#all(java.
	 * lang.Object, java.util.Map, java.lang.String, java.lang.String,
	 * java.lang.Throwable)*/
	 
	@Override
	public void all(Object payloadobj, Map<String, String> headers, String processState, String logMsg,
			Throwable error) {
		putMDCParams(payloadobj, headers, processState);

		if (null != logMsg && null != error) {
			boolean isErrorThrowable = error instanceof Throwable;
			if (isErrorThrowable) {
				MDC.put(ConnectLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(Level.toLevel(ConnectLoggerConstants.ALL), logMsg, error);
			} else {
				MDC.put(ConnectLoggerConstants.ERROR_MESSAGE, error);
				enterpriseLogger.log(Level.toLevel(ConnectLoggerConstants.ALL), logMsg);
			}
		}
		// to clear the MDC after logging
		// Remove the current error message from MDC. SO that this error message
		// will not be carry forward from next log statements.
		MDC.remove(ConnectLoggerConstants.ERROR_MESSAGE);

		// Set default logger code for mule logging statements
		if (null != common_logger_code) {
			MDC.put(ConnectLoggerConstants.PROCESS_STATE, common_logger_code);
		}
	}

	
/*	 * (non-Javadoc)
	 * 
	 * @see com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#off()*/
	 
	@Override
	public void off() {
		enterpriseLogger.setLevel(Level.OFF);
	}

	/**
	 * Put mdc params.
	 *
	 * @param emObject
	 *            the em object
	 * @param headers
	 *            the headers
	 * @param processState
	 *            the process state
	 */
/*	
	 * (non-Javadoc)
	 * 
	 * @see com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#
	 * putMDCParams(java.lang.Object, java.util.Map, java.lang.String)
	 */
	public void putMDCParams(@Payload Object emObject, Map<String, String> headers, String processState) {
		Map<String, String> payloadMDCParamMap = new HashMap<String, String>();
		if (null != emObject) {

			if (emObject instanceof com.redn.connect.vo.ConnectEnterpriseMessage) {

				com.redn.connect.vo.ConnectEnterpriseMessage enterpriseMessage = (com.redn.connect.vo.ConnectEnterpriseMessage) emObject;
				com.redn.connect.vo.EnterpriseHeader enterpriseHeader = enterpriseMessage.getEnterpriseHeader();
				if (null != enterpriseHeader) {

					if (null != enterpriseHeader.getMessageId()) {
						payloadMDCParamMap.put(ConnectLoggerConstants.MESSAGE_ID, enterpriseHeader.getMessageId());
					}

					if (null != enterpriseHeader.getAction()) {
						payloadMDCParamMap.put(ConnectLoggerConstants.ACTION, enterpriseHeader.getAction());

					}

					if (null != enterpriseHeader.getMessageSource()) {
						payloadMDCParamMap.put(ConnectLoggerConstants.SOURCE, enterpriseHeader.getMessageSource());

					}

					if (null != enterpriseMessage.getEnterpriseHeader()) {
					
						//if (null != enterpriseHeaderResource) {
							if (null != enterpriseHeader.getMessageSource()) {
								payloadMDCParamMap.put(ConnectLoggerConstants.MESSAGE_SOURCE,
										enterpriseMessage.getEnterpriseHeader().getMessageSource());

							}

							if (null != enterpriseHeader.getServiceName()) {
								payloadMDCParamMap.put(ConnectLoggerConstants.SERVICENAME,
										enterpriseMessage.getEnterpriseHeader().getServiceName());
							}

						//}

					}

				}

			} else if (emObject instanceof com.redn.connect.vo.ConnectEnterpriseMessage) {

				com.redn.connect.vo.ConnectEnterpriseMessage enterpriseMessage = (com.redn.connect.vo.ConnectEnterpriseMessage) emObject;
				com.redn.connect.vo.EnterpriseHeader enterpriseHeader = enterpriseMessage.getEnterpriseHeader();
				if (null != enterpriseHeader) {

					if (null != enterpriseHeader.getMessageId()) {
						payloadMDCParamMap.put(ConnectLoggerConstants.MESSAGE_ID, enterpriseHeader.getMessageId());
					}

					if (null != enterpriseHeader.getAction()) {
						payloadMDCParamMap.put(ConnectLoggerConstants.ACTION, enterpriseHeader.getAction());

					}

					if (null != enterpriseHeader.getSourceSystem()) {
						payloadMDCParamMap.put(ConnectLoggerConstants.SOURCE, enterpriseHeader.getSourceSystem());

					}

					if (null != enterpriseHeader.getMessageSource()) {
						
						//if (null != enterpriseHeaderResource) {
							if (null != enterpriseHeader.getMessageSource()) {
								payloadMDCParamMap.put(ConnectLoggerConstants.MESSAGE_SOURCE,
										enterpriseHeader.getMessageSource());

							}

							if (null != enterpriseHeader.getServiceName()) {
								payloadMDCParamMap.put(ConnectLoggerConstants.SERVICENAME,
										enterpriseHeader.getServiceName());
							}

					//	}

					}

				}

			} else {
				if (null != headers.get(ConnectLoggerConstants.HEADER_KEY_MESSAGE_ID)) {
					payloadMDCParamMap.put(ConnectLoggerConstants.MESSAGE_ID,
							headers.get(ConnectLoggerConstants.HEADER_KEY_MESSAGE_ID));
				}
				if (null != headers.get(ConnectLoggerConstants.HEADER_KEY_MESSAGE_SOURCE)) {
					payloadMDCParamMap.put(ConnectLoggerConstants.SOURCE,
							headers.get(ConnectLoggerConstants.HEADER_KEY_MESSAGE_SOURCE));
				}
				if (null != headers.get(ConnectLoggerConstants.HEADER_KEY_MESSAGE_ACTION)) {
					payloadMDCParamMap.put(ConnectLoggerConstants.ACTION,
							headers.get(ConnectLoggerConstants.HEADER_KEY_MESSAGE_ACTION));
				}
				if (null != headers.get(ConnectLoggerConstants.HEADER_KEY_RESOURCE_ID)) {
					payloadMDCParamMap.put(ConnectLoggerConstants.MESSAGE_SOURCE,
							headers.get(ConnectLoggerConstants.HEADER_KEY_RESOURCE_ID));
				}
				if (null != headers.get(ConnectLoggerConstants.HEADER_KEY_RESOURCE_NAME)) {
					payloadMDCParamMap.put(ConnectLoggerConstants.SERVICENAME,
							headers.get(ConnectLoggerConstants.HEADER_KEY_RESOURCE_NAME));
				}
				if (null != headers.get(ConnectLoggerConstants.HEADER_KEY_SOURCE_ID)) {
					payloadMDCParamMap.put(ConnectLoggerConstants.SOURCE_ID,
							headers.get(ConnectLoggerConstants.HEADER_KEY_SOURCE_ID));
				}

			}

		}
		if (null != payloadMDCParamMap && payloadMDCParamMap.keySet().size() > 0) {
			Iterator<Entry<String, String>> entries = payloadMDCParamMap.entrySet().iterator();
			while (entries.hasNext()) {
				Entry<String, String> entry = entries.next();
				MDC.put(entry.getKey(), entry.getValue());
			}
		}
		if (null != processState) {
			MDC.put(ConnectLoggerConstants.PROCESS_STATE, processState);
		}

	}

	
/*	 * (non-Javadoc)
	 * 
	 * @see
	 * com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#toString()*/
	 
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	
/*	 * (non-Javadoc)
	 * 
	 * @see com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#
	 * getConfigRoot()*/
	 
	@Override
	public String getConfigRoot() {
		return configRoot;
	}

	
/*	 * (non-Javadoc)
	 * 
	 * @see com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#
	 * setConfigRoot(java.lang.String)*/
	 
	@Override
	public void setConfigRoot(String configRoot) {
		this.configRoot = configRoot;
	}

	
/*	 * (non-Javadoc)
	 * 
	 * @see com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#
	 * getDecryptionAlgorithm()*/
	 
	@Override
	public String getDecryptionAlgorithm() {
		return decryptionAlgorithm;
	}

	
/*	 * (non-Javadoc)
	 * 
	 * @see com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#
	 * setDecryptionAlgorithm(java.lang.String)*/
	 
	@Override
	public void setDecryptionAlgorithm(String decryptionAlgorithm) {
		this.decryptionAlgorithm = decryptionAlgorithm;
	}

	
/*	 * (non-Javadoc)
	 * 
	 * @see com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#
	 * getDecryptionPassword()*/
	 
	@Override
	public String getDecryptionPassword() {
		return decryptionPassword;
	}

	
/*	 * (non-Javadoc)
	 * 
	 * @see com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#
	 * setDecryptionPassword(java.lang.String)*/
	 
	@Override
	public void setDecryptionPassword(String decryptionPassword) {
		this.decryptionPassword = decryptionPassword;
	}

	
/*	 * (non-Javadoc)
	 * 
	 * @see
	 * com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#getLocation
	 * ()*/
	 
	@Override
	public String getLocation() {
		return location;
	}

	
	/* * (non-Javadoc)
	 * 
	 * @see
	 * com.umgi.es.common.util.log.enterpriselogger.EnterpriseLogger#setLocation
	 * (java.lang.String)*/
	 
	@Override
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public void shutDown() {
		//System.out.println("What to do here? LogManager is shared with others!");
	}


}
