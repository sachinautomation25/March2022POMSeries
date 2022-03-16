package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.utils.ElementUtil;
import com.qa.trcrm.utils.JavaScriptUtil;

public class HomePage extends BasePage {

	WebDriver driver;
	ElementUtil util;
	JavaScriptUtil jsUtil;

	By homePageHeader = By.xpath("//span[text()='Homepage']");
	By loggedInUser = By.xpath("//span[text()=' sachin sharma']");
	
	By contactPage=By.xpath("//li[@id='contactMenuLi']/a");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
		jsUtil=new JavaScriptUtil(driver);
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
	public ContactPage contactPage() {
		util.presenceOfElementLocated(driver, contactPage);
		//util.doActionClick(driver,contactPage);
		jsUtil.clickElementByJS(util.getElement(driver, contactPage));
		
		return new ContactPage(driver);
	}

}
