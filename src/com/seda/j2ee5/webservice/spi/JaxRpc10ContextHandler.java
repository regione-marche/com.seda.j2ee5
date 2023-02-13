/**
 * 
 */
package com.seda.j2ee5.webservice.spi;

import javax.servlet.ServletContext;
import javax.xml.rpc.server.ServletEndpointContext;

import com.seda.commons.logger.LoggerBaseHandler;

/**
 * @author dbadm
 *
 */
public abstract class JaxRpc10ContextHandler extends LoggerBaseHandler implements javax.xml.rpc.server.ServiceLifecycle {

	/* ------------------------- */	
	// Manage ServletEndpointContext 
	/* ------------------------- */	
	private ServletEndpointContext endPointContext;
	protected final ServletEndpointContext getServletEndpointContext() {
		return this.endPointContext;
	}
	protected final void setServletEndpointContext(ServletEndpointContext endPointContext) {
		this.endPointContext = endPointContext;
	}		
	/* ------------------------- */	
	// Manage ServletContext
	/* ------------------------- */	
	private javax.servlet.ServletContext context;		
	protected final ServletContext getServletContext() {
		return this.context;
	}
	protected final void setServletContext(ServletContext context) {
		this.context = context;
	}		

	
}
