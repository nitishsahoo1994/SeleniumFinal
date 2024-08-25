package com.monitor.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.monitor.actiondriver.Action;
import com.monitor.base.BaseClass;

public class LoginPage extends BaseClass {
	
	@FindBy(xpath = "//img[@alt='My WebMethods']")
	WebElement myWebmethodLogo;
	
	@FindBy(xpath = "//input[@id='wm_login-username']")
	WebElement userName;
	
	@FindBy(xpath = "//input[@id='wm_login-password']")
	WebElement password;

	
	@FindBy(xpath = "//button[@id='submit_login']")
	WebElement loginBtn;

	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public HomePage login(String uname,String pass) {
		Action.type(userName, uname);
		Action.type(password, pass);
		Action.click(driver, loginBtn);
		return new HomePage();
	}
	
	public boolean validaetLogo() {
		return Action.isDisplayed(driver, myWebmethodLogo);
	}
	
	public String getTitle() {
		String monitorTitle=driver.getTitle();
		return monitorTitle; 
		
	}
	
}
