package com.epam.rd.java.basic.practice4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class Part2 {
    private static Logger log = Logger.getLogger(Part2.class.getName());
    private static final String EXCEPTION = "IOException ex {}";

    public static void main(String[] args) {
        BufferedWriter outBuff = null;
        FileWriter fW = null;
        try {
            fW = new FileWriter("part2.txt");
            outBuff = new BufferedWriter(fW);
            for (int i = 0; i < 10; i++) {
                outBuff.write(String.valueOf((int) (Math.random() * 50)));
                if (i != 9) {
                    outBuff.write(" ");
                }
            }
            outBuff.flush();
            outBuff.close();
            fW.close();
        } catch (IOException ex) {
            log.info(EXCEPTION);
        }


        ArrayList<Integer> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("part2.txt"), "Cp1251");
            while (scanner.hasNextLine()) {
                list.add(scanner.nextInt());
            }
            scanner.close();
        } catch (IOException ex) {
            log.info(EXCEPTION);
        }


        System.out.print("input ==> ");
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                System.out.println(list.get(i));
            } else System.out.print(list.get(i) + " ");
        }


        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    int y = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, y);
                }
            }
        }
        System.out.print("output ==> ");
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                System.out.println(list.get(i));
            } else System.out.print(list.get(i) + " ");
        }
        int x = 0;
        try {
            fW = new FileWriter("part2_sorted.txt");
            outBuff = new BufferedWriter(fW);
            for (Integer integer : list) {
                outBuff.write(String.valueOf(integer));
                if (x != list.size() - 1) {
                    outBuff.write(" ");
                }
                x++;
            }
            outBuff.flush();
            outBuff.close();
            fW.close();
        } catch (IOException ex) {
            log.info(EXCEPTION);
        }
    }
}
