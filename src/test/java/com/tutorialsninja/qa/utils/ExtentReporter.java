package com.tutorialsninja.qa.utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;
import java.util.*;
import java.io.*;

import java.io.*;

public class ExtentReporter  {
	
	public static  ExtentReports generateExtentReport() throws IOException {
		
		ExtentReports extentReport =new ExtentReports();
		File extentReportFile =new File("C:\\Users\\Techugo\\eclipse-workspace\\TutorialsNinjaProj\\test-output\\ExtentReport\\extentReport.html");
		ExtentSparkReporter spark = new ExtentSparkReporter(extentReportFile);
		
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Tutorials ninja Test automation results");
		spark.config().setDocumentTitle("TN automation");
		spark.config().setTimeStampFormat("dd/mm/yyyy  hh:mm:ss");
		
		
		extentReport.attachReporter(spark);

		Properties configProp =new Properties();
		File configPropFile =new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\tutorialsninja\\config\\config.properties");
		FileInputStream fisConfig = new FileInputStream(configPropFile);
		configProp.load(fisConfig);
		
		extentReport.setSystemInfo("Application url" , configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser name" , configProp.getProperty("browserName"));
		
		return extentReport;
		}

}
