package com.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.training.browser.DriverManager;
import com.training.constants.Constants;
import com.training.reports.LogStatus;
import com.training.utils.TestUtils;



public class BasePage {
	
	protected BasePage() {
		
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	
	/*
	 * Whenever you are creating an object for an class -->the constructor will be called.
	 * If the sub class is extending a parent class then parent class constructor will also be called.
	 * protected keyword --> by child classes
	 */
	
	public static void click(WebElement element, String name) {
		explicitlyWait(element);
		element.click();
		LogStatus.pass(name +" is clicked successfully");
		LogStatus.pass(name + "is clicked successfully", TestUtils.pullScreenshotPath());
	}
	
	public static void explicitlyWait(WebElement element) {
		new WebDriverWait(DriverManager.getDriver(),Constants.EXPLICITWAIT)
				.until(ExpectedConditions.visibilityOf(element));
	}
	
}
