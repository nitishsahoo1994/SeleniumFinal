package com.monitor.xpath;



public class Xpath {
	
	 public String simpleProcessLink_WmMonitorTestProcesses="//a[text()='simpleProcess (WmMonitorUITestingProcesses)']";
	 
//	 Edit Process Page
	 public String modelname_css="div[id$='processDetailsForm:modelNameLine']>div";
	 public String modelVersion_css="div[id$='processDetailsForm:modelVersionLine']>div";
	 public String modelUsed_css="div[id$='processDetailsForm:usedLine']>div";
	 public String modelDesc_css="div[id$='processDetailsForm:descriptionLine']>div";
	 public String executionEnabledCheckBox="input[id$='processDetailsForm:executionEnabledCheck']";
	 public String process_setting_css="a[id$='ProcessSettings']";
	 public String instance_threshold="//span[text()='Instances Threshold']";
	 public String simpleProcess_UI="//a[text()='simpleProcess (WmMonitorUITestingProcesses)']";
	 
	 
	 public String simple_flow="//td[text()='Simple Flow2']/following-sibling::td/child::input[1]";
	 public String five_minute_wait="//td[text()='Five Minute Wait']/following-sibling::td/child::input[1]";
	 
	 public String noOfBusinessProcess_css="span[id*='dataTotal__primary']";
	 public String no_matchesFound="//span[text()='No matches found']";
	 
	 
	 
	 //Process Instance Page
	 public String instanceDetailsIcon="//span[text()='simpleProcess']//parent::td//following-sibling::td[@class='caf-align-center']//child::a//child::img";
	 
	 public String instanceId="//span[text()='simpleProcess']//parent::td//following-sibling::td[2]//child::span";
	 
	 
	
}

