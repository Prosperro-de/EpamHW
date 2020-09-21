package com.epam.rd.java.basic.practice7.util;

import com.epam.rd.java.basic.practice7.entity.Deposit;

import java.util.Comparator;
import java.util.List;

public class Sorter {
    private Sorter() {
    }

    public static final Comparator<Deposit> SORT_DEPOSITS_BY_BANK_NAME = Comparator.comparing(Deposit::getBankName);
    public static final Comparator<Deposit> SORT_DEPOSITS_BY_AMOUNT = Comparator.comparing(Deposit::getAmountOnDeposit);
    public static final Comparator<Deposit> SORT_DEPOSITS_BY_TERM = Comparator.comparing(Deposit::getTimeConstraints);

    public static void printSortedList(List<Deposit> list) {
        list.forEach(System.out::println);
        System.out.println();

        System.out.println("Sorted by bank name");
        list.sort(Sorter.SORT_DEPOSITS_BY_BANK_NAME);
        list.forEach(System.out::println);
        System.out.println();

        System.out.println("Sorted by bank deposit amount");
        list.sort(Sorter.SORT_DEPOSITS_BY_AMOUNT);
        list.forEach(System.out::println);
        System.out.println();

        System.out.println("Sorted by deposit term");
        list.sort(Sorter.SORT_DEPOSITS_BY_TERM);
        list.forEach(System.out::println);
    }
}
