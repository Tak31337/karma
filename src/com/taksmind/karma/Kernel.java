/*
 *  Kernel.java
 * 
 *  Created on Oct 12, 2009, 2:05:57 PM
 * 
 *  Copyright (c) 2009 Hippos Development Team. All rights reserved.
 * 
 *  This file is part of JHippo.
 * 
 *  JHippo is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  JHippo is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with JHippo.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.taksmind.karma;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.taksmind.karma.events.Event;
import com.taksmind.karma.events.Message;
import com.taksmind.karma.events.PrivateMessage;
import com.taksmind.karma.functions.AutoVoice;
import com.taksmind.karma.functions.Crypto;
import com.taksmind.karma.functions.FloodProtection;
import com.taksmind.karma.functions.Function;
import com.taksmind.karma.functions.Google;
import com.taksmind.karma.functions.Help;
import com.taksmind.karma.functions.Join;
import com.taksmind.karma.functions.Kick;
import com.taksmind.karma.functions.Part;
import com.taksmind.karma.functions.Quit;
import com.taksmind.karma.functions.Say;
import com.taksmind.karma.functions.Slap;
import com.taksmind.karma.functions.Welcome;
import com.taksmind.karma.functions.Authentication.Access;
import com.taksmind.karma.functions.Authentication.Login;
import com.taksmind.karma.functions.Authentication.Register;
import com.taksmind.karma.util.Vote;



/**
 *
 * @author tak <tak@taksmind.com>
 */
public class Kernel {
    /**
     * Object to handle public threads
     */
    public ExecutorService publicThreadExecutor;
    /**
     * Object to handle private threads.
     */
    public ExecutorService privateThreadExecutor;

    /*imported events*/
    private Event Message;
    private Event PrivateMessage;

    /*imported functions*/
    private Function Quit;
    private Function Say;
    private Function Join;
    private Function Crypto;
    private Function Help;
    private Function Kick;
    private Function Vote;
    private Function Part;
    private Function Slap;
    private Function Welcome;
    private Function AutoVocie;

    private Function Google;
    private Function FloodProtection;
    
    //registration functions
    private Function Register;
    private Function Login;
    private Function Access;

    /**
     * Kernel class, handles functions.
     */
    public Kernel() {
        /*declare events*/
        Message = new Message();
        PrivateMessage = new PrivateMessage();

        /*declare functions*/
        Quit            = new Quit();
        Say             = new Say();
        Join            = new Join();
        Crypto          = new Crypto();
        Help            = new Help();
        Kick            = new Kick();
        Vote            = new Vote();
        Google          = new Google();
        Part            = new Part();
        Slap            = new Slap();
        Welcome         = new Welcome();
        AutoVocie       = new AutoVoice();

        FloodProtection = new FloodProtection();

        Register = new Register();
        Login = new Login();
        Access = new Access();
    }

    /**
     * @param channel channel message came from
     * @param sender person who sent message
     * @param login login from person who sent message
     * @param hostname host name of person who sent message
     * @param message message that was sent
     * @param calendar time the message was sent
     */
    public void onMessage(String channel, String sender, 
                          String login,   String hostname,
                          String message, Date calendar) {


        /*sends data to Message, and onto Listener*/
        ArrayList<Object> args = new ArrayList<Object>();
        args.add(channel);
        args.add(sender);
        args.add(login);
        args.add(hostname);
        args.add(message);
        args.add(calendar);

        Message.eventStart(args);

        createFunctions();

        /*runs debugging method if activated*/
        if (Main.debug) {
            debug("onMessage()", args);
        }

    }

    /**
     * @param sender sender of the private message
     * @param login login of the person who sent private message
     * @param hostname hostname of the person who sent private message
     * @param message message that was sent.
     * @param calendar time that the private message was sent. 
     */
    public void onPrivateMessage(String sender, String login,
            String hostname, String message,
            Date calendar) {

        /*sends data to Message, and onto Listener*/
        ArrayList<Object> args = new ArrayList<Object>();
        args.add(sender);
        args.add(login);
        args.add(hostname);
        args.add(message);
        args.add(calendar);

        PrivateMessage.eventStart(args);

        createPrivateFunctions();

        if (Main.debug) {
            debug("onPrivateMessage()", args);
        }
    }

    /**
     * @param method method that was called
     * @param args arguments passed through message.
     * debugging message for added verbosity
     */
    public void debug( String method, ArrayList<Object> args ) {

        /*dumping variables to screen*/
        System.out.println(method + " {");

        for (Object arg : args) {
            System.out.println("     " + arg.toString());
        }

        System.out.println(" }");
    }

    /**
     * method for creation and management of public functions.
     */
    public void createFunctions() {
        publicThreadExecutor = Executors.newCachedThreadPool();
        publicThreadExecutor.execute( Quit );
        publicThreadExecutor.execute( Say );
        publicThreadExecutor.execute( Join );
        publicThreadExecutor.execute( Crypto );
        publicThreadExecutor.execute( Access );
        publicThreadExecutor.execute( Help );
        publicThreadExecutor.execute( Kick );
        publicThreadExecutor.execute( Vote );
        publicThreadExecutor.execute( Google );
        publicThreadExecutor.execute( FloodProtection );
        publicThreadExecutor.execute( Part );
        publicThreadExecutor.execute( Slap );
        publicThreadExecutor.execute( Welcome );
        publicThreadExecutor.execute( AutoVocie );
    }

    /**
     * method for creation and management of private functions.
     */
    public void createPrivateFunctions() {
        privateThreadExecutor = Executors.newCachedThreadPool();
        privateThreadExecutor.execute( Register );
        privateThreadExecutor.execute( Login );
    }
}
