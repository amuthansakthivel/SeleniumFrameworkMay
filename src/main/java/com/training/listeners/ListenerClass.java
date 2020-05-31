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
import com.training.reports.LogStatus;
import com.training.utils.TestUtils;

public class ListenerClass implements ITestListener {
	
	private static String TestCaseName;
	

	public static String getTestCaseName() {
		return TestCaseName;
	}

	public static void setTestCaseName(String testCaseName) {
		TestCaseName = testCaseName;
	}

	public void onTestStart(ITestResult result) {
	
		setTestCaseName(result.getName());
		
		ExtentManager.setExTest(ExtentReport.report.startTest(result.getMethod().getDescription()));
		LogStatus.pass(result.getMethod().getDescription() +" is started successfully");
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentReport.report.endTest(ExtentManager.getTest());
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		LogStatus.fail(result.getMethod().getDescription() +" is failed");
		LogStatus.fail(result.getThrowable().toString());
		LogStatus.fail(result.getName() + "is clicked ", TestUtils.pullScreenshotPath());
		ExtentReport.report.endTest(ExtentManager.getTest());
	
	}

	public void onTestSkipped(ITestResult result) {
		LogStatus.skip(result.getName() + " is skipped ");
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

	
}
