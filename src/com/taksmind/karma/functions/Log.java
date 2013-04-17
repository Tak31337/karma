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
package com.taksmind.karma.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;

import com.taksmind.karma.Main;

/**
 *
 * @author tak <tak@taksmind.com>
 */
public class Log extends Function {

    private static String channel;
    private static String ftpServer;
    private static String ftpUser;
    private static String ftpPassword;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");      
	private static String date;
    
    @Override
    public void run() {
        /*if there is a message store and check it*/
        if (bot.hasMessage()) {
            channel = bot.getChannel();
        }
		
        if (checkAccess(4, bot.getSender()) == false) {
            return;
        }
        
        if( bot.getMessage().startsWith("~uploadlogs") ) {
        	uploadFile();
        }
    }
    
    public static void uploadFile() {
		try {
			date = sdf.parse(sdf.format(new Date())).toString().replaceAll(" ", "_").replaceAll("_[0-9]+:[0-9]+:[0-9]+.CDT", "");
		    Properties properties = new Properties(); //creates configuration object
		    properties.load(new FileInputStream(Main.configuration));
		    ftpServer = properties.getProperty("ftpServer");
		    ftpUser   = properties.getProperty("ftpUser");
		    ftpPassword = properties.getProperty("ftpPassword");
		       
	        FTPClient client = new FTPClient();
	        client.setDataTimeout(6000000);
	        client.setConnectTimeout(6000000);
	        
	        client.connect("ftp.taksmind.com", 21);
	        client.login("tak31337", "B1aqu3sp0T");
	        
	        client.changeWorkingDirectory("/downloads/logs");
	        FileInputStream fis = new FileInputStream(new File("logs/" + date + ".log"));
	        client.storeFile("logs/" + date + ".log", fis);
	        client.logout();
		} catch (ParseException e) {
			System.err.println("Error parsing log file");
			e.printStackTrace();
		} catch (SocketException e) {
			Main.bot.sendMessage(channel, "some exception happened.");
			e.printStackTrace();
		} catch (IOException e) {
			Main.bot.sendMessage(channel, "can not open or upload file.");
			e.printStackTrace();
		}        
    }
}
