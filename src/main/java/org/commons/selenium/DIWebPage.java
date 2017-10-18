package org.commons.selenium;

import java.nio.file.Paths;

import org.commons.constants.DIConstants;
import org.commons.properties.DIProperties;

public class DIWebPage extends DIWebDriver {

	

	public void load() {
		DIProperties.getInstance().setPropertyPath(Paths.get(DIConstants.PROPERTIES_PATH, DIConstants.PROPERTIES_FILE_PATH).toString());
		driver.get(DIProperties.getInstance().getProperty(DIConstants.URL_PROPERTY));
	}

	public void load(String url) {
		driver.get(url);
	}
	
	public void quit() {
		driver.quit();
	}

}
