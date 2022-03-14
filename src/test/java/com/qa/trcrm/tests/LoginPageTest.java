package com.qa.trcrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pages.HomePage;
import com.qa.trcrm.pages.LoginPage;
import com.qa.trcrm.utils.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic-101:define login feature for trcrm apllication")
@Feature("US-1001:create features for login with signup,title and login")
public class LoginPageTest {

	BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;

	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
	}

	@Test(priority = 1)
	@Description("verify login page title test")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitleTest() {
		Assert.assertEquals(loginPage.getLoginPageTitle(), AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	@Description("verify sign up link test")
	@Severity(SeverityLevel.CRITICAL)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.verifySignUpLink());
	}

	@Description("verify login test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void loginTest() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homePage.isHomePageHeaderExist(), AppConstants.HOME_PAGE_HEADER);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
