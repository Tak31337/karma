/*
 * Vote.java
 *
 * Created on Nov 26, 2010, 9:02:34 PM
 *
 * Copyright (c) 2009 Hippos Development Team. All rights reserved.
 *
 * This file is part of Karma.
 *
 * Karma is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Karma is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Karma.  If not, see <http://www.gnu.org/licenses/>.
 */


package com.taksmind.karma.util;

import java.util.HashMap;

import com.taksmind.karma.Main;
import com.taksmind.karma.functions.Function;

/**
 * @author tak <tak@taksmind.com>
 *
 * Class to take and hold votes.
 */
public class Vote extends Function {

    private String message;
    private String channel;
    private String sender;
    /**
     * variable to hold "yes" votes
     */
    public static int voteYes = 0;
    /**
     * variable to hold "no" votes
     */
    public static int voteNo = 0;
    /**
     * All voters, so no one can vote twice.
     */
    public static HashMap<String, Integer> voters = new HashMap<String, Integer>();

    @Override
    public void run() {
        if (bot.hasMessage()) {
            message = bot.getMessage();
            channel = bot.getChannel();
            sender  = bot.getSender();
        }

        if (this.message.startsWith("~vote")) {
            tokenize(false, 5, this.message);
            try {
                if ((voters != null) && (((Integer) voters.get(this.sender)).intValue() == 1)) {
                    return;
                }
            } catch (NullPointerException ex) {
            	ex.printStackTrace();
            }
            if (this.parameters.equalsIgnoreCase(" y")) {
                voteYes += 1;
                voters.put(this.sender, Integer.valueOf(1));
            } else if (this.parameters.equalsIgnoreCase(" n")) {
                voteNo += 1;
                voters.put(this.sender, Integer.valueOf(1));
            }

            Main.bot.sendMessage(this.channel, voteYes + " " + voteNo);
        }
    }
}
