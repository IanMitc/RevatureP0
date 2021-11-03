package com.mitchell.ian.UI.Utility;

import java.util.Scanner;

public class Ask {
    static public int forInt(String question) throws Exception {
        Scanner scanner = ScannerFactory.getScanner();

        System.out.print(question + ": ");
        if (!scanner.hasNextInt()) {
            scanner.next();
            throw new Exception("Please Enter a valid Integer");
        }

        int response = scanner.nextInt();
        if (scanner.hasNextLine())
            scanner.nextLine();

        return response;
    }

    static public double forDouble(String question) throws Exception {
        Scanner scanner = ScannerFactory.getScanner();

        System.out.print(question + ": ");
        if (!scanner.hasNextDouble()) {
            scanner.next();
            throw new Exception("Please Enter a valid Number");
        }

        double response = scanner.nextDouble();
        if (scanner.hasNextLine())
            scanner.nextLine();

        return response;
    }

    static public String forString(String question) {
        Scanner scanner = ScannerFactory.getScanner();

        System.out.print(question + ": ");

        return scanner.nextLine();
    }

    static public boolean forBoolean(String question, char trueChar, char falseChar) throws Exception {
        Scanner scanner = ScannerFactory.getScanner();

        trueChar = Character.toUpperCase(trueChar);
        falseChar = Character.toUpperCase(falseChar);

        System.out.print(question + " (" + trueChar + '/' + falseChar + "): ");
        String result = scanner.nextLine();
        char charResult = Character.toUpperCase(result.charAt(0));

        if (result.length() != 1 || (charResult != trueChar && charResult != falseChar))
            throw new Exception("Please choose " + trueChar + " or " + falseChar);
        else
            return charResult == trueChar;
    }
}
