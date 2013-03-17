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
package com.taksmind.karma.functions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.taksmind.karma.Main;
import com.taksmind.karma.util.JSONArray;
import com.taksmind.karma.util.JSONObject;


/**
 * @author tak <tak@taksmind.com>
 * @author http://frankmccown.blogspot.com/
 * 
 * makeQuery() method from: 
 *  http://www.ajaxlines.com/ajax/stuff/article/using_google_is_ajax_search_api_with_java.php
 * 
 * Class to google from IRC
 */
public class Google extends Function {

    private String message;
    private String channel;
	private String escapedParameters;
	private final String HTTP_REFERER = "http://www.taksmind.com/";
    
    @Override
    public void run() {
        if (this.bot.hasMessage()) {
            message = bot.getMessage();
            channel = bot.getChannel();
        }

        if (this.message.startsWith("~google")) {
            tokenize(true, 7, this.message);

            while (tokenParameters.hasMoreTokens()) {
                escapedParameters = (escapedParameters + "+" + tokenParameters.nextToken() );
            }

            //System.out.println(escapedParameters);
            makeQuery(escapedParameters);
            
            escapedParameters = "";
        }
    }
    
    private void makeQuery(String query) {

    	System.out.println(" Querying for " + query);

    	try {

    	    URL url = new URL("http://ajax.googleapis.com/ajax/services/search/web?start=0&rsz=small&v=1.0&q=" + query);
    	    URLConnection connection = url.openConnection();
    	    connection.addRequestProperty("Referer", HTTP_REFERER);

    	    // Get the JSON response
    	    String line;
    	    StringBuilder builder = new StringBuilder();
    	    BufferedReader reader = new BufferedReader(
    	        new InputStreamReader(connection.getInputStream()));
    	    while((line = reader.readLine()) != null) {
    	    	builder.append(line);
    	    }

    	    String response = builder.toString();
    	    JSONObject json = new JSONObject(response);

    	    System.out.println("Total results = " +
    	      json.getJSONObject("responseData")
    	      .getJSONObject("cursor")
    	      .getString("estimatedResultCount"));
 
    	    JSONArray ja = json.getJSONObject("responseData")
    	    .getJSONArray("results");

    	    Main.bot.sendMessage(channel, " Results:");
    	    for (int i = 0; i < ja.length(); i++) {
    	    	Main.bot.sendMessage(channel, (i+1) + ". ");
    	    	JSONObject j = ja.getJSONObject(i);
    	    	Main.bot.sendMessage(channel, j.getString("titleNoFormatting"));
    	    	Main.bot.sendMessage(channel, j.getString("url"));
    	    }
    	  }
    	  catch (Exception e) {
    		  Main.bot.sendMessage(channel, "Could not complete search request..");
    		  e.printStackTrace();
    	  }
    }
}
