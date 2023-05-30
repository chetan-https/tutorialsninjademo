package com.tutorialsninjaqa.qa.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.*;
import java.io.*;
public class base {
	WebDriver driver;
	public Properties prop;
	public Properties propData;
	
	public void loadPropertiesFile() {
		 prop =new Properties();
		File propFile=new File("C:\\Users\\Techugo\\eclipse-workspace\\TutorialsNinjaProj\\src\\test\\java\\com\\tutorialsninja\\config\\config.properties");
	
		try {
			FileInputStream fis =new FileInputStream(propFile);
			prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		propData =new Properties();
		File propFile1= new File("C:\\Users\\Techugo\\eclipse-workspace\\TutorialsNinjaProj\\src\\test\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		try {
			FileInputStream fis1 =new FileInputStream(propFile1);
			propData.load(fis1);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
	}
	
public WebDriver initializeWebdriverAndOpenApplications(String browserName) {
	if(browserName.equalsIgnoreCase("Chrome"))
	{
		ChromeOptions option = new ChromeOptions();
         option.addArguments("--remote-allow-origins=*");
	    driver= new ChromeDriver(option);
	}else if(browserName.equalsIgnoreCase("Firefox")) 
	{
		driver =new FirefoxDriver();
	}
	else if(browserName.equalsIgnoreCase("Edge")) 
	{
		driver =new EdgeDriver();
	}
	driver.manage().window().maximize();
	driver.get(prop.getProperty("url"));
	return driver;
}
}
