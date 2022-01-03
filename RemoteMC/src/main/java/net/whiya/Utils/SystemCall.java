package net.whiya.Utils;

public class SystemCall {
    public static void sendCommand(String command, Server server) {
        Runtime rt = Runtime.getRuntime();
        CmdOutput rte = new CmdOutput();
        CmdOutput errorReported, outputMessage;
        try {
            Process proc = rt.exec(command);
            // errorReported = rte.getStreamWrapper(proc.getErrorStream(), "ERROR", server);
            outputMessage = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT", server);
            // errorReported.run();
            outputMessage.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//// 鯖の起動
//    public static void start(String server_name){
//
//        // server情報の取得
//        Server server = serverData.getServer(server_name);
//
//        // フォルダ移動
//        String command = "cd " + server.getPath();
//
//        sendCommand(command);
//
//
//        command = config.getStartCommand().replace("{jar_name}", server.getjarName())
//                .replace("{min}", server.getMinMemory())
//                .replace("{max}", server.getMaxMemory())
//                .replace("{option}", server.getOption());
//
//        sendCommand(command);
//    }
//
//public static void sendCommand(String command){
//        Runtime rt = Runtime.getRuntime();
//        Main rte = new Main();
//        printOutput errorReported, outputMessage;
//        try {
//            Process proc = rt.exec(command);
//            errorReported = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
//            outputMessage = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
//            errorReported.start();
//            outputMessage.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }