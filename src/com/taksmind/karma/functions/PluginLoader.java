package com.taksmind.karma.functions;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.taksmind.karma.Main;
import com.taksmind.karma.functions.Crypto;
import com.taksmind.karma.functions.Function;

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