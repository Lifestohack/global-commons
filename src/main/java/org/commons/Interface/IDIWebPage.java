package org.commons.Interface;

import org.commons.models.DIWebElements;
import org.openqa.selenium.WebElement;

public interface IDIWebPage {

	public void waitUntilElementAvailable(final DIWebElements guiElement);

	public void waitUntilElementAvailable(final DIWebElements guiElement, long timeoutDuration, long pollingDuration);

	public void waitUntilElementNotAvailable(final DIWebElements guiElement);

	public void waitUntilElementNotAvailable(final DIWebElements guiElement, long timeoutDuration,
			long pollingDuration);

	public boolean isElementPresent(DIWebElements guiElement);

	public boolean isElementPresent(DIWebElements guiElement, long timeoutDuration);

	public WebElement getElement(DIWebElements guiElement);
}
