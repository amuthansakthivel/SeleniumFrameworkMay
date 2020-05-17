package com.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.training.browser.DriverManager;

public class HamburgerPage extends BasePage{
	
	@FindBy(xpath="//div[text()='Mobiles, Computers']/parent::a")
	private WebElement link_mobilesandcomputers;

	
	@FindBy(xpath="//a[text()='Laptops']")
	private WebElement link_laptops;
	
	

	
	public HamburgerPage clickMobilesAndComputers() {
		link_mobilesandcomputers.click();
		return this;
	}
	
	public LaptopPage clickLaptops() {
		link_laptops.click();
		return new LaptopPage();
	}
}
