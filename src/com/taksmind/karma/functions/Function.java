/*
 *  Function.java
 * 
 *  Created on Oct 14, 2009, 6:02:05 PM
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

import java.util.StringTokenizer;

import com.taksmind.karma.Main;
import com.taksmind.karma.util.Listener;

/**
 *
 * @author tak <tak@taksmind.com>
 */
public class Function implements Runnable {
    /*listener object than all functions can use*/

    protected Listener bot = new Listener();
    protected StringTokenizer tokenParameters;
    protected String parameters;

    /**
     * @param tokenize boolean to mark if the parameters will be tokenized or not. 
     * @param command how many characters the command is, will be substring'ed
     * @param message message  message that was sent by the user
     * 
     * method that stores everything after the command into a StringTokenizer 
     * separated by spaces " ", or just a String.
     */
    public void tokenize(boolean tokenize, int command, String message) {

        if (tokenize) {
            tokenParameters = new StringTokenizer(message.substring(command));
        } else {
            parameters = message.substring(command);
        }

    }

    /**
     * @param level level of registered user
     * @param sender sender who executed command
     * @return returns true if access false if denied.
     * 
     * parent method to see if the sender has the required access level
     */
    public boolean checkAccess( int level, String sender ) {
        if ( Main.users.get( sender ) != null) {
            if ( Integer.parseInt((String) Main.users.get( sender )) >= level ) {
                return true;
            }
        }
        return false;
    }

    /*inherited and thrown away*/
    public void run() {
        throw new UnsupportedOperationException("Not supported.");
    }
}
