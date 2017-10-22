package test.commons.global.selenium;

import org.commons.models.DIWebElements;
import org.commons.selenium.SelectorsManger;

public class Page1 extends SelectorsManger{

	public Page1() {
		super(Page1.class);
		// TODO Auto-generated constructor stub
	}

	public DIWebElements diwas, bhattarai;

		
	

	public void loadurl() {
		Load();
	}

	public void goToAboutUsPage() {
		Click(diwas);
	}

}
