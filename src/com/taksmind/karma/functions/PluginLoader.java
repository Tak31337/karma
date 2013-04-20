package com.taksmind.karma.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.taksmind.karma.Main;
import com.taksmind.karma.plugin.Plugin;


public class PluginLoader extends Function {
    private String message;
    private String channel;
    private String plugin;

    @Override
    public void run() {
        /*if there is a message store and check it*/
        if (bot.hasMessage()) {
            message = bot.getMessage();
            channel = bot.getChannel();
            
        }
        
        for( Plugin p : Main.commands.getActivePlugins() ) {
        	if( message.startsWith(p.getTrigger()) ) {
        		tokenize(false, p.getTrigger().length(), message);
        		Runtime run = Runtime.getRuntime();
				try {
					if ( Main.debug ) {
						System.out.println("Executing " + p.getCommand() + parameters);
						System.out.println(p.getDescription());
					}
					Process proc = run.exec(p.getCommand() + parameters);
					proc.getOutputStream().close();
					BufferedReader stdInput = new BufferedReader(new 
				             InputStreamReader(proc.getInputStream()));
					String s;
					while ((s = stdInput.readLine()) != null) {
						Main.bot.sendMessage(channel, s);
					}
					stdInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        
        /*This method checks if the user has the proper access*/
        if (checkAccess(4, bot.getSender()) == false) {
            return;
        }

        if (message.startsWith("~load")) {
            tokenize(true, 5, message);
            plugin = (String) tokenParameters.nextElement();
            boolean load = true;
            for( Plugin p : Main.commands.getActivePlugins() ) {
            	if( p.getName().equalsIgnoreCase(plugin) ) {
            		Main.bot.sendMessage(channel, "Plugin: " + plugin + " already loaded.");
            		load = false;
            	}
            }
            if ( load ) {
            	Main.bot.sendMessage(channel, "Loading: " + plugin);
            	Main.commands.addPluginByName(plugin);
            }
        }
        
        if (message.startsWith("~unload")) {
            tokenize(true, 7, message);
            plugin = (String) tokenParameters.nextElement();
            boolean unload = false;
            for( Plugin p : Main.commands.getActivePlugins() ) {
            	if( p.getName().equalsIgnoreCase(plugin) ) {
            		Main.bot.sendMessage(channel, "Plugin: " + plugin + " unloaded.");
            		Main.commands.removePluginByName(p.getName());
            		unload = true;
            	}
            }
            if(unload) {
            	Main.bot.sendMessage(channel, "Plugin: " + plugin + " not loaded.");
            }
        }
        
        if (message.startsWith("~plugins")) {
        	Main.bot.sendMessage(channel, "All plugins:");
            for( Plugin p : Main.commands.getAllPlugins() ) {
            	Main.bot.sendMessage(channel, p.getName() + ": " + p.getDescription());
            }
        }

        if (message.startsWith("~activeplugins")) {
        	Main.bot.sendMessage(channel, "Active plugins:");
            for( Plugin p : Main.commands.getActivePlugins() ) {
            	Main.bot.sendMessage(channel, p.getName() + ": " + p.getDescription());
            }
        }
    }
}