package net.whiya;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import net.whiya.DataBase.Config;
import net.whiya.DataBase.ServerData;
import net.whiya.Utils.Logger;
import net.whiya.Utils.Server;
import net.whiya.Utils.SystemCall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class Main {
    private static Config config;
    private static SystemCall systemCall;
    public static DiscordClient client;
    public static GatewayDiscordClient gateway;
    public static ServerData serverData;



    public static void main(final String[] args) {
        config      = new Config();
        serverData  = new ServerData();
        systemCall  = new SystemCall();

        config.load();
        serverData.load();


        //discordbot関係開始

        //トークン
        client = DiscordClient.create(config.getToken());
        gateway = client.login().block();

        //イベント
        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            String content = message.getContent();
            String[] ct = content.split(" ");

            //ボット確認
            if (message.getAuthor().get().isBot()){
                return;
            }

            //prefix
            if("rm!".equals(ct[0])){
                final MessageChannel channel = message.getChannel().block();
                //channel.createMessage("prefixだよ").block();

                switch (ct[1]){
                    case "start" :
                        start(ct[2]);
                        channel.createMessage("スタートを検知").block();
                        break;
                    case "stop" :
                        stop(ct[2]);
                        channel.createMessage("ストップを検知").block();
                        //処理
                        break;
                    case "help" :
                        //処理
                        break;
                    default:
                        //その他
                        //cmd(ct[2]);
                }
            }
        });

        gateway.onDisconnect().block();

        //discordbot関係終了
    }

    // 鯖の起動
    public static void start(String server_name){

        // server情報の取得
        Server server = serverData.getServer(server_name);

        // フォルダ移動
        String command = "ping google.com";
        Logger.info(command);

        SystemCall.sendCommand(command, server);


        command = config.getStartCommand().replace("{jar_name}", server.getJarName())
                .replace("{min}", server.getMinMemory())
                .replace("{max}", server.getMaxMemory())
                .replace("{option}", server.getOption());

        SystemCall.sendCommand(command, server);
    }

    //鯖の停止
    public static void stop(String server_name){
        //鯖の名前からティレクトリなどを取得
        // String[] server = config.read(server_name);

        String command = "stop";
        //sendCommand(server, command);


    }


}
