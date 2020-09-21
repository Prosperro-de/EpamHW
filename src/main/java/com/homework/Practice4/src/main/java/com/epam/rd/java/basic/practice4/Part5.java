package com.epam.rd.java.basic.practice4;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Logger;

public class Part5 {
    private static Logger log = Logger.getLogger(Part5.class.getName());

    public static void main(String[] args) {
        Locale locale1 = new Locale("ru", "RU");
        Locale locale2 = new Locale("en", "US");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] mas = sc.nextLine().split(" ");
            if (mas[0].equals("stop")) {
                break;
            }
            Locale locale = new Locale(mas[1]);
            ResourceBundle defRb = ResourceBundle.getBundle("resources", locale);
            System.out.println(defRb.getString(mas[0]));
        }
        sc.close();
    }
}


