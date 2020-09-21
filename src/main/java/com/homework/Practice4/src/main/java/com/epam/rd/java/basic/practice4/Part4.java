package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 {
    private static Logger log = Logger.getLogger(Part4.class.getName());

    public static void main(String[] args) {
        StringIterator stringIterator = new StringIterator();
        while (stringIterator.hasNext()) {
            System.out.println(stringIterator.next());
        }
    }
    public static String getInput(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "Cp1251");
            while(scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            String s = sb.toString();
            s = s.replaceAll(System.lineSeparator(), " ");
            scanner.close();
            return s;
        }catch (IOException ex) {
            log.info("IOException ex {}");
        }
        return sb.toString();
    }//getInput

    static class StringIterator implements Iterator<String> {
       Pattern pattern = Pattern.compile("(?:^|[A-ZА-Я0-9Ё])[^.]+(?=\\.).");
        Matcher matcher = pattern.matcher(getInput("part4.txt"));
        @Override
        public boolean hasNext() {
            return matcher.find();
        }

        @Override
        public String next() {
                return matcher.group();

        }

        @Override
        public void remove() {
            throw new  UnsupportedOperationException();
        }
    }
}
