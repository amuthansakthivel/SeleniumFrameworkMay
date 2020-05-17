package com.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;
import com.training.browser.DriverManager;
import com.training.reports.ExtentManager;

public class HomePage extends BasePage{
	
	
	@FindBy(id="nav-hamburger-menu")
	private WebElement link_hamburgermenu;
	
	
	
	
	public  HamburgerPage clickHamburgerMenu() {
		link_hamburgermenu.click();
		ExtentManager.getTest().log(LogStatus.PASS, "HamburgerMenu is clicked successfully");
		return new HamburgerPage();
	}
	
	//button-->
	//a-->
	//input-->
	//td
	//tr
	//i
	//div
	/*
	 * id
	 * name
	 * class
	 * linktext
	 * partial link text
	 * css
	 * xpath
	 * 
	 */
	

}
