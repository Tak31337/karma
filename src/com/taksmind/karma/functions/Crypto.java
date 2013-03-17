/*
 *  Crypto.java
 * 
 *  Created on Oct 27, 2009, 11:08:41 PM
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

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.taksmind.karma.Main;
import com.taksmind.karma.util.MD5;

/**
 *
 * @author tak <tak@taksmind.com>
 */
public class Crypto extends Function {

    private String message;
    private String sendTo;
    private MD5 encryptor;

    @Override
    public void run() {
        /*if there is a message store and check it*/
        if (bot.hasMessage()) {
            message = bot.getMessage();
            sendTo = bot.getChannel();
        }

        if (message.startsWith("~md5")) {

            try {
                encryptor = MD5.getInstance();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("couldn't create md5 object");
            }

            tokenize(false, 4, message);

            try {
                Main.bot.sendMessage(sendTo, encryptor.hashData(parameters.getBytes()));
            } catch (Exception ex) {
                Main.bot.sendAction(sendTo, "Could not encrypt.");
            }

        }
    }
}
