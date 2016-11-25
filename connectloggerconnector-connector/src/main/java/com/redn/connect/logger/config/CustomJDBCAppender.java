package com.redn.connect.logger.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.LoggingEvent;

public class CustomJDBCAppender extends JDBCAppender {
	
	/** Holds value of property values. */
	private String values;
	/** the prepared statement object **/
	private PreparedStatement stmt = null;
	/** Holds value of property preparedSQL. */
	private String preparedSQL;
	
	/** The conn. */
	// Holds Database connection
	Connection conn;

	/** Creates a new instance of CustomJDBCAppender */
	public CustomJDBCAppender() {
	}

	@Override
	@SuppressWarnings("unchecked")
	public void doAppend(LoggingEvent event) {
		buffer.add(event);
		if (buffer.size() >= bufferSize) {
			flushBuffer();
		}
	}

	/**
	 * overridden method from the JDBCAppender. This method sets the parameters
	 * for the prepared statement before executing the statement
	 **/
	@Override
	public void execute(String sql) throws SQLException {
		Level thresholdLevel = Level.toLevel(this.getThreshold().toString());
		Level loggingEventLevel = Level.toLevel(sql.split(Pattern.quote("#$#$#$#"))[1].trim());
		// suppress the logs for lesser levels and not logging debug level logs
		// to DB
		if (loggingEventLevel.isGreaterOrEqual(Level.INFO) && loggingEventLevel.isGreaterOrEqual(thresholdLevel)) {
			// verify the process state is not null (there by confirm log
			// message is from enterprise logger)
			if (sql.split(Pattern.quote("#$#$#$#"))[13] != null
					&& sql.split(Pattern.quote("#$#$#$#"))[13].trim().length() > 0) {
				PreparedStatement stmt = getPreparedStatement();
				String tokens[] = sql.split("\\#\\$\\#\\$\\#\\$\\#");

				for (int i = 1; i <= tokens.length; i++) {
					stmt.setString(i, tokens[i - 1]);
				}
				stmt.executeUpdate();
			}
		}
	}

	/**
	 * This method obtains the prepared statement object.
	 *
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	private PreparedStatement getPreparedStatement() throws SQLException {
		// reuse the getConnection() method from the super class
		conn = getConnection();
		if (stmt == null) {
			stmt = conn.prepareStatement(getPreparedSQL());
		}
		return stmt;
	}

	/**
	 * Getter for property values.
	 * 
	 * @return Value of property values.
	 */
	public String getValues() {
		return this.values;
	}

	/**
	 * Setter for property values.
	 * 
	 * @param values
	 *            New value of property values.
	 */
	// These values are set from EnterpriseLogger class
	public void setValues(String values) {
		PatternLayout layout = new PatternLayout(values);
		this.setLayout(layout);
		this.values = values;
	}

	/**
	 * Getter for property preparedSQL.
	 * 
	 * @return Value of property preparedSQL.
	 */
	public String getPreparedSQL() {
		return this.preparedSQL;
	}

	/**
	 * Setter for property preparedSQL.
	 * 
	 * @param preparedSQL
	 *            New value of property preparedSQL.
	 */
	public void setPreparedSQL(String preparedSQL) {
		this.preparedSQL = preparedSQL;
	}

	@Override
	public Connection getConnection() throws SQLException {
		try {
			if (null != conn) {
				// If database connection is lost, exception will be thrown
				// to create new connection
				PreparedStatement prepStmt = conn.prepareStatement("select 1");
				prepStmt.executeQuery();
			} else {
				// If connection is not established, create new connection
				conn = super.getConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Create a new connection if exception raised due to connection
			// lost.
			conn = DriverManager.getConnection(ConnectLogger1.setURL, ConnectLogger1.setUser,
					ConnectLogger1.setPassword);
			stmt = null;
		}
		return conn;
	}

	
}
