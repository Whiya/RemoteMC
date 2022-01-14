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

    private String token, prefix;
    private Long channel;

    public void load() {
        mapper = new ObjectMapper();
        try {
            json = mapper.readTree(Paths.get("./config.json").toFile()).toString();
            jnode = mapper.readTree(json);
            onode = jnode.deepCopy();

            token = onode.get("token").asText();
            prefix = onode.get("prefix").asText();
            channel = onode.get("channel").asLong();
        } catch (Exception e) {
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


    public String getToken() {
        return token;
    }

    public String getPrefix() {
        return prefix;
    }

    public Long getChannel() {
        return channel;
    }

    public void setChannel(Long channel) {
        this.channel = channel;
    }
}
