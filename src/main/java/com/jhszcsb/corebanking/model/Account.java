package com.jhszcsb.corebanking.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Account {

    private final String identifier;
    private List<TransactionLog> transactionLogs;

    public Account(String identifier, List<TransactionLog> transactionLogs) {
        this.identifier = identifier;
        this.transactionLogs = transactionLogs;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<TransactionLog> getTransactionLogs() {
        return transactionLogs;
    }

    public double getCurrentBalance() {
        if (!transactionLogs.isEmpty()) {
            List<TransactionLog> logsSorted = transactionLogs.stream().sorted(Comparator.comparing(TransactionLog::getDate)).collect(Collectors.toList());
            TransactionLog lastTransactionLog = logsSorted.get(transactionLogs.size() - 1);
            return lastTransactionLog.getCurrentBalance();
        }
        return 0;
    }

    // this is another implementation of getting the current balance
    // pro: it's a better solution as it uses all transactions to get the amount
    // contra: performance issues when having a big amount of data
    public double getCurrentBalance2() {
        return transactionLogs.stream().map(t -> {
            if (TRANSACTION_TYPE.WITHDRAWAL.equals(t.getTransactionType())) {
                return -t.getAmount();
            } else {
                return t.getAmount();
            }
        }).reduce(0.0, (a, b) -> a + b);
    }

    public void addTransactionLog(TransactionLog transactionLog) {
        transactionLogs.add(transactionLog);
    }
}
