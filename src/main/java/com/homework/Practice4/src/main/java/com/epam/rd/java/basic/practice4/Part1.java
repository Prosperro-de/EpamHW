package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1{
    private static Logger log = Logger.getLogger(Part1.class.getName());

    public static String convert(String input) {
        StringBuffer sb = new StringBuffer();
        Pattern p  = Pattern.compile("(?Us)[\\w&&\\D]{4,}");
        Matcher m = p.matcher(input);
        while(m.find()) {
            m.appendReplacement(sb, m.group().substring(2));

        }
        m.appendTail(sb);
        return sb.toString().trim();
    }//convert

    public static String getInput(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "Cp1251");
            while(scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb.toString();
        }catch (IOException ex) {
            log.info("IOException ex {}");
        }
            return sb.toString();
    }//getInput
    public static void main(String[] args) {
        String str = getInput("part1.txt");
        System.out.println(convert(str));


    }

}