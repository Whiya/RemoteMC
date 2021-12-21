package net.whiya;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Entity;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;

public final class Main {

    public static void main(final String[] args) {
        final String token = "";
        final DiscordClient client = DiscordClient.create(token);
        final GatewayDiscordClient gateway = client.login().block();

        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            String content = message.getContent();
            String[] ct = content.split(" ");

            //ボット確認
            if (message.getAuthor().get().isBot()){
                return;
            }

            if ("!ping".equals(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage("Pong!").block();
            }


            if("rm!".equals(ct[0])){
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage("prefixだよ").block();

                switch (ct[1]){
                    case "create" :

                        //処理
                        break;
                    case "delete" :
                        //処理
                        break;
                    case "その他" :
                        //処理
                        break;
                    default:
                        //うわわ
                }
            }
        });

        gateway.onDisconnect().block();
    }
}
