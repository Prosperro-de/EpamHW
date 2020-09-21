package com.epam.rd.java.basic.practice7.constants;


public enum XML {
    BANK("Bank"),
    DEPOSIT("Deposit"),
    ID("ID"),
    TYPE("type"),
    NAME("Name"),
    COUNTRY("Country"),
    DEPOSITOR("Depositor"),
    AMOUNT("Amount"),
    PERCENT("percent"),
    TIME("time");

    XML(String tagName) {
        this.value = tagName;
    }

    private String value;

    public boolean equalsTo(String tagName) {
        return this.value.equals(tagName);
    }

    public String value() {
        return value;
    }
}
