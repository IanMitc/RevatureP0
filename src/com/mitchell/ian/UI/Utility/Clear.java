package com.mitchell.ian.UI.Utility;

// clear console method borrowed from https://www.delftstack.com/howto/java/java-clear-console/
public class Clear {

    public static void console() {
        try {
            ProcessBuilder pb;
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if (operatingSystem.contains("Windows")) {
                pb = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                pb = new ProcessBuilder("clear");
            }
            Process startProcess = pb.inheritIO().start();
            startProcess.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


