package com.training.listeners;

import java.lang.reflect.Method;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentTest;
import com.training.reports.ExtentManager;
import com.training.reports.ExtentReport;

public class ListenerClass implements ITestListener {

	public void onTestStart(ITestResult result,Method m) {
	
		ExtentTest test=  ExtentReport.report.startTest(m.getName());
		ExtentManager.setExTest(test);
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentReport.report.endTest(ExtentManager.getTest());
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentReport.report.endTest(ExtentManager.getTest());
	
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentReport.report.endTest(ExtentManager.getTest());
	
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentReport.report.endTest(ExtentManager.getTest());
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

}
