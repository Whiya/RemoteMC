package net.whiya.Utils;

import discord4j.common.util.Snowflake;
import net.whiya.Main;

import java.util.TimerTask;

public class Timer extends TimerTask {
    @Override
    public void run() {
        if(Main.result.equals(" ")) {
            return;
        }
        if(Main.result.length() > 4000) {
            Main.client.getChannelById(Snowflake.of(Main.config.getChannel()))
                    .createMessage(Main.result.substring(3998)).block();
            Main.result = " ";
            return;
        }
        Main.client.getChannelById(Snowflake.of(Main.config.getChannel())).createMessage(Main.result).block();
        Main.result = " ";
    }
}

