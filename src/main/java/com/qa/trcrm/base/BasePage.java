package com.qa.trcrm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	OptionsManager options;

	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	public synchronized WebDriver getDriver() {
		return tldriver.get();
	}

	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser");

		options = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tldriver.set(new ChromeDriver(options.getChromeOptions()));

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();

			tldriver.set(new FirefoxDriver(options.getFirefoxOptions()));
		} else if (browserName.equalsIgnoreCase("safari"))

		{
			WebDriverManager.getInstance(SafariDriver.class).setup();

			tldriver.set(new SafariDriver());
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			tldriver.set(new EdgeDriver());
		} else {

			System.out.println(browserName + " not found");

			try {
				throw new Exception("NoSuchBrowserException");
			} catch (Exception e) {
			}
		}
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		return getDriver();
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

	public String getScreenshot() {

		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		try {
			FileUtils.copyFile(src, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
