package test.commons.global.selenium;

public class TestProject {

	Page1 page;

	public TestProject() {
		page = new Page1();
	}

	public void loadUrl() {
		page.loadurl();
	}

	public void goToAboutUsPage() {
		page.goToAboutUsPage();
	}

}
