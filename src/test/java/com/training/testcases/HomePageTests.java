package com.training.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.training.browser.Driver;
import com.training.browser.DriverManager;

public class HomePageTests extends BaseTest{
	
	
	
	
	
	//@Test
	public void c() throws Exception {
	
		DriverManager.getDriver().findElement(By.name("q")).sendKeys("Automation");
		Thread.sleep(4000);
	}

}
