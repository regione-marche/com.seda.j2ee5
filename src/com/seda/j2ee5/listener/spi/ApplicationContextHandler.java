/**
 * 
 */
package com.seda.j2ee5.listener.spi;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;

import com.seda.commons.logger.LoggerBaseHandler;
/**
 * @author dbadm
 *
 */
public abstract class ApplicationContextHandler extends LoggerBaseHandler implements ServletContextListener {

	// Manage Servlet context
	private ServletContext context;	
	protected final ServletContext getServletContext() {
		return this.context;
	}
	protected final void setServletContext(ServletContext context) {
		this.context = context;
	}	
	
}
