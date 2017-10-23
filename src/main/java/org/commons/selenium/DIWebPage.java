package org.commons.selenium;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.commons.Interface.IDIWebPage;
import org.commons.constants.DIConstants;
import org.commons.models.DIWebElements;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DIWebPage extends DIWebDriver implements IDIWebPage{

	public DIWebPage() {

	}
	

	public void waitUntilElementAvailable(final DIWebElements guiElement) {
		waitUntilElementAvailable(guiElement, DIConstants.DEFAULT_TIMEOUT, DIConstants.DEFAULT_INVESTIGATION_TIME);
	}

	public void waitUntilElementAvailable(final DIWebElements guiElement, long timeoutDuration, long pollingDuration) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
				.withTimeout(timeoutDuration, TimeUnit.MILLISECONDS)
				.pollingEvery(pollingDuration, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return getElement(guiElement);
			}
		});
	}

	public void waitUntilElementNotAvailable(final DIWebElements guiElement) {
		waitUntilElementNotAvailable(guiElement, DIConstants.DEFAULT_TIMEOUT, DIConstants.DEFAULT_INVESTIGATION_TIME);
	}

	public void waitUntilElementNotAvailable(final DIWebElements guiElement, long timeoutDuration,
			long pollingDuration) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
				.withTimeout(timeoutDuration, TimeUnit.MILLISECONDS)
				.pollingEvery(pollingDuration, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(DIWebPageElement.getBy(guiElement)));
	}

	public boolean isElementPresent(DIWebElements guiElement) {
		return isElementPresent(guiElement, DIConstants.DEFAULT_TIMEOUT);
	}

	public boolean isElementPresent(DIWebElements guiElement, long timeoutDuration) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), timeoutDuration);
			wait.until(ExpectedConditions.visibilityOf(getElement(guiElement)));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public WebElement getElement(DIWebElements guiElement) {
		return DIWebPageElement.findElement(getDriver(), guiElement);
	}


	
}
