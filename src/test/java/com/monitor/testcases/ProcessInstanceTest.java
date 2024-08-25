package com.monitor.testcases;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.monitor.actiondriver.Action;
import com.monitor.base.BaseClass;
import com.monitor.pageobjects.BusinessProcess;
import com.monitor.pageobjects.HomePage;
import com.monitor.pageobjects.LoginPage;
import com.monitor.pageobjects.ProcessInstance;
import com.monitor.xpath.Xpath;

public class ProcessInstanceTest extends BaseClass {

	
	 LoginPageTest loginPageTest;
	 HomePage homePage;
	 LoginPage loginPage;
	 BusinessProcess businessProcess;
	 Xpath xpath= new Xpath();
	 ProcessInstanceTest processtest;
	 ProcessInstance processInstance;
	 
	 String ProcessInstanceName="simpleProcess";
	 String ProcessInstanceNoMatch="12345";
	 String ProcessModelWildCardName="sim*produce";
	 String ProcessModelNoWildCardName="Test*produce";
	 String ProcessModelI18NMatchName="Zeitalter";
	 
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

		@Test(enabled = true,priority = 1)
		public void navigateToProcessInstance() {
			driver.navigate().to(prop.getProperty("url")+prop.getProperty("processInstanceURL"));
		}
		
		@Test(enabled = true,priority = 2)
		public void drill_Into_Process_Instance_Details() {
			driver.navigate().to(prop.getProperty("url")+prop.getProperty("processInstanceURL"));
			Action.fluentWait(driver, driver.findElement(By.xpath("//span[text()='simpleProcess']")), 10);
			driver.findElement(By.xpath(xpath.instanceDetailsIcon)).click();
			
		}
		
		
		@Test(enabled = true,priority = 3)
		public void search_for_Process_Instance_by_exact_match_of_Model_Name() {
			 processtest=new ProcessInstanceTest();
			 processtest.navigateToProcessInstance();
			 
			 processInstance=new ProcessInstance();
			 processInstance.searchOnProcessInstance(ProcessInstanceName);
			 
			 
			 String expectProcessInstance=driver.findElement(By.xpath("//span[text()='simpleProcess']")).getText();
			 

			
			 Assert.assertEquals(ProcessInstanceName, expectProcessInstance);
			
			
		}
		
		 
		@Test (enabled = true,priority = 4)
		public void search_for_Process_Instance_by_no_match_of_Model_Name() {
			 processtest=new ProcessInstanceTest();
			 processtest.navigateToProcessInstance();
			
			 processInstance=new ProcessInstance();
			 processInstance.searchOnProcessInstance(ProcessInstanceNoMatch);
			 
			 boolean noMatchesFound=driver.findElement(By.xpath((xpath==null)?"":xpath.no_matchesFound)).isDisplayed();
			 Assert.assertTrue(noMatchesFound);
		}
		
		@Test (enabled = true,priority = 5)
		public void search_for_Process_Instance_by_WildcardMatch_of_Model_Name() throws InterruptedException {
			Thread.sleep(5000);
			 processtest=new ProcessInstanceTest();
			 processtest.navigateToProcessInstance();
			
			 processInstance=new ProcessInstance();
			 processInstance.searchOnProcessInstance(ProcessModelWildCardName);
			 
			 boolean noMatchesFound=driver.findElement(By.xpath((xpath==null)?"":xpath.no_matchesFound)).isDisplayed();
			 Assert.assertTrue(noMatchesFound);
		}
		
		@Test(enabled = true,priority = 6)
		public void search_for_Process_Instance_by_Process_Instance_Id() {
			
			 processtest=new ProcessInstanceTest();
			 processtest.navigateToProcessInstance();
			
			 processInstance=new ProcessInstance();
			 processInstance.clickOnSerach();
			 
			 String instnaceIDValue=driver.findElement(By.xpath(xpath.instanceId)).getText();
			 processInstance.searchOnProcessInstance(instnaceIDValue);
			 
			 String expectProcessInstance=driver.findElement(By.xpath("//span[text()='simpleProcess']")).getText();
			 Assert.assertEquals(ProcessInstanceName, expectProcessInstance);
		}
		
		
		
		
		@Test(enabled = true,priority = 7)
		public void process_Instance_No_Wildcard_Match_Search_From_Keywords_Tab() {
			 processtest=new ProcessInstanceTest();
			 processtest.navigateToProcessInstance();
			
			 processInstance=new ProcessInstance();
			 processInstance.clickOnSerach();
			 processInstance.searchOnProcessInstance(ProcessModelNoWildCardName);
			 
			 boolean noMatchesFound=driver.findElement(By.xpath((xpath==null)?"":xpath.no_matchesFound)).isDisplayed();
			 Assert.assertTrue(noMatchesFound);
		}
		
		
		
		@Test(enabled = true,priority = 8)
		public void process_Instance_I18NMatch_Search_From_Advanced_Tab() {
			 processtest=new ProcessInstanceTest();
			 processtest.navigateToProcessInstance();
			
			 processInstance=new ProcessInstance();
			 processInstance.clickOnSerach();
			 processInstance.searchOnProcessInstance(ProcessModelI18NMatchName);
			 
			 boolean noMatchesFound=driver.findElement(By.xpath((xpath==null)?"":xpath.no_matchesFound)).isDisplayed();
			 Assert.assertTrue(noMatchesFound);
		}
		
}
