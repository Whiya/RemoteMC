package net.whiya.Config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.whiya.Utils.Logger;

import java.nio.file.Paths;

public class Config {
    private ObjectMapper mapper;
    private String json;
    private JsonNode jnode;
    private ObjectNode onode;

    public void load() {
        mapper = new ObjectMapper();
        try {
            json = mapper.readTree(Paths.get("./config.json").toFile()).toString();
            jnode = mapper.readTree(json);
            onode = jnode.deepCopy();
        }catch (Exception e) {
            Logger.warn("Couldn't load config.json");
        }
    }

    public void save() {
        try {
            mapper.writeValue(Paths.get("./config.json").toFile(), onode);
        }catch (Exception e) {
            Logger.warn("Couldn't save config.json");
        }
    }

    public void putInt(String path, int value) {
        onode.put(path, value);
    }

    public void putString(String path, String value) {
        onode.put(path, value);
    }

    public String[] read(String serverName) {
        //サーバー名から path と screen_nameを取得
        String[] value ={onode.get(serverName).path("path").toString(),onode.get(serverName).path("screen_name").toString() };
        return value;
    }

    public String getBotToken() {
        Logger.info(onode.get("token").toString());
        return onode.get("token").toString();
    }

    public JsonNode getConfig() {
        return this.jnode;
    }
}
