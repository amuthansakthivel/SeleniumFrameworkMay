package com.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage{
	
	
	@FindBy(id="nav-hamburger-menu")
	private WebElement link_hamburgermenu;
	
	
	
	
	public  HamburgerPage clickHamburgerMenu() {
		click(link_hamburgermenu, "Hamburger button");
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
