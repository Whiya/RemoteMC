package net.whiya;

import net.whiya.Config.Config;

public class Temp {
    public static void main(String[] args) {
        Config config = new Config();
        config.load();
        config.save();
    }
}
