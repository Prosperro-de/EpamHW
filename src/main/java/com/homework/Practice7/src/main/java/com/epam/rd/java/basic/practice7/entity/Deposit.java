package com.epam.rd.java.basic.practice7.entity;

public class Deposit {
    String bankName;            // - название банка
    String country;         // - страна регистрации.
    Type type;            // - тип вклада (до востребования, срочный, расчетный, накопительный, сберегательный, металлический).
    String depositor;       // - имя вкладчика.
    String accountId;       // - номер счета.
    Long amountOnDeposit;   // - сумма вклада.
    Integer profitability;  // - годовой процент.
    Integer timeConstraints;// - срок вклада.

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Long getAmountOnDeposit() {
        return amountOnDeposit;
    }

    public void setAmountOnDeposit(Long amountOnDeposit) {
        this.amountOnDeposit = amountOnDeposit;
    }

    public Integer getProfitability() {
        return profitability;
    }

    public void setProfitability(Integer profitability) {
        this.profitability = profitability;
    }

    public Integer getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(Integer timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "bankName='" + bankName + '\'' +
                ", country='" + country + '\'' +
                ", type='" + type.getValue() + '\'' +
                ", depositor='" + depositor + '\'' +
                ", accountId='" + accountId + '\'' +
                ", amountOnDeposit=" + amountOnDeposit +
                ", profitability=" + profitability +
                ", timeConstraints=" + timeConstraints +
                '}';
    }

    public enum Type {
        PR("poste restante"),
        TD("term deposit"),
        SC("settlement contribution"),
        AD("accumulative deposit"),
        SD("savings deposit"),
        MD("metal deposit");

        private String value;

        public String getValue() {
            return value;
        }

        Type(String value) {
            this.value = value;
        }
    }
}