package com.epam.rd.java.basic.practice1;
@SuppressWarnings(value= "all")
public class Part3 {

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String arg : args) {
            stringBuilder.append(arg).append(" ");
        }
        System.out.print(stringBuilder.toString().trim());
    }
	
}
