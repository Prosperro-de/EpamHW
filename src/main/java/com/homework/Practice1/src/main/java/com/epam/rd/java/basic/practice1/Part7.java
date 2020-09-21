package com.epam.rd.java.basic.practice1;
@SuppressWarnings(value= "all")
public class Part7 {

    public static void main(String[] args) {
        System.out.println("A" + " ==> " + str2int("A") + " ==> " + int2str(1));
        System.out.println("B" + " ==> " + str2int("B") + " ==> " + int2str(2));
        System.out.println("Z" + " ==> " + str2int("Z") + " ==> " + int2str(26));
        System.out.println("AA" + " ==> " + str2int("AA") + " ==> " + int2str(27));
        System.out.println("AZ" + " ==> " + str2int("AZ") + " ==> " + int2str(52));
        System.out.println("BA" + " ==> " + str2int("BA") + " ==> " + int2str(53));
        System.out.println("ZZ" + " ==> " + str2int("ZZ") + " ==> " + int2str(702));
        System.out.println("AAA" + " ==> " + str2int("AAA") + " ==> " + int2str(703));
    }

    public static int str2int(String number) {
        char[] chars = number.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            result *= 26;
            result += chars[i] - 'A' + 1;
        }
        return result;
    }

    public static String int2str(int number) {
        int a = 0;
        int b = 0;
        int num = number;
        Character ch;
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            a = ((num - 1) / 26);
            b = (num - 1) % 26;
            ch = (char) (b + 65);
            sb.append(ch.toString());
            num = a;
        }
        sb.reverse();
        return sb.toString();

    }

    public static String rightColumn(String number) {
        char[] chars = number.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            result *= 26;
            result += chars[i] - 'A' + 1;
        }
        int a = 0;
        int b = 0;
        int num = result + 1;
        Character ch;
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            a = ((num - 1) / 26);
            b = (num - 1) % 26;
            ch = (char) (b + 65);
            sb.append(ch.toString());
            num = a;
        }
        sb.reverse();
        return sb.toString();
    }
}
