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
	private static ArrayList<Plugin> activePlugins = new ArrayList<Plugin>();
	private static File fileDir;
	Properties properties = new Properties(); //creates configuration object
	
	public CommandLoader() {	    
	    try {
	        properties.load(new FileInputStream(Main.configuration));
	        fileDir = new File(properties.getProperty("plugins"));
	    } catch (IOException ioex) {
	    	System.err.println("configuration file can not be accessed!");
	    }
	    findPlugins(true);
	}
	
	private void findPlugins(boolean activateAll) {
	    File[] files = fileDir.listFiles(new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.toLowerCase().endsWith(".json");
	        }
	    });
	    
	    for( File f : files ) {
	    	try{
	    		Scanner s = new Scanner(f);
	    		s.useDelimiter("\\Z");
	    		Plugin p = new Gson().fromJson(s.next(), Plugin.class);
	    		boolean add = true;
	    		for( Plugin x : plugins ) {
	    			if( x.getName().equalsIgnoreCase(p.getName()) ) {
	    				add = false;
	    			}
	    		}
	    		if( add ) {
	    			plugins.add(p);
	    		}
	    		if(activateAll) {
	    			activePlugins.add(p);
	    		}
	    		p = null;
	    		s.close();
	    	} catch( Exception e ) {
	    		System.err.println("Some problem parsing json");
	    		e.printStackTrace();
	    	}
	    }
	}
	
	public ArrayList<Plugin> getActivePlugins() {
		return activePlugins;
	}
	
	public ArrayList<Plugin> getAllPlugins() {
		findPlugins(false);
		return plugins;
	}
	
	public void removePluginByName(String name) {
		for( Plugin p : plugins ) {
			if ( p.getName().equalsIgnoreCase(name) ) {
				activePlugins.remove(p);
			}
		}
	}
	
	public void addPluginByName(String name) {
		findPlugins(false);
		for( Plugin p : plugins ) {
			if ( p.getName().equalsIgnoreCase(name) ) {
				activePlugins.add(p);
			}
		}
	}
}

