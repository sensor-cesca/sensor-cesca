package com.cesca.tools.PropertyManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyManager {
	
	private static PropertyManager instance  = null;
	private static String propertiesFileName = "config.properties";
	private static Properties properties = null;
	private static Properties defaultProperties = null;
	 
	public static PropertyManager getInstance(){
		if (instance == null){
			instance = new PropertyManager();
			instance.loadProperties();
		}
		return instance;
	}
	
	public static void setDefaultProperties(Properties properties){
		defaultProperties = properties;
	}
	
	public static void setProperties(Properties props){
		properties = props;
	}
	
	private void loadProperties(){
		if (properties == null){
			properties = new Properties();
			try {
				InputStream in = new FileInputStream(propertiesFileName);
				properties.load(in);
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	
	
	public String getProperty(String key){
		String value = (String) properties.get(key);
		if (value == null){
			value = (String) defaultProperties.get(key);
		}
		return value;
	}

}
