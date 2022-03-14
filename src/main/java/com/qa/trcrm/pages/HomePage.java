package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.utils.ElementUtil;

public class HomePage extends BasePage {

	WebDriver driver;
	ElementUtil util;

	By homePageHeader = By.xpath("//span[text()='Homepage']");
	By loggedInUser = By.xpath("//span[text()=' sachin sharma']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	public String getHomePageTitle() {
		return util.getTitle();
	}

	public String isHomePageHeaderExist() {
		if (util.isDisplayed(driver, homePageHeader)) {
			return util.doGetText(driver, homePageHeader);
		}
		return null;
	}

	public String isUserLoggedIn() {
		util.presenceOfElementLocated(driver, loggedInUser);
		return util.doGetText(driver,loggedInUser);
	}

}
