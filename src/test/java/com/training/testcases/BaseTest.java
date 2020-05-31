package com.training.testcases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.training.browser.Driver;
import com.training.constants.Constants;
import com.training.reports.ExtentReport;

public class BaseTest {
	
	@BeforeSuite
	public void setUpSuite() {
		ExtentReport.initializeReports();
	}
	
	@AfterSuite
	public void tearDownSuite() throws IOException {
		ExtentReport.closeReports();
	
		File htmlFile = new File(Constants.EXTENTREPORTPATH);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}

	@BeforeMethod
	public void setUp() {
		
	}
	
	@AfterMethod
	public void tearDown() {
		Driver.tearDownDriver();
	}

}
