package com.epam.rd.java.basic.practice3;


public class Part3 {

    public static void main(String[] args) throws Exception{
        //something
    }

    public static String convert(String input) {
        StringBuilder sb = new StringBuilder();
        String[] inputArray = input.split(" ");
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i].length() > 2) {
                if (inputArray[i].substring(0, 1).matches("[a-zа-я]")) {
                    inputArray[i] = inputArray[i].substring(0, 1).toUpperCase()
                            + inputArray[i].substring(1);
                } else {
                    inputArray[i] = inputArray[i].substring(0, 1).toLowerCase()
                            + inputArray[i].substring(1);
                }
            }
        }
        for (int i = 0; i < inputArray.length; i++) {
            sb.append(inputArray[i] + " ");
        }
        return sb.toString().trim();
    }
}
