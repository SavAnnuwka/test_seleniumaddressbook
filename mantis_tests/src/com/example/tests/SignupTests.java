package com.example.tests;

import static org.testng.Assert.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import com.example.fw.AccountHelper;
import com.example.fw.AdminHelper;
import com.example.fw.JamesHelper;
import com.example.fw.User;

public class SignupTests extends testBase {
	public User user = new User()
	.setLogin("testuser1")
	.setPassword("qa12345")
	.setEmail("testuser1@localhost.localdomain");
	private JamesHelper james;
	private AccountHelper accHelper;
	private AdminHelper admin;
	
    
    @BeforeClass
    public void  initShortcuts(){
    	james = app.getJamesHelper();
    	admin = app.getAdminHelper();
    	accHelper = app.getAccountHelper();
    
    }
   
    public void createUser()
    {   if (!james.doesUserExist(user.login))
    	james.createUser(user.login, user.password);
    }
	
	@Test
	public void newUserShouldSignUp() throws Exception{
		createUser();
		admin.deleteUser(user);
	    accHelper.signup(user);
		accHelper.login(user);
		app.getMailHelper().getNewMail(user.login, user.password);
		assertThat(accHelper.loggedUser(), equalTo("Logged in as: "+ user.login + " (reporter)"));
			
	}
	
	//@Test
	public void existingUserShouldSignUp(){
	/*	if (!admin.userExist(user)){
			admin.create(user);
		}*/
		try {
			accHelper.signup(user);
		} catch (Exception e)
		{    
		 assertThat(e.getMessage(), containsString("That username is already being used. Please go back and select another one."));
		 return;	
		} fail("Exception expected");
		//DeleteMantisUser(user.login);
	}

	
	
	public void DeleteMantisUser(String user){
		
		accHelper.deleteUser(user);
	}
	
	  @AfterClass
	    public void deleteUser() {
		  if (james.doesUserExist(user.login))
			  james.deleteUser(user.login);
		 
	    }
}
