package org.commons.selenium;

import org.commons.constants.DIConstants;
import org.commons.models.DIWebElements;
import org.commons.properties.DIProperties;
import org.openqa.selenium.By;

public class DIWebPageActions extends DIWebDriver {

	DIWebElements diwas = new DIWebElements();
	DIWebPageElement webPageElement = null;
	
	public DIWebPageActions(){
		webPageElement = new DIWebPageElement();
		diwas.setElementName("a");
		diwas.setElementValue("diwas");
		diwas.setLocateType("id");
	}
	

	public void Load() {
		getDriver().get(
				DIProperties.getInstance()
						.getProperty(DIConstants.URL_PROPERTY));
	}

	public void Load(String url) {
		getDriver().get(url);
	}

	public void Click() {
		webPageElement.findElement(getDriver(), diwas).click();
	}

	public void Quit() {
		getDriver().quit();
	}

}
