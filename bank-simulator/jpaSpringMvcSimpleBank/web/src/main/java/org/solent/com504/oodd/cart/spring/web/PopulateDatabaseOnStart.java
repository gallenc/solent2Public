/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.spring.web;

import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.bank.model.dto.BankAccount;
import org.solent.com504.oodd.bank.model.dto.User;
import org.solent.com504.oodd.bank.model.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author cgallen
 */
@Component
public class PopulateDatabaseOnStart {

    private static final Logger LOG = LogManager.getLogger(PopulateDatabaseOnStart.class);

    @Autowired
    private BankService bankService;

    @PostConstruct
    public void initDatabase() {
        LOG.debug("initialising database with test values");

        List<String> issuerBanks = bankService.getSupportedIssuerBanks();

        User user1 = new User();
        user1.setFirstName("test");
        user1.setSecondName("user1");
        user1.setAddress("Solent University");
        user1.setPassword("defaulttestpass");
        user1.setUsername("testuser1");
        String supportedIssuerBank1 = issuerBanks.get(0);
        String individualAccountIdentifier = "000000001";
        BankAccount account1 = bankService.createBankAccount(user1, supportedIssuerBank1, individualAccountIdentifier);
        account1.setBalance(1000.00);
        bankService.saveBankAccount(account1);

        User user2 = new User();
        user2.setFirstName("test");
        user2.setSecondName("user2");
        user2.setAddress("Solent University");
        user2.setPassword("defaulttestpass");
        user2.setUsername("testuser2");
        String supportedIssuerBank2 = issuerBanks.get(1);
        individualAccountIdentifier = "000000002";
        BankAccount account2 = bankService.createBankAccount(user2, supportedIssuerBank2, individualAccountIdentifier);
        LOG.debug("created 2 test accounts: account1 = " + account1
                + " account2 = " + account2);
    }
}
