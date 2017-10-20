package test.commons.global;

import org.commons.selenium.DIWebPageActions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Selenium extends DIWebPageActions{

	@Test
	public void Login() {
		
		Load();
		Click();
		Quit();
		
	}

}