package com.example.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class HelperWithWebDriverBase extends HelperBase{
	
	
	private WebDriver driver;

	
	public HelperWithWebDriverBase(ApplicationManager manager)
	{   super(manager);
		driver = manager.getWebDriverHelper().getDriver();
		}
	
	//Fill one element
	protected void type(By locator, String name) {
		driver.findElement(locator).clear();
	    driver.findElement(locator).sendKeys(name);
		}
	
	//fill Drop-down lists
	protected void fillDropDownElement(String name, String value) {
		if (isElementPresent(By.name(name))==true)
		{
		new Select(driver.findElement(By.name(name))).selectByVisibleText(value);}
	}



	protected void openUrl(String string) {
		driver.get(manager.getProperty("baseUrl") + string);
	}
	
	protected void openAbsolutUrl(String string) {
		driver.get(string);
	}
	
	public WebElement findElement(By linkText) {
		try {
		return driver.findElement(linkText);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public List<WebElement> findElements(By linkText) {
		return driver.findElements(linkText);
	}
	
	protected void click(By linkText) {
		findElement(linkText).click();
	}
	

	protected String CurrentUrl() {
		 	return driver.getCurrentUrl();
	 }
	 
	 
	private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e)
    {
      return false;
    }
  }
}
