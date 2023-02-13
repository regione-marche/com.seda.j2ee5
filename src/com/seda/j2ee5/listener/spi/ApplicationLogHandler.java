/**
 * 
 */
package com.seda.j2ee5.listener.spi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Hierarchy;

import com.seda.commons.properties.PropertiesLoader;

/**
 * @author dbadm
 *
 */
public abstract class ApplicationLogHandler extends ApplicationContextHandler {

	/* =================================================== */	
	// Load and save a log4j hierarchy for this application
	/* =================================================== */
	protected final void configureLogger(String ctxAttribute, String log4jPropertiesPath) {
		//configure log4j from the provided properties path		
		try {
			configureLogger(ctxAttribute, PropertiesLoader.load(log4jPropertiesPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	protected final void configureLogger(String ctxAttribute, Properties log4jProperties) {
		// Define new log4j Hierarchy for application context private use
		Hierarchy log4jWebHierarchy = loggerHierarchyServer().configure(log4jProperties);
		// Save the Hierarchy in the ServletContext
    	getServletContext().setAttribute(ctxAttribute, log4jWebHierarchy);		
    	// Define local logger server
		loggerServer().setLogger(log4jWebHierarchy.getLogger(this.getClass().getName()));
	}	

}
