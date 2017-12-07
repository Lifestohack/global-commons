package test.commons.global.selenium;

import org.commons.selenium.DIPageFactory;
import org.openqa.selenium.WebDriver;

public class TestProject extends DIPageFactory {

	public TestProject(WebDriver driver) {
		super(driver);
	}

	public void loadUrl() {
		getPage(Page1.class).loadurl();
	}

	public void goToAboutUsPage() {
		getPage(Page1.class).goToAboutUsPage();
	}

}
