package com.qa.trcrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pages.HomePage;
import com.qa.trcrm.pages.LoginPage;
import com.qa.trcrm.utils.AppConstants;

public class HomePageTest2 {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;

	@BeforeTest
	@Parameters("browser")
	public void setUp(String browser) {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop,browser);
		loginPage = new LoginPage(driver);

		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		Assert.assertEquals(homePage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void verifyHomePageHeaderTest() {
		Assert.assertEquals(homePage.isHomePageHeaderExist(), AppConstants.HOME_PAGE_HEADER);
	}

	@Test(priority = 3)
	public void verifyLoggedInUserTest() {
		Assert.assertEquals(homePage.isUserLoggedIn(), prop.getProperty("accountname"));
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
