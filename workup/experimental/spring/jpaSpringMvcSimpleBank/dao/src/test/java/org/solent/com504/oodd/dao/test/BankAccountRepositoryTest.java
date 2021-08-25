/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.dao.test;

import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.oodd.bank.model.dto.BankAccount;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.bank.model.dto.User;
import org.solent.com504.oodd.dao.impl.BankAccountRepository;
import org.solent.com504.oodd.dao.impl.BankTransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the OrderServiceConfig class
@ContextConfiguration(classes = DAOTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class BankAccountRepositoryTest {

    private static final Logger LOG = LogManager.getLogger(BankAccountRepositoryTest.class);

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    @Before
    public void before() {
        bankTransactionRepository.deleteAll();
        bankAccountRepository.deleteAll();
    }

    @Test
    public void testBankAccount() {
        LOG.debug("****************** starting testBankAccount()");

        assertEquals(0, bankAccountRepository.count());

        User user1 = new User();
        user1.setFirstName("craig");
        user1.setSecondName("gallen");
        user1.setAddress("Burnett Close");

        String testSortCode = "11-11-11";
        String testAccountNo = "123456789";

        BankAccount ba1 = new BankAccount();
        ba1.setSortcode(testSortCode);
        ba1.setAccountNo(testAccountNo);
        ba1.setOwner(user1);
        ba1.setActive(true);

        CreditCard creditcard1 = new CreditCard();
        creditcard1.setEndDate("11/23");
        creditcard1.setCvv("123");
        creditcard1.setName(ba1.getOwner().getFirstName() + " " + ba1.getOwner().getSecondName());
        String testCreditcardNo = "4444444444444448";
        creditcard1.setCardnumber(testCreditcardNo);
        ba1.setCreditcard(creditcard1);

        BankAccount bax = bankAccountRepository.save(ba1);

        assertEquals(1, bankAccountRepository.count());

        Optional<BankAccount> optional = bankAccountRepository.findById(bax.getId());
        BankAccount foundba1 = optional.get();

        LOG.debug("found BankAccount: " + foundba1);

        // test find by bank account number  
        BankAccount retrievedBankAccount = bankAccountRepository.findBankAccountByNumber(testSortCode, testAccountNo);
        assertNotNull(retrievedBankAccount);
        LOG.debug("found by number : " + retrievedBankAccount);

        retrievedBankAccount = bankAccountRepository.findBankAccountByNumber(testSortCode, "111111111");
        assertNull(retrievedBankAccount);

        // test find by creditcard mumber
        retrievedBankAccount = bankAccountRepository.findBankAccountByCreditCardNo(testCreditcardNo);
        assertNotNull(retrievedBankAccount);
        retrievedBankAccount = bankAccountRepository.findBankAccountByCreditCardNo("5555555555555555");
        assertNull(retrievedBankAccount);

        LOG.debug("****************** test complete");
    }

    @After
    public void after() {
        bankTransactionRepository.deleteAll();
        bankAccountRepository.deleteAll();
    }

}
