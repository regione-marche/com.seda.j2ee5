/**
 * 
 */
package com.seda.j2ee5.jndi;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.QueueConnectionFactory;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import com.seda.j2ee5.jndi.spi.JndiServer;

/**
 * @author dbadm
 *
 */
public class JndiProxyJmsConnection {

	private JndiServer jndiServer;
	
	private JndiServer getJndiServer() {
		// lazy constructor
		if (jndiServer==null) {
			jndiServer=new JndiServer();
		}
		return jndiServer;
	}	
	
	/**
	 * Gets a Connection from the ConnectionFactory
	 * 
	 * @param securityPrincipal
	 * @param securityCredentials
	 * @param initialcontextFactory
	 * @param providerUrl
	 * @param jndiName
	 * @param start
	 * @return
	 * @throws JndiProxyException
	 */
	public Connection getConnection(String securityPrincipal,
			String securityCredentials,
			String initialcontextFactory,
			String providerUrl,
			String jndiName, boolean start) throws JndiProxyException {

		try {
			InitialContext initialContext = getJndiServer().getInitialContext(securityPrincipal, securityCredentials, 
					initialcontextFactory, providerUrl);
			return getConnection(initialContext, jndiName, start);
		} catch (NamingException x) {
			throw new JndiProxyException(x);
		}
	}
	
	/**
	 * Gets a Connection from the ConnectionFactory
	 * 
	 * @param initialContext
	 * @param jndiName
	 * @param start
	 * @return
	 * @throws JndiProxyException
	 */
	public Connection getConnection(InitialContext initialContext,
			String jndiName, boolean start) throws JndiProxyException {

		Connection connection = null;
		
		try {
			ConnectionFactory connectionFactory = getJndiServer().getConnectionFactory(initialContext, jndiName);
			connection = connectionFactory.createConnection();
			if (start) connection.start();			
		} catch (NamingException x) {
			throw new JndiProxyException(x);
		} catch (JMSException x) {
			throw new JndiProxyException(x);
		}

		return connection;
	}
	
	/**
	 * Gets a Connection from the QueueConnectionFactory
	 * 
	 * @param securityPrincipal
	 * @param securityCredentials
	 * @param initialcontextFactory
	 * @param providerUrl
	 * @param jndiName
	 * @param start
	 * @return
	 * @throws JndiProxyException
	 */
	public Connection getQueueConnection(String securityPrincipal,
			String securityCredentials,
			String initialcontextFactory,
			String providerUrl,
			String jndiName, boolean start) throws JndiProxyException {

		try {
			InitialContext initialContext = getJndiServer().getInitialContext(securityPrincipal, securityCredentials, 
					initialcontextFactory, providerUrl);
			return getQueueConnection(initialContext, jndiName, start);
		} catch (NamingException x) {
			throw new JndiProxyException(x);
		}
	}
	
	/**
	 * Gets a Connection from the QueueConnectionFactory
	 * 
	 * @param initialContext
	 * @param jndiName
	 * @param start
	 * @return
	 * @throws JndiProxyException
	 */
	public Connection getQueueConnection(InitialContext initialContext,
			String jndiName, boolean start) throws JndiProxyException {

		Connection connection = null;
		
		try {
			QueueConnectionFactory connectionFactory = getJndiServer().getQueueConnectionFactory(initialContext, jndiName);
			connection = connectionFactory.createConnection();
			if (start) connection.start();			
		} catch (NamingException x) {
			throw new JndiProxyException(x);
		} catch (JMSException x) {
			throw new JndiProxyException(x);
		}

		return connection;
	}		
	
	/**
	 * Gets a Connection from the TopicConnectionFactory
	 * 
	 * @param securityPrincipal
	 * @param securityCredentials
	 * @param initialcontextFactory
	 * @param providerUrl
	 * @param jndiName
	 * @param start
	 * @return
	 * @throws JndiProxyException
	 */
	public Connection getTopicConnection(String securityPrincipal,
			String securityCredentials,
			String initialcontextFactory,
			String providerUrl,
			String jndiName, boolean start) throws JndiProxyException {

		try {
			InitialContext initialContext = getJndiServer().getInitialContext(securityPrincipal, securityCredentials, 
					initialcontextFactory, providerUrl);
			return getTopicConnection(initialContext, jndiName, start);
		} catch (NamingException x) {
			throw new JndiProxyException(x);
		}
	}
	
	/**
	 * Gets a Connection from the TopicConnectionFactory
	 * 
	 * @param initialContext
	 * @param jndiName
	 * @param start
	 * @return
	 * @throws JndiProxyException
	 */
	public Connection getTopicConnection(InitialContext initialContext,
			String jndiName, boolean start) throws JndiProxyException {

		Connection connection = null;
		
		try {
			TopicConnectionFactory connectionFactory = getJndiServer().getTopicConnectionFactory(initialContext, jndiName);
			connection = connectionFactory.createConnection();
			if (start) connection.start();			
		} catch (NamingException x) {
			throw new JndiProxyException(x);
		} catch (JMSException x) {
			throw new JndiProxyException(x);
		}

		return connection;
	}			
	
	
}


