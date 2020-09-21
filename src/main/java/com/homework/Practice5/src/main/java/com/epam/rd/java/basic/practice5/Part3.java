package com.epam.rd.java.basic.practice5;


import java.util.logging.Level;
import java.util.logging.Logger;

public class Part3 {
    private static final Logger LOGGER = Logger.getLogger(Part3.class.getName());
    private int COUNT_OF_ITERATIONS;
    private Thread[] THREADS;
    Object monitor = new Object();


    public Part3(int COUNT_OF_THREAD, int COUNT_OF_ITERATIONS) {
        this.COUNT_OF_ITERATIONS = COUNT_OF_ITERATIONS;
        this.THREADS = new Thread[COUNT_OF_THREAD];
    }

    private int counter;

    private int counter2;

    public static void main(final String[] args) {
        Part3 part3 = new Part3(4, 2);
        part3.compare();
        part3.compareSync();
    }

    public void compare() {
        for (int i = 0; i < THREADS.length; i++) {
            THREADS[i] = new Thread(new MyThread());
        }
        startAndJoinThreads();
        counter = 0;
        counter2 = 0;
    }


    public void compareSync() {
        for (int i = 0; i < THREADS.length; i++) {
            THREADS[i] = new Thread(new MyThreadSync());
        }
            startAndJoinThreads();

    }


    public void startAndJoinThreads() {
        for (Thread thread : THREADS) {
            thread.start();
        }
        for (Thread thread : THREADS) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                LOGGER.log(Level.INFO, e.getMessage());
                Thread.currentThread().interrupt();

            }
        }
    }

    class MyThread implements Runnable {
        @Override
        public void run() {
            for (int j = 0; j < COUNT_OF_ITERATIONS; j++) {
                if (counter == counter2) {
                    System.out.println(counter + " == " + counter2);
                } else {
                    System.out.println(counter + " != " + counter2);
                }
                counter++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    LOGGER.log(Level.INFO, e.getMessage());
                    Thread.currentThread().interrupt();
                }
                counter2++;
            }
        }
    }class MyThreadSync implements Runnable {
        @Override
        public void run() {
                for (int j = 0; j < COUNT_OF_ITERATIONS; j++) {
                    synchronized (monitor) {
                    if (counter == counter2) {
                        System.out.println(counter + " == " + counter2);
                    } else {
                        System.out.println(counter + " != " + counter2);
                    }
                    counter++;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        LOGGER.log(Level.INFO, e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                    counter2++;
                }
            }
        }
    }
}