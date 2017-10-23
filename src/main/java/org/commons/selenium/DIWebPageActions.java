package org.commons.selenium;

import org.commons.constants.DIConstants;
import org.commons.models.DIWebElements;
import org.commons.properties.DIProperties;

public class DIWebPageActions extends DIWebPage  {

	public DIWebPageActions() {

	}

	public void load() {
		getDriver().get(DIProperties.getInstance().getProperty(DIConstants.URL_PROPERTY));
	}

	public void load(String url) {
		getDriver().get(url);
	}
			
	public void click(DIWebElements guiElement) {
		waitUntilElementAvailable(guiElement);
		getElement(guiElement).click();
	}

	public void quit() {
		getDriver().quit();
	}
}
