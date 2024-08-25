package com.monitor.testcases;

import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.monitor.base.BaseClass;
import com.monitor.pageobjects.HomePage;
import com.monitor.pageobjects.LoginPage;
import com.monitor.utility.Log;

public class LoginPageTest extends BaseClass {
	
	LoginPage loginPage;
	HomePage homepage;
	
	
	@BeforeClass
	public static void setUp() throws MalformedURLException {
		launchApp();
	}
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
	
	@Test
	public void loginTest() {
		Log.startTestCase("loginTest");
		loginPage=new LoginPage();
		homepage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		
	}
	
	

}
