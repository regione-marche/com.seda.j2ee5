/**
 * 
 */
package com.seda.j2ee5.jndi;

import javax.jms.Destination;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.seda.j2ee5.jndi.spi.JndiServer;

/**
 * @author dbadm
 *
 */
public class JndiProxyJmsDestination {

	private JndiServer jndiServer;
	
	private JndiServer getJndiServer() {
		// lazy constructor
		if (jndiServer==null) {
			jndiServer=new JndiServer();
		}
		return jndiServer;
	}	
	
	public Destination getDestination(String securityPrincipal,
			String securityCredentials,
			String initialcontextFactory,
			String providerUrl,
			String jndiName) throws JndiProxyException {

		try {
			InitialContext initialContext = getJndiServer().getInitialContext(securityPrincipal, securityCredentials, 
					initialcontextFactory, providerUrl);
			return getDestination(initialContext, jndiName);
		} catch (NamingException x) {
			throw new JndiProxyException(x);
		}
	}
	
	public Destination getDestination(InitialContext initialContext,
			String jndiName) throws JndiProxyException {

		Destination destination = null;
		try {
			destination = getJndiServer().getDestination(initialContext, jndiName);
		} catch (NamingException x) {
			throw new JndiProxyException(x);
		}
		return destination;
	}		
	
}
