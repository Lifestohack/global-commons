package test.commons.global;

import org.commons.selenium.DIWebPage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Selenium extends DIWebPage{

	@Test
	public void Login() {
		
		load();
		quit();
		
	}

}