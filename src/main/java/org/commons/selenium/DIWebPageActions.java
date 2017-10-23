package org.commons.selenium;

import java.lang.reflect.Method;

import org.commons.constants.DIConstants;
import org.commons.models.DIWebElements;
import org.commons.properties.DIProperties;

public class DIWebPageActions extends DIWebDriver {

	DIWebPageElement webPageElement = null;
	
	public DIWebPageActions(){
		webPageElement = new DIWebPageElement();
	}
	

	public void load() {
		getDriver().get(
				DIProperties.getInstance()
						.getProperty(DIConstants.URL_PROPERTY));
	}

	public void load(String url) {
		getDriver().get(url);
	}

	public void click(DIWebElements guiElement) {
		webPageElement.findElement(getDriver(), guiElement).click();
	}

	public void quit() {
		getDriver().quit();
	}

}
