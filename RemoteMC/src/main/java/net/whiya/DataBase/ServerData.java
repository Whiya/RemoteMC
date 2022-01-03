package net.whiya.DataBase;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.whiya.Utils.Logger;
import net.whiya.Utils.Server;

import java.nio.file.Paths;

public class ServerData {
    private ObjectMapper    mapper;
    private String          json;
    private JsonNode        jnode;
    private ObjectNode      onode;

    public void load() {
        mapper = new ObjectMapper();
        try {
            json    = mapper.readTree(Paths.get("./server_data.json").toFile()).toString();
            jnode   = mapper.readTree(json);
            onode   = jnode.deepCopy();


            //server_data.jsonの例

            Server server = new Server();
            server.setName("serverName");
            server.setPath("/home/servsers");
            server.setJarName("server.jar");
            server.setMaxMemory("2G");
            server.setMinMemory("1G");
            server.setOption("");
            server.setConsoleChannelID(9999999999L);
            putServer(server);


            save();
        } catch (Exception e) {
            Logger.warn("Couldn't load server_data.json");
        }
    }

    //server_data.jsonのセーブ
    public void save() {
        try {
            mapper.writeValue(Paths.get("./server_data.json").toFile(), onode);
        }catch (Exception e) {
            Logger.warn("Couldn't save config.json");
        }
    }

    //サーバーの追加（コマンドからよう）

    public void putServer(Server server) {
        onode.putObject(server.getName()).
                put("path", server.getPath()).
                put("jarName", server.getJarName()).
                put("maxMemory",server.getMaxMemory()).
                put("minMemory", server.getMaxMemory()).
                put("option", server.getOption()).
                put("console_channel_id", server.getConsoleChannelID());
    }

    //サーバー情報を与えます

    public Server getServer(String serverName) {
        Server server = new Server();
        try {
            server.setName(serverName);
            server.setPath(onode.get(serverName).get("path").asText());
            server.setJarName(onode.get(serverName).get("jarName").asText());
            server.setMinMemory(onode.get(serverName).get("minMemory").asText());
            server.setMaxMemory(onode.get(serverName).get("maxMemory").asText());
            server.setOption(onode.get(serverName).get("option").asText());
            server.setConsoleChannelID(onode.get(serverName).get("console_channel_id").asLong());
            return server;
        }catch (Exception e) {
            Logger.warn(e.toString());
            return null;
        }
    }
}
