package com.qa.trcrm.pages;

import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;

public class ProfilePage extends BasePage {

	WebDriver driver;
	public ProfilePage(WebDriver driver)
	{
		this.driver=driver;
	}
  public void get()
  {
    System.out.println("get");
  }
 public void get3()
  {
    System.out.println("get3");
  }
}
