package com.mitchell.ian.UI.Utility;

import java.util.Scanner;

public class ScannerFactory {
    private static Scanner scanner = null;

    private ScannerFactory(){

    }

    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
