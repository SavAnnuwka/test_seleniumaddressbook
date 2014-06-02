package com.example.fw;

import java.util.Properties;

public class ApplicationManager {

	private static ApplicationManager singleton;

	private WebDriverHelper webDriverHelper;
	private HibernateHelper hibernateHelper;
	private Properties props;
	private AccountHelper accountHelper;

	private MailHelper mailHelper;

	private JamesHelper jamesHelper;

	private FtpHelper ftpHelper;

	private AdminHelper adminHelper;





	public static ApplicationManager getInstance() {
		if (singleton == null) {
			singleton = new ApplicationManager();
		}
		return singleton;
	}

	public void stop() {
		if (webDriverHelper != null) {
			webDriverHelper.stop();
			ftpHelper.restoreFile();
		}
	}
	
	public WebDriverHelper getWebDriverHelper() {
		if (webDriverHelper == null) {
			webDriverHelper = new WebDriverHelper(this);
		}
		return webDriverHelper;
	}


	public HibernateHelper getHibernateHelper() {
		if (hibernateHelper == null) {
			hibernateHelper = new HibernateHelper(this);
		}
		return hibernateHelper;
	}


	public void setProperties(Properties props) {
		this.props = props;
	}
	
	public String getProperty(String key) {
		return props.getProperty(key);
	}

	public String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}

	public AccountHelper getAccountHelper() {
		if (accountHelper == null) {
			accountHelper = new AccountHelper(this);
		}
		return accountHelper;
	}
	
	public MailHelper getMailHelper() {
		if (mailHelper == null) {
			mailHelper = new MailHelper(this);
		}
		return mailHelper;
	}
		public JamesHelper getJamesHelper() {
			if (jamesHelper == null) {
				jamesHelper = new JamesHelper(this);
			}
			return jamesHelper;
		}

		public FtpHelper getFtpHelper() {
			if (ftpHelper == null) {
				ftpHelper = new FtpHelper(this);
			}
			return ftpHelper;
		}

		public AdminHelper getAdminHelper() {
			if (adminHelper == null) {
				adminHelper = new AdminHelper(this);
			}
			return adminHelper;
		}

	

}
