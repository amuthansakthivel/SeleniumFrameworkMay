package com.training.reports;

import com.relevantcodes.extentreports.ExtentTest;

public class ExtentManager {
	
	public static ThreadLocal<ExtentTest> exTest = new ThreadLocal<ExtentTest>();

	public static ExtentTest getTest() {
		return exTest.get();
	}

	public static void setExTest(ExtentTest test) {
		exTest.set(test);
	}
	
	
	

}
