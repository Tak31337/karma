/*
 *  Join.java
 * 
 *  Created on Oct 25, 2009, 11:51:35 AM
 * 
 *  Copyright (c) 2009 Hippos Development Team. All rights reserved.
 * 
 *  This file is part of Karma.
 * 
 *  Karma is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  Karma is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with Karma.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.taksmind.karma.functions.scheduled;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.taksmind.karma.Main;

/**
 *
 * @author tak <tak@taksmind.com>
 */
public class Log implements Runnable {

    private static String ftpServer;
    private static String ftpUser;
    private static String ftpPassword;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");      
	private static String date;
	private static File logDirectory;
	private static FTPClient client;
    
    @Override
    public void run() {
    	try {
			uploadFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void uploadFile() throws IOException {
		try {
			logDirectory = new File("logs");
    		if ( !logDirectory.isDirectory() ) {
    			logDirectory.mkdir();
    		}
    		date = sdf.parse(sdf.format(new Date())).toString().replaceAll(" ", "").replaceAll("[0-9]+:[0-9]+:[0-9]+CDT", "");
    		
			Properties properties = new Properties(); //creates configuration object
		    properties.load(new FileInputStream(Main.configuration));
		    ftpServer = properties.getProperty("ftpServer");
		    ftpUser   = properties.getProperty("ftpUser");
		    ftpPassword = properties.getProperty("ftpPassword");
		       
	        client = new FTPClient();
	        
	        client.connect(ftpServer);
            int reply = client.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                client.disconnect();
                System.err.println("FTP server refused connection.");
            }
            
	        if (client.login(ftpUser, ftpPassword)) {
	        		client.enterLocalPassiveMode();
	        		String localFile = logDirectory.getAbsolutePath() + "/" + date + ".log";
	        		String remoteFile = "/downloads/logs/" + date + ".log";
	        		if( Main.debug ) {
	        			System.out.println(localFile);
	        			System.out.println(remoteFile);
	        		}
			        FileInputStream fis = new FileInputStream(new File(localFile));
			        if (client.storeFile(remoteFile, fis)) {
			        	System.out.println("File uploaded successfully.");
			        } 
			        else {
			        	System.err.println("Uploading file failed..");
			        }
			        fis.close();
			        client.logout();
			        client.disconnect();
	        } else {
	        	System.out.println("Could not authenticate with FTP server..");
	        	client.logout();
	        }
	        
		} catch (ParseException e) {
			System.err.println("Error parsing log file");
			e.printStackTrace();
		} catch (SocketException e) {
			System.err.println("some exception happened.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("can not open or upload file.");
			e.printStackTrace();
		} finally {
			if (client.isConnected()) {
				client.disconnect();
			}
		}
    }
}
