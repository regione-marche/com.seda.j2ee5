/**
 * 
 */
package com.seda.j2ee5.webservice.spi;

import com.seda.commons.properties.bundle.PropertiesBundle;
import com.seda.commons.properties.tree.PropertiesTree;
/**
 * @author dbadm
 *
 */
public abstract class JaxRpc10ConfigurationHandler extends JaxRpc10LogHandler {

	/* ------------------------- */
	// manage properties bundle
	/* ------------------------- */		
	private PropertiesBundle propertiesBundle;
	private String bundleContextName;
	
	protected String getBundleContextName() {
		return this.bundleContextName==null?"":this.bundleContextName;
	}
	private void setBundleContextName(String bundleContextName) {
		this.bundleContextName = bundleContextName;
	}
	
	protected final PropertiesBundle propertiesBundle(String bundleContextName) {
		// checks for cached logger from the same context
		if (!getBundleContextName().equals(bundleContextName)) {
			getPropertiesBundleFromContext(bundleContextName);
		}
		// if is from the same context 
		return getPropertiesBundle();		
	}
	
	protected final PropertiesBundle propertiesBundle() {
		return getPropertiesBundle();
	}	

	private final void getPropertiesBundleFromContext(String bundleContextName) {
		setBundleContextName(bundleContextName);		
		setPropertiesBundle((PropertiesBundle)getServletContext().getAttribute(bundleContextName));
	}
	
	private final PropertiesBundle getPropertiesBundle() {
		return this.propertiesBundle;
	}	
	
	private final void setPropertiesBundle(PropertiesBundle propertiesBundle) {
		this.propertiesBundle = propertiesBundle;
	}
	protected final boolean hasPropertiesBundle() {
		return propertiesBundle==null?false:true;
	}
	/* ------------------------- */
	// manage properties tree
	/* ------------------------- */		
	private PropertiesTree propertiesTree;
	private String treeContextName;
	
	protected String getTreeContextName() {
		return this.treeContextName==null?"":this.treeContextName;
	}
	private void setTreeContextName(String treeContextName) {
		this.treeContextName = treeContextName;
	}
	
	protected final PropertiesTree propertiesTree(String treeContextName) {
		// checks for cached logger from the same context
		if (!getTreeContextName().equals(treeContextName)) {
			getPropertiesTreeFromContext(treeContextName);			
		}
		// if is from the same context 
		return getPropertiesTree();
	}
	
	protected final PropertiesTree propertiesTree() {
		return getPropertiesTree();
	}	
	
	private final void getPropertiesTreeFromContext(String treeContextName) {
		setTreeContextName(treeContextName);		
		setPropertiesTree((PropertiesTree)getServletContext().getAttribute(treeContextName));
	}
	
	private final PropertiesTree getPropertiesTree() {
		return this.propertiesTree;
	}	
	
	private final void setPropertiesTree(PropertiesTree propertiesTree) {
		this.propertiesTree = propertiesTree;
	}
	protected final boolean hasPropertiesTreeNode() {
		return propertiesTree==null?false:true;
	}	
	
}
