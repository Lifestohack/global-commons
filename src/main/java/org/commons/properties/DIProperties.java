package org.commons.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.commons.constants.DIConstants;
import org.commons.files.DIFiles;

public class DIProperties {

	private static final Logger logger = LogManager
			.getLogger(DIProperties.class);

	private static DIProperties instance = null;
	private Properties property = null;
	private Properties propertyValue = null;
	private Properties technicalProperty = null;

	public static DIProperties getInstance() {
		if (instance == null) {
			instance = new DIProperties();
		}
		return instance;

	}

	private DIProperties() {
		property = ReadProperty(Paths.get(DIConstants.PROPERTIES_PATH,
				DIConstants.PROPERTIES_FILE_PATH).toString());
		propertyValue = ReadProperty(Paths.get(
				DIConstants.PROPERTIES_PATH,
				DIConstants.PROPERTIES_FILE_PATH
						+ DIConstants.PROPERTIES_VALUE_FILE_PATH).toString());
		technicalProperty = ReadProperty(Paths.get(DIConstants.PROPERTIES_PATH,
				DIConstants.TECHNICAL_PROPERTIES).toString());
	}

	// Getting Property Value

	public String getProperty(String key) {
		String value = property.getProperty(key);
		String valueSign = value.substring(0, 1);
		if (valueSign == "$" || valueSign.equals("$")) {
			value = getFunctionalProperty(key, value);
		}
		return value;
	}

	public String getFunctionalProperty(String key, String value) {
		if (propertyValue == null) {
			return value;
		} else {
			return propertyValue.getProperty(key + "." + value);
		}
	}

	public String getTechnicalProperty(String key) {
		return technicalProperty.getProperty(key);
	}

	// Setting Property Value

	public void setProperty(String key, String value) {
		property.setProperty(key, value);
	}

	public void setFunctionalProperty(String key, String value) {
		propertyValue.setProperty(key, value);
	}

	public void setTechnicalProperty(String key, String value) {
		technicalProperty.setProperty(key, value);
	}

	// Getting Property Path

	public Path getPropertyPath() {
		return Paths.get(DIConstants.PROPERTIES_PATH,
				DIConstants.PROPERTIES_FILE_PATH);
	}

	public Path getPropertyValuePath() {
		return Paths.get(DIConstants.PROPERTIES_PATH,
				DIConstants.PROPERTIES_FILE_PATH
						+ DIConstants.PROPERTIES_VALUE_FILE_PATH);
	}

	public Path getTechnicalPropertyPath() {
		return Paths.get(DIConstants.PROPERTIES_PATH,
				DIConstants.TECHNICAL_PROPERTIES);
	}

	// Setting Property Path

	public void setPropertyPath(String propertypath) {
		property = null;
		property = ReadProperty(propertypath);
		String pathValue = propertypath
				+ DIConstants.PROPERTIES_VALUE_FILE_PATH;
		if (DIFiles.isValidFile(pathValue)) {
			propertyValue = null;
			propertyValue = ReadProperty(pathValue);
		}
	}

	public void setPropertyandValuePath(String propertypath,
			String propertyValuePath) {
		setPropertyPath(propertypath);
		setPropertyValuePath(propertyValuePath);
	}

	public void setPropertyValuePath(String propertyValuePath) {
		propertyValue = ReadProperty(propertyValuePath);
	}

	public void setTechnialPropertyPath(String propertypath) {
		technicalProperty = null;
		technicalProperty = ReadProperty(propertypath);
	}

	public void dispose() {
		if (property != null) {
			property = null;
		}
		if (propertyValue != null) {
			propertyValue = null;
		}
		if (technicalProperty != null) {
			technicalProperty = null;
		}
	}

	public Properties ReadProperty(String path) {
		Properties prop = new Properties();
		InputStream input = DIProperties.class.getClassLoader()
				.getResourceAsStream(path);
		try {
			logger.info("Reading the property file:" + path);

			input = new FileInputStream(path);

			try {
				prop.load(input);
			} catch (IOException e) {
				logger.info(e.toString());
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

}
