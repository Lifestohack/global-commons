package org.commons.selenium;

import org.commons.models.DIWebElements;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DIWebPageElement {

	public static By getBy(DIWebElements guiElement) {
		if (guiElement.getLocateType().equalsIgnoreCase("id")) {
			if (guiElement.getElementValue().startsWith("//")) {
				System.err.println("elementValue contains potential xpath");
			}
			return By.id(guiElement.getElementValue());
		} else if (guiElement.getLocateType().equalsIgnoreCase("name")) {
			return By.name(guiElement.getElementValue());
		} else if (guiElement.getLocateType().equalsIgnoreCase("linktext")) {
			return By.linkText(guiElement.getElementValue());
		} else if (guiElement.getLocateType().equalsIgnoreCase("xpath")) {
			return By.xpath(guiElement.getElementValue());
		} else if (guiElement.getLocateType().equalsIgnoreCase("css")) {
			return By.cssSelector(guiElement.getElementValue());
		} else if (guiElement.getLocateType().equalsIgnoreCase(
				"partiallinktext")) {
			return By.partialLinkText(guiElement.getElementValue());
		} else if (guiElement.getLocateType().equalsIgnoreCase("tagn")) {
			return By.tagName(guiElement.getElementValue());
		} else if (guiElement.getLocateType().equalsIgnoreCase("class")) {
			return By.className(guiElement.getElementValue());
		} else if (guiElement.getLocateType().equalsIgnoreCase("xpath")) {
			return By.xpath("//*[contains(@id,'" + guiElement.getElementValue()
					+ "')]");
		}
		return null;
	}

	public static WebElement findElement(WebDriver driver, DIWebElements guiElement) {
		WebElement element = null;
		try {
			element = driver.findElement(
					DIWebPageElement.getBy(guiElement));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return element;
	}
}
