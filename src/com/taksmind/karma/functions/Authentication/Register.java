/*
 *  Register.java
 * 
 *  Created on Oct 28, 2009, 5:19:20 PM
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
package com.taksmind.karma.functions.Authentication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.taksmind.karma.Main;
import com.taksmind.karma.functions.Function;
import com.taksmind.karma.util.MD5;


/**
 *
 * @author tak <tak@taksmind.com>
 */
public class Register extends Function {

    private String message;
    private Properties register;
    private Properties access;
    private MD5 encryptor;
    
    private static File userFile;
    private static File accessFile;
    
    private boolean firstRun = false;

    @Override
    public void run() {
        if (bot.hasPrivateMessage()) {
            message = bot.getPrivateMessage();
        }

        if (message.startsWith("~register")) {
            tokenize(false, 9, message);

            try {
                encryptor = MD5.getInstance();
                register  = new Properties();
                access    = new Properties();
                userFile  = new File("config/users.properties");
                accessFile = new File("config/access.properties");

                /*writes to passwd file, ex. user=SDFA234FSE434*/
                if ( !userFile.exists() ) {
                	userFile.createNewFile();
                }
                register.load(new FileInputStream("config/users.properties"));
                register.put(bot.getPrivateSender(),
                        encryptor.hashData(parameters.getBytes()));
                register.store(new FileOutputStream("config/users.properties"), null);
                
                if ( !accessFile.exists() ) {
                	accessFile.createNewFile();
                	firstRun = true;
                }
                access.load(new FileInputStream("config/access.properties"));
                if (firstRun) {
                	access.put(bot.getPrivateSender(), "4");
                } else {
                	access.put(bot.getPrivateSender(), "0");
                }
                access.store(new FileOutputStream("config/access.properties"), null);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE,
                        null, ex);
                System.err.println("couldn't create md5 object");
            } catch (IOException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE,
                        null, ex);
                System.err.println("Input Output error");
            }

            Main.bot.sendMessage(bot.getPrivateSender(),
                    "registration successful ");
        }
    }
}
