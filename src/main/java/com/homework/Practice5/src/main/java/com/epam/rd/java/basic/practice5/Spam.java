package com.epam.rd.java.basic.practice5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Spam {

    private static final Logger LOGGER = Logger.getLogger(Spam.class.getName());
    private Thread[] newThreads;
    private String[] messages;
    private int[] count;

    public Spam(String[] messages, int[] intervals) {
        this.messages = messages;
        this.count = intervals;
        this.newThreads = new Thread[messages.length];
    }

    public static void main(final String[] args) {
        String[] messages = new String[]  {"@@@","bbbbbbb"};
        int[] times = new int[] { 333, 222 };
        Spam spam = new Spam(messages, times);
        spam.start();
        spam.waitPush();
        spam.stop();

    }

    public void start() {
        for(int index = 0; index < messages.length; index++) {
            String message = messages[index];
            int time = count[index];
            Thread thread = new Printer(message, time);
            newThreads[index] = thread;
            thread.start();

        }
    }

    public void stop() {
        for(Thread thread : newThreads) {
            thread.interrupt();
        }
        for(Thread thread : newThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                LOGGER.log(Level.INFO, e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

    }

    private void waitPush() {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!((bufferedReader.readLine()).equals("")));
        } catch (IOException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }

    }

    public static class Printer extends Thread {
        private final String message;
        private final int timer;

        public Printer(String message, int time) {
            this.message = message;
            this.timer = time;
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                System.out.println(message);
                try {
                    Thread.sleep(timer);
                } catch (InterruptedException e) {
                    LOGGER.log(Level.INFO, e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }

    }

}