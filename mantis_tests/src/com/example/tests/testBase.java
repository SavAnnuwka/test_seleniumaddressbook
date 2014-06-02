package com.example.tests;



import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.hibernate.transform.ToListResultTransformer;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.util.logging.Logger;
import com.example.fw.ApplicationManager;

public class testBase {
    
	protected Logger log = Logger.getLogger("TEST");
	
	public static ApplicationManager app;
	

	@BeforeClass
	@Parameters ({"configFile"})
	public void setUp(@Optional String configFile ) throws Exception {
		if (configFile==null) {
			configFile = "application.properties";
		}
		Properties props = new Properties();
		props.load(new FileReader(configFile));
		 log.info("setUp start");
		 app =  ApplicationManager.getInstance();
		 app.setProperties(props);
		 log.info("setUp end");
		 app.getFtpHelper().installConfigWithoutCaptcha();
	  }
	
	@AfterTest
	public void tearDown() throws Exception {
		 log.info("tearDown start");
		ApplicationManager.getInstance().stop();
		 log.info("tearDown end");

	  }
	
	
}

	