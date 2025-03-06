package com.monitor.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import com.monitor.actiondriver.Action;

public class BaseClass {
	//private static final Capabilities Capabilities = null;
	public static Properties prop;
	public static Properties loc;

	public static WebDriver driver;
	//loadConfig method is to load the configuration
	@BeforeSuite(groups = { "Smoke", "Sanity", "Regression" })
	public void loadConfig() {
		DOMConfigurator.configure("log4j.xml");

		try {
			prop = new Properties();
			loc =new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/Configuration/Config.properties");
			FileInputStream ipLocator = new FileInputStream(
					System.getProperty("user.dir") + "/Configuration/locator.properties");
			
			prop.load(ip);
			loc.load(ipLocator);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void launchApp() throws MalformedURLException  {
		ChromeOptions options=new ChromeOptions();
	    	options.addArguments("--headless");
	    	options.addArguments("--disable-gpu");
	    	options.addArguments("--no-sandbox");
	    	
    	 	driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		Action.implicitWait(driver, 10);
		Action.pageLoadTimeOut(driver, 30);
		driver.get(prop.getProperty("url"));
	}
}
