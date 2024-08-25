package com.monitor.utility;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentClass implements ITestListener {
	
	
	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;
	
	
	public void configureReport() {
		htmlReporter=new ExtentSparkReporter("report01.html");
		reports=new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		htmlReporter.config().setTheme(Theme.DARK);;
		
	}
	
	
	public void onStart(ITestContext context) {
		configureReport();
	}
	
	
	public void onFinish(ITestContext context) {
		
		reports.flush();
	}
	
	public void onTestFailure(ITestResult Result) {
		System.out.println("Name of the test method failed: " +Result.getName());
		test=reports.createTest(Result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is: "+Result.getName(), ExtentColor.RED));
		
	}
	
	public void onTestSkipped(ITestResult Result) {
		System.out.println("On test method skip: "+Result.getName());
		test=reports.createTest(Result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the skipped test case is: "+Result.getName(), ExtentColor.YELLOW));
	}
	public void onTestStart(ITestResult Result) {
		System.out.println("On test method started: "+Result.getName());
	}
	
	public void onTestSuccess(ITestResult Result) {
		System.out.println("on test method successfully executed: " +Result.getName());
		test=reports.createTest(Result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the passed test case is: "+Result.getName(), ExtentColor.GREEN));
	}
	
	
	
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	
}
