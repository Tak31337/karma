package com.taksmind.karma.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import com.google.gson.Gson;
import com.taksmind.karma.Main;

public class CommandLoader {
	private static ArrayList<Plugin> plugins = new ArrayList<Plugin>();
	private static File fileDir;
	
	public CommandLoader() {
	    Properties properties = new Properties(); //creates configuration object
	    try {
	        properties.load(new FileInputStream(Main.configuration));
	        fileDir = new File(properties.getProperty("plugins"));
	    } catch (IOException ioex) {
	    	System.err.println("configuration file can not be accessed!");
	    }
	    File[] files = fileDir.listFiles(new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.toLowerCase().endsWith(".json");
	        }
	    });
	    
	    for( File f : files ) {
	    	try{
	    		Scanner s = new Scanner(f);
	    		s.useDelimiter("\\Z");
	    		plugins.add(new Gson().fromJson(s.next(), Plugin.class));
	    		s.close();
	    	} catch( Exception e ) {
	    		System.err.println("Some problem parsing json");
	    		e.printStackTrace();
	    	}
	    }
	}
	
	public ArrayList<Plugin> getPlugins() {
		return plugins;
	}
	
	public void addPlugin(Plugin p) {
		plugins.add(p);
	}
	
	public void removePlugin(Plugin p) {
		plugins.remove(p);
	}
	
	public void removePluginByName(String name) {
		for( Plugin p : plugins ) {
			if ( p.getName().equalsIgnoreCase(name) ) {
				plugins.remove(p);
			}
		}
	}
}

