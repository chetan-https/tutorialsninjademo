package com.tuotrialsninja.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;

public class Mylisteners implements ITestListener{
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		extentReport = ExtentReporter.generateExtentReport();
	    System.out.println("Execution of project tests started ");
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String testName =result.getName();
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName()+" started executing");
		System.out.println(testName+"stat executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String testName =result.getName();
		System.out.println(testName+"got successfully executed");
		extentTest.log(Status.PASS,result.getName()+" got successfully executed");
	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stubString testName =result.getName();
		String testName =result.getName();
		System.out.println(testName+"got failed");
		System.out.println(result.getThrowable());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String testName =result.getName();
		System.out.println(testName+"got skipped");
		System.out.println(result.getThrowable());
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+" got skipped");
	}


	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Finished executing project tests");
		extentReport.flush();
	}
	
	

}
