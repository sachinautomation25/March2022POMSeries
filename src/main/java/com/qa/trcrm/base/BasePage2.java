package com.qa.trcrm.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage2 {

	public WebDriver driver;
	public Properties prop;

	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser");
		boolean isHeadless = Boolean.parseBoolean(prop.getProperty("headless"));

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			if (isHeadless) {
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
			} else {
				driver = new ChromeDriver();
			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if (isHeadless) {
				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--headless");
				driver = new FirefoxDriver(fo);
			} else {
				driver = new FirefoxDriver();

			}
		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();

			driver = new SafariDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {

			System.out.println(browserName + " not found");

			try {
				throw new Exception("NoSuchBrowserException");
			} catch (Exception e) {
			}
		}
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		return driver;
	}

	public WebDriver init_driver(Properties prop, String browserName) {

		// String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver = new SafariDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {

			System.out.println(browserName + " not found");

			try {
				throw new Exception("NoSuchBrowserException");
			} catch (Exception e) {
			}
		}
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		return driver;
	}

	public Properties init_prop() {
		try {
			FileInputStream fis = new FileInputStream("./src/main/java/com/qa/trcrm/config/config.properties");

			prop = new Properties();
			prop.load(fis);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
}
