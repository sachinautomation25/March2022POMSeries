package com.qa.trcrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pages.ContactPage;
import com.qa.trcrm.pages.HomePage;
import com.qa.trcrm.pages.LoginPage;
import com.qa.trcrm.utils.AppConstants;
import com.qa.trcrm.utils.ExcelUtil;

public class ContactPageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	ContactPage contactPage;

	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactPage = homePage.contactPage();

	}

	@Test(priority = 1)
	public void verifyContactPageHeaderTest() {
		Assert.assertEquals(contactPage.verifyContactPageHeader(), AppConstants.CONTACTS_PAGE_HEADER);
	}

	@Test(priority = 2,dataProvider = "dp2")
	public void verifyAddPersonTest(String name, String email) {
		String text = contactPage.addPerson(name, email);
		Assert.assertEquals(text, AppConstants.CONTACTS_PERSON_ADDED);
	}

	@DataProvider
	public String[][] dp() {
		String data[][] = new String[2][2];
		data[0][0] = "rahul";
		data[0][1] = "rahul@gmail.com";
		data[1][0] = "deepak";
		data[1][1] = "deepak@gmail.com";

		return data;
	}

	@DataProvider
	public Object[][] dp2() {
		Object data[][] = ExcelUtil.getData();
		return data;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
