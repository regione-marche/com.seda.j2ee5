/**
 * 
 */
package com.seda.j2ee5.servlet.spi;

import com.seda.j2ee5.jndi.JndiProxy;

/**
 * @author dbadm
 *
 */
public class ServletHandler extends ServletConfigurationHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2350665589554280863L;

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
	
}
