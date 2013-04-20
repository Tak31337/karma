/*
 * Help.java
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

import com.taksmind.karma.Main;

/**
 *
 * @author tak <tak@taksmind.com>
 */
public class Help extends Function {

    private String message;
    private String channel;

    public void run() {
        /*if there is a message store and check it*/
        if (bot.hasMessage()) {
            message = bot.getMessage();
            channel = bot.getChannel();
        }

        if ( message.startsWith("~help") ) {

              Main.bot.sendMessage(channel, "   Registration(send Karma Bot a private messsage): \n");
              Main.bot.sendMessage(channel, "~register *password* \n");
              Main.bot.sendMessage(channel, "~login    *password* \n\n");
              Main.bot.sendMessage(channel, "~access   *person* *new access level*   (rq. access lvl 4)");
              Main.bot.sendMessage(channel, " ");

              Main.bot.sendMessage(channel, "Normal commands: \n");
              Main.bot.sendMessage(channel, "~md5      *string to encrypt* \n");
              Main.bot.sendMessage(channel, "~draw     (draws a random tarot card from Major Arcana) \n");
              Main.bot.sendMessage(channel, "~speak    *message* (talk to me.) \n");
              Main.bot.sendMessage(channel, "~card     *Major Arcana tarot card name* \n");
              Main.bot.sendMessage(channel, "~zodiac   *mm* *dd* (links to your birthday zodiac) also get daily horoscopes: ex. ~zodiac taurus\n");
              Main.bot.sendMessage(channel, "~google   *string to search* \n");
              Main.bot.sendMessage(channel, "~say      *send to* *message*         (rq. access lvl 2) \n\n");
              Main.bot.sendMessage(channel, "~slap     *nick*                      (rq. access lvl 2) \n\n");
              Main.bot.sendMessage(channel, " ");
              
              Main.bot.sendMessage(channel, "   Oper commands: \n");
              Main.bot.sendMessage(channel, "~kick       *person*                  (This is a votekick ~vote y or ~vote n)\n");
              Main.bot.sendMessage(channel, "~welcome    (toggle welcome)          (rq. access lvl 3) \n");
              Main.bot.sendMessage(channel, "~autovoice  (toggle auto voice)       (rq. access lvl 3) \n");
              Main.bot.sendMessage(channel, "~join       *channel*                 (rq. access lvl 3) \n");
              Main.bot.sendMessage(channel, "~part       *channel* *reason*        (rq. access lvl 3) \n");
              Main.bot.sendMessage(channel, "~quit                                 (rq. access lvl 4)\n\n");
              
              Main.bot.sendMessage(channel, "    Plugin commands: \n");
              Main.bot.sendMessage(channel, "~plugins                              (req. access lvl 4) list all plugins\n");
              Main.bot.sendMessage(channel, "~acviteplugins                        (req. access lvl 4) list all active plugins\n");
              Main.bot.sendMessage(channel, "~load      *plugin*                   (req. access lvl 4) load plugin\n");
              Main.bot.sendMessage(channel, "~unload    *plugin*                   (req. access lvl 4) unload plugin\n");
        }
    }
}
