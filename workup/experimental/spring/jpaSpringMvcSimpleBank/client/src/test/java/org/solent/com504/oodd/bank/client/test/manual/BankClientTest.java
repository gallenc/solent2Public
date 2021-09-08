/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.bank.client.test.manual;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.oodd.bank.client.impl.BankRestClientImpl;
import org.solent.com504.oodd.bank.model.client.BankRestClient;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.bank.model.dto.TransactionReplyMessage;

/**
 *
 * @author cgallen
 */
public class BankClientTest {
        final static Logger LOG = LogManager.getLogger(BankClientTest.class);

    @Test
    public void testClient() {
        String bankUrl = "http://localhost:8080/bank/rest";

        BankRestClient client = new BankRestClientImpl(bankUrl);

        CreditCard fromCard = new CreditCard();
        fromCard.setCardnumber("5133880729208144");
        fromCard.setCvv("123");
        fromCard.setEndDate("11/21");
        fromCard.setIssueNumber("01");
        fromCard.setName("test user1");

        CreditCard toCard = new CreditCard();
        toCard.setCardnumber("5133880729208144");
        toCard.setCvv("123");
        toCard.setEndDate("11/21");
        toCard.setIssueNumber("01");
        toCard.setName("test user1");

        Double amount = 0.0;

        TransactionReplyMessage reply = client.transferMoney(fromCard, toCard, amount);
LOG.debug("transaction reply:"+reply);
    }
}
