package com.jhszcsb.corebanking.service;

import com.jhszcsb.corebanking.model.Account;
import com.jhszcsb.corebanking.model.TRANSACTION_TYPE;
import com.jhszcsb.corebanking.model.TransactionLog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class HistoryServiceTest {

    private HistoryService underTest;
    private Account csabi;

    @Before
    public void setUp() {
        underTest = new HistoryService();
        List<TransactionLog> logsForCsabi = new ArrayList<>();
        logsForCsabi.add(new TransactionLog(LocalDateTime.of(2019, Month.JANUARY, 11, 10, 0), 100, 100, TRANSACTION_TYPE.DEPOSIT, "csabi", "ATM"));
        logsForCsabi.add(new TransactionLog(LocalDateTime.of(2019, Month.JANUARY, 12, 11, 0), 10, 90, TRANSACTION_TYPE.WITHDRAWAL, "csabi", "ATM"));
        logsForCsabi.add(new TransactionLog(LocalDateTime.of(2019, Month.JANUARY, 12, 12, 0), 10, 100, TRANSACTION_TYPE.DEPOSIT, "csabi", "ATM"));
        csabi = new Account("csabi", logsForCsabi);
    }

    @Test
    public void transactionLogsByType() {
        List<TransactionLog> deposits = underTest.getTransactionLogsByType(csabi, TRANSACTION_TYPE.DEPOSIT);
        Assert.assertTrue(deposits.size() > 0);
        for (TransactionLog log : deposits) {
            Assert.assertEquals(log.getTransactionType(), TRANSACTION_TYPE.DEPOSIT);
        }
        List<TransactionLog> withdrawals = underTest.getTransactionLogsByType(csabi, TRANSACTION_TYPE.WITHDRAWAL);
        Assert.assertTrue(withdrawals.size() > 0);
        for (TransactionLog log : withdrawals) {
            Assert.assertEquals(log.getTransactionType(), TRANSACTION_TYPE.WITHDRAWAL);
        }
    }

    @Test
    public void transactionFilteredByDate() {
        LocalDate testDate = LocalDate.of(2019, Month.JANUARY, 11);
        List<TransactionLog> transactionLogsByDate = underTest.getTransactionLogsByDate(csabi, testDate);

        Assert.assertTrue(transactionLogsByDate.size() > 0);
        for (TransactionLog log : transactionLogsByDate) {
            Assert.assertTrue(log.getDate().isAfter(testDate.atStartOfDay()) &&
                    log.getDate().isBefore(LocalDateTime.from(testDate.plusDays(1).atStartOfDay())));
        }
    }


}
