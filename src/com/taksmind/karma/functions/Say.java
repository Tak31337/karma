/*
 *  Say.java
 * 
 *  Created on Oct 14, 2009, 7:22:22 PM
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

import java.util.NoSuchElementException;

import com.taksmind.karma.Main;

/**
 *
 * @author tak <tak@taksmind.com>
 */
public class Say extends Function {

    private String message;
    private String sendTo;
    private String sendMessage = "";

    public void run() {
        /*if there is a message store and check it*/
        if (bot.hasPrivateMessage()) {
            message = bot.getPrivateMessage();
        }

        /*This method checks if the user has the proper access*/
        if (checkAccess(2, bot.getPrivateSender()) == false) {
            return;
        }

        if (message.startsWith("~say")) {
            tokenize(true, 4, message);
            sendTo = (String) tokenParameters.nextElement();

            while (tokenParameters.hasMoreElements()) {
                sendMessage += (String) tokenParameters.nextElement() + " ";
            }

            try {
                Main.bot.sendMessage(sendTo,
                        sendMessage);
            } catch (NoSuchElementException e) {
                e.printStackTrace();
                System.err.println("incorrect syntax");
            }

            sendMessage = "";
        }
    }
}
