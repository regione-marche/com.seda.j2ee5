/**
 * 
 */
package com.seda.j2ee5.listener.spi;

import javax.servlet.ServletContextEvent;

import com.seda.j2ee5.jndi.JndiProxy;
/**
 * @author dbadm
 *
 */
public abstract class ApplicationListenerHandler extends ApplicationConfigurationHandler {

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

	/* ========================================= */
	/* Servlet LifeCycle */
	/* ========================================= */	
	/**
	 * Destroy the context.
	 * @param ServletContextEvent - The {@link ServletContextEvent} object.
	 */
	public void contextDestroyed(ServletContextEvent event){};
	/**
	 * Initialize the Context.
	 * @param ServletContextEvent - The {@link ServletContextEvent} object.
	 */
	public void contextInitialized(ServletContextEvent event){
		// store the ServletContext
		setServletContext(event.getServletContext());		
	};		
}
