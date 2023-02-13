/**
 * 
 */
package com.seda.j2ee5.webservice.spi;

import javax.xml.rpc.ServiceException;

import com.seda.j2ee5.jndi.JndiProxy;
/**
 * @author dbadm
 *
 */
public abstract class JaxRpc10WebServiceHandler extends JaxRpc10ConfigurationHandler  {
	/* ------------------------- */
	// Manage jndiProxy to discover jndi resources 
	/* ------------------------- */	
	private JndiProxy jndiProxy;
	
	private final JndiProxy getJndiProxy() {
		if (jndiProxy==null)
			jndiProxy = new JndiProxy();
		return jndiProxy;
	}

	protected final JndiProxy jndiProxy() {
		return getJndiProxy();
	}
	/* ------------------------- */
	// Implemented Methods from ServiceLifecycle 
	/* ------------------------- */	
	public void init(Object endPointContext) throws ServiceException {
		setServletEndpointContext((javax.xml.rpc.server.ServletEndpointContext)endPointContext);
		setServletContext(getServletEndpointContext().getServletContext());		
	}

	public void destroy() {
		
	}
}
