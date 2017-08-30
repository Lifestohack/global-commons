package org.commons.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

import org.commons.constants.DIConstants;
import org.commons.logger.DILogger;

public class DIProperties {
	private InputStream input = null;
	private static final Logger logger = Logger.getLogger(DILogger.class.getName());
	private static DIProperties instance = null; 
	private Properties prop = null;
	
	public static DIProperties getInstance(){
		if(instance == null){
			instance =  new DIProperties();
		}
		return instance;
		
	}
	
	private DIProperties() {
		initiate(null);
	}
	
	private void initiate(String path){
		
		String propertyPath = getPropertyPath().toString();
		
		if(path != null){
			propertyPath = path;
		}
		
		prop = new Properties();
		input = DIProperties.class.getClassLoader().getResourceAsStream(propertyPath);
		try {
			logger.info("Reading the property file:" + propertyPath);	
			if(input==null){
				input = new FileInputStream(propertyPath);
			}
			try {
				prop.load(input);
			} catch (IOException e) {
				logger.severe(e.toString());
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			if(path == null){
				logger.info(propertyPath + " is not found.");
				e.printStackTrace();
			}else{
				
			}
		
		} finally {
			if (input != null) {
				close();
			}
		}
	}
	
	public String getProperty(String key){
		return prop.getProperty(key);
	}
	
	public void setProperty(String key, String value){
		prop.setProperty(key, value);
	}
	
	public Path getPropertyPath(){
		return Paths.get(DIConstants.PROPERTIES_PATH, DIConstants.PROPERTIES_FILE_PATH);
	}
	
	public void setPropertyPath(String path){
		prop.clear();
		initiate(path);
	}

	public void close(){
		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
