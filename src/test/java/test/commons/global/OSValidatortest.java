package test.commons.global;

import static org.junit.Assert.*;

import org.commons.global.OSValidator;
import org.junit.Test;

public class OSValidatortest {

	@Test
	public void test() {

		String os = OSValidator.osName();
		assertEquals("Windows 7",os);
	}

}
