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

        BankAccount ba1 = new BankAccount();
        ba1.setOwner(user1);
        ba1.setActive(true);
        CreditCard creditcard1 = new CreditCard();
        creditcard1.setEndDate("11/23");
        creditcard1.setCvv("123");
        creditcard1.setName(ba1.getOwner().getFirstName()+" "+ba1.getOwner().getSecondName());
        creditcard1.setCardnumber("4444444444444448");
        ba1.setCreditcard(creditcard1);
        
        BankAccount bax = bankAccountRepository.save(ba1);

        assertEquals(1, bankAccountRepository.count());

        Optional<BankAccount> optional = bankAccountRepository.findById(bax.getId());
        BankAccount foundba1 = optional.get();

        LOG.debug("found BankAccount: " + foundba1);

        LOG.debug("****************** test complete");
    }
    
    
    @After
    public void after() {
        bankTransactionRepository.deleteAll();
        bankAccountRepository.deleteAll();
    }

}
