package com.example.fw;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.FindElement;
public class AccountHelper extends HelperWithWebDriverBase {

	public  AccountHelper(ApplicationManager manager) {
		super(manager);
	}

	public void signup(User user) {	
		openUrl("/");
		click(By.xpath("html/body/div[4]/span[1]/a"));//("SPAN.bracket-link"));
		fillFormSignup(user);
	}

	private void fillFormSignup(User user) {
		type(By.name("username"), user.login);
		type(By.name("email"), user.email);
		click(By.cssSelector("input.button"));
		WebElement ErrorMessage = findElement(By.cssSelector("table.width50 tbody tr td p"));
		if (ErrorMessage!= null){
			 throw new RuntimeException(ErrorMessage.getText());
		}
		String msg = manager.getMailHelper().getNewMail(user.login, user.password);
		String confirmationLink = getConfirmationLink(msg);
		openAbsolutUrl(confirmationLink);
		type(By.name("password"), user.password);
		type(By.name("password_confirm"), user.password);
		click(By.cssSelector("input.button"));
	}

	public String getConfirmationLink(String msg) {
		Pattern regex = Pattern.compile("http\\S*");
		Matcher matcher = regex.matcher(msg);
		if (matcher.find()) {
			return matcher.group();
		} else {
			return "";
		}
	}

	public void login(User user) {
		openUrl("/");
		type(By.name("username"), user.login);
		type(By.name("password"), user.password);
		click(By.cssSelector("input.button"));

	}
	
	public String loggedUser() {
        WebElement Element = findElement(By.cssSelector("td.login-info-left"));
        return Element.getText();
		
	}
	
	public void deleteUser(String user) {
		
		WebElement LogOut = findElement(By.cssSelector("[href*='logout_page.php']"));
		 if (LogOut!=null)  
			 LogOut.click();
		openUrl("/");
		type(By.name("username"), manager.getProperty("admin.username"));
		type(By.name("password"), manager.getProperty("admin.password"));
		click(By.cssSelector("input.button"));
		click(By.linkText("Manage"));

		click(By.cssSelector("[href*='manage_user_page.php']"));
		type(By.name("username"), user);
		click(By.xpath("html/body/form/input[2]"));
		click(By.cssSelector("[action*='manage_user_delete.php']>input.button"));
		click(By.cssSelector("input.button"));
		//click(By.cssSelector("Delete User"));
		//click(By.cssSelector("Delete Account"));
	}
}
