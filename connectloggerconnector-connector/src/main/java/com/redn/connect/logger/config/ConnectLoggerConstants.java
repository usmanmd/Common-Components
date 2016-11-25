package com.redn.connect.logger.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;

public class ConnectLoggerConstants {
	
	/**
	 * Logger Levels
	 */

	@Deprecated
	public static final String INFO = "INFO";

	/** The Constant DEBUG. */
	@Deprecated
	public static final String DEBUG = "DEBUG";

	/** The Constant WARN. */
	@Deprecated
	public static final String WARN = "WARN";

	/** The Constant ERROR. */
	@Deprecated
	public static final String ERROR = "ERROR";

	/** The Constant TRACE. */
	@Deprecated
	public static final String TRACE = "TRACE";

	/** The Constant FATAL. */
	@Deprecated
	public static final String FATAL = "FATAL";

	/** The Constant ALL. */
	@Deprecated
	public static final String ALL = "ALL";

	/** The Constant OFF. */
	@Deprecated
	public static final String OFF = "OFF";

	/**
	 * MDC Parameters
	 */
	public static final String PROCESS_STATE = "PROCESS_STATE";

	/** The Constant LOGGER_NAME. */
	public static final String LOGGER_NAME = "LOGGER_NAME";

	/** The Constant SERVICE_NAME. */
	public static final String SERVICE_NAME = "SERVICE_NAME";

	/** The Constant NODE_NAME. */
	public static final String NODE_NAME = "NODE_NAME";

	/** The Constant FILE_LOCATION. */
	public static final String FILE_LOCATION = "FILE_LOCATION";

	/** The Constant ERROR_MESSAGE. */
	public static final String ERROR_MESSAGE = "ERROR_MESSAGE";

	/**
	 * Enterprise Message Header MDC Parameters
	 */
	static final String MESSAGE_ID = "MESSAGE_ID";

	/** The Constant SOURCE. */
	static final String SOURCE = "SOURCE";
	
	/** The Constant SOURCE_SYSTEM. */
	static final String SOURCE_SYSTEM = "SOURCE_SYSTEM";

	/** The Constant ACTION. */
	static final String ACTION = "ACTION";

	/** The Constant RESOURCE_NAME. */
	static final String RESOURCE_NAME = "RESOURCE_NAME";
	
	/** The Constant SERVICENAME. */
	static final String SERVICENAME = "SERVICENAME";
	
	/**The Constant MESSAGE_SOURCE. */
	static final String MESSAGE_SOURCE = "MESSAGE_SOURCE";

	/** The Constant RESOURCE_ID. */
	static final String RESOURCE_ID = "RESOURCE_ID";

	/** The Constant SOURCE_ID. */
	static final String SOURCE_ID = "SOURCE_ID";

	/** The Constant MDC_KEYS. */
	static final String[] MDC_KEYS = { MESSAGE_ID, SOURCE, ACTION, SERVICENAME, MESSAGE_SOURCE, SOURCE_ID };

	/** The Constant MDC_KEYS. */
	static final String[] ALL_MDC_KEYS = { SERVICE_NAME, NODE_NAME, MESSAGE_ID, SOURCE, ACTION, SERVICENAME,
			MESSAGE_SOURCE, SOURCE_ID, PROCESS_STATE, ERROR_MESSAGE };
	
	static final String[] CLEAN_MDC_KEYS = { MESSAGE_ID, SOURCE, ACTION, SERVICENAME,
		MESSAGE_SOURCE, SOURCE_ID, PROCESS_STATE, ERROR_MESSAGE };

	/** The Constant HEADER_KEY_MESSAGE_ID. */
	static final String HEADER_KEY_MESSAGE_ID = "messageId";

	/** The Constant HEADER_KEY_MESSAGE_SOURCE. */
	static final String HEADER_KEY_MESSAGE_SOURCE = "messageSource";

	/** The Constant HEADER_KEY_MESSAGE_ACTION. */
	static final String HEADER_KEY_MESSAGE_ACTION = "messageAction";

	/** The Constant HEADER_KEY_RESOURCE_NAME. */
	static final String HEADER_KEY_RESOURCE_NAME = "resourceName";

	/** The Constant HEADER_KEY_RESOURCE_ID. */
	static final String HEADER_KEY_RESOURCE_ID = "resourceId";

	/** The Constant HEADER_KEY_SOURCE_ID. */
	static final String HEADER_KEY_SOURCE_ID = "sourceId";

	/** The Constant HEADER_KEYS. */
	static final String[] HEADER_KEYS = { HEADER_KEY_MESSAGE_ID, HEADER_KEY_MESSAGE_SOURCE, HEADER_KEY_MESSAGE_ACTION,
			HEADER_KEY_RESOURCE_NAME, HEADER_KEY_RESOURCE_ID, HEADER_KEY_SOURCE_ID };

	/** The Constant SESSIONHEADERS_VARS_INJECTION. */
	public static final String SESSIONHEADERS_VARS_INJECTION = HEADER_KEY_MESSAGE_ID + "?," + HEADER_KEY_MESSAGE_SOURCE + "?,"
			+ HEADER_KEY_MESSAGE_ACTION + "?," + HEADER_KEY_RESOURCE_ID + "?," + HEADER_KEY_RESOURCE_NAME + "?,"
			+ HEADER_KEY_SOURCE_ID + "?";

	/** The Constant HEADERKEYS_SIZE. */
	private static final int HEADERKEYS_SIZE = HEADER_KEYS.length;

	/** The Constant HEADER_MAP_2_MDC_KEYS. */
	static final Map<String, String> HEADER_MAP_2_MDC_KEYS = new HashMap<>(HEADERKEYS_SIZE);

	static {
		for (int i = 0; i < HEADER_KEYS.length; i++) {
			String headerKey = HEADER_KEYS[i];
			String mdcKey = MDC_KEYS[i];
			HEADER_MAP_2_MDC_KEYS.put(headerKey, mdcKey);
		}
	}

	/** The Constant ALL_LOG_LEVEL_PACKAGES. */
	@Deprecated
	static final String ALL_LOG_LEVEL_PACKAGES = "es.common.logger.global.packages.level.ALL";

	/** The Constant TRACE_LOG_LEVEL_PACKAGES. */
	@Deprecated
	static final String TRACE_LOG_LEVEL_PACKAGES = "es.common.logger.global.packages.level.TRACE";

	/** The Constant DEBUG_LOG_LEVEL_PACKAGES. */
	@Deprecated
	static final String DEBUG_LOG_LEVEL_PACKAGES = "es.common.logger.global.packages.level.DEBUG";

	/** The Constant INFO_LOG_LEVEL_PACKAGES. */
	@Deprecated
	static final String INFO_LOG_LEVEL_PACKAGES = "es.common.logger.global.packages.level.INFO";

	/** The Constant WARN_LOG_LEVEL_PACKAGES. */
	@Deprecated
	static final String WARN_LOG_LEVEL_PACKAGES = "es.common.logger.global.packages.level.WARN";

	/** The Constant ERROR_LOG_LEVEL_PACKAGES. */
	@Deprecated
	static final String ERROR_LOG_LEVEL_PACKAGES = "es.common.logger.global.packages.level.ERROR";

	/** The Constant FATAL_LOG_LEVEL_PACKAGES. */
	@Deprecated
	static final String FATAL_LOG_LEVEL_PACKAGES = "es.common.logger.global.packages.level.FATAL";

	/** The Constant OFF_LOG_LEVEL_PACKAGES. */
	@Deprecated
	static final String OFF_LOG_LEVEL_PACKAGES = "es.common.logger.global.packages.level.OFF";

	/** The Constant LOG_LEVEL_PACKAGES_PROP_PREFIX. */
	static final String LOG_LEVEL_PACKAGES_PROP_PREFIX = "es.common.logger.global.packages.level.";

	/** The Constant ALL_LEVELS. */
	static final List<Level> ALL_LEVELS = Arrays.asList(new Level[] { Level.ALL, Level.TRACE, Level.DEBUG, Level.INFO,
			Level.WARN, Level.ERROR, Level.FATAL, Level.OFF });

	/** The Constant ES_DEFAULT_MULE_LOGGING_CODE. */
	static final String ES_DEFAULT_MULE_LOGGING_CODE = "es.common.logger.mule.default.logging.code";

	/** The Constant ES_LOGGER_SERVICE_NAME_LOGGER_IMPL_PREFIX. */
	static final String ES_LOGGER_SERVICE_NAME_LOGGER_IMPL_PREFIX = "es.common.logger.impl.for.service.";

	/** The Constant ES_LOGGER_ENTERPRISELOGGER. */
	static final String ES_LOGGER_ENTERPRISELOGGER = "EnterpriseLogger";

	/** The Constant ES_LOGGER_ENTERPRISELOGGER2. */
	static final String ES_LOGGER_ENTERPRISELOGGER2 = "EnterpriseLogger2";

	/** The Constant ENTERPRISE_CONSOLE. */
	static final String ENTERPRISE_CONSOLE = "EnterpriseConsole";

	/** The Constant ENTERPRISE_FILE. */
	static final String ENTERPRISE_FILE = "EnterpriseFile";

	/** The Constant ENTERPRISE_DATABASE. */
	static final String ENTERPRISE_DATABASE = "EnterpriseDatabase";

	/** The Constant LOG_CONFIG_NAME. */
	public static final String LOG_CONFIG_NAME = "es_config_common_logger_module.properties";

	/** The Constant ENV_CONFIGROOT. */
	public static final String ENV_CONFIGROOT = "env.configRoot";

	/** The Constant ENV_LOCATION. */
	public static final String ENV_LOCATION = "env.location";

	/** The Constant ENV_DECRYPTION_ALGORITHM. */
	public static final String ENV_DECRYPTION_ALGORITHM = "env.decryptionAlgorithm";

	/** The Constant ENV_DECRYPTION_PASSWORD. */
	public static final String ENV_DECRYPTION_PASSWORD = "env.decryptionPassword";

	/**
	 * The name of the enterprise logger 2 datasource name
	 * "ComboPooledDataSource" from c3p0
	 */
	static final String ENTERPRISE_LOGGER_DATA_SOURCE = "enterprise.logger.data.source";

	/**
	 * The pool default login timeout in seconds
	 */
	static final int POOL_LOGIN_TIMEOUT_SECONDS = 10;

	/**
	 * The pool default max connection age in seconds
	 */
	static final int POOL_MAX_CONNECTION_AGE_SECONDS = 60;

	/**
	 * The pool default connection test query
	 */
	static final String POOL_TEST_QUERY = "SELECT 1";

	/**
	 * The pool default maximum number of helper threads for resizing
	 */
	static final int POOL_NUM_HELPER_THREADS = 3;

	/**
	 * The pool default max cached prepared statements per connection
	 */
	static final int POOL_MAX_CACHED_STATEMENTS_PER_CONNECTION = 1;

	/**
	 * The pool default max cached prepared statements
	 */
	static final int POOL_MAX_CACHED_STATEMENTS = 100;

	/**
	 * The pool default initial size
	 */
	static final int POOL_INITIAL_SIZE = 1;

	/**
	 * The pool default idle connection test period in seconds
	 */
	static final int POOL_IDLE_TEST_PERIOD_SECONDS = 10;

	/**
	 * The pool default checkout timeout in milliseconds
	 */
	static final int POOL_CHECKOUT_TIMEOUT_MILLIS = 10000;

	/**
	 * The pool default behavior of autocommit on close
	 */
	static final boolean POOL_AUTOCOMMIT_ON_CLOSE = true;

	/**
	 * The pool default retry delay for acquiring a connection
	 */
	static final int POOL_ACQUIRE_RETRY_DELAY_MILLIS = 500;

	/**
	 * The pool default number of retries for acquiring a connection
	 */
	static final int POOL_ACQUIRE_RETRIES = 10;

	/**
	 * The pool default acquire increment
	 */
	static final int POOL_ACQUIRE_INCREMENT = 1;

	/**
	 * The JDBCAppender default buffer size - bigger means less round trips to
	 * database and more bulk JDBC batch inserts improving performance
	 */
	public static final int DB_APPENDER_BUFFER_SIZE = 5;

	/**
	 * The AsyncAppender default buffer size - the size of the array blocking
	 * queue in memory
	 */
	public static final int ASYNC_DB_APPENDER_BUFFER_SIZE = 10000;

	/**
	 * The default number of core threads to use for the asynchronous appender
	 * thread pool
	 */
	public static final int ASYNC_DB_APPENDER_CORE_THREADS = 1;

	/**
	 * The default number of max threads to use for the asynchronous appender
	 * thread pool
	 */
	public static final int ASYNC_DB_APPENDER_MAX_THREADS = 100;

	
}
