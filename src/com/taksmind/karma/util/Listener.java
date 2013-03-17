/*
 *  Listener.java
 * 
 *  Created on Oct 13, 2009, 10:28:50 PM
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
package com.taksmind.karma.util;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author tak <tak@taksmind.com>
 */
public class Listener {

    private static ArrayList messageArgs;
    /*THE FOLLOWING METHODS ARE FOR THE MESSAGE EVENT*/

    public static void setMessageListener(ArrayList args) {
        messageArgs = args;
    }

    /*Thease methods are retrieved from the Message class*/
    public String getChannel() {
        return (String) messageArgs.get(0);
    }

    public String getSender() {
        return (String) messageArgs.get(1);
    }

    public String getLogin() {
        return (String) messageArgs.get(2);
    }

    public String getHostname() {
        return (String) messageArgs.get(3);
    }

    public String getMessage() {
        return (String) messageArgs.get(4);
    }

    public Date getTime() {
        return (Date) messageArgs.get(5);
    }

    /*methods to check for parameters*/
    public boolean hasChannel() {
        if (messageArgs.get(0).toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasSender() {
        if (messageArgs.get(1).toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasLogin() {
        if (messageArgs.get(2).toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasHostname() {
        if (messageArgs.get(3).toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasMessage() {
        if (messageArgs.get(4).toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasTime() {
        if (messageArgs.get(5).toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    /*THE FOLLOWING METHODS ARE FOR THE PRIVATE MESSAGE EVENT*/
    private static ArrayList PrivateMessageArgs;

    public static void setPrivateMessageListener(ArrayList args) {
        PrivateMessageArgs = args;
    }
    /*Thease methods are retrieved from the Message class*/

    public String getPrivateSender() {
        return (String) PrivateMessageArgs.get(0);
    }

    public String getPrivateLogin() {
        return (String) PrivateMessageArgs.get(1);
    }

    public String getPrivateHostname() {
        return (String) PrivateMessageArgs.get(2);
    }

    public String getPrivateMessage() {
        return (String) PrivateMessageArgs.get(3);
    }

    public Date getPrivateTime() {
        return (Date) PrivateMessageArgs.get(4);
    }

    /*methods to check for parameters*/
    public boolean hasPrivateSender() {
        if (PrivateMessageArgs.get(0).toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasPrivateLogin() {
        if (PrivateMessageArgs.get(1).toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasPrivateHostname() {
        if (PrivateMessageArgs.get(2).toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasPrivateMessage() {
        if (PrivateMessageArgs.get(3).toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasPrivateTime() {
        if (PrivateMessageArgs.get(4).toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
