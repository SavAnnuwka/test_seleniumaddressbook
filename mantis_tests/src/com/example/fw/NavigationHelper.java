package com.example.fw;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperWithWebDriverBase {
	

	public NavigationHelper(ApplicationManager manager){
		super(manager);
		}
	
	
	public void goToGroupListPage() {
		click(By.linkText("groups"));
	}

	//open main page
	public void openMainPage() {		
		openUrl(manager.getProperty("baseUrl"));
	}


	public void goAddContactPage() {
		// check page and go to main if we are not on the main page
		click(By.linkText("add new"));
	}
	
	//return to Home page
		protected void returnToContactListPage() {
			// check page and go to main if we are not on the main page
			click(By.linkText("home"));
		}


		public void goPrintPhonepage() {
			click(By.linkText("print phones"));
			
		}


		public void clickExport() {
			openUrl(manager.getProperty("baseUrl")+ "csv.php");
			
		}
	
}
