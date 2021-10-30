package com.mitchell.ian.UI.Utility;

import java.util.Scanner;

public class Ask {
    static public int forInt(String question, Scanner scanner) throws Exception {
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

    static public double forDouble(String question, Scanner scanner) throws Exception {
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

    static public String forString(String question, Scanner scanner) {
        System.out.print(question + ": ");

        return scanner.nextLine();
    }

    static public boolean forBoolean(String question, char trueChar, char falseChar, Scanner scanner) throws Exception {
        trueChar = Character.toUpperCase(trueChar);
        falseChar = Character.toUpperCase(falseChar);

        System.out.print(question + " (" + trueChar + '/' + falseChar + "): ");
        String result = scanner.nextLine();

        if (result.length() != 1 || (result.charAt(0) != trueChar && result.charAt(0) != falseChar))
            throw new Exception("Please choose " + trueChar + " or " + falseChar);
        else
            return Character.toUpperCase(result.charAt(0)) == trueChar;
    }
}
