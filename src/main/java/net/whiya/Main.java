package net.whiya;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import net.whiya.Config.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class Main {

    private static Config config;

    public Main.printOutput getStreamWrapper(InputStream is, String type) {
        return new Main.printOutput(is, type);
    }

    public static void main(final String[] args) {
        config = new Config();
        config.load();


        String token = config.getBotToken();
        token = token.substring(0, token.length() - 1);
        token = token.substring(1);
        System.out.println(token);
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

            //prefix
            if("rm!".equals(ct[0])){
                final MessageChannel channel = message.getChannel().block();
                //channel.createMessage("prefixだよ").block();

                switch (ct[1]){
                    case "start" :
                        start(ct[2]);
                        //処理
                        break;
                    case "stop" :
                        stop(ct[2]);
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


    }

    //鯖の起動
    public static void start(String server_name){
        //鯖の名前からティレクトリなどを取得
        String[] server = config.read(server_name);

        String command = "java -Xms2G -Xmx2G -jar server.jar nogui";
        sendCommand(server, command, server_name);
    }

    //鯖の停止
    public static void stop(String server_name){
        //鯖の名前からティレクトリなどを取得
        String[] server = config.read(server_name);

        String command = "stop";
        sendCommand(server, command, server_name);


    }

    public static void sendCommand(String[] server,String command, String server_name){
        Runtime rt = Runtime.getRuntime();
        Main rte = new Main();
        printOutput errorReported, outputMessage;
        try {
            //Process sc = rt.exec("screen -S " + server[1] +" -X stuff 'cd " + server[0] + "'echo -ne '\\015'");
            //Process proc = rt.exec("screen -S " + server[1] +" -X stuff '" + command + "''echo -ne '\\015'");
            Process proc = rt.exec("screen -X survival");
            errorReported = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
            outputMessage = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
            errorReported.start();
            outputMessage.start();
            proc = rt.exec("ls");
            errorReported = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
            outputMessage = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
            errorReported.start();
            outputMessage.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class printOutput extends Thread {
        InputStream is = null;

        printOutput(InputStream is, String type) {
            this.is = is;
        }

        public void run() {
            String s = null;
            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is));
                while ((s = br.readLine()) != null) {
                    System.out.println(s);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
