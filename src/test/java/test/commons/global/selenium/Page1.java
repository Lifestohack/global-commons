package test.commons.global.selenium;

import org.commons.models.DIWebElements;
import org.commons.selenium.DIWebPageActions;
import org.openqa.selenium.WebDriver;

public class Page1 extends DIWebPageActions{

	public Page1(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public DIWebElements dashboard;

		
	

	public void loadurl() {
		load();
	}

	public void goToAboutUsPage() {
		click(dashboard);
	}

}
