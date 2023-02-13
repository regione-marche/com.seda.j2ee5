/**
 * 
 */
package com.seda.j2ee5.listener.spi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.seda.commons.properties.PropertiesLoader;
import com.seda.commons.properties.bundle.PropertiesBundle;
import com.seda.commons.properties.bundle.PropertiesBundleException;
import com.seda.commons.properties.bundle.PropertiesBundlePair;
import com.seda.commons.properties.tree.PropertiesTree;
import com.seda.commons.properties.tree.PropertiesNodeException;

/**
 * @author dbadm
 *
 */
public abstract class ApplicationConfigurationHandler extends ApplicationLogHandler {

	/* ========================================== */	
	// manage Properties Bundle
	/* ========================================== */	
	private PropertiesBundle propertiesBundle;
	
	protected final PropertiesBundle getPropertiesBundle() {
		if (this.propertiesBundle==null) {
			propertiesBundle = new PropertiesBundle();
		}
		return propertiesBundle;
	}
	protected final void setPropertiesBundle(PropertiesBundle propertiesBundle) {
		this.propertiesBundle = propertiesBundle;
	}
	protected final boolean hasPropertiesBundle() {
		return propertiesBundle==null?false:true;			
	}
	
	protected final void configurePropertiesBundle(String ctxAttribute, PropertiesBundlePair... bundlePairs) {
		// load and store external properties file using system variables to get its path
		try {
			getPropertiesBundle().loadProperties(bundlePairs);
		} catch (PropertiesBundleException e) {
			e.printStackTrace();
			return;
		}		
		getServletContext().setAttribute(ctxAttribute, getPropertiesBundle());
	}	
	/* ========================================== */
	// manage Properties Tree
	/* ========================================== */	
	private PropertiesTree propertiesTree;
	
	protected final PropertiesTree propertiesTree() {
		return getPropertiesTree();
	}
	
	private final PropertiesTree getPropertiesTree() {
		return propertiesTree;
	}
	private final void setPropertiesTree(PropertiesTree propertiesTree) {
		this.propertiesTree = propertiesTree;
	}
	protected final boolean hasPropertiesTree() {
		return propertiesTree==null?false:true;			
	}	
	
	protected final void configurePropertiesTree(String ctxAttribute, String propertiesPath) {
		// load and store external properties file 
		try {
			configurePropertiesTree(ctxAttribute, PropertiesLoader.load(propertiesPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	protected final void configurePropertiesTree(String ctxAttribute, Properties properties) {
		try {
			setPropertiesTree(new PropertiesTree(properties));
		} catch (PropertiesNodeException e) {
			e.printStackTrace();
			return;
		}		
		getServletContext().setAttribute(ctxAttribute, getPropertiesTree());
	}
	
	
}
