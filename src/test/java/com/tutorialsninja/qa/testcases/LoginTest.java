package com.tutorialsninja.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.utils.Utilities;
import com.tutorialsninjaqa.qa.base.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.pages.AccountPage;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Date;

public class LoginTest extends base {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		loadPropertiesFile();
		driver = initializeWebdriverAndOpenApplications(prop.getProperty("browserName"));
	    HomePage homePage =new HomePage(driver);
	    homePage.clickOnMyAccount();
	    homePage.selectLoginOption();
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

	@Test(priority = 1 ,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email ,String password) {

		LoginPage loginPage= new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginButton();
		
		AccountPage accountPage =new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption());
		//Required when the xpath is not dynamic 
//		driver.findElement(By.xpath("//input[@id=\"input-email\"]")).sendKeys(email);
//		driver.findElement(By.xpath("//input[@id=\"input-password\"]")).sendKeys(password);
		
// using properties file 
//		driver.findElement(By.xpath("//input[@id=\"input-email\"]")).sendKeys(propData.getProperty("Email"));
//		driver.findElement(By.xpath("//input[@id=\"input-password\"]")).sendKeys(propData.getProperty("Password"));
//		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();

//		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());

	}

	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() {

		LoginPage loginPage= new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateTimestamp());
		loginPage.enterPassword("12345ndasjknjkfnwejkn");
		loginPage.clickOnLoginButton();
//		driver.findElement(By.xpath("//input[@id=\"input-email\"]"))
//				.sendKeys("amotooricap9" + Utilities.generateTimestamp() + "@gmail.com");
//		driver.findElement(By.xpath("//input[@id=\"input-password\"]")).sendKeys("12345ndasjknjkfnwejkn");
//		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();

		String actual_warning_message = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		String expected_warning_message = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actual_warning_message.contains(expected_warning_message),
				"Expected warning is not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginWithInValidEmailValidPassword() {

		driver.findElement(By.xpath("//input[@id=\"input-email\"]"))
				.sendKeys("amotooricap9" + Utilities.generateTimestamp() + "@gmail.com");
		driver.findElement(By.xpath("//input[@id=\"input-password\"]")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();

		String actual_warning_message = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		String expected_warning_message = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actual_warning_message.contains(expected_warning_message),
				"Expected warning is not displayed");
     
	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailInValidPassword() {

		driver.findElement(By.xpath("//input[@id=\"input-email\"]")).sendKeys("amotooricap9@gmail.com");
		driver.findElement(By.xpath("//input[@id=\"input-password\"]")).sendKeys("12345ndasjknjkfnwejkn");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();

		String actual_warning_message = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		String expected_warning_message = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actual_warning_message.contains(expected_warning_message),
				"Expected warning is not displayed");

	}

}
