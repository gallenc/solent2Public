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
import org.solent.com504.oodd.dao.impl.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;

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

    @Test
    public void bankServiceTest() {

        List<String> supportedIssuerBanks = bankService.getSupportedIssuerBanks();
        
        User user1 = new User();
        user1.setFirstName("craig");
        user1.setSecondName("gallen");
        user1.setAddress("Burnett Close");
        
        BankAccount account1 = bankService.createBankAccount(user1, supportedIssuerBanks.get(0));
        LOG.debug("created bank account "+account1);
        
         User user2 = new User();
        user2.setFirstName("fred");
        user2.setSecondName("blogs");
        user2.setAddress("Winchester");
        
        BankAccount account2 = bankService.createBankAccount(user2, supportedIssuerBanks.get(1));
        LOG.debug("created bank account "+account2);
        
        List<BankAccount> bankAccounts = bankService.findAllBankAccounts();
        assertEquals(2,bankAccounts.size());
        
        LOG.debug("list of accounts ");
        for(BankAccount ba : bankAccounts){
            LOG.debug("  "+ba);
        }
        
  
        
    }

}
