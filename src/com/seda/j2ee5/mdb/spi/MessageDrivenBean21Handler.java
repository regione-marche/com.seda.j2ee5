/**
 * 
 */
package com.seda.j2ee5.mdb.spi;

import javax.ejb.EJBException;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;

import com.seda.j2ee5.jndi.JndiProxy;

/**
 * @author dbadm
 *
 */
public abstract class MessageDrivenBean21Handler extends MessageDrivenBean21BaseHandler {
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
	/**
	 * 
	 */
	private static final long serialVersionUID = -2597286217093436394L;

	public abstract void ejbCreate();

	public abstract void ejbRemove();
	
	public abstract void onMessage(Message message);
	
	public abstract void setMessageDrivenContext(MessageDrivenContext arg0) throws EJBException;
}
