/*
 *  Login.java
 * 
 *  Created on Nov 5, 2009, 12:08:47 AM
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

import java.io.FileInputStream;
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
public class Login extends Function {

    private Properties users = new Properties();
    private Properties access = new Properties();
    private MD5 encryptor;
    private String message;
    private String password;

    public void run() {
        if (bot.hasPrivateMessage()) {
            message = bot.getPrivateMessage();
        }

        if (message.startsWith("~login")) {
            tokenize(false, 6, message);

            try {
                encryptor = MD5.getInstance();
                users.load(new FileInputStream("config/users.properties"));
                access.load(new FileInputStream("config/access.properties"));

                /*encrypts given password, ex. user=SDFA234FSE434*/
                password = encryptor.hashData(parameters.getBytes());

            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE,
                        null, ex);
                System.err.println("couldn't create md5 object");
            } catch (IOException ex) {
                System.err.println("Error loading configuration file(s): [try !register first]");
                System.exit(0);
            }

            if (users.getProperty(bot.getPrivateSender()).equals(password)) {
                Main.users.put(bot.getPrivateSender(),
                        access.getProperty(bot.getPrivateSender()));

                Main.bot.sendMessage(bot.getPrivateSender(),
                        "Login successful access level: "
                        + Main.users.get(bot.getPrivateSender()));
            } else {
                Main.bot.sendMessage(bot.getPrivateSender(),
                        "Login Failed");
            }
        }
    }
}
