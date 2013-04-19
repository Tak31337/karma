package com.taksmind.karma.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;

import com.taksmind.karma.Main;
import com.taksmind.karma.plugin.CommandLoader;
import com.taksmind.karma.plugin.Plugin;


public class PluginLoader extends Function {
    private String message;
    private String channel;
    private CommandLoader commands;
    public ExecutorService pluginThreadExecutor;

    @Override
    public void run() {
        /*if there is a message store and check it*/
        if (bot.hasMessage()) {
            message = bot.getMessage();
            channel = bot.getChannel();
            commands = new CommandLoader();
        }

        for( Plugin p : commands.getPlugins() ) {
        	if( message.startsWith(p.getTrigger()) ) {
        		tokenize(false, p.getTrigger().length(), message);
        		Runtime run = Runtime.getRuntime();
				try {
					if ( Main.debug ) {
						System.out.println("Executing " + p.getCommand() + parameters);
						System.out.println(p.getDescription());
					}
					Process proc = run.exec(p.getCommand() + parameters);
					BufferedReader stdInput = new BufferedReader(new 
				             InputStreamReader(proc.getInputStream()));
					String s;
					while ((s = stdInput.readLine()) != null) {
						Main.bot.sendMessage(channel, s);
					}
					stdInput.close();
					// output.append(out);
				} catch (IOException e) {
					e.printStackTrace();
				}

        	}
        }
    }
}