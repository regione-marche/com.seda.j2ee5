/**
 * 
 */
package com.seda.j2ee5.queue;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.InitialContext;

import com.seda.j2ee5.jndi.JndiProxy;
import com.seda.j2ee5.jndi.JndiProxyException;
/**
 * @author dbadm
 *
 */
public class QueueProducer {

	public final static int CONNECTION = +12;
	public final static int QUEUE_CONNECTION = +6;	
	public final static int TOPIC_CONNECTION = +3;	
	
	private JndiProxy jndiProxy;
	
	private InitialContext initialContext=null;
	private Connection connection=null;
	private Session session=null;
	private Destination destination=null;
	private MessageProducer messageProducer=null;
	private ObjectMessage objectMessage=null;

	private JndiProxy getJndiProxy() {
		// lazy constructor
		if (jndiProxy==null) {
			jndiProxy=new JndiProxy();
		}
		return jndiProxy;
	}
	
	private InitialContext getInitialContext() {
		return initialContext;
	}
	
	private Connection getConnection() {
		return connection;
	}
	
	private Destination getDestination() {
		return destination;
	}	
	
	private MessageProducer getMessageProducer() {
		return messageProducer;
	}	
	
	private ObjectMessage getObjectMessage() {
		return objectMessage;
	}
	
	public QueueProducer(String securityPrincipal, String securityCredentials,
			String initialcontextFactory, String providerUrl) throws JndiProxyException {
		initialContext = getJndiProxy().getContext(securityPrincipal, securityCredentials, 
				initialcontextFactory, providerUrl);
	}
	
	public void startup(String connectionFactoryName, String detinationName) throws QueueProducerException {
		startup(connectionFactoryName, detinationName,QueueProducer.CONNECTION);
	}
	
	public void startup(String connectionFactoryName, String detinationName, int connectionType) throws QueueProducerException {
		try {
			destroy();			
			createConnection(connectionFactoryName, connectionType);
			session = getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
			defineDestination(detinationName);
			messageProducer = session.createProducer(getDestination());
			objectMessage = session.createObjectMessage();			
		} catch (JndiProxyException x) {
			throw new QueueProducerException(x);
		} catch (JMSException x) {
			throw new QueueProducerException(x);
		}
	}	
	
	public void sendText(String t) throws QueueProducerException {
		sendObject(t);
	}
	
	public void sendObject(Serializable o) throws QueueProducerException {
		if (getObjectMessage()==null || getMessageProducer()==null) {
			throw new QueueProducerException(new IllegalArgumentException("Object Message-MessageProducer"));
		}
		try {
			getObjectMessage().setObject(o);
			getMessageProducer().send(getObjectMessage());			
		} catch (JMSException x) {
			throw new QueueProducerException(x);			
		}
	}	
	
	private void createConnection(String connectionFactoryName, int connectionType) throws JndiProxyException {
		switch (connectionType) {
		case QueueProducer.CONNECTION: 
			createConnection(connectionFactoryName, true);			
			break;
		case QueueProducer.QUEUE_CONNECTION: 
			createQueueConnection(connectionFactoryName, true);			
			break;
		case QueueProducer.TOPIC_CONNECTION: 
			createTopicConnection(connectionFactoryName, true);			
			break;						
		default:
			createConnection(connectionFactoryName, true);
		}
	}
	private void createConnection(String connectionFactoryName, boolean start) throws JndiProxyException {
		connection=getJndiProxy().getJmsConnection(getInitialContext(), connectionFactoryName, start);
	}
	private void createQueueConnection(String connectionFactoryName, boolean start) throws JndiProxyException {
		connection=getJndiProxy().getJmsQueueConnection(getInitialContext(), connectionFactoryName, start);
	}
	private void createTopicConnection(String connectionFactoryName, boolean start) throws JndiProxyException {
		connection=getJndiProxy().getJmsTopicConnection(getInitialContext(), connectionFactoryName, start);
	}	
	private void defineDestination(String detinationName) throws JndiProxyException {
		destination = getJndiProxy().getJmsDestination(getInitialContext(), detinationName);
	}

	public void destroy() {
		closeJmsConnection();
		//
		connection = null;
		session = null;
		destination = null;
		messageProducer = null;
		objectMessage = null;		
	}
	
	private void closeJmsConnection() {
		if (connection==null) return;
		try {
			connection.close();
		} catch (JMSException x) {}
	}	
	
}
