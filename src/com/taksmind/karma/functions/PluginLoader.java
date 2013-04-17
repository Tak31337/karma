package com.taksmind.karma.functions;


public class PluginLoader extends Function {
    private String message;
    private String sendTo;

    @Override
    public void run() {
        /*if there is a message store and check it*/
        if (bot.hasMessage()) {
            message = bot.getMessage();
            sendTo = bot.getChannel();
        }


    }
}