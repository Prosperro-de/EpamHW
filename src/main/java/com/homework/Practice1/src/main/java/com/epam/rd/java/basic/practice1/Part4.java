package com.epam.rd.java.basic.practice1;
@SuppressWarnings(value= "all")
public class Part4 {

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int count = 1;
        int finalCount = 0;

        for (int i = Integer.MAX_VALUE; i > 0; i--) {
            if (x % count == 0 && y % count == 0) {
                finalCount = count;
            }
            count++;
        }
        System.out.print(finalCount);

    }
}
