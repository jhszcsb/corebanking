package com.jhszcsb.corebanking.service;

import com.jhszcsb.corebanking.model.TRANSACTION_TYPE;
import com.jhszcsb.corebanking.model.Account;
import com.jhszcsb.corebanking.model.TransactionLog;

import java.time.LocalDateTime;

public class AccountService {

    private static final String ATM_CODE = "ATM";

    public double withdraw(Account account, final double amount) throws Exception {
        double currentBalance = account.getCurrentBalance();
        if (currentBalance < amount) {
            throw new Exception("Not enough balance to perform withdraw!");
        }
        currentBalance -= amount;
        account.addTransactionLog(
                new TransactionLog(
                        LocalDateTime.now(), amount, currentBalance, TRANSACTION_TYPE.WITHDRAWAL, account.getIdentifier(), ATM_CODE));
        return currentBalance;
    }

    public double deposit(Account account, final double amount) {
        double currentBalance = account.getCurrentBalance();
        currentBalance += amount;
        account.addTransactionLog(
                new TransactionLog(
                        LocalDateTime.now(), amount, currentBalance, TRANSACTION_TYPE.DEPOSIT, ATM_CODE, account.getIdentifier()));
        return currentBalance;
    }

    public double transfer(Account from, Account to, final double amount) throws Exception {
        double currentBalanceOnFrom = from.getCurrentBalance();
        double currentBalanceOnTo = to.getCurrentBalance();
        if (currentBalanceOnFrom < amount) {
            throw new Exception("Not enough balance to perform withdraw!");
        }
        currentBalanceOnFrom -= amount;
        currentBalanceOnTo += amount;
        from.addTransactionLog(
                new TransactionLog(
                        LocalDateTime.now(), amount, currentBalanceOnFrom, TRANSACTION_TYPE.TRANSFER, from.getIdentifier(), to.getIdentifier()));
        to.addTransactionLog(
                new TransactionLog(
                        LocalDateTime.now(), amount, currentBalanceOnTo, TRANSACTION_TYPE.TRANSFER, from.getIdentifier(), to.getIdentifier()));
        return amount;
    }
}
