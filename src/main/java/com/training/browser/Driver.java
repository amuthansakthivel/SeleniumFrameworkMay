package com.training.browser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.training.constants.Constants;
import com.training.utils.JsonParser;

public class Driver {

	private static WebDriver driver;

	/*
	 * Singleton Class pattern --> Single instance class -->You can have only one instance of the class for a thread at a
	 * 					given time
	 * 
	 * How to achieve this?
	 * 
	 * Private constructor
	 * Static method 
	 * 
	 * Why I need a singleton when I can directly use it as a method?
	 * 
	 * 1. Driver 
	 * 2. Extent Report
	 * 
	 *To avoid thread safety issues in the static webdriver class variables-->ThreadLocal
	 *
	 */

	private Driver(String browser) {
		invokeDriver(browser);
		maximize();
		setImplicitWait();

	}
	public static void setUpDriver(String browser) {
		if(DriverManager.getDriver()==null) {
			new Driver(browser); //anonymous object -->No reference to this object
		}
	}

	public static void tearDownDriver() {
		if(DriverManager.getDriver()!=null) {
			DriverManager.getDriver().quit();
			DriverManager.setDriver(null);
		}
	}

	
	private static void invokeDriver(String browser) {
		if(browser.equalsIgnoreCase(Constants.CHROME)) {
			System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVERPATH);
			driver= new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase(Constants.FIREFOX) || (browser.equalsIgnoreCase(Constants.MOZILLA))) {
			System.setProperty("webdriver.gecko.driver", Constants.GECKODRIVERPATH);
			driver= new FirefoxDriver();
		}
		else {
			System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVERPATH);
			driver= new ChromeDriver();
		}
		driver.get(JsonParser.getValue("config.global.url"));
		DriverManager.setDriver(driver);
	}

	private static void maximize() {
		DriverManager.getDriver().manage().window().maximize();
	
	}
	private static void setImplicitWait() {
		DriverManager.getDriver().manage().timeouts().implicitlyWait(Constants.IMPLICITWAIT, TimeUnit.SECONDS);
	}
}
