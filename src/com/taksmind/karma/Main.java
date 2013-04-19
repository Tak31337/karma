/*
 *  Main.java
 *
 * Created on 6 October 2009, 11:03 PM
 *
 *  Copyright (c) 2009, Hippos Development Team
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
package com.taksmind.karma;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.jibble.jmegahal.JMegaHal;

import com.taksmind.karma.util.Delete2;
import com.taksmind.karma.util.TakeInput;


/**
 *
 * @author tak <tak@taksmind.com>
 */
public class Main {
    /**
     * debugging mode variable
     */
    public static boolean debug = true;
    
    /**
     * Welcome message variable
     */
    public static boolean welcome = false;
    
    /**
     * 
     */
    public static boolean autoVoice = false;
    
    /**
     * public HashMap for all users signed into Karma
     */
    public static HashMap<String, String> users = new HashMap<String, String>();

    /**
     * public declaration of our bot object
     */
    public static Karma bot;

    /**
     * public declaration of our brain.
     */
    public static JMegaHal ai;
    
    /**
     * variable to handle our configuration file
     */
    public static String configuration = "config/Karma.properties";
    
    
    /**
     * variable to handle taking command line user input.
     * (and subset variables to store data)
     */
    public  static TakeInput userInput = new TakeInput();
    private static String   server;
    private static String   password;
    private static String   nick;
    private static String   channel;
    private static String   plugins;
    private static String   ftpServer;
    private static String   ftpUser;
    private static String   ftpPassword;
    
    private static File file;
    private static File configDirectory;
    /**
     * @param args arguments passed from command line
     * @throws Exception 
     * Karma command line based main method.
     */
    public static void main(String[] args) throws Exception {
        /*GPLv3 copyright*/
        System.out.println("Karma Copyright (C) 2009 Hippos Development Team\n" 
                           +"This program comes with ABSOLUTELY NO WARRANTY.\n"
                           +"This is free software, and you are welcome to redistribute it\n"
                           +"under certain conditions; see http://www.gnu.org/licenses/\n\n");

        /*check for -debug flag*/
        if (args.length > 0) {
            for (String arg : args) {
                if (arg.equalsIgnoreCase("--debug")) {
                    debug = true;
                } else if (arg.equalsIgnoreCase("--help") || arg.equalsIgnoreCase("-h")) {
                    System.out.println("-h or --help, this menu\n"
                                       +"--debug, enter debugging mode\n");
                }
            }
        }

        Properties properties = new Properties(); //creates configuration object
        try {
            properties.load(new FileInputStream(configuration));
        } catch (IOException ioex) {
        	configDirectory = new File("config");
        	if ( !configDirectory.exists() ) {
        		configDirectory.mkdir();
        	}
            System.err.println("Error loading " + configuration + " Correctly generating now."); 
            Delete2.delete(configuration);
            
            server   = userInput.grabInput("Enter IRC Server: ");
            password = userInput.grabInput("Enter Nickserv Password: ");
            nick     = userInput.grabInput("Enter Karma's Nick/IRC Handle: ");
            channel  = userInput.grabInput("Enter the channel to join: ");
            plugins  = userInput.grabInput("Enter plugin directory: ");
            ftpServer = userInput.grabInput("Enter your FTP server (optional): ");
            ftpUser  = userInput.grabInput("Enter your FTP username (optional): ");
            ftpPassword = userInput.grabInput("Enter your FTP password (optional): ");
            
            //makes our new file.
            file = new File(configuration);
            file.createNewFile();
            
            properties.load(new FileInputStream(configuration));
            properties.put("server", server);
            properties.put("password", password);
            properties.put("nick", nick);
            properties.put("channel", channel);
            properties.put("plugins", plugins);
            properties.put("ftpServer", ftpServer);
            properties.put("ftpUser", ftpUser);
            properties.put("ftpPassword", ftpPassword);
            
            properties.store(new FileOutputStream(configuration), null);
        }

        ai = new JMegaHal();
        ai.add("My name is karma");
        ai.add("Hello, how are you doing?");
        ai.add("this sucks. :/");
        ai.add("google it!");
        ai.add("Have you tried the ~help command?");
        
        /*create our robot*/
        bot = new Karma();
        bot.connect(properties.getProperty("server"));
        if ( properties.getProperty("password") != null ) {
            bot.identify(properties.getProperty("password"));
        }
        bot.joinChannel(properties.getProperty("channel"));
    }    
}
