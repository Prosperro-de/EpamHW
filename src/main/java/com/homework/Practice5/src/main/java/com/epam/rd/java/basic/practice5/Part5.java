package com.epam.rd.java.basic.practice5;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part5 implements Callable {
    private static final Logger LOGGER = Logger.getLogger(Part5.class.getName());
    private static final Object MONITOR = new Object();
    private static final int THREADS_NUMBER = 10;
    private static final int COLUMNS = 20;
    private static final int EOL_LENGTH = System.lineSeparator().length();
    private static String fileName = "Part5.txt";
    private static final String CHARSET_NAME = "Cp1251";

    private RandomAccessFile out;
    private int position;
    private String characters;

    public Part5(RandomAccessFile out, int position, String charToFill, int stringLength) {
        this.out = out;
        this.position = position;

        StringBuilder sb = new StringBuilder(stringLength);
        for (int i = 0; i < COLUMNS; i++) {
            sb.append(charToFill);
            if (i == COLUMNS-1 && !charToFill.equals("9")){
              sb.append(System.lineSeparator());
            }
        }
        characters = sb.toString();
    }

    @Override
    public Object call() {
        RandomAccessFile raf = getRAF();
        synchronized (MONITOR) {
            try {
                raf.seek(position);
                raf.write(characters.getBytes(CHARSET_NAME));
            } catch (IOException e) {
                LOGGER.log(Level.INFO, e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        return null;
    }

    private RandomAccessFile getRAF() {
        return out;
    }

    public static void main(String[] args) {
        try {
            Files.deleteIfExists(new File(fileName).toPath());
            File file = new File(fileName);
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            ExecutorService exe = Executors.newFixedThreadPool(THREADS_NUMBER);

            ArrayList<Future> futures = new ArrayList<>();

            int position = 0;
            for (int i = 0; i < THREADS_NUMBER; i++) {
                String charToFill = String.valueOf(i);
                int charsLength = COLUMNS * charToFill.length();
                int stringLength = charsLength + EOL_LENGTH;

                Future f = exe.submit(new Part5(raf, position, charToFill, stringLength));
                futures.add(f);

                position += stringLength;
            }

            for (Future future : futures) {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    LOGGER.log(Level.INFO, e.getMessage());
                    Thread.currentThread().interrupt();
                } catch (ExecutionException e) {
                    LOGGER.log(Level.INFO, e.getMessage());
                    Thread.currentThread().interrupt();

                }
            }
            exe.shutdown();
            raf.close();
            System.out.println(new String(Files.readAllBytes(file.toPath()), CHARSET_NAME));
        } catch (IOException e) {
            LOGGER.log(Level.INFO, e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}