/*
 * Google.java
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
package com.taksmind.karma.util.google;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.taksmind.karma.Main;
import com.taksmind.karma.functions.Function;

/**
 * @author tak <tak@taksmind.com>
 * @author http://frankmccown.blogspot.com/
 * 
 *         makeQuery() method from: http://www.ajaxlines.com/ajax/stuff/article/
 *         using_google_is_ajax_search_api_with_java.php
 * 
 *         Class to google from IRC
 */
public class Google extends Function {

	private String message;
	private String channel;
	private String escapedParameters;

	@Override
	public void run() {
		if (this.bot.hasMessage()) {
			message = bot.getMessage();
			channel = bot.getChannel();
		}

		if (this.message.startsWith("~google")) {
			tokenize(true, 7, this.message);
			escapedParameters = "";

			while (tokenParameters.hasMoreTokens()) {
				escapedParameters += tokenParameters.nextToken();
				if (!tokenParameters.hasMoreTokens()) {
					break;
				}
				escapedParameters += "+";
			}

			// System.out.println(escapedParameters);
			makeQuery(escapedParameters);

			escapedParameters = "";
		}
	}

	private void makeQuery(String search) {

		System.out.println(" Querying for " + search);

		try {
		    String google = "http://ajax.googleapis.com/ajax/services/search/web?start=0&rsz=small&v=1.0&q=";
		    String charset = "UTF-8";

		    URL url = new URL(google + URLEncoder.encode(search, charset));
		    Reader reader = new InputStreamReader(url.openStream(), charset);
		    GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
		    
		    int i = 0;
			for (Result r : results.getResponseData().getResults()) {
				i++;
				Main.bot.sendMessage(channel, i + ": " + r.getTitle());
				Main.bot.sendMessage(channel, r.getUrl());
			}
		} catch (Exception e) {
			Main.bot.sendMessage(channel, "Could not complete search request..");
			e.printStackTrace();
		}
	}
}
