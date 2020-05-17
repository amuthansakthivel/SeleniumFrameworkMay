package com.training.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.training.browser.Driver;
import com.training.reports.ExtentReport;

public class BaseTest {
	
	@BeforeSuite
	public void setUpSuite() {
		ExtentReport.initializeReports();
	}
	
	@AfterSuite
	public void tearDownSuite() {
		ExtentReport.closeReports();
	}

	@BeforeMethod
	public void setUp() {
		
	}
	
	@AfterMethod
	public void tearDown() {
		Driver.tearDownDriver();
	}

}
