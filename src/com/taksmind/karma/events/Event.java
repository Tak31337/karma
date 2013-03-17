/*
 *  Event.java
 * 
 *  Created on Oct 13, 2009, 9:14:12 PM
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
package com.taksmind.karma.events;

import java.util.ArrayList;

/**
 *
 * @author tak <tak@taksmind.com>
 */
public interface Event {
    /**
     * @param args arguments to pass to evenStart()
     * calls the event to start
     */
    public void eventStart(ArrayList<Object> args);
}
