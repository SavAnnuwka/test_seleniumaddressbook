package com.example.fw;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverHelper {

	private WebDriver driver;
	//private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private final ApplicationManager manager;
	
	public WebDriverHelper(ApplicationManager manager){
		this.manager = manager;
		String browser = manager.getProperty("browser");
		if ("firefox".equals(browser)){
			driver = new FirefoxDriver();
	    } else if ("chrome".equals(browser)){
	    	String path = manager.getProperty("pathWebDriverChrome");
	    	System.setProperty("webdriver.chrome.driver", path);
		    driver = new ChromeDriver();
		    
	    } else if ("ie".equals(browser)){
	    	   driver = new InternetExplorerDriver();
	    }
		String temp = manager.getProperty("implicitWait" , "11");
		driver.manage().timeouts().implicitlyWait(
				Integer.parseInt(temp), TimeUnit.SECONDS);
		driver.get(manager.getProperty("baseUrl"));
	}
	
	public void stop() {
		driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) 
	    {
	      fail(verificationErrorString);
	    }	
	}

	public WebDriver getDriver() {
		return driver;
	}
}
