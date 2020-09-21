package com.epam.rd.java.basic.practice3;


public class Part5 {


    enum Roman{

        ROMAN ("Roman");
        String string;

        static final String[] i = {"", "I", "II", "III", "IV", "V",
                "VI", "VII", "VIII", "IX"};
        static  final String[] c = {"", "C", "CC", "CCC", "CD", "D",
                "DC", "DCC", "DCCC", "CM"};
        static final String[] x = {"", "X", "XX", "XXX", "XL", "L",
                "LX", "LXX", "LXXX", "XC"};
        Roman(String str) {
            this.string = str;
        }
    }
    public static void main(String[] args) {
        //empty method

    }
    public static String decimal2Roman(int num) {

        String hund = Roman.c[(num % 1000) / 100];
        String tens = Roman.x[(num % 100) / 10];
        String ones = Roman.i[num % 10];
        return hund + tens + ones;
    }
    public static int roman2Decimal(String str) {
        int res = 0;
        int iter = 0;
        for (; iter < str.length(); iter++) {
            int s1 = value(str.charAt(iter));
            if (iter + 1 < str.length()) {
                int s2 = value(str.charAt(iter + 1));
                if (s1 >= s2) {
                    res = res + s1;
                }
                else {
                    res = res + s2 - s1;
                    iter++;
                }
            }
            else {
                res = res + s1;
                iter++;
            }
        }
        return res;
    }
    static int value(char r) {
        String str = String.valueOf(r);
        switch (str){
            case "I":
                return 1;
            case "V":
                return 5;
            case "X":
                return 10;
            case "L":
                return 50;
            case "C":
                return 100;
            default: return -1;
        }
    }
}



