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
import org.commons.files.DIFiles;
import org.commons.logger.DILogger;

public class DIProperties {
	private InputStream input = null;
	private static final Logger logger = Logger.getLogger(DILogger.class.getName());
	private static DIProperties instance = null; 
	private Properties property = null;
	private Properties propertyValue = null;
	
	public static DIProperties getInstance(){
		if(instance == null){
			instance =  new DIProperties();
		}
		return instance;
		
	}
	
	private DIProperties() {
		property = initiate(Paths.get(DIConstants.PROPERTIES_PATH, DIConstants.PROPERTIES_FILE_PATH).toString());
		propertyValue = initiate(Paths.get(DIConstants.PROPERTIES_PATH, DIConstants.PROPERTIES_FILE_PATH + DIConstants.PROPERTIES_VALUE_FILE_PATH).toString());
	}
	
	private Properties initiate(String path){
		Properties prop = new Properties();
		input = DIProperties.class.getClassLoader().getResourceAsStream(path);
		try {
			logger.info("Reading the property file:" + path);	

				input = new FileInputStream(path);
		
			try {
				prop.load(input);
			} catch (IOException e) {
				logger.severe(e.toString());
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
				logger.info(path + " is not found.");
				e.printStackTrace();		
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return prop;
	}
	
	public String getProperty(String key){
		String value = property.getProperty(key);
		String valueSign = value.substring(0, 1);
		if(valueSign == "$" || valueSign.equals("$")){
			value =getFunctionalProperty(key, value);
		}
		return value;
	}
	
	private String getFunctionalProperty(String key, String value){
		if(propertyValue == null){
			return value;
		}else{
			return propertyValue.getProperty(key + "." + value);
		}
		
	}
	
	public void setProperty(String key, String value){
		property.setProperty(key, value);
	}
	
	public Path getPropertyPath(){
		return Paths.get(DIConstants.PROPERTIES_PATH, DIConstants.PROPERTIES_FILE_PATH);
	}
	
	public void setPropertyPath(String path){
		property = initiate(path);
		propertyValue.clear();
		propertyValue = null;
		String pathValue = path + DIConstants.PROPERTIES_VALUE_FILE_PATH;
		if(DIFiles.isValidFile(pathValue)){
			propertyValue = initiate(pathValue);
		}
	}
	
	public void setPropertyValuePath(String path){
		propertyValue = initiate(path);
	}
	
	

}
