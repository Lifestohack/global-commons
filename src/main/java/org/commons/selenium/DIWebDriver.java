package org.commons.selenium;

import java.nio.file.Paths;

import org.commons.constants.DIConstants;
import org.commons.models.BrowserType;
import org.commons.properties.DIProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DIWebDriver {

	WebDriver driver = null;
	DIProperties propertyFile = DIProperties.getInstance();

	public DIWebDriver() {
		propertyFile
				.setPropertyPath(Paths.get(DIConstants.PROPERTIES_PATH, DIConstants.TECHNICAL_PROPERTIES).toString());
		String browserType = propertyFile.getProperty(DIConstants.BROWSER_PROPERTY);

		if (browserType.toLowerCase().equals(BrowserType.CHROME.toString().toLowerCase())) {
			Chrome();
		} else if (browserType.toLowerCase().equals(BrowserType.FIREFOX.toString().toLowerCase())) {
			Firefox();
		} else if (browserType.toLowerCase().equals(BrowserType.INTERNETEXPLORER.toString().toLowerCase())) {
			InternetExplorer();
		} else if (browserType.toLowerCase().equals(BrowserType.EDGE.toString().toLowerCase())) {
			Edge();
		} else if (browserType.toLowerCase().equals(BrowserType.SAFARI.toString().toLowerCase())) {
			Safari();
		} else if (browserType.toLowerCase().equals(BrowserType.OPERA.toString().toLowerCase())) {
			Opera();
		}
	}

	private void Firefox() {
		System.setProperty("webdriver.gecko.driver", "drivers\\Firefox\\geckodriver-v0.19.0-win32-1\\geckodriver.exe");
		driver = new FirefoxDriver();
	}

	private void InternetExplorer() {
		System.setProperty("webdriver.ie.driver", "drivers\\InternetExplorer\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
	}

	private void Chrome() {
		 System.setProperty("webdriver.chrome.driver", "drivers\\Chrome\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	private void Edge() {
		 System.setProperty("webdriver.edge.driver", "drivers\\Edge\\MicrosoftWebDriver.exe");
			
		
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

}
