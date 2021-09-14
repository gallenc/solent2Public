/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.dao.test;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.oodd.bank.model.dto.BankAccount;
import org.solent.com504.oodd.bank.model.dto.BankTransaction;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.bank.model.dto.BankTransactionStatus;
import org.solent.com504.oodd.bank.model.dto.User;
import org.solent.com504.oodd.dao.impl.BankAccountRepository;
import org.solent.com504.oodd.dao.impl.BankTransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import solent.ac.uk.ood.examples.cardcheck.CalculateLunnDigit;

/**
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the OrderServiceConfig class
@ContextConfiguration(classes = DAOTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class BankTransactionRepositoryTest {

    private static final Logger LOG = LogManager.getLogger(BankAccountRepositoryTest.class);

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    @Before
    public void before() {
        bankTransactionRepository.deleteAll();
        bankAccountRepository.deleteAll();
        assertEquals(0, bankAccountRepository.count());
        assertEquals(0, bankTransactionRepository.count());
    }

    public void createAccounts() {
        for (int i = 0; i < 10; i++) {
            User user1 = new User();
            user1.setFirstName("craig");
            user1.setSecondName("gallen_" + i);

            BankAccount ba1 = new BankAccount();
            ba1.setOwner(user1);
            ba1.setActive(true);
            CreditCard creditcard1 = new CreditCard();
            creditcard1.setEndDate("11/23");
            creditcard1.setCvv("123");
            creditcard1.setName(ba1.getOwner().getFirstName() + " " + ba1.getOwner().getSecondName());

            String formatted = String.format("%03d", i);
            String pan = "550000555555" + formatted;
            // correct mastercard 5500005555555559

            String check = CalculateLunnDigit.calculateCheckDigit(pan);
            String ccNumber = pan + check;

            creditcard1.setCardnumber(ccNumber);
            ba1.setCreditcard(creditcard1);

            BankAccount bax = bankAccountRepository.save(ba1);
        }
        assertEquals(10, bankAccountRepository.count());
    }

    @Test
    public void testTransactions() {
        LOG.debug("****************** starting testTransactions()");
        createAccounts();

        List<BankAccount> acctList = bankAccountRepository.findAll();
        assertEquals(10, acctList.size());

        BankTransaction transaction1 = new BankTransaction();
        transaction1.setAmount(100.00);
        transaction1.setFromAccount(acctList.get(0));
        transaction1.setToAccount(acctList.get(1));
        transaction1.setStatus(BankTransactionStatus.SUCCESS);
        transaction1.setTransactionDate(new Date());

        transaction1 = bankTransactionRepository.save(transaction1);
        LOG.debug("saved transaction: " + transaction1);
        assertEquals(1, bankTransactionRepository.count());

        List<BankTransaction> transactions = bankTransactionRepository.findAll();
        for (BankTransaction bt : transactions) {
            LOG.debug("found transaction: " + bt);
        }
        
        // test find by credit card number
        String testCardNumber = acctList.get(0).getCreditcard().getCardnumber();
        transactions = bankTransactionRepository.findBankTransactionsFromCreditCardNumber(testCardNumber);
        assertEquals(1, transactions.size());
        LOG.debug("found transaction ny creditcard: "+testCardNumber+ " " + transactions.get(0));
        
        
        LOG.debug("****************** test complete");
    }

    @After
    public void after() {
        bankTransactionRepository.deleteAll();
        bankAccountRepository.deleteAll();
    }

}
