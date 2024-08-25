package com.monitor.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.monitor.actiondriver.Action;
import com.monitor.base.BaseClass;

public class BusinessProcess extends BaseClass { 
	
	@FindBy(css = "input[id$='searchResultsForm:searchBarControl:keywordsTextInput']")
	WebElement searchBox;
	
	@FindBy(css = "button[id$='searchResultsForm:searchBarControl:asyncSimpleSearchGoButton']")
	WebElement searchBtn;
	
	@FindBy(xpath = "//span[contains(text(),'Export Table')]")
	WebElement processModelNameFullName;
	
	@FindBy(xpath = "//a[text()='simpleProcess (WmMonitorUITestingProcesses)']")
	WebElement simpleProcess_UI;
	
	
	
	
	@FindBy(css = "a[id$='ProcessSettings']")
	WebElement processSetting;
	
	
	@FindBy(xpath = "//span[text()='Instances Threshold']")
	WebElement instances_threshold;
	
	@FindBy(xpath = "//label[text()='Log Transitions']")
	WebElement log_transition;
	
	
	@FindBy(xpath = "//span[text()='Save']")
	WebElement saveButton;
	
	
	
	@FindBy(xpath = "//a[text()='Step Settings']")
	WebElement step_setting_button;
	
	@FindBy(xpath = "//a[text()='Advanced']")
	WebElement advanceBtn;
	
	
	public BusinessProcess() {
		PageFactory.initElements(driver, this);
	}

	
	public  void searchOnBusinessProcess(String businessProcessName) {
		Action.type(searchBox, businessProcessName);
		Action.click(driver,searchBtn );
		
	}
	
	public void clickOn_processModelNameFullName() {
		Action.click(driver, processModelNameFullName);
	}
	
	
	public void clickOn_ProcessSetting() {
		Action.click(driver, processSetting);
	}
	
	public void waitTill_instanceThreshold() {
		Action.fluentWait(driver, instances_threshold, 10);
	}
	
	public void waitTill_LogTransition() {
		Action.fluentWait(driver, log_transition, 10);
	}
	
	public void click_saveButton() {
		Action.click(driver, saveButton);
	}
	
	public void click_step_setting() {
		Action.click(driver, step_setting_button);
	}
	
	public  void clickOnAdvanceBtn() {
		Action.click(driver, advanceBtn);
	}
	
	
	public  void clickOnsimpleProcess_UI() {
		Action.click(driver, simpleProcess_UI);
	}
	
}
