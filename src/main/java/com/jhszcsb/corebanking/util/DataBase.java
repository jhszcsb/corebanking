package com.jhszcsb.corebanking.util;

import com.jhszcsb.corebanking.model.Account;
import com.jhszcsb.corebanking.model.TRANSACTION_TYPE;
import com.jhszcsb.corebanking.model.TransactionLog;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {

    private Map<String, Account> accounts = new HashMap<>();

    public DataBase() {
        List<TransactionLog> logsForCsabi = new ArrayList<>();
        logsForCsabi.add(new TransactionLog(LocalDateTime.of(2019, Month.JANUARY, 11, 10, 0), 1000, 1000, TRANSACTION_TYPE.DEPOSIT, "csabi", "ATM"));
        logsForCsabi.add(new TransactionLog(LocalDateTime.of(2019, Month.JANUARY, 12, 10, 10), 1000, 2000, TRANSACTION_TYPE.DEPOSIT, "csabi", "ATM"));
        logsForCsabi.add(new TransactionLog(LocalDateTime.of(2019, Month.JANUARY, 12, 11, 23), 1000, 3000, TRANSACTION_TYPE.DEPOSIT, "csabi", "ATM"));

        List<TransactionLog> logsForGeri = new ArrayList<>();
        logsForGeri.add(new TransactionLog(LocalDateTime.of(2019, Month.JANUARY, 12, 10, 0), 1000, 1000, TRANSACTION_TYPE.DEPOSIT, "geri", "ATM"));
        logsForGeri.add(new TransactionLog(LocalDateTime.of(2019, Month.JANUARY, 12, 10, 30), 1000, 2000, TRANSACTION_TYPE.DEPOSIT, "geri", "ATM"));

        accounts.put("csabi", new Account("csabi", logsForCsabi));
        accounts.put("geri", new Account("geri", logsForGeri));
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }
}
