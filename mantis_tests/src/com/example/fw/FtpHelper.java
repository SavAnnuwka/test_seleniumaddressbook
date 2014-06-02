package com.example.fw;


import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FtpHelper extends HelperBase{

	public static Logger log = Logger.getLogger(FtpHelper.class.getName());

	private FTPClient ftp;

	public FtpHelper(ApplicationManager manager) {
		super(manager);
	}

	private void initFtpConnection() {
		String ftpserver = manager.getProperty("ftp.host");
		String login = manager.getProperty("ftp.login");
		String password = manager.getProperty("ftp.password");
		String appPath = manager.getProperty("ftp.appPath");

		if (ftp == null) {
			ftp = new FTPClient();
		}
		if (ftp.isConnected()) {
			return;
		}

		try {
			ftp.connect(ftpserver);
		    ftp.login(login, password);
		    log.info("Connected to"+ftpserver+":"+ftp.getReplyString());
			ftp.changeWorkingDirectory(appPath);

	    } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void installConfigWithoutCaptcha() {
		String configFile= manager.getProperty("ftp.configFile");
		initFtpConnection();
		try {
			// Check if there is backup of config file
			boolean backupExists = false;
			FTPFile[] files = ftp.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().equals(configFile+".bak")) {
					backupExists = true;
					break;
				}
			}
			if (backupExists) {
				ftp.rename(configFile, configFile+".bak");
			}

		 InputStream in = this.getClass().getResourceAsStream("/"+configFile);
		ftp.storeFile(configFile, in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeFtpConnection();
	}
	
	
	
	/*public void backupFile(String file, String fileBackup) {
		initFtpConnection();
		try {
			// Check if there is backup of config file
			boolean backupExists = false;
			FTPFile[] files = ftp.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().equals(fileBackup)) {
					backupExists = true;
					break;
				}
			}
			if (!backupExists) {
				ftp.rename(file, fileBackup);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		closeFtpConnection();
	}*/

	public void restoreFile() {
		String configFile= manager.getProperty("ftp.configFile");
		initFtpConnection();
		try {
			// Check if there is backup of config file
			boolean backupExists = false;
			FTPFile[] files = ftp.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().equals(configFile+".bak")) {
					backupExists = true;
					break;
				}
			}
			if (backupExists) {
				ftp.deleteFile(configFile);
				ftp.rename(configFile+".bak", configFile);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	closeFtpConnection();
	}

	public void uploadFile(InputStream in, String targetFile) {
		initFtpConnection();
		try {
			ftp.storeFile(targetFile, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeFtpConnection() {
		try {
			ftp.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
