package test.commons.global;

import static org.junit.Assert.assertEquals;

import org.commons.properties.DIProperties;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DIPropertiesTest {

	@Test
	public void getPropertyTest() {
		DIProperties diPropertiestest = DIProperties.getInstance();
		String propertyValue = diPropertiestest.getProperty("example1");
		assertEquals("Test", propertyValue);
		diPropertiestest.dispose();
	}

	@Test
	public void setandGetPropertyTest() {
		DIProperties diPropertiestest = DIProperties.getInstance();
		diPropertiestest.setProperty("test2", "$example2");
		String propertyValue = diPropertiestest.getProperty("test2");
		assertEquals(null, propertyValue);
		diPropertiestest.dispose();
	}

	@Test
	public void getFunctionalPropertyTest() {
		DIProperties diPropertiestest = DIProperties.getInstance();
		String functionalPropertyValue = diPropertiestest
				.getProperty("example");
		assertEquals("https://example.com", functionalPropertyValue);
		diPropertiestest.dispose();
	}

	@Test
	public void setPropertyPathandValueTest() {
		DIProperties diPropertiestest = DIProperties.getInstance();
		diPropertiestest.setPropertyandValuePath(
				"src/main/resources/Properties/functional.properties.test",
				null);
		String propertyValue = diPropertiestest.getProperty("example4");
		assertEquals("Test4", propertyValue);
		diPropertiestest.dispose();
	}

	@Test
	public void setPropertyPathTest() {
		DIProperties diPropertiestest = DIProperties.getInstance();
		diPropertiestest
				.setPropertyPath("src/main/resources/Properties/functional.properties.test");
		String propertyValue = diPropertiestest.getProperty("example4");
		assertEquals("Test4", propertyValue);
		diPropertiestest.dispose();
	}

	@Test(expected = NullPointerException.class)
	public void setPropertyPathNullTest() {
		DIProperties diPropertiestest = DIProperties.getInstance();
		try {
			diPropertiestest.setPropertyPath(null);
		} finally {
			diPropertiestest.dispose();
		}
	}

}