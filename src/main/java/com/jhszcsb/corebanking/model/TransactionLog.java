package com.jhszcsb.corebanking.model;

import java.time.LocalDateTime;

public final class TransactionLog {

    private final LocalDateTime date;
    private final double amount;
    private final double currentBalance;
    private final TRANSACTION_TYPE transactionType;
    private final String from;
    private final String to;

    public TransactionLog(LocalDateTime date, double amount, double currentBalance, TRANSACTION_TYPE transactionType, String from, String to) {
        this.date = date;
        this.amount = amount;
        this.currentBalance = currentBalance;
        this.transactionType = transactionType;
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public TRANSACTION_TYPE getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "\nTransactionLog{" +
                "date=" + date +
                ", amount=" + amount +
                ", currentBalance=" + currentBalance +
                ", transactionType=" + transactionType +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}' + "\n";
    }
}
