package com.training.testcases;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.browser.Driver;
import com.training.browser.DriverManager;
import com.training.constants.Constants;
import com.training.pages.HamburgerPage;
import com.training.pages.HomePage;
import com.training.pages.LaptopPage;
import com.training.reports.ExtentManager;
import com.training.reports.ExtentReport;
import com.training.utils.JsonParser;

public class LoginTests extends BaseTest{

	/*
	 * 1. Never hardcode
	 * 2. Modularise the code --> Split into smaller componenets when needed for resusability
	 * 3. Framework should be easy to use for others
	 * 4. Should follow coding standard and conventions
	 */

	//boiler plate codes-->not needed code -->unnecessary code
	//Class names or Interface name--> AmuthanSakthivel
	//packagename--->amuthan.sakthivel
	//constant ---> AMUTHANSAKTHIVEL or AMUTHAN_SAKTHIVEL
	//methodname -->amuthanSakthivel

	//testng option will not be available if you have not installed the testng plugin
	
	HomePage hpage;
	HamburgerPage hampage;
	LaptopPage lpage;
	
	@Test
	public void b(Method m) throws Exception {
		Driver.setUpDriver("chrome");
		hpage= new HomePage();
		hampage= hpage.clickHamburgerMenu();
		
		lpage=hampage.clickMobilesAndComputers().clickLaptops();
		
		lpage.clickAppleCheckbox().chooseHighToLow();
	
		Thread.sleep(4000);
	}

	//@Test
	public void a() throws Exception {

		DriverManager.getDriver().findElement(By.name("q")).sendKeys("Selenium");
		Thread.sleep(4000);
	}

}
