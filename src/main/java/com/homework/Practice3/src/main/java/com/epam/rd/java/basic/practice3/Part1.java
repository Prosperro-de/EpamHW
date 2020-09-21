package com.epam.rd.java.basic.practice3;


import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    static SecureRandom rand = new SecureRandom();
    public static void main(String[] args) {
        //empty method
    }

    public static String convert1(String input) {
        input = input.substring(17);
        Pattern pattern = Pattern.compile(";.+?;");
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll(": ") + "\n";
    }

    public static String convert2(String input) {
        input = input.substring(16);
        Pattern pattern = Pattern.compile(";.+?com");
        Matcher matcher = pattern.matcher(input);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            sb.append(input.substring(start + 1, end) + ")" + "\n");
        }
        pattern = Pattern.compile(";");
        matcher = pattern.matcher(sb.toString());
        String almost = matcher.replaceAll(" (email: ");
        String[] almostArray = almost.split("[\\s\\n]");
        int cursor = 0;
        String temp;
        for (int i = 0; i < 4; i++) {
            temp = almostArray[cursor];
            almostArray[cursor] = almostArray[cursor + 1];
            almostArray[cursor + 1] = temp;
            cursor += 4;
        }
        sb = new StringBuilder();
        for (int i = 0; i < almostArray.length; i++) {
            sb.append(almostArray[i] + " ");
            if (i == 3 || i == 7 || i == 11) {
                sb.setLength(sb.length() - 1);
                sb.append("\n");
            }
        }
        return sb.toString().trim() + "\n";
    }

    public static String convert3(String input) {

        StringBuilder sbMail = new StringBuilder().append("mail.com ==> ");
        StringBuilder sbGoogle = new StringBuilder().append("google.com ==> ");
        input = input.substring(16);
        String[] splitedString = input.split(".com");
        for (int i = 0; i < splitedString.length; i++) {
            if (splitedString[i].contains("mail")) {
                String[] surnameList = splitedString[i].split(";");
                sbMail.append(surnameList[0].substring(1) + ", ");
            } else {
                String[] surnameList = splitedString[i].split(";");
                sbGoogle.append(surnameList[0].substring(1) + ", ");
            }
        }
        sbMail.setLength(sbMail.length() - 2);
        sbGoogle.setLength(sbGoogle.length() - 2);
        return sbMail.toString() + "\n" + sbGoogle.toString() + "\n";
    }

    public static String convert4(String input) {
        input = input.substring(16);

        String[] splitedString = input.split(".com");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splitedString.length; i++) {
            sb.append(splitedString[i] + ".com");
            sb.append(String.format("%04d", rand.nextInt(10000)));
        }
        return "Login;Name;Email;Password" + "\n" + sb.toString().trim();
    }
}