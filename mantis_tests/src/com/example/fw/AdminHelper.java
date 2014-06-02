package com.example.fw;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WaitingRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AdminHelper extends HelperBase{

	private WebClient web;
	private String baseUrl;
	

	public AdminHelper(ApplicationManager applicationManager) {
		super(applicationManager);
		web = new WebClient();
		web.setRefreshHandler(new ThreadedRefreshHandler());
		baseUrl = manager.getProperty("baseUrl");	
	}

	public boolean userExist(User user) throws Exception {
		HtmlPage userPage = openUserPage(user);
		HtmlForm deleteForm = findUserRemovalForm(userPage);
	    return  deleteForm!=null; 
	}
	
	public void deleteUser(User user) throws Exception {
		
		HtmlPage userPage = openUserPage(user);
		HtmlForm deleteForm = findUserRemovalForm(userPage);
		if (deleteForm!=null)
		{
		HtmlPage commitPage = (HtmlPage) deleteForm.getInputByValue("Delete User").click();
		HtmlForm commitForm = findUserRemovalForm(commitPage);
		commitForm.getInputByValue("Delete Account").click();
		}
	}




	private HtmlPage openUserPage(User user) throws Exception {	
	    loginAdminIfRequered();
		String userId = manager.getHibernateHelper().getUserId(user.login);	
		HtmlPage userPage = (HtmlPage) web.getPage(baseUrl+"manage_user_edit_page.php?user_id="+userId);
		return userPage;
	}

	private String loginAdminIfRequered() throws IOException, MalformedURLException {
		
		String adminLogin = manager.getProperty("admin.login");
		String adminPassword = manager.getProperty("admin.password");
		
			
		HtmlPage  mainPage = web.getPage(baseUrl);
		HtmlForm loginForm = mainPage.getFormByName("login_form");
		if (loginForm!=null)
		{
			loginForm.getInputByName("username").setValueAttribute(adminLogin);
			loginForm.getInputByName("password").setValueAttribute(adminPassword);
			loginForm.getInputByValue("Login").click();
		}
		return baseUrl;
	}

	private HtmlForm findUserRemovalForm(HtmlPage userPage) {
		List<HtmlForm> forms = userPage.getForms();
		for (HtmlForm form : forms) {
			if ("manage_user_delete.php".equals(form.getActionAttribute())){
				return form;
			}
				
		}
		return null;
	}
}


