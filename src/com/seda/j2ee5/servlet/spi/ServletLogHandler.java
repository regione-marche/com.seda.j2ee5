/**
 * 
 */
package com.seda.j2ee5.servlet.spi;

import org.apache.log4j.Hierarchy;

import com.seda.commons.logger.LoggerHierarchyServer;
import com.seda.commons.logger.LoggerServer;

/**
 * @author dbadm
 *
 */
public class ServletLogHandler extends ServletBaseHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4274518948870830954L;
	/* =========================================== */
	// manage Log4j Hierarchy
	/* =========================================== */	
	private LoggerHierarchyServer loggerHierarchyServer;
	
	protected final LoggerHierarchyServer loggerHierarchyServer() {
		return getLoggerHierarchyServer();
	}
	private final LoggerHierarchyServer getLoggerHierarchyServer() {
		if (loggerHierarchyServer==null) loggerHierarchyServer = new LoggerHierarchyServer();		
		return this.loggerHierarchyServer;
	}		
	
	/* =========================================== */	
	// manage logger server for this application
	/* =========================================== */	
	private LoggerServer loggerServer;
	
	protected final LoggerServer loggerServer() {
		return getLoggerServer();
	}
	private final LoggerServer getLoggerServer() {
		if (loggerServer==null) loggerServer = new LoggerServer();		
		return this.loggerServer;
	}	
	
	/* ------------------------- */
	// manage logger
	/* ------------------------- */	
	private String loggerContextName;
	
	protected String getLoggerContextName() {
		return this.loggerContextName==null?"":this.loggerContextName;
	}
	private void setLoggerContextName(String loggerContextName) {
		this.loggerContextName = loggerContextName;
	}	
	
	protected final void logger(String loggerContextName) {
		// checks for cached logger from the same context
		if (getLoggerContextName().equals(loggerContextName)) {
			// if is from the same context and has logger return 
			return;
		}
		getLoggerFromContext(loggerContextName);
	}
	
	private final void getLoggerFromContext(String loggerContextName) {
		Hierarchy log4jWebHierarchy =
			(Hierarchy) getServletContext().getAttribute(loggerContextName);
		if(log4jWebHierarchy != null) {
			loggerServer().setLogger(log4jWebHierarchy.getLogger(this.getClass().getName()));
			setLoggerContextName(loggerContextName);					
		} else 
			loggerServer().setLogger(null);
	}
		
	/* =========================================== */	
	// simple logger method
	/* =========================================== */	
	protected final void debug(String message) {
		debug(message, null);
	}
	protected final void debug(String message,Throwable t) {
		loggerServer().debug(message,t);
	}
	protected final void info(String message){
		info(message, null);
	}
	protected final void info(String message,Throwable t) {
		loggerServer().info(message,t);
	}
	protected final void warn(String message) {
		warn(message, null);		
	}
	protected final void warn(String message,Throwable t) {
		loggerServer().warn(message,t);						
	}	
	protected final void error(String message) {
		error(message, null);
	}
	protected final void error(String message,Throwable t) {
		loggerServer().error(message,t);
	}
	protected final void fatal(String message) {
		fatal(message, null);
	}
	protected final void fatal(String message,Throwable t) {
		loggerServer().fatal(message,t);
	}		
	
	
	
}
