package com.epam.rd.java.basic.practice5;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part4 {
    private static final Logger LOGGER = Logger.getLogger(Part4.class.getName());
    private static final String FILE = "part4.txt";
    private static final int SLEEP_TIME = 10;

    public static void main(final String[] args) {
        System.out.println(singleThreading());
        System.out.println(multiThreading());
    }

    private static String multiThreading(){
        long startTime = System.currentTimeMillis();
        String findString = readFromFile();
        String[] strings = findString.split(System.lineSeparator());
        int[] arrayMaxValuesOfEachStringMatrix = new int[strings.length];

        for (int i = 0; i < strings.length; i++){
            NewTread thread = new NewTread(strings[i]);
            thread.start();
            sleep(SLEEP_TIME);
            arrayMaxValuesOfEachStringMatrix[i] = thread.value;
        }
        int maxValue = Arrays.stream(arrayMaxValuesOfEachStringMatrix).max().getAsInt();
        long endTime = System.currentTimeMillis();
        return maxValue + System.lineSeparator() + (endTime - startTime);
    }

    private static String singleThreading(){
        long startTime = System.currentTimeMillis();
        String findString = readFromFile();
        String strings = findString.replace(System.lineSeparator(), " ");
        NewTread singleThread = new NewTread(strings);
        singleThread.start();
        sleep(SLEEP_TIME);
        int maxValue = singleThread.value;
        long endTime = System.currentTimeMillis();
        return maxValue + System.lineSeparator() + (endTime - startTime);

    }

    private static void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Interrupted exception", e);
            Thread.currentThread().interrupt();
        }
    }

    private static String readFromFile() {
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE))){
            String string;
            while ((string = bufferedReader.readLine()) != null){
                stringBuilder.append(string).append(System.lineSeparator());
            }
        } catch (IOException ex){
            LOGGER.log(Level.INFO, ex.getMessage());
        }
        return stringBuilder.deleteCharAt(stringBuilder.length()-1).toString();
    }

    private static class NewTread extends Thread {
        private int value;
        private String string;
        public NewTread(String strings) {
            this.string = strings;
        }

        @Override
        public void run() {
            String[] digitsInString = string.split(" ");
            for(String digitInString : digitsInString){
                int digit = Integer.parseInt(digitInString.trim());
                if(value < digit){
                    value = digit;
                }
            }
        }
    }
}