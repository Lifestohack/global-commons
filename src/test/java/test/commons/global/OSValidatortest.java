package test.commons.global;

import static org.junit.Assert.assertEquals;

import org.commons.global.OSValidator;
import org.junit.Test;

public class OSValidatortest {

	// Test to be run only on windows

	@Test
	public void osNameTest() {

		String os = OSValidator.osName();
		assertEquals("Windows 7", os);
	}

	@Test
	public void osLanguageTest() {

		String osLanguage = OSValidator.osLanguage();
		assertEquals("en", osLanguage);
	}

	@Test
	public void osArchTest() {

		String osArch = OSValidator.osArch();
		assertEquals("amd64", osArch);

	}

	@Test
	public void isWindowTest() {
		boolean iswindow = OSValidator.isWindows();
		assertEquals(true, iswindow);
	}

	@Test
	public void isLinuxTest() {
		boolean isLinux = OSValidator.isLinux();
		assertEquals(false, isLinux);
	}

	@Test
	public void isMacTest() {
		boolean isMac = OSValidator.isMac();
		assertEquals(false, isMac);
	}

	@Test
	public void isSolarisTest() {
		boolean isSolaris = OSValidator.isSolaris();
		assertEquals(false, isSolaris);
	}

	@Test
	public void isSunOsTest() {
		boolean isSunOs = OSValidator.isSunOs();
		assertEquals(false, isSunOs);
	}

	@Test
	public void isUnixTest() {
		boolean isUnix = OSValidator.isUnix();
		assertEquals(false, isUnix);
	}

}
