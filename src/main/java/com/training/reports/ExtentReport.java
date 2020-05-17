package com.training.reports;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.training.constants.Constants;

public class ExtentReport {
	
	public static ExtentReports report;
	
	/*
	 * singleton pattern is implemented here as well like Driver.java
	 */
	
	private ExtentReport() {
		report =new ExtentReports(Constants.EXTENTREPORTPATH);
		report.loadConfig(new File(Constants.EXTENTCONFIGFILEPATH));
	}
	
	public static void initializeReports() {
		if(report==null) {
			new ExtentReport();
		}
	}
	
	public static void closeReports() {
		report.flush();
		report.close();
	}

}
