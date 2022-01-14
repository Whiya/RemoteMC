package net.whiya.Utils;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.channel.TextChannel;
import net.whiya.Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Temp {
    public static void sendCMD(String cmd, Long channelID) {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process result = runtime.exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(result.getInputStream()));
            while(true) {
                String line = br.readLine();
                if (line == null) break;
                Main.result = Main.result + "\n" + line;
            }
        } catch (Exception e) {
            Logger.warn(e.toString());
            System.out.println("\n"+cmd + "コマンドにしっぱいしました ");
        }
    }


}
