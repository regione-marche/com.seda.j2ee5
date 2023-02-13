/**
 * 
 */
package com.seda.j2ee5.mdb.spi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.ejb.MessageDrivenBean;
import javax.jms.MessageListener;

import com.seda.commons.logger.LoggerBaseHandler;
/**
 * @author dbadm
 *
 */
public abstract class MessageDrivenBean21ContextHandler extends LoggerBaseHandler implements MessageListener, MessageDrivenBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2597286217093436394L;
	
	/* =================================================== */	
	// Load and save a log4j hierarchy for this application
	/* =================================================== */
	protected final void configureLogger(String log4jPropertiesPath) {
		//configure log4j from the provided properties path		
		try {
			configureLoggerServer(log4jPropertiesPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	protected final void configureLogger(Properties log4jProperties) {
		configureLoggerServer(log4jProperties);
	}		
	
}
