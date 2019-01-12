package com.jhszcsb.corebanking.service;

import com.jhszcsb.corebanking.model.Account;
import com.jhszcsb.corebanking.model.TRANSACTION_TYPE;
import com.jhszcsb.corebanking.model.TransactionLog;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryService {

    public List<TransactionLog> getTransactionLogsByDate(Account account, LocalDate date) {
        return account.getTransactionLogs().stream()
                .sorted(Comparator.comparing(TransactionLog::getDate))
                .filter(t -> (t.getDate().isAfter(date.atStartOfDay()) && t.getDate().isBefore(LocalDateTime.from(date.plusDays(1).atStartOfDay()))))
                .collect(Collectors.toList());
    }

    public List<TransactionLog> getTransactionLogsOrderedByDate(Account account) {
        return account.getTransactionLogs().stream().sorted(Comparator.comparing(TransactionLog::getDate)).collect(Collectors.toList());
    }

    public List<TransactionLog> getTransactionLogsByType(Account account, TRANSACTION_TYPE transactionType) {
        return account.getTransactionLogs().stream()
                .filter(transactionLog -> transactionLog.getTransactionType().equals(transactionType)).collect(Collectors.toList());
    }

    public void printTransactionLogs(Account account) {
        System.out.println(getTransactionLogsOrderedByDate(account));
    }
}
