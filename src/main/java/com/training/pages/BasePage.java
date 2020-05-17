package com.training.pages;

import org.openqa.selenium.support.PageFactory;

import com.training.browser.DriverManager;

public class BasePage {
	
	protected BasePage() {
		
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	
	/*
	 * Whenever you are creating an object for an class -->the constructor will be called.
	 * If the sub class is extending a parent class then parent class constructor will also be called.
	 * protected keyword --> by child classes
	 */
}
