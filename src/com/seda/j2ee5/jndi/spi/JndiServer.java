/**
 * 
 */
package com.seda.j2ee5.jndi.spi;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.QueueConnectionFactory;
import javax.jms.TopicConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author dbadm
 *
 */
public final class JndiServer {
	
	private String jndiNamePrefix = "java:comp/env/";

	/* ------------------------------------- */
	// Initial Context propeties
	/* ------------------------------------- */			

	private Properties contextProperties;
	
	private Properties getContextProperties() {
		if (contextProperties==null) {
			contextProperties=new Properties();
		}
		return contextProperties;
	}
	
	/* ------------------------------------- */
	// Initial Context
	/* ------------------------------------- */			
	
	public InitialContext getInitialContext(String initialcontextFactory) throws NamingException {
		return getInitialContext(null,null,initialcontextFactory,null);
	}	
	
	public InitialContext getInitialContext(String securityPrincipal,
			String securityCredentials,
			String initialcontextFactory,
			String providerUrl) throws NamingException {
		
		if (initialcontextFactory==null) {
			return new InitialContext();
		}
		getContextProperties().clear();
		getContextProperties().put(Context.INITIAL_CONTEXT_FACTORY, initialcontextFactory);
		// check for auths
		if (securityPrincipal!=null && securityCredentials!=null) {
			getContextProperties().put(Context.SECURITY_PRINCIPAL, securityPrincipal);  
			getContextProperties().put(Context.SECURITY_CREDENTIALS, securityCredentials);			
		}
		// check for remote jndi directory			
		if (providerUrl!=null) {
			getContextProperties().put(Context.PROVIDER_URL, providerUrl);			
		}
		// return the context
		return new InitialContext(getContextProperties());
	}
	
	/* ------------------------------------- */
	// Sql DataSource
	/* ------------------------------------- */	
	
	public DataSource getDataSource(String initialContextFactory,
			String jndiName) throws NamingException {
		InitialContext initialContext = getInitialContext(initialContextFactory);		
		return getDataSource(initialContext, jndiName);
	}	
	
	public DataSource getDataSource(InitialContext initialContext, 
			String jndiName) throws NamingException {
		return (DataSource)initialContext.lookup(jndiNamePrefix + jndiName);
	}
	
	/* ------------------------------------- */
	// ConnectionFactory
	/* ------------------------------------- */		
	public ConnectionFactory getConnectionFactory(String securityPrincipal,
			String securityCredentials,
			String initialcontextFactory,
			String providerUrl,
			String jndiName) throws NamingException {
		
		InitialContext initialContext = getInitialContext(securityPrincipal, 
				securityCredentials, 
				initialcontextFactory, 
				providerUrl);
		return getConnectionFactory(initialContext, jndiName);
	}	
	public ConnectionFactory getConnectionFactory(InitialContext initialContext,
			String jndiName) throws NamingException {
		return (ConnectionFactory) initialContext.lookup(jndiName);
	}	
	/* ------------------------------------- */
	// Queue ConnectionFactory
	/* ------------------------------------- */		
	public QueueConnectionFactory getQueueConnectionFactory(String securityPrincipal,
			String securityCredentials,
			String initialcontextFactory,
			String providerUrl,
			String jndiName) throws NamingException {
		
		InitialContext initialContext = getInitialContext(securityPrincipal, 
				securityCredentials, 
				initialcontextFactory, 
				providerUrl);
		return getQueueConnectionFactory(initialContext, jndiName);
	}	
	public QueueConnectionFactory getQueueConnectionFactory(InitialContext initialContext,
			String jndiName) throws NamingException {
		return (QueueConnectionFactory) initialContext.lookup(jndiName);
	}
	
	/* ------------------------------------- */
	// Topic ConnectionFactory
	/* ------------------------------------- */		
	public TopicConnectionFactory getTopicConnectionFactory(String securityPrincipal,
			String securityCredentials,
			String initialcontextFactory,
			String providerUrl,
			String jndiName) throws NamingException {
		
		InitialContext initialContext = getInitialContext(securityPrincipal, 
				securityCredentials, 
				initialcontextFactory, 
				providerUrl);
		return getTopicConnectionFactory(initialContext, jndiName);
	}	
	public TopicConnectionFactory getTopicConnectionFactory(InitialContext initialContext,
			String jndiName) throws NamingException {
		return (TopicConnectionFactory) initialContext.lookup(jndiName);
	}	
	
	
	/* ------------------------------------- */
	// Jms Destination
	/* ------------------------------------- */			
	public Destination getDestination(String securityPrincipal,
			String securityCredentials,
			String initialcontextFactory,
			String providerUrl,
			String jndiName) throws NamingException {
		
		InitialContext initialContext = getInitialContext(securityPrincipal, 
				securityCredentials, 
				initialcontextFactory, 
				providerUrl);		
		return getDestination(initialContext, jndiName);
	}
	public Destination getDestination(InitialContext initialContext, 
			String jndiName) throws NamingException {
		return (Destination) initialContext.lookup(jndiName);
	}
	
}
