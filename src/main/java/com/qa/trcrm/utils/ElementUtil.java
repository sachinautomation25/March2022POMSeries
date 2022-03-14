package com.qa.trcrm.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	WebDriver driver;
	WebDriverWait wait;
	Actions action;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, AppConstants.DEFAULT_EXPLICT_TIME_OUT);
		action = new Actions(driver);
	}

	public WebElement getElement(WebDriver driver, By locator) {
		WebElement element = null;
		try {

			element = driver.findElement(locator);

		} catch (Exception e) {
			System.out.println("some exception occured while creating the web element: " + locator);
		}
		return element;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void doSendKeys(WebDriver driver, By locator, String value) {
		getElement(driver, locator).sendKeys(value);
	}

	public void doClick(WebDriver driver, By locator) {
		getElement(driver, locator).click();
	}

	public String doGetText(WebDriver driver, By locator) {
		return getElement(driver, locator).getText();
	}

	public boolean isDisplayed(WebDriver driver, By locator) {
		return getElement(driver, locator).isDisplayed();
	}

	public void doActionSendkeys(WebDriver driver, By locator, String value) {

		action.sendKeys(getElement(driver, locator), value).build().perform();
	}

	public void doActionClick(WebDriver driver, By locator) {

		action.click(getElement(driver, locator)).click().build().perform();
	}

	public void presenceOfElementLocated(WebDriver driver, By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
}
