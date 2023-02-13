/**
 * 
 */
package com.seda.j2ee5.webservice.spi;

import org.apache.log4j.Hierarchy;
/**
 * @author dbadm
 *
 */
public abstract class JaxRpc10LogHandler extends JaxRpc10ContextHandler {

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
		
	
}
