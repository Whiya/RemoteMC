package net.whiya.Config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.whiya.Utils.Logger;

import java.io.IOException;
import java.nio.file.Paths;

public class Config {
    private ObjectMapper objectMapper;
    private JsonNode json;

    public void load() {
        objectMapper = new ObjectMapper();
        try {
            json = objectMapper.readTree(Paths.get("./data.json").toFile());
        }catch (IOException e) {
            Logger.warn("Couldn't load config.json");
        }
    }

    public void save() {

    }

    public JsonNode getConfig() {
        return this.json;
    }
}
