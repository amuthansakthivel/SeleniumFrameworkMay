package com.training.testcases;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.training.browser.Driver;
import com.training.browser.DriverManager;
import com.training.pages.HamburgerPage;
import com.training.pages.HomePage;
import com.training.pages.LaptopPage;
import com.training.utils.TestUtils;

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
	public void dataProviderTest(Hashtable<String, String> data) {
		System.out.println(data.get("username"));
		System.out.println(data.get("password"));
		System.out.println(data.get("firstname"));
	}
	
	@Test
	public void testcase1(Hashtable<String, String> data) {
		
	}
	@Test
	public void testcase2(Hashtable<String, String> data) {
		
	}
	
	@Test
	public void somethingElse(Hashtable<String,String> data) throws Exception {
		Driver.setUpDriver("chrome");
		hpage= new HomePage();
		hampage= hpage.clickHamburgerMenu();
		Assert.assertEquals(1, 2);
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
