package com.monitor.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.monitor.actiondriver.Action;
import com.monitor.base.BaseClass;

public class HomePage extends BaseClass{
	
	@FindBy(xpath = "//a[text()='Logout']")
	WebElement signoutBtn;
	
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	
	public void signOut() {
		Action.click(driver, signoutBtn);
	}
}
