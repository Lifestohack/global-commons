package test.commons.global.selenium;

import java.lang.reflect.Method;

import org.commons.models.DIWebElements;
import org.commons.selenium.TestDataProvider;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testng.annotations.Test;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Selenium extends TestCases{

	
	
	@Test(dataProvider = "dataProvider", dataProviderClass = TestDataProvider.class)
	public void Selenium(String testdataName) {
		System.out.print(testdataName);
		project.loadUrl();
		project.goToAboutUsPage();
	}

}