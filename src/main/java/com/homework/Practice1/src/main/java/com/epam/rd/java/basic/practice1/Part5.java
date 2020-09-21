package com.epam.rd.java.basic.practice1;
@SuppressWarnings(value= "all")
public class Part5 {

    public static void main(String[] args) {
        char[] chars = args[0].toCharArray();
        int finalCount = 0;
        for (int i = 0; i < chars.length; i++) {
            finalCount += Integer.parseInt(String.valueOf(chars[i]));
        }
        System.out.print(finalCount);
    }

}
