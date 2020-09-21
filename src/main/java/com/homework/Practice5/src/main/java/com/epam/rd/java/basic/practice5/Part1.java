package com.epam.rd.java.basic.practice5;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Part1 {
    private static final Logger LOGGER = Logger.getLogger(Part1.class.getName());


    public static void main(String[] args) {

        Thread firstThread = new FirstThread();
        firstThread.start();
        Thread secondThread = new Thread(new SecondThread());
        try {
            Thread.sleep(2000);
            firstThread.join();
        } catch (InterruptedException e) {
            LOGGER.log(Level.INFO, e.getMessage());
            Thread.currentThread().interrupt();
        }
        secondThread.start();
        try {
            secondThread.join();
        } catch (InterruptedException e) {
            LOGGER.log(Level.INFO, e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    static class FirstThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 4; i++){
                System.out.println(Thread.currentThread().getName());
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    LOGGER.log(Level.INFO, e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static class SecondThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    LOGGER.log(Level.INFO, e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
