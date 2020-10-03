package com.training.browser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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

	private Driver(String browser,String testname) throws MalformedURLException {
		invokeDriver(browser,testname);
		maximize();
		setImplicitWait();

	}
	public static void setUpDriver(String browser,String testname) throws MalformedURLException {
		if(DriverManager.getDriver()==null) {
			new Driver(browser,testname); //anonymous object -->No reference to this object
		}
	}

	public static void tearDownDriver() {
		if(DriverManager.getDriver()!=null) {
			DriverManager.getDriver().quit();
			DriverManager.setDriver(null);
		}
	}


	private static void invokeDriver(String browser,String testname) throws MalformedURLException {
		if(Constants.RUNMODE.equals("local")) {
			if(browser.equalsIgnoreCase(Constants.CHROME)) {
				ChromeOptions options= new ChromeOptions();
				options.addArguments("--headless");
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
			
		}
		else if(Constants.RUNMODE.equals("remote")){
			DesiredCapabilities cap = null;
			if(browser.equalsIgnoreCase(Constants.CHROME)) {
				cap= new DesiredCapabilities();
				cap.setBrowserName(browser);
				cap.setVersion("84.0");
				cap.setPlatform(Platform.ANY);
			}
			else if(browser.equalsIgnoreCase(Constants.FIREFOX)) {
				cap = new DesiredCapabilities();
				cap.setBrowserName(browser);
				cap.setPlatform(Platform.ANY);
				cap.setVersion("78.0");
			}
			cap.setCapability("enableVNC", true);
			cap.setCapability("enableVideo", true);
			cap.setCapability("name", browser+testname);
			cap.setCapability("videoName", browser+testname+".mp4");
			cap.setCapability("timeZone", "Asia/Calcutta");
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			driver.get(JsonParser.getValue("config.global.url"));
		}

	
		DriverManager.setDriver(driver);
	}

	private static void maximize() {
		DriverManager.getDriver().manage().window().maximize();

	}
	private static void setImplicitWait() {
		DriverManager.getDriver().manage().timeouts().implicitlyWait(Constants.IMPLICITWAIT, TimeUnit.SECONDS);
	}
}
