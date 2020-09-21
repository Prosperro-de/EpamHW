package com.epam.rd.java.basic.practice7.entity;

import java.util.ArrayList;
import java.util.List;


public class Bank {
    private List<Deposit> deposits;

    public List<Deposit> getDeposits() {
        if (deposits == null) {
            deposits = new ArrayList<>();
        }
        return deposits;
    }
}
