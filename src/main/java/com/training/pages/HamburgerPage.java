package com.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HamburgerPage extends BasePage{
	
	@FindBy(xpath="//div[text()='Mobiles, Computers']/parent::a")
	private WebElement link_mobilesandcomputers;

	
	@FindBy(xpath="//a[text()='Laptops']")
	private WebElement link_laptops;
	
	
	public HamburgerPage clickMobilesAndComputers() {
		click(link_mobilesandcomputers, "Mobile and computers");
		return this;
	}
	
	public LaptopPage clickLaptops() {
		click(link_laptops, "Laptops");
		return new LaptopPage();
	}
}
