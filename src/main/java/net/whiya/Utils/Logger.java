package net.whiya.Utils;

import java.util.Date;

public class Logger {
    private final static Date dateObj = new Date();

    public static void info(String log) {
        System.out.println("[" + dateObj.getTime() + "] [Info]"  + log);
    }

    public static void warn(String log) {
        System.out.println("[" + dateObj.getTime() + "] [" + "\u001b[00;31m" + "Warn" + "\u001b[00m" + "]" + log);
    }
}
