/*
 *  TakeInput.java
 *
 *  Created on 29 June 2010, 9:55 PM
 *
 *  Copyright (c) 2009, Hippos Development Team
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author tak <tak@taksmind.com>
 *
 * Class to handle taking command line user input.
 */
public class TakeInput {
	BufferedReader br;
	public String grabInput( String message ) {
		System.out.print(message + ": ");
		br = new BufferedReader(new InputStreamReader(System.in));
		try {
			return br.readLine();
		} catch (IOException e) {
			System.err.println("Error with user input.");
			e.printStackTrace();
		}
		
		return ""; //throw away return.s
	}
}
