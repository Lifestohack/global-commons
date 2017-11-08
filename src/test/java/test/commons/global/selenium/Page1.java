package test.commons.global.selenium;

import org.commons.models.DIWebElements;
import org.commons.selenium.DIWebPageActions;

public class Page1 extends DIWebPageActions{

	public DIWebElements dashboard;

		
	

	public void loadurl() {
		load();
	}

	public void goToAboutUsPage() {
		click(dashboard);
	}

}
