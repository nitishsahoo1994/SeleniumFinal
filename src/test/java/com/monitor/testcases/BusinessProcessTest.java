package com.monitor.testcases;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
import com.monitor.xpath.Xpath;


public class BusinessProcessTest extends BaseClass{
	 LoginPageTest loginPageTest;
	 HomePage homePage;
	 LoginPage loginPage;
	 BusinessProcess businessProcess;
	 Xpath xpath= new Xpath();
	 
	String ProcessModelName="simpleProcess"; 
	String ProcessModelNameFullName="simpleProcess (WmMonitorUITestingProcesses)";
	String ProcessModelNamePartial="simpleP";
	String ProcessModelNameNoMatch="ryyys";
	String ProcessModelWildCardName="sim*produce";
	String ProcessModelNoWildCardName="Test*produce";
	String ProcessModelCaseInsensitiveName="SIMPLEPROCESS";
	String ProcessModelI18NMatchName="Zeitalter";
	String noOfBusinessProcess="3 of 3";
	
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
	public void navigateToBusinessProcess() {
		driver.navigate().to(prop.getProperty("url")+prop.getProperty("businessProcessURL"));
	}
	
	
	@Test(enabled = true, priority = 2)
	public void drill_into_business_process_details()  {
		businessProcess=new BusinessProcess();
		xpath = new Xpath();
	
		driver.navigate().to(prop.getProperty("url")+prop.getProperty("businessProcessURL"));
		businessProcess.searchOnBusinessProcess("simpleprocess");
		
    	
//    	driver.get(driver.getCurrentUrl());
//    	String str=(xpath==null)?"":xpath.simpleProcessLink_WmMonitorTestProcesses;
//    	Action.click(driver, driver.findElement(By.xpath(str)));
		
	}
	
	
	@Test(enabled = true)
	public void business_Process_Information_Process_Details_Verification() throws InterruptedException  {
		
		BusinessProcessTest bpt=new BusinessProcessTest();
		xpath = new Xpath();
		bpt.drill_into_business_process_details();
		
		
		
		
		businessProcess=new BusinessProcess();
//		
		String modelName="simpleProcess (WmMonitorUITestingProcesses)";
		String modelVersion="1";
		String modelDesc="For_wm_monitor_ui unit_tests";
		String modelUsed="Yes";
		
		Thread.sleep(5000);
		businessProcess.clickOnsimpleProcess_UI();
		
//		//Validate Model Name Version Desc Model Used Status
		
		Assert.assertEquals(modelName, driver.findElement(By.cssSelector((xpath==null)?"":xpath.modelname_css)).getText());
		Assert.assertEquals(modelVersion, driver.findElement(By.cssSelector((xpath==null)?"":xpath.modelVersion_css)).getText());
		Assert.assertEquals(modelDesc, driver.findElement(By.cssSelector((xpath==null)?"":xpath.modelDesc_css)).getText());
		Assert.assertEquals(modelUsed, driver.findElement(By.cssSelector((xpath==null)?"":xpath.modelUsed_css)).getText());
//	
//		//Validate Ececution Enabled On
		Assert.assertTrue(Action.isSelected(driver, driver.findElement(By.cssSelector((xpath==null)?"":xpath.executionEnabledCheckBox))));
	}
	
	
	@Test(enabled = true)
	public void business_Process_Information_Process_Logging_Level_Verification() throws InterruptedException {
		BusinessProcessTest bpt=new BusinessProcessTest();
		xpath = new Xpath();
		bpt.drill_into_business_process_details();
		businessProcess=new BusinessProcess();
		
		Thread.sleep(5000);
		businessProcess.clickOnsimpleProcess_UI();
		
		
		//click on process_setting
				businessProcess.clickOn_ProcessSetting();
				
				businessProcess.waitTill_instanceThreshold();
				
				businessProcess.waitTill_LogTransition();
				
				String[] logiing_level={"1 - None",
				                        "2 - Errors",
				                        "3 - Process Only",
				                        "4 - Process and Start Steps",
				                        "5 - Process and all events and activities and looped activities"};
		 		List<WebElement> lst=Action.getAllValuesFromDropDown(driver.findElement(By.xpath("//span[text()='Logging Level']/parent::label/following-sibling::div/child::select")));
				
		 		
		 		if(logiing_level.length==lst.size()) {
		 			for(int i=0;i<lst.size();i++) {
		 				Assert.assertEquals(lst.get(i).getText(), logiing_level[i]);
		 			}
		 		}
				else {
					System.out.println("Actual and expected have diff in size");
				}		
		
	}
	
	
	@Test(enabled = true)
	public void business_Process_Information_Process_Logging_Level_Selection() throws InterruptedException {
		businessProcess=new BusinessProcess();
		BusinessProcessTest bpt=new BusinessProcessTest();
		xpath = new Xpath();
		bpt.drill_into_business_process_details();
		
		Thread.sleep(5000);
		businessProcess.clickOnsimpleProcess_UI();
		
		businessProcess.clickOn_ProcessSetting();
		
		businessProcess.waitTill_instanceThreshold();
		
		Action.selectByIndex(driver.findElement(By.xpath("//span[text()='Logging Level']/parent::label/following-sibling::div/child::select")), 4);
		
		businessProcess.click_saveButton();	
	}
	
	@Test(enabled = true)
	public void business_Process_Information_Process_Logging_Inst_Treshold() {
		
	}
	
	
	
	@Test(enabled = true)
	public  void Business_Process_Information_Process_Settings_Verification() {
		
	}
	
	
	@Test(enabled = true)
	public  void business_Process_Information_Process_Step_Settings_Verification() throws InterruptedException {
		businessProcess=new BusinessProcess();
		BusinessProcessTest bpt=new BusinessProcessTest();
		xpath = new Xpath();
		bpt.drill_into_business_process_details();
		
		Thread.sleep(5000);
		businessProcess.clickOnsimpleProcess_UI();
		
		businessProcess.click_step_setting();
		
		WebElement simple_flow=driver.findElement(By.xpath((xpath==null)?"":xpath.simple_flow));
		WebElement five_minute_wait=driver.findElement(By.xpath((xpath==null)?"":xpath.five_minute_wait));
		
		if(simple_flow.isSelected()) {
			System.out.println("Simple flow is slected");
		}
		else {
			Action.click(driver, simple_flow);
		}
		
		if(five_minute_wait.isSelected()) {
			System.out.println("Five minute Wait is slected");
		}
		else {
			Action.click(driver, five_minute_wait);
		}
		businessProcess.click_saveButton();
	}
	
	
	@Test(enabled = true)
	public void process_Search_From_Keywords_Tab() throws InterruptedException {
		businessProcess=new BusinessProcess();
		driver.navigate().to(prop.getProperty("url")+prop.getProperty("businessProcessURL"));
		businessProcess.searchOnBusinessProcess(ProcessModelName);
		
    	driver.get(driver.getCurrentUrl());
    	String actualNoofBusinessProcess=driver.findElement(By.cssSelector((xpath==null)?"":xpath.noOfBusinessProcess_css)).getText();
		Assert.assertTrue(actualNoofBusinessProcess.contains(noOfBusinessProcess));	
	}
	
	
	@Test(enabled = true)
	public void business_Process_Full_Process_Model_Name_Search_From_Keywords_Tab() {
		businessProcess=new BusinessProcess();
		driver.navigate().to(prop.getProperty("url")+prop.getProperty("businessProcessURL"));
		businessProcess.searchOnBusinessProcess(ProcessModelNameFullName);
		
    	driver.get(driver.getCurrentUrl());
    	String actualNoofBusinessProcess=driver.findElement(By.cssSelector((xpath==null)?"":xpath.noOfBusinessProcess_css)).getText();
		Assert.assertTrue(actualNoofBusinessProcess.contains(noOfBusinessProcess));
	}
		
	@Test(enabled = true)
	public void Business_Process_Partial_Name_Search_From_Keywords_Tab() {
		businessProcess=new BusinessProcess();
		driver.navigate().to(prop.getProperty("url")+prop.getProperty("businessProcessURL"));
		businessProcess.searchOnBusinessProcess(ProcessModelNamePartial);
		
    	driver.get(driver.getCurrentUrl());
    	String actualNoofBusinessProcess=driver.findElement(By.cssSelector((xpath==null)?"":xpath.noOfBusinessProcess_css)).getText();
		Assert.assertTrue(actualNoofBusinessProcess.contains(noOfBusinessProcess));
	}
	
	@Test(enabled = true)
	public void business_Process_No_match_Name_Search_From_Keywords_Tab() {
		businessProcess=new BusinessProcess();
		driver.navigate().to(prop.getProperty("url")+prop.getProperty("businessProcessURL"));
		businessProcess.searchOnBusinessProcess(ProcessModelNameNoMatch);
		
    	driver.get(driver.getCurrentUrl());
    	boolean noMatchesFound=driver.findElement(By.xpath((xpath==null)?"":xpath.no_matchesFound)).isDisplayed();
		Assert.assertTrue(noMatchesFound);
	}
	
	
	@Test(enabled = true)
	public void Business_Process_WildcardMatch_Name_Search_From_Keywords_Tab() {
		businessProcess=new BusinessProcess();
		driver.navigate().to(prop.getProperty("url")+prop.getProperty("businessProcessURL"));
		businessProcess.searchOnBusinessProcess(ProcessModelWildCardName);
		
    	driver.get(driver.getCurrentUrl());
    	boolean noMatchesFound=driver.findElement(By.xpath((xpath==null)?"":xpath.no_matchesFound)).isDisplayed();
		Assert.assertTrue(noMatchesFound);
	}
	
	@Test(enabled = true)
	public void business_Process_No_Wildcard_Match_Search_From_Keywords_Tab() {
		businessProcess=new BusinessProcess();
		driver.navigate().to(prop.getProperty("url")+prop.getProperty("businessProcessURL"));
		businessProcess.searchOnBusinessProcess(ProcessModelNoWildCardName);
		
    	driver.get(driver.getCurrentUrl());
    	boolean noMatchesFound=driver.findElement(By.xpath((xpath==null)?"":xpath.no_matchesFound)).isDisplayed();
		Assert.assertTrue(noMatchesFound);
	}
	
	@Test(enabled = true)
	public void business_Process_CaseInsensitiveMatch_Name_Search_From_Keywords_Tab() {
		businessProcess=new BusinessProcess();
		driver.navigate().to(prop.getProperty("url")+prop.getProperty("businessProcessURL"));
		businessProcess.searchOnBusinessProcess(ProcessModelCaseInsensitiveName);
		
    	driver.get(driver.getCurrentUrl());
    	String actualNoofBusinessProcess=driver.findElement(By.cssSelector((xpath==null)?"":xpath.noOfBusinessProcess_css)).getText();
		Assert.assertTrue(actualNoofBusinessProcess.contains(noOfBusinessProcess));
	}
	

	@Test(enabled = true)
	public void business_Process_Search_From_Advanced_Tab() {
		businessProcess=new BusinessProcess();
		driver.navigate().to(prop.getProperty("url")+prop.getProperty("businessProcessURL"));
		businessProcess.searchOnBusinessProcess(ProcessModelI18NMatchName);
		
    	driver.get(driver.getCurrentUrl());
    	boolean noMatchesFound=driver.findElement(By.xpath((xpath==null)?"":xpath.no_matchesFound)).isDisplayed();
		Assert.assertTrue(noMatchesFound);
	}
	
	@Test(enabled = true)
	public void process_Partial_Search_From_Advanced_Keywords_Tab() throws InterruptedException {
		businessProcess=new BusinessProcess();
		driver.navigate().to(prop.getProperty("url")+prop.getProperty("businessProcessURL"));
		businessProcess.clickOnAdvanceBtn();
		
		Action.fluentWait(driver, driver.findElement(By.xpath("//span[text()='Set Model Description']")), 10);
		businessProcess.searchOnBusinessProcess(ProcessModelNamePartial);
		driver.get(driver.getCurrentUrl());
    	String actualNoofBusinessProcess=driver.findElement(By.cssSelector((xpath==null)?"":xpath.noOfBusinessProcess_css)).getText();
		Assert.assertTrue(actualNoofBusinessProcess.contains(noOfBusinessProcess));	
	}
	
	
	
	
	@Test(enabled = true)
	public void business_Process_Full_Business_Process_Search_From_Advanced_Keywords_Tab() {
		businessProcess=new BusinessProcess();
		driver.navigate().to(prop.getProperty("url")+prop.getProperty("businessProcessURL"));
		businessProcess.clickOnAdvanceBtn();
		
		Action.fluentWait(driver, driver.findElement(By.xpath("//span[text()='Set Model Description']")), 10);
		businessProcess.searchOnBusinessProcess(ProcessModelNameFullName);
		driver.get(driver.getCurrentUrl());
    	String actualNoofBusinessProcess=driver.findElement(By.cssSelector((xpath==null)?"":xpath.noOfBusinessProcess_css)).getText();
		Assert.assertTrue(actualNoofBusinessProcess.contains(noOfBusinessProcess));	
	}
	
	@Test(enabled = true)
	public void business_Process_No_Match_Business_Process_Search_From_Advanced_Keywords_Tab() {
		businessProcess=new BusinessProcess();
		driver.navigate().to(prop.getProperty("url")+prop.getProperty("businessProcessURL"));
		businessProcess.clickOnAdvanceBtn();
		
		Action.fluentWait(driver, driver.findElement(By.xpath("//span[text()='Set Model Description']")), 10);
		businessProcess.searchOnBusinessProcess(ProcessModelNameNoMatch);
		driver.get(driver.getCurrentUrl());
		boolean noMatchesFound=driver.findElement(By.xpath((xpath==null)?"":xpath.no_matchesFound)).isDisplayed();
		Assert.assertTrue(noMatchesFound);
	}
	
	@Test(enabled = true)
	public void business_Process_WildcardMatch_Business_Process_Search_From_Advanced_Keywords_Tab() {
		businessProcess=new BusinessProcess();
		driver.navigate().to(prop.getProperty("url")+prop.getProperty("businessProcessURL"));
		businessProcess.clickOnAdvanceBtn();
		
		
		Action.fluentWait(driver, driver.findElement(By.xpath("//span[text()='Set Model Description']")), 10);
		businessProcess.searchOnBusinessProcess(ProcessModelWildCardName);
		driver.get(driver.getCurrentUrl());
		boolean noMatchesFound=driver.findElement(By.xpath((xpath==null)?"":xpath.no_matchesFound)).isDisplayed();
		Assert.assertTrue(noMatchesFound);
	}
	
	@Test(enabled = true)
	public void business_Process_No_Wildcard_Match_Search_From_Advanced_Tab() {
		businessProcess=new BusinessProcess();
		driver.navigate().to(prop.getProperty("url")+prop.getProperty("businessProcessURL"));
		businessProcess.clickOnAdvanceBtn();
		
		Action.fluentWait(driver, driver.findElement(By.xpath("//span[text()='Set Model Description']")), 10);
		businessProcess.searchOnBusinessProcess(ProcessModelNoWildCardName);
		driver.get(driver.getCurrentUrl());
		boolean noMatchesFound=driver.findElement(By.xpath((xpath==null)?"":xpath.no_matchesFound)).isDisplayed();
		Assert.assertTrue(noMatchesFound);
	}
	
	@Test(enabled = true)
	public void business_Process_I18NMatch_Business_Process_Search_From_Advanced_Tab() {
		businessProcess=new BusinessProcess();
		driver.navigate().to(prop.getProperty("url")+prop.getProperty("businessProcessURL"));
		businessProcess.clickOnAdvanceBtn();
		
		Action.fluentWait(driver, driver.findElement(By.xpath("//span[text()='Set Model Description']")), 10);
		businessProcess.searchOnBusinessProcess(ProcessModelI18NMatchName);
		driver.get(driver.getCurrentUrl());
		boolean noMatchesFound=driver.findElement(By.xpath((xpath==null)?"":xpath.no_matchesFound)).isDisplayed();
		Assert.assertTrue(noMatchesFound);
	}
	
	@Test(enabled = true)
	public void business_Process_CaseInsensitiveMatch_Business_Process_Search_From_Advanced_Keywords_Tab() {
		businessProcess=new BusinessProcess();
		driver.navigate().to(prop.getProperty("url")+prop.getProperty("businessProcessURL"));
		businessProcess.clickOnAdvanceBtn();
		
		Action.fluentWait(driver, driver.findElement(By.xpath("//span[text()='Set Model Description']")), 10);
		businessProcess.searchOnBusinessProcess(ProcessModelCaseInsensitiveName);
		driver.get(driver.getCurrentUrl());
    	String actualNoofBusinessProcess=driver.findElement(By.cssSelector((xpath==null)?"":xpath.noOfBusinessProcess_css)).getText();
		Assert.assertTrue(actualNoofBusinessProcess.contains(noOfBusinessProcess));	
	}
	
}
