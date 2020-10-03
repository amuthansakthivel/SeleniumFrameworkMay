package com.training.testcases;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.training.browser.Driver;
import com.training.browser.DriverManager;
import com.training.pages.HamburgerPage;
import com.training.pages.HomePage;
import com.training.pages.LaptopPage;

public class HomePageTests extends BaseTest{
	
	
	HomePage hpage;
	HamburgerPage hampage;
	LaptopPage lpage;
	
	
	
	@Test
	public void testcase3(Hashtable<String,String> data) throws Exception {
		Driver.setUpDriver(data.get("browser"),data.get("testcasename"));
		hpage= new HomePage();
		/*hampage= hpage.clickHamburgerMenu();
		
		lpage=hampage.clickMobilesAndComputers().clickLaptops();
		
		lpage.clickAppleCheckbox().chooseHighToLow();
	*/
		DriverManager.getDriver().get("https://opensource-demo.orangehrmlive.com/");
		DriverManager.getDriver().findElement(By.id("txtUsername")).sendKeys("Admin");
		DriverManager.getDriver().findElement(By.id("txtPassword")).sendKeys("admin123");
		DriverManager.getDriver().findElement(By.id("btnLogin")).click();
		System.out.println(DriverManager.getDriver().getTitle());
		Thread.sleep(4000);
		DriverManager.getDriver().findElement(By.xpath("//a[contains(text(),'Welcome')]")).click();
		
		DriverManager.getDriver().findElement(By.xpath("//a[text()='Logout']")).click();
	
		
	}

}
