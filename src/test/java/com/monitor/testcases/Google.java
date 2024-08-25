package com.monitor.testcases;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Google {
	public WebDriver driver;
	
	@Test
	public void lunchGoogle() {
		//WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
    	options.addArguments("--disable-gpu");
    	options.addArguments("--no-sandbox");
		driver = new ChromeDriver(options);
    	
		driver.get("https://www.google.com/");
	    System.out.println("Title of the page is: "+driver.getTitle());	
	    driver.quit();
	}
	
	
	
}