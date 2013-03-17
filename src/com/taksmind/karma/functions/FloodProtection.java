/*
 * FloodProtection.java
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
package com.taksmind.karma.functions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import com.taksmind.karma.Main;

/**
 * @author tak <tak@taksmind.com>
 *
 */
public class FloodProtection extends Function {

    private String sender;
    private static HashMap<String, LinkedList<Date>> floodLog = new HashMap<String, LinkedList<Date>>();
    private static LinkedList<Date> list = new LinkedList<Date>();
    private static final SimpleDateFormat df = new SimpleDateFormat("mm:ss.SSSS");

    @Override
    public void run() {
        if ( bot.hasMessage() ) {
            sender = bot.getSender();

            if ((!this.bot.hasTime())
                    || (!addDate(sender, bot.getTime()))) {
                return;
            }
            list = floodLog.get(sender);

            Date first = list.getFirst();

            Date last = list.getLast();

            long diff = last.getTime() - first.getTime();

            Date difference = new Date(diff);

            if (diff < 19000L) {
                Main.bot.kick(bot.getChannel(), this.sender, new StringBuilder().append("Flood Detected over span of : ").append(df.format(difference)).toString());

                floodLog.remove(sender);
                list.clear();
                //Vote.voters.clear();
            }
        }
    }

    private static boolean addDate(String sender, Date date) {
        boolean isFull = false;
        if (floodLog.get(sender) != null) {
            list = floodLog.get(sender);
            list.addLast(date);
        } else {
            list.addLast(date);
        }

        if (list.size() > 10) {
            Date oldest = list.removeFirst();
            isFull = true;
            System.out.println(new StringBuilder().append("List is full, removed oldest Date ").append(oldest.toString()).toString());
        }
        System.out.println(new StringBuilder().append("Date ").append(date.toString()).append(" added to").append((isFull) ? " full" : "").append(" list").toString());

        floodLog.remove(sender);
        floodLog.put(sender, list);
        return isFull;
    }
}
