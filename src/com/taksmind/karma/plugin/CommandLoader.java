package com.taksmind.karma.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import com.taksmind.karma.Main;

public class CommandLoader {
	private static ArrayList<String> plugins = new ArrayList<String>();
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
	}
}

