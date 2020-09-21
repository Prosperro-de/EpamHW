package com.epam.rd.java.basic.practice4;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
    private static final Logger log = Logger.getLogger(Part6.class.getName());

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String scr = null;
        try {
            scr = reader.readLine();
            reader.close();
        } catch (IOException ex) {
            log.info("IOException ex {}");
        }

        if (scr.equalsIgnoreCase("stop")) {
            System.exit(0);
        } else if (scr.equalsIgnoreCase("cyrl")) {
            StringRUIterator stringRUIterator = new StringRUIterator();
            System.out.print("Cyrl:");
            while (stringRUIterator.hasNext()) {
                System.out.print(stringRUIterator.next().replaceAll("-", " "));
            }
            System.out.print(" " + System.lineSeparator());
        } else if (scr.equalsIgnoreCase("latn")) {
            StringEnIterator stringEnIterator = new StringEnIterator();
            System.out.print("Latn: ");

            while (stringEnIterator.hasNext()) {
                System.out.print(stringEnIterator.next());
            }
            System.out.println();
        } else System.out.println("smth: Incorrect input");
    }

    public static String getInput(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "Cp1251");
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            String s = sb.toString();
            s = s.replaceAll(System.lineSeparator(), " ");
            scanner.close();
            return s;
        } catch (IOException ex) {
            log.info("IOException ex {}");

        }
        return sb.toString();
    }//getInput

    static class StringEnIterator implements Iterator<String> {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9\\-]+\\s");
        Matcher matcher = pattern.matcher(getInput("part6.txt"));

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
            throw new UnsupportedOperationException();
        }
    }

    static class StringRUIterator implements Iterator<String> {
        Pattern pattern = Pattern.compile("\\s[а-яА-Я0-9ёЁіїґє\\-]+");
        Matcher matcher = pattern.matcher(getInput("part6.txt"));

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
            throw new UnsupportedOperationException();
        }
    }
}