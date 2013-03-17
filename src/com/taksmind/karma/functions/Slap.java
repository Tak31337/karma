/*
 *  Join.java
 * 
 *  Created on Oct 25, 2009, 11:51:35 AM
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

import com.taksmind.karma.Main;

/**
 *
 * @author tak <tak@taksmind.com>
 */
public class Slap extends Function {

    private String message;
    private String channel;
    private String nick;

    String[] actions = { "slaps", "hits", "beats up", "bashes", "hurts", "kills", "tortures" };
    String[] adjectives = { "a large", "an enormous", "a small", "a medium sized", "an extra large", "a questionable", "a suspicious", "a terrifying", "a scary", "a breath taking", "a horrifying" };
    String[] objects = { "Windows ME user guide", "Windows 2000", "brick", "non-functional AT power source", "axe", "shovel", "mIRC 6.01", "salmon", "Khaled Mardam-Bey", "iron bar", "Back Street Boys CD", "whip", "QuakeNET server", "Pentium 4 CPU", "set of Windows 3.11 floppies", "camel", "christmas tree", "Compaq laptop", "picture of Bill Gates", "RIAA", "MagLite<tm> torch covered with vaseline", "MS IIS", "slap-script" };
    
    @Override
    public void run() {
        /*if there is a message store and check it*/
        if (bot.hasMessage()) {
            message = bot.getMessage();
            channel = bot.getChannel();
        }

        /*This method checks if the user has the proper access*/
        if (checkAccess(2, bot.getSender()) == false) {
            return;
        }

        if (message.startsWith("~slap")) {
            tokenize(true, 5, message);
            nick = (String) tokenParameters.nextElement();
            
            Main.bot.sendMessage(channel, actions[(int)(Math.random()*6)] + " " + nick + " with " 
            		+ adjectives[(int)(Math.random()*10)] + " " + objects[(int)(Math.random()*22)]);
        }
    }
}
