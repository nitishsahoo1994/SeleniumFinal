package com.monitor.testcases;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.monitor.base.BaseClass;
import com.monitor.pageobjects.HomePage;
import com.monitor.pageobjects.LoginPage;
import com.monitor.pageobjects.Service;
import com.monitor.xpath.Xpath;

public class ServiceTest extends BaseClass {
	
	 LoginPageTest loginPageTest;
	 HomePage homePage;
	 LoginPage loginPage;
	 Service service;
	 Xpath xpath= new Xpath();
	 ServiceTest serviceTest;
	 
	 String FlowServiceName="simpleFlowService";
	 String PartialFlowServiceNameOne="Flow";
	 String PartialFlowServiceNameTwo= "simple";
	 
	 
		 @BeforeClass
		 public static void setUp() throws MalformedURLException {
				launchApp();
			}
			
		@AfterClass
		public void tearDown() {
				driver.quit();
			}
	
		@BeforeMethod
		public void loginToMWS() {
				loginPage=new LoginPage();
				homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
			}
			
		@AfterMethod
		public void logout() {
				homePage=new HomePage();
				homePage.signOut();
			}
		
		@Test(enabled=true )
		public void navigateToServicePage() {
			driver.navigate().to(prop.getProperty("url")+prop.getProperty("svcportletURL"));
		}
	 
		
		 @Test(enabled =true)
		 public void search_for_Flow_Service_with_exact_match_of_service_name() {
			serviceTest= new ServiceTest();
			serviceTest.navigateToServicePage();
			
			service=new Service();
			service.searchOnProcessInstance(FlowServiceName);
			
			String seriveName=driver.findElement(By.xpath("//a[contains(text(),'simpleFlowService')]")).getText();
			Assert.assertTrue(seriveName.contains(FlowServiceName) );
		 }
		 
		 
		 @Test(enabled =true)
		 public void search_for_Flow_Service_with_partial_match_of_service_name_with_Audit()  {
			serviceTest= new ServiceTest();
			serviceTest.navigateToServicePage();
			
			service=new Service();
			service.searchOnProcessInstance(PartialFlowServiceNameOne);
			
			String seriveName=driver.findElement(By.xpath("//a[contains(text(),'simpleFlowService')]")).getText();
			Assert.assertTrue(seriveName.contains(PartialFlowServiceNameOne) );
		 }
		 
		 
		 @Test(enabled =true)
		 public void search_for_Flow_Service_with_partial_match_of_service_name_with_Flow()  {
			serviceTest= new ServiceTest();
			serviceTest.navigateToServicePage();
			
			service=new Service();
			service.searchOnProcessInstance(PartialFlowServiceNameTwo);
			
			String seriveName=driver.findElement(By.xpath("//a[contains(text(),'simpleFlowService')]")).getText();
			Assert.assertTrue(seriveName.contains(PartialFlowServiceNameTwo) );
		 }
}
