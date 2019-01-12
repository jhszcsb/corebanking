package com.jhszcsb.corebanking.service;

import com.jhszcsb.corebanking.model.Account;
import com.jhszcsb.corebanking.model.TRANSACTION_TYPE;
import com.jhszcsb.corebanking.model.TransactionLog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class AccountServiceTest {

    private AccountService underTest;
    private Account csabi;
    private Account geri;

    @Before
    public void setUp() {
        underTest = new AccountService();
        List<TransactionLog> logsForCsabi = new ArrayList<>();
        logsForCsabi.add(new TransactionLog(LocalDateTime.of(2019, Month.JANUARY, 12, 10, 0), 100, 100, TRANSACTION_TYPE.DEPOSIT, "csabi", "ATM"));
        List<TransactionLog> logsForGeri = new ArrayList<>();
        logsForGeri.add(new TransactionLog(LocalDateTime.of(2019, Month.JANUARY, 12, 11, 0), 100, 100, TRANSACTION_TYPE.DEPOSIT, "geri", "ATM"));
        csabi = new Account("csabi", logsForCsabi);
        geri = new Account("geri", logsForGeri);
    }

    @Test
    public void successfulWithdrawal() throws Exception {
        underTest.withdraw(csabi, 10);
        Assert.assertEquals(csabi.getCurrentBalance(), 90, 0);

    }

    @Test(expected = Exception.class)
    public void unsuccessfulWithdrawal() throws Exception {
        underTest.withdraw(csabi, 100000);
    }

    @Test
    public void successfulDeposit() {
        underTest.deposit(csabi, 10);
        Assert.assertEquals(csabi.getCurrentBalance(), 110, 0);
    }

    @Test
    public void successfulTransfer() throws Exception {
        underTest.transfer(csabi, geri, 10);
        Assert.assertEquals(csabi.getCurrentBalance(), 90, 0);
        Assert.assertEquals(geri.getCurrentBalance(), 110, 0);

    }

    @Test(expected = Exception.class)
    public void unsuccessfulTransfer () throws Exception {
        underTest.transfer(csabi, geri, 100000);
    }
}
