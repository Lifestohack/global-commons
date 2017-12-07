package test.commons.global.selenium;

import org.commons.selenium.DISetUp;
import org.commons.selenium.DIWebDriver;

public class TestCases extends DIWebDriver{

	TestProject project;
	
	public TestCases() {
		DISetUp.setUpLogDirectory();
		project = new TestProject(getDriver());
		
	}
	
	public void getAllSelectors() {
		System.out.println();
		
	}
	
	
}
