/*
 *  Access.java
 * 
 *  Created on Nov 5, 2009, 9:44:59 PM
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
package com.taksmind.karma.functions.Authentication;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.taksmind.karma.Main;
import com.taksmind.karma.functions.Function;


/**
 *
 * @author tak <tak@taksmind.com>
 */
public class Access extends Function {

    private String message;
    private String user;
    private String level;
    private Properties access;

    public void run() {
        /*if there is a message store and check it*/
        if (bot.hasPrivateMessage()) {
            message = bot.getPrivateMessage();
        }

        /*This method checks if the user has the proper access*/
        if (checkAccess(4, bot.getPrivateSender()) == false) {
            return;
        }

        if (message.startsWith("~access")) {
            tokenize(true, 7, message);

            user = tokenParameters.nextToken();
            level = tokenParameters.nextToken();

            try {
                access = new Properties();

                access.load(new FileInputStream("config/access.properties"));
                access.put(user, level);
                access.store(new FileOutputStream("config/access.properties"), null);
            } catch (IOException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE,
                        null, ex);
                System.err.println("Error loading configuration file(s): [try !register first]");
            }

            Main.bot.sendMessage(bot.getPrivateSender(), "access level changed");
        }
    }
}
