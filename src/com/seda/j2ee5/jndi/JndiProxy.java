/**
 * 
 */
package com.seda.j2ee5.jndi;

import javax.naming.InitialContext;

/**
 * @author dbadm
 *
 */
public class JndiProxy {

	private JndiProxyContext jndiResourceProxyContext;
	
	private JndiProxyContext getJndiResourceProxyContext() {
		// lazy constructor
		if (jndiResourceProxyContext==null) {
			jndiResourceProxyContext=new JndiProxyContext();
		}
		return jndiResourceProxyContext;
	}		

	private JndiProxySqlConnection jndiResourceProxySqlConnection;
	
	private JndiProxySqlConnection getJndiResourceProxySqlConnection() {
		// lazy constructor
		if (jndiResourceProxySqlConnection==null) {
			jndiResourceProxySqlConnection=new JndiProxySqlConnection();
		}
		return jndiResourceProxySqlConnection;
	}	
	
	private JndiProxyJmsConnection jndiResourceProxyJmsConnection;
	
	private JndiProxyJmsConnection getJndiResourceProxyJmsConnection() {
		// lazy constructor
		if (jndiResourceProxyJmsConnection==null) {
			jndiResourceProxyJmsConnection=new JndiProxyJmsConnection();
		}
		return jndiResourceProxyJmsConnection;
	}	
	
	private JndiProxyJmsDestination jndiResourceProxyJmsDestination;
	
	private JndiProxyJmsDestination getJndiResourceProxyJmsDestination() {
		// lazy constructor
		if (jndiResourceProxyJmsDestination==null) {
			jndiResourceProxyJmsDestination=new JndiProxyJmsDestination();
		}
		return jndiResourceProxyJmsDestination;
	}	

	/* ------------------------------------- */
	// Sql Connection
	/* ------------------------------------- */	
	public java.sql.Connection getSqlConnection(String initialContextFactory, String jndiName, boolean autoCommit) throws JndiProxyException {
		return getJndiResourceProxySqlConnection().getConnection(initialContextFactory, jndiName, autoCommit);
	}	
	
	public java.sql.Connection getSqlConnection(String initialContextFactory, 
			String jndiName, String user, String password, boolean autoCommit) throws JndiProxyException {
		return getJndiResourceProxySqlConnection().getConnection(autoCommit, initialContextFactory, jndiName, user, password);
	}	
	
	public java.sql.Connection getSqlConnection(String jndiName, boolean autoCommit) throws JndiProxyException {
		return getJndiResourceProxySqlConnection().getConnection(autoCommit, null, jndiName, null, null);
	}		
	
	/* ------------------------------------- */
	// Initial context
	/* ------------------------------------- */
	
	public InitialContext getContext(String initialcontextFactory) throws JndiProxyException {
		return getJndiResourceProxyContext().getInitialContext(initialcontextFactory);
	}	
	
	public InitialContext getContext(String securityPrincipal, String securityCredentials,
			String initialcontextFactory, String providerUrl) throws JndiProxyException {
		return getJndiResourceProxyContext().getInitialContext(securityPrincipal, securityCredentials, 
				initialcontextFactory, providerUrl);
	}
	
	/* ------------------------------------- */
	// Jms Connection from ConnectionFactory
	/* ------------------------------------- */	
	public javax.jms.Connection getJmsConnection(String securityPrincipal, String securityCredentials,
			String initialcontextFactory, String providerUrl,
			String jndiName) throws JndiProxyException {
		
		return getJmsConnection(securityPrincipal, securityCredentials, 
				initialcontextFactory, providerUrl, jndiName, true);
	}
	
	public javax.jms.Connection getJmsConnection(String securityPrincipal, String securityCredentials,
			String initialcontextFactory, String providerUrl,
			String jndiName,
			boolean start) throws JndiProxyException {
		
		return getJndiResourceProxyJmsConnection().getConnection(securityPrincipal, securityCredentials, 
				initialcontextFactory, providerUrl, jndiName, start);
		
	}
	
	public javax.jms.Connection getJmsConnection(InitialContext initialContext,
			String jndiName,
			boolean start) throws JndiProxyException {
		
		return getJndiResourceProxyJmsConnection().getConnection(initialContext, jndiName, start);
	}
		
	
	/* ------------------------------------- */
	// Jms Connection from QueueConnectionFactory
	/* ------------------------------------- */	
	public javax.jms.Connection getJmsQueueConnection(String securityPrincipal, String securityCredentials,
			String initialcontextFactory, String providerUrl,
			String jndiName) throws JndiProxyException {
		
		return getJmsQueueConnection(securityPrincipal, securityCredentials, 
				initialcontextFactory, providerUrl, jndiName, true);
	}
	
	public javax.jms.Connection getJmsQueueConnection(String securityPrincipal, String securityCredentials,
			String initialcontextFactory, String providerUrl,
			String jndiName,
			boolean start) throws JndiProxyException {
		
		return getJndiResourceProxyJmsConnection().getQueueConnection(securityPrincipal, securityCredentials, 
				initialcontextFactory, providerUrl, jndiName, start);
		
	}
	
	public javax.jms.Connection getJmsQueueConnection(InitialContext initialContext,
			String jndiName,
			boolean start) throws JndiProxyException {
		
		return getJndiResourceProxyJmsConnection().getQueueConnection(initialContext, jndiName, start);
	}	
	
	/* ------------------------------------- */
	// Jms Connection from TopicConnectionFactory
	/* ------------------------------------- */	
	public javax.jms.Connection getJmsTopicConnection(String securityPrincipal, String securityCredentials,
			String initialcontextFactory, String providerUrl,
			String jndiName) throws JndiProxyException {
		
		return getJmsTopicConnection(securityPrincipal, securityCredentials, 
				initialcontextFactory, providerUrl, jndiName, true);
	}
	
	public javax.jms.Connection getJmsTopicConnection(String securityPrincipal, String securityCredentials,
			String initialcontextFactory, String providerUrl,
			String jndiName,
			boolean start) throws JndiProxyException {
		
		return getJndiResourceProxyJmsConnection().getTopicConnection(securityPrincipal, securityCredentials, 
				initialcontextFactory, providerUrl, jndiName, start);
		
	}
	
	public javax.jms.Connection getJmsTopicConnection(InitialContext initialContext,
			String jndiName,
			boolean start) throws JndiProxyException {
		
		return getJndiResourceProxyJmsConnection().getTopicConnection(initialContext, jndiName, start);
	}		
	
	/* ------------------------------------- */
	// Jms Destination
	/* ------------------------------------- */		
	public javax.jms.Destination getJmsDestination(String securityPrincipal, String securityCredentials,
			String initialcontextFactory, String providerUrl,
			String jndiName) throws JndiProxyException {
		
		return getJndiResourceProxyJmsDestination().getDestination(securityPrincipal, securityCredentials, 
				initialcontextFactory, providerUrl, jndiName);
		
	}
	
	public javax.jms.Destination getJmsDestination(InitialContext initialContext,
			String jndiName) throws JndiProxyException {
		
		return getJndiResourceProxyJmsDestination().getDestination(initialContext, jndiName);
	}	
}
