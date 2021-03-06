/*
 *  Karma.java
 *
 *  Created on 6 October 2009, 11:03 PM
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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

/**
 *
 * @author tak <tak@taksmind.com>
 */
public class Karma extends PircBot {
    Kernel sendEvent;  //kernel/event handler object
    
    /**
     * Configuration object
     */
    public static Properties properties;
    Date calendar = null; //to create our timestamps
    String timeStamp = null;
    String topic = "";

    /**
     * Karma class, sets Karma properties
     * based on configuration. 
     */
    public Karma() {
        /*loading the Karma configuration file*/
        properties = new Properties();
        try {
            properties.load(new FileInputStream("config/Karma.properties"));
        } catch (IOException ioex) {
            System.err.println("Error loading config file: Karma.properties, Generating now");
            
            ioex.printStackTrace();
            System.exit(0);
        }

        /*Set all Karmas pircbot options*/
        this.setName(properties.getProperty("nick"));
        this.setVersion("Karma v3.1.0");
        this.setVerbose(true);
        this.setAutoNickChange(true);
        this.setLogin("Karma");

        /*making kernel object to handle events*/
        sendEvent = new Kernel();
    }
    
    @Override
    public void onJoin(String channel, String sender, 
    		String login, String hostname) {
    	if( !sender.equalsIgnoreCase(this.getNick()) && Main.welcome ) {
    		this.sendMessage(channel, "Welcome " + sender + "!");
    	}
    	
    	if( !sender.equalsIgnoreCase(this.getNick()) && Main.autoVoice ) {
    		this.voice(channel, sender);
    	}
    }
    
    @Override
    public void onMessage(String channel, String sender,
            String login, String hostname,
            String message) {

        calendar = Calendar.getInstance().getTime();
        //timeStamp = calendar.toString();        
        sendEvent.onMessage(channel, sender, login, hostname, message, calendar);
        if( message.startsWith("~") ) {
        	Main.ai.add(message.substring(0, message.length()));
        }
        else {
        	Main.ai.add(message);
        }
    }

    @Override
    public void onPrivateMessage(String sender, String login,
            String hostname, String message) {

        calendar = Calendar.getInstance().getTime();
        sendEvent.onPrivateMessage(sender, login, hostname, message, calendar);
    }

    @Override
    public void onDisconnect() {
        try {
            reconnect();
        } catch (IOException ex) {
            Logger.getLogger(Karma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IrcException ex) {
            Logger.getLogger(Karma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onKick(String channel, String kickerNick,
            String kickerLogin, String kickerHostname,
            String recipientNick, String reason) {
        if (recipientNick.equals(Main.bot.getName())) {
            Main.bot.joinChannel(channel);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Karma.class.getName()).log(Level.SEVERE, null, ex);
            }
            Main.bot.ban(channel, kickerNick);
            Main.bot.kick(channel, kickerNick, "Beware. I am a robot");
        }
    }

}
