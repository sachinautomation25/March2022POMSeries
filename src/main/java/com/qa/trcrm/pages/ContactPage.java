package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.utils.ElementUtil;

public class ContactPage extends BasePage {

	WebDriver driver;
	ElementUtil util;

	By contactsPageHeader = By.xpath("(//h2[text()])[1]");
	By addPersonButton = By.xpath("//button[@class='hidden-xs hidden-sm btn btn-danger mr5 ng-scope ng-binding']");

	By name = By.name("name");
	By surname=By.name("surname");
	By emailId = By.id("email0");

	By saveButton = By.xpath("//button[@class='btn btn-primary btn-large ng-binding']");
	By personAdded = By.xpath("//span[text()='Person added.']");

	public ContactPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	public String verifyContactPageHeader() {
		util.presenceOfElementLocated(driver, contactsPageHeader);
		return util.doGetText(driver, contactsPageHeader);
	}

	public String addPerson(String firstName, String email) {
		util.presenceOfElementLocated(driver, addPersonButton);
		
		util.doClick(driver, addPersonButton);

		util.presenceOfElementLocated(driver, name);

		util.doSendKeys(driver, name, firstName);
		util.doSendKeys(driver, surname, email);
		util.doClick(driver, saveButton);
		
		util.presenceOfElementLocated(driver, personAdded);
		
		return util.doGetText(driver, personAdded);
	}
}
