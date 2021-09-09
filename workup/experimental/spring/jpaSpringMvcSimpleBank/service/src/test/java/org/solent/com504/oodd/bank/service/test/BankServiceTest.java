/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.bank.service.test;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.solent.com504.oodd.bank.model.dto.BankAccount;
import org.solent.com504.oodd.bank.model.dto.User;
import org.solent.com504.oodd.bank.model.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.oodd.bank.model.dto.BankTransaction;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.bank.model.dto.BankTransactionStatus;
import org.solent.com504.oodd.password.PasswordUtils;

/**
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the OrderServiceConfig class
@ContextConfiguration(classes = ServiceTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class BankServiceTest {

    private static final Logger LOG = LogManager.getLogger(BankServiceTest.class);

    @Autowired
    private BankService bankService;

    @Before
    public void createAccounts() {
        bankService.deleteAllData();
        assertTrue(bankService.findAllBankTransactions().isEmpty());
        assertTrue(bankService.findAllBankAccounts().isEmpty());

        List<String> supportedIssuerBanks = bankService.getSupportedIssuerBanks();

        User user1 = new User();
        user1.setFirstName("craig");
        user1.setSecondName("gallen");
        user1.setAddress("Burnett Close");
        user1.setUsername("user1");
        user1.setPassword("password1");

        BankAccount account1 = bankService.createBankAccount(user1, supportedIssuerBanks.get(0));
        LOG.debug("created bank account " + account1);

        // test password
        assertEquals("user1", account1.getOwner().getUsername());
        assertTrue(PasswordUtils.checkPassword("password1", account1.getOwner().getHashedPassword()));

        User user2 = new User();
        user2.setFirstName("fred");
        user2.setSecondName("blogs");
        user2.setAddress("Winchester");
        user2.setUsername("user2");
        user2.setPassword("password2");

        BankAccount account2 = bankService.createBankAccount(user2, supportedIssuerBanks.get(1));
        LOG.debug("created bank account " + account2);

        // test password
        assertEquals("user2", account2.getOwner().getUsername());
        assertTrue(PasswordUtils.checkPassword("password2", account2.getOwner().getHashedPassword()));

        List<BankAccount> bankAccounts = bankService.findAllBankAccounts();
        assertEquals(2, bankAccounts.size());

        LOG.debug("list of accounts ");
        for (BankAccount ba : bankAccounts) {
            LOG.debug("  " + ba);
        }

    }

    @Test
    public void bankServicetransferTest1() {
        LOG.debug("start bankServicetransferTest()");
        List<BankAccount> bankAccounts = bankService.findAllBankAccounts();
        BankAccount ba1 = bankAccounts.get(0);
        BankAccount ba2 = bankAccounts.get(1);

        assertNotNull(ba1.getOwner());

        ba1.setBalance(100.00);
        ba2.setBalance(0.00);
        bankService.saveBankAccount(ba1);
        bankService.saveBankAccount(ba2);

        BankTransaction transaction = bankService.transferMoney(ba1, ba2, 100.00);
        LOG.debug("transaction: " + transaction);

        assertEquals(BankTransactionStatus.SUCCESS, transaction.getStatus());

        BankAccount ba1_retreived = bankService.findBankAccountByNumber(ba1.getSortcode(), ba1.getAccountNo());
        BankAccount ba2_retreived = bankService.findBankAccountByNumber(ba2.getSortcode(), ba2.getAccountNo());

        assertEquals(0.00, ba1_retreived.getBalance(), 0.01);
        assertEquals(100.00, ba2_retreived.getBalance(), 0.01);

        // now try again - should fail due to insufficient funds
        LOG.debug("end bankServicetransferTest()");

    }

    @Test
    public void bankServicetransferTest2() {
        LOG.debug("start bankServicetransferTest() - credit cards");
        List<BankAccount> bankAccounts = bankService.findAllBankAccounts();
        BankAccount ba1 = bankAccounts.get(0);
        BankAccount ba2 = bankAccounts.get(1);

        ba1.setBalance(100.00);
        ba2.setBalance(0.00);
        bankService.saveBankAccount(ba1);
        bankService.saveBankAccount(ba2);

        CreditCard cc1 = ba1.getCreditcard();
        CreditCard cc2 = ba2.getCreditcard();

        BankTransaction transaction = bankService.transferMoney(cc1, cc2, 100.00);
        LOG.debug("credit card transaction: " + transaction);

        BankAccount ba1_retreived = bankService.findBankAccountByNumber(ba1.getSortcode(), ba1.getAccountNo());
        BankAccount ba2_retreived = bankService.findBankAccountByNumber(ba2.getSortcode(), ba2.getAccountNo());

        assertEquals(0.00, ba1_retreived.getBalance(), 0.01);
        assertEquals(100.00, ba2_retreived.getBalance(), 0.01);
        LOG.debug("end bankServicetransferTest()");

    }

}
