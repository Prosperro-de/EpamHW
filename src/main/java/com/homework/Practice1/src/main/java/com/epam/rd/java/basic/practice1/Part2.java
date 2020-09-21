package com.epam.rd.java.basic.practice1;
@SuppressWarnings(value= "all")
public class Part2 {

    public static void main(String[] args) {
        int x = 0;
        for (String arg : args) {
            x += Integer.parseInt(arg);
        }
        System.out.print(x);
    }
}
