package net.whiya.DataBase;

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

    private String token, startCommand;

    //config.jsonのロード

    public void load() {
        mapper = new ObjectMapper();
        try {
            json = mapper.readTree(Paths.get("./config.json").toFile()).toString();
            jnode = mapper.readTree(json);
            onode = jnode.deepCopy();

            token = onode.get("token").asText();
            startCommand = onode.get("start_command").asText();
        } catch (Exception e) {
            Logger.warn("Couldn't load config.json");
        }
    }

    //config.jsonのセーブ

    public void save() {
        try {
            mapper.writeValue(Paths.get("./config.json").toFile(), onode);
        }catch (Exception e) {
            Logger.warn("Couldn't save config.json");
        }
    }

    //トークンを与えます

    public String getToken() {
        return token;
    }

    //起動構成を与えます

    public String getStartCommand() {
        return startCommand;
    }
}
