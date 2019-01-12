package com.jhszcsb.corebanking;

import com.jhszcsb.corebanking.model.Account;
import com.jhszcsb.corebanking.model.TRANSACTION_TYPE;
import com.jhszcsb.corebanking.service.AccountService;
import com.jhszcsb.corebanking.service.HistoryService;
import com.jhszcsb.corebanking.util.DataBase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class CorebankingApplication {

	public static void main(String[] args) {
        SpringApplication.run(CorebankingApplication.class, args);


        DataBase db = new DataBase();
        AccountService accountService = new AccountService();
        HistoryService historyService = new HistoryService();

        Account csabi = db.getAccounts().get("csabi");
        Account geri = db.getAccounts().get("geri");

        System.out.println("csabi:" + csabi.getCurrentBalance());
        System.out.println("Deposit 50 to csabi");
        accountService.deposit(csabi, 50);
        System.out.println("csabi:" + csabi.getCurrentBalance());
        System.out.println("Withdraw 200 from csabi");
        try {
            accountService.withdraw(csabi, 200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("csabi:" + csabi.getCurrentBalance());
        System.out.println();
        System.out.println("csabi:" + csabi.getCurrentBalance());
        System.out.println("geri:" + geri.getCurrentBalance());
        System.out.println("Transfer 10 from csabi to geri");
        try {
            accountService.transfer(csabi, geri, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("csabi:" + csabi.getCurrentBalance());
        System.out.println("geri:" + geri.getCurrentBalance());

        System.out.println();
        System.out.println("csabi transaction history: " + csabi.getTransactionLogs());

        System.out.println();
        System.out.println("csabi transactions sorted by date: " + historyService.getTransactionLogsOrderedByDate(csabi));
        System.out.println();

        System.out.println("csabi deposits: " + historyService.getTransactionLogsByType(csabi, TRANSACTION_TYPE.DEPOSIT));
        System.out.println("csabi withdrawals: " + historyService.getTransactionLogsByType(csabi, TRANSACTION_TYPE.WITHDRAWAL));
        System.out.println("csabi transfers: " + historyService.getTransactionLogsByType(csabi, TRANSACTION_TYPE.TRANSFER));
        System.out.println("geri transfers: " + historyService.getTransactionLogsByType(geri, TRANSACTION_TYPE.TRANSFER));
        System.out.println();

        System.out.println("csabi transactions on 2019.01.12: " + historyService.getTransactionLogsByDate(csabi,
                LocalDate.of(2019, Month.JANUARY, 12)));

    }

}

