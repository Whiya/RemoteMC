package net.whiya;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import net.whiya.DataBase.Config;
import net.whiya.Utils.SystemCall;
import net.whiya.Utils.Temp;

import java.util.Timer;

public final class Main {
    private static SystemCall systemCall;
    public static Config config;
    public static DiscordClient client;
    public static GatewayDiscordClient gateway;
    public static String result = " ";
    private static final Timer time = new Timer();



    public static void main(final String[] args) {
        config      = new Config();
        systemCall  = new SystemCall();

        config.load();


        //discordbot関係開始

        //トークン
        client = DiscordClient.create(config.getToken());
        gateway = client.login().block();

        time.scheduleAtFixedRate(new net.whiya.Utils.Timer(), 1000, 1000);

        //イベント
        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            String content = message.getContent();

            //ボット確認
            if (message.getAuthor().get().isBot()){
                return;
            }

            //prefixがあるか
            if(content.startsWith(config.getPrefix())) {
                String[] ct = content.split(" ");

                //index確認
                if (ct.length < 2 ){
                    final MessageChannel channel = message.getChannel().block();
                    channel.createMessage("コマンドが間違っています。").block();
                    return;
                }


                //初期設定()
                if (ct[0].equals(config.getPrefix())){
                    config.setChannel(Long.valueOf(ct[1]));
                    client.getChannelById(Snowflake.of(Main.config.getChannel()))
                            .createMessage("コンソールチャンネルをここに設定しました。").block();
                    return;
                }
            }

            //コンソールチャンネルid確認

            long id = event.getMessage().getChannelId().asLong();
            if(id == config.getChannel()){
                Temp.sendCMD(event.getMessage().getContent(), event.getMessage().getChannelId().asLong());
                //cmd(event.getMessage().getContent());
            }
        });

        gateway.onDisconnect().block();

        //discordbot関係終了
    }

    //コマンド
    public static void cmd(String cmd){
        SystemCall.sendCommand(cmd);
    }



}
