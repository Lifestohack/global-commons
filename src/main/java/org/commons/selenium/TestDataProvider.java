package org.commons.selenium;

import org.testng.annotations.DataProvider;


public class TestDataProvider {

	@DataProvider(name = "dataProvider")
	 public static Object[][] dataProviderMethod() {
        return new Object[][] { { "data one" } };
    }
}
