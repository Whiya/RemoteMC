package net.whiya.Utils;

import java.util.Objects;

public class Server {
    private String name, path, jarName, minMemory, maxMemory, option;
    private long consoleChannelID;

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public String getJarName() {
        return jarName;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMaxMemory() {
        return maxMemory;
    }

    public String getMinMemory() {
        return minMemory;
    }

    public String getOption() {
        return option;
    }

    public void setMaxMemory(String maxMemory) {
        this.maxMemory = maxMemory;
    }

    public void setMinMemory(String minMemory) {
        this.minMemory = minMemory;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public long getConsoleChannelID() {
        return consoleChannelID;
    }
    public void setConsoleChannelID(long consoleChannelID) {
        this.consoleChannelID = consoleChannelID;
    }



    @Override
    public String toString() {
        return "Server{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", jar_path='" + jarName + '\'' +
                ", minMemory='" + minMemory + '\'' +
                ", maxMemory='" + maxMemory + '\'' +
                ", option='" + option + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Server server = (Server) o;
        return Objects.equals(name, server.name) && Objects.equals(path, server.path) && Objects.equals(jarName, server.jarName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, path, jarName);
    }
}
