package com.training.listeners;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentTest;
import com.training.reports.ExtentManager;
import com.training.reports.ExtentReport;
import com.training.reports.LogStatus;
import com.training.reports.TestStatus;
import com.training.utils.SendResultsToElasticSearch;
import com.training.utils.TestUtils;

public class ListenerClass implements ITestListener {

	private static String TestCaseName;
	TestStatus testStatus;


	public static String getTestCaseName() {
		return TestCaseName;
	}

	public static void setTestCaseName(String testCaseName) {
		TestCaseName = testCaseName;
	}

	public void onTestStart(ITestResult result) {
		testStatus = new TestStatus();
		setTestCaseName(result.getName());

		ExtentManager.setExTest(ExtentReport.report.startTest(result.getMethod().getDescription()));
		LogStatus.pass(result.getMethod().getDescription() +" is started successfully");
	}

	public void onTestSuccess(ITestResult result) {
		setAllDetails(result,"PASS");
		ExtentReport.report.endTest(ExtentManager.getTest());

	}

	public void onTestFailure(ITestResult result) {
		setAllDetails(result,"FAIL");
		try {
			LogStatus.fail(result.getMethod().getDescription() +" is failed");
			LogStatus.fail(result.getThrowable().toString());
			LogStatus.fail(result.getName() + "is clicked ", TestUtils.pullScreenshotPath());
		}
		catch(Exception e) {

		}
		finally {
			ExtentReport.report.endTest(ExtentManager.getTest());
		}

	}

	public void onTestSkipped(ITestResult result) {
		setAllDetails(result,"SKIP");
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

	public void setAllDetails(ITestResult result,String status) {
		this.testStatus.setDescription(result.getMethod().getDescription());
		this.testStatus.setStatus(status);
		this.testStatus.setTestClass(result.getTestClass().getName());
		System.out.println(LocalDateTime.now().toString());
		this.testStatus.setExecutionDate(LocalDateTime.now().toString());
		SendResultsToElasticSearch.sendResults(this.testStatus);
	}

}
