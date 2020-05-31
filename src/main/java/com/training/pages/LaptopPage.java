package com.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LaptopPage extends BasePage{
	
	@FindBy(name="s-ref-checkbox-Apple")
	private WebElement checkbox_apple;
	
	@FindBy(xpath="//span[text()='Sort by:']")
	private WebElement drpdwn_menu;
	
	
	
	@FindBy(xpath="//a[text()='Price: High to Low']")
	private WebElement drpdwn_hightolow;
	
	public LaptopPage clickAppleCheckbox() {
		click(checkbox_apple, "Apple checkbox");
		return this;
	}
	
	public LaptopPage chooseHighToLow() {
		drpdwn_menu.click();
		drpdwn_hightolow.click();
		return this;
	}

}
