package test.commons.global.selenium;

import org.commons.models.DIWebElements;
import org.commons.selenium.DISelectorsManager;

public class Page1 extends DISelectorsManager{

	public Page1() {
		super(Page1.class);
		// TODO Auto-generated constructor stub
	}

	public DIWebElements dashboard;

		
	

	public void loadurl() {
		Load();
	}

	public void goToAboutUsPage() {
		Click(dashboard);
	}

}
