/**
 * 
 */
package com.seda.j2ee5.jndi;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.seda.j2ee5.jndi.spi.JndiServer;

/**
 * @author dbadm
 *
 */
public class JndiProxySqlConnection {

	private JndiServer jndiServer;
	
	private JndiServer getJndiServer() {
		// lazy constructor
		if (jndiServer==null) {
			jndiServer=new JndiServer();
		}
		return jndiServer;
	}
	
	@Deprecated
	public Connection getConnection(String jndiName, boolean autoCommit) throws JndiProxyException {
		return getConnection(null, jndiName, autoCommit);
	}	
	
	public Connection getConnection(String initialContextFactory, String jndiName, boolean autoCommit) throws JndiProxyException {
		return getConnection(initialContextFactory, jndiName, null, null, autoCommit);
	}	

	@Deprecated
	public Connection getConnection(String jndiName, String user,
			String password, boolean autoCommit) throws JndiProxyException {
		return getConnection(null, jndiName, user, password, autoCommit);
	}
	
	public Connection getConnection(boolean autoCommit, String initialContextFactory, 
			String jndiName, String user, String password) throws JndiProxyException {
		return getConnection(initialContextFactory, jndiName, user, password, autoCommit);
	}	
	
	public Connection getConnection(String initialContextFactory, String jndiName, String user,
			String password, boolean autoCommit) throws JndiProxyException {
		Connection connection = null;		
		try {
			// looking for jndi resource in context factory			
			DataSource dataSource = getJndiServer().getDataSource(initialContextFactory, jndiName);
			// if user and password are specified override existing in application server configuration
			if (user != null && password != null)
				connection = dataSource.getConnection(user,password);
			else 
				connection = dataSource.getConnection();	
			
			connection.setAutoCommit(autoCommit);			
		}
		catch (NamingException x) {
			throw new JndiProxyException(x);
		}catch (SQLException x ) {
			throw new JndiProxyException(x);
		}
		return connection;
	}	
}
