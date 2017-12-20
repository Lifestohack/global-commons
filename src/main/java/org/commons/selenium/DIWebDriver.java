package org.commons.selenium;

import org.commons.constants.DIConstants;
import org.commons.global.OSValidator;
import org.commons.models.BrowserType;
import org.commons.properties.DIProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DIWebDriver {

	private WebDriver driver = null;
	private DIProperties propertyFile = DIProperties.getInstance();

	public DIWebDriver() {

		String browserType = propertyFile
				.getTechnicalProperty(DIConstants.BROWSER_PROPERTY);

		if (browserType.toLowerCase().equals(
				BrowserType.CHROME.toString().toLowerCase())) {
			Chrome();
		} else if (browserType.toLowerCase().equals(
				BrowserType.FIREFOX.toString().toLowerCase())) {
			Firefox();
		} else if (browserType.toLowerCase().equals(
				BrowserType.INTERNETEXPLORER.toString().toLowerCase())) {
			InternetExplorer();
		} else if (browserType.toLowerCase().equals(
				BrowserType.EDGE.toString().toLowerCase())) {
			Edge();
		} else if (browserType.toLowerCase().equals(
				BrowserType.SAFARI.toString().toLowerCase())) {
			Safari();
		} else if (browserType.toLowerCase().equals(
				BrowserType.OPERA.toString().toLowerCase())) {
			Opera();
		}
	}

	private void Firefox() {
		System.setProperty("webdriver.gecko.driver",
				"drivers\\Firefox\\geckodriver-v0.19.0-win32-1\\geckodriver.exe");
		// File pathToBinary = new File(
		// "C:\\work\\Frameworks\\FirefoxPortable32-56.0.1\\FirefoxPortable\\FirefoxPortable.exe");
		// FirefoxOptions firefoxOptions = new FirefoxOptions();
		// FirefoxBinary firefoxBinary = new FirefoxBinary(pathToBinary);
		// firefoxOptions.setCapability(FirefoxDriver.BINARY, firefoxBinary);
		driver = new FirefoxDriver();

	}

	private void InternetExplorer() throws UnsupportedOperationException {
		
		if(!OSValidator.isWindows()){
			throw new UnsupportedOperationException("Operating System not supported. Please run on Windows.");
		}
		
		System.setProperty("webdriver.ie.driver",
				"drivers\\InternetExplorer\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");

		InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
		internetExplorerOptions
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
		internetExplorerOptions.setCapability(
				InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		internetExplorerOptions.setCapability(
				InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);

		driver = new InternetExplorerDriver(internetExplorerOptions);
	}

	private void Chrome() {
		System.setProperty("webdriver.chrome.driver",
				"drivers\\Chrome\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	private void Edge() {
		System.setProperty("webdriver.edge.driver",
				"drivers\\Edge\\MicrosoftWebDriver.exe");

		driver = new EdgeDriver();
	}

	private void Opera() {
		driver = new OperaDriver();
	}

	private void Safari() {
		driver = new SafariDriver();
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public void closeBrowserOnFocous() {
		driver.close();
	}
	
	public void closeAllBrowser() {
		driver.quit();
	}

}
