/*
 * Kick.java
 *
 * Created on Nov 26, 2009, 6:08:30 PM
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

package com.taksmind.karma.functions;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jibble.pircbot.User;

import com.taksmind.karma.Main;
import com.taksmind.karma.util.Vote;

/**
 * @author tak <tak@taksmind.com>
 *
 */
public class Kick extends Function {

    private String message;
    private String channel;
    /**
     * the number of votes
     */
    public static int voteCount;

    @Override
    public void run() {
        if (bot.hasMessage()) {
            message = bot.getMessage();
            channel = bot.getChannel();
        }

        if (this.message.startsWith("~kick")) {
        	Vote.voteYes = 0;
            Vote.voteNo  = 0;
            Vote.voters.clear();
            
            tokenize(false, 5, message);
            User[] users = Main.bot.getUsers(channel);
            String totalUsers = "";
            int userCount = 0;
            int voteRequired = 1;

            for (User user : users) {
                ++userCount;
                totalUsers = totalUsers + " " + user.toString();
            }

            voteRequired = 2;

            Main.bot.sendMessage(channel, totalUsers);
            Main.bot.sendMessage(channel, "A vote was called (!vote y, or !vote n): [" + voteRequired + "/" + userCount + "]");
            try {
                Thread.sleep(30000L);
            } catch (InterruptedException ex) {
                Logger.getLogger(Kick.class.getName()).log(Level.SEVERE, null, ex);
            }

            Main.bot.sendMessage(channel, "yes: " + Vote.voteYes + " no: " + Vote.voteNo);

            if ( (Vote.voteYes - Vote.voteNo) >= voteRequired ) {
            	Main.bot.kick(channel, parameters, "vote success.");
            } else {
            	Main.bot.sendMessage(channel, "Vote failed.");
                return;
            }
        }
    }
}
