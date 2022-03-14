package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.utils.ElementUtil;

public class LoginPage extends BasePage {

	WebDriver driver;
	HomePage homePage;
	ElementUtil util;

	By username = By.id("_username");
	By password = By.id("_password");
	By loginBtn = By.cssSelector("input[type='submit'][value='Login']");
	By signUpNow = By.linkText("Sign Up Now");

	By homePageHeader = By.xpath("//span[text()='Homepage']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	public String getLoginPageTitle() {
		return util.getTitle();
	}

	public boolean verifySignUpLink() {
		return util.isDisplayed(driver, signUpNow);
	}

	public HomePage doLogin(String email, String pwd) {

		util.doSendKeys(driver, username, email);
		util.doSendKeys(driver, password, pwd);
		util.doClick(driver, loginBtn);

		return new HomePage(driver);
	}
}
