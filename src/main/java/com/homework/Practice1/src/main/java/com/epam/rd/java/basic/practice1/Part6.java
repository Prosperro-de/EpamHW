package com.epam.rd.java.basic.practice1;

@SuppressWarnings(value= "all")
public class Part6 {

    public static void main(String[] args) {
        int y = 1;
        int[] array = new int[Integer.parseInt(args[0])];
        for (int i = 0; i < array.length; i++) {

            while (true) {
                y++;
                if (y == 2 || y == 3 || y == 5) {
                    array[i] = y;
                    break;
                } else if (y % 2 != 0 && y % 3 != 0 && y % 5 != 0) {
                    break;
                }
            }
            array[i] = y;
        }
        for (int i = 0; i <array.length; i++)
        {
            if (i == array.length-1) {
                System.out.print(array[i]);
            } else System.out.print(array[i]+ " ");
        }


    }
}
