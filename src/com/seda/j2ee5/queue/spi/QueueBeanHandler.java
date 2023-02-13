/**
 * 
 */
package com.seda.j2ee5.queue.spi;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author dbadm
 *
 */
public abstract class QueueBeanHandler implements Serializable {

	private static final long serialVersionUID = 1L;

	private HashMap<String, Properties> hashMap = new HashMap<String, Properties>();

	public QueueBeanHandler(){}

	public final void putProperties(String key, Properties value) {
		hashMap.put(key, value);
	}

	public final Properties getProperties(String key) {
		return hashMap.get(key);
	}

	public final String getProperty(String propertiesKey, String key) {
		return getProperties(propertiesKey).getProperty(key, null);
	}
	
	public final String getProperty(String propertiesKey, String key, String defaultValue) {
		return getProperties(propertiesKey).getProperty(key, defaultValue);
	}	
	
	public final void setProperty(String propertiesKey, String key, String value) {
		getProperties(propertiesKey).setProperty(key, value);
	}	

	public final String property(String propertiesKey, String key) {
		return getProperty(propertiesKey, key, null);
	}
	
	public final String property(String propertiesKey, String key, String defaultValue) {
		return getProperty(propertiesKey, key, defaultValue);
	}	
	
	public final void destroy() {
		this.hashMap = null;
	}
}	
