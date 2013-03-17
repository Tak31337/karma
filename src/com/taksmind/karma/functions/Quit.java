/*
 *  Quit.java
 * 
 *  Created on Oct 14, 2009, 5:44:45 PM
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
package com.taksmind.karma.functions;

import com.taksmind.karma.Main;

/**
 *
 * @author tak <tak@taksmind.com>
 */
public class Quit extends Function {

    private String message;

    public void run() {
        /*if there is a message store and check it*/
        if (bot.hasMessage()) {
            message = bot.getMessage();
        }

        /*This method checks if the user has the proper access*/
        if (checkAccess(4, bot.getSender()) == false) {
            return;
        }

        if (message.startsWith("~quit")) {
            tokenize(false, 5, message); //parse

            /*if the command was just !quit default quit message*/
            if (parameters.isEmpty()) {
                Main.bot.quitServer("Mission Complete");
                System.exit(0);
            } /*quit with parameters*/ else {
                Main.bot.quitServer(parameters);
                System.exit(0);
            }
        }
    }
}
