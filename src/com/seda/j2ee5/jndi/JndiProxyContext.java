/**
 * 
 */
package com.seda.j2ee5.jndi;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.seda.j2ee5.jndi.spi.JndiServer;

/**
 * @author dbadm
 *
 */
public class JndiProxyContext {

	private JndiServer jndiServer;
	
	private JndiServer getJndiServer() {
		// lazy constructor
		if (jndiServer==null) {
			jndiServer=new JndiServer();
		}
		return jndiServer;
	}	
	
	public InitialContext getInitialContext(String initialcontextFactory) throws JndiProxyException  {
		return getInitialContext(null,null,initialcontextFactory,null);
	}		
	
	public InitialContext getInitialContext(String securityPrincipal,
			String securityCredentials,
			String initialcontextFactory,
			String providerUrl) throws JndiProxyException {
		
		InitialContext initialContext;
		
		try {
			initialContext = getJndiServer().getInitialContext(securityPrincipal, securityCredentials, initialcontextFactory, providerUrl);
		} catch (NamingException x) {
			throw new JndiProxyException(x);
		}

		return initialContext;		
		
	}
}
