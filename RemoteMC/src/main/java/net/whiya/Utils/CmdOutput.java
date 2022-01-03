package net.whiya.Utils;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import net.whiya.DataBase.ServerData;
import net.whiya.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CmdOutput extends Thread {
    InputStream is = null;
    Server server;
    private final static GatewayDiscordClient gateway = Main.gateway;
    private final static ServerData serverData = Main.serverData;
    private final static DiscordClient client = Main.client;


    public CmdOutput getStreamWrapper(InputStream is, String type, Server server) {
        this.is = is;
        this.server = server;
        return null;
    }

    public void run() {
        String s = null;
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is));
            while ((s = br.readLine()) != null) {
                client.getChannelById(Snowflake.of(server.getConsoleChannelID())).createMessage(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
