/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardcheck.service.rest.client.test;

import java.util.Date;
import javax.ws.rs.core.MediaType;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.cardcheck.model.Account;
import solent.ac.uk.ood.examples.cardcheck.model.Card;
import solent.ac.uk.ood.examples.cardcheck.model.Transaction;
import solent.ac.uk.ood.examples.cardcheck.model.TransactionResult;
import solent.ac.uk.ood.examples.cardcheck.service.TransactionApi;
import solent.ac.uk.ood.examples.cardcheck.service.rest.client.manual.TransactionApiRestClientImpl;

/**
 *
 * @author cgallen
 */
public class TransactionApiRestClientTest {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionApiRestClientTest.class);

    private static final String TEST_URL = "http://localhost:8680";

    @Test
    public void testRequestPreAuthorisationJSON() {
        String testUrl = TEST_URL;
        TransactionApi tapi = new TransactionApiRestClientImpl(testUrl, MediaType.APPLICATION_JSON_TYPE);
        Transaction requestTransaction = new Transaction();
        requestTransaction.setAmount(100.5);

        Card card1 = new Card();
        card1.setCardno("4444444444444448"); //visa
        card1.setCvv("123");
        card1.setEnddate("1118");
        card1.setName("C R Gallen");
        requestTransaction.setCardFrom(card1);

        Card card2 = new Card();
        card2.setCardno("4444444444444448"); //visa
        card2.setCvv("123");
        card2.setEnddate("1118");
        card2.setName("C R Gallen");
        requestTransaction.setCardTo(card2);
        requestTransaction.setTimestamp(new Date());

        TransactionResult transactionResult = tapi.requestPreAuthorisation(requestTransaction);
    }

    @Test
    public void testrequestTransaction() {
        String testUrl = TEST_URL;
        TransactionApi tapi = new TransactionApiRestClientImpl(testUrl, MediaType.APPLICATION_XML_TYPE);
         Transaction requestTransaction = new Transaction();
        requestTransaction.setAmount(100.5);

        Card card1 = new Card();
        card1.setCardno("4444444444444448"); //visa
        card1.setCvv("123");
        card1.setEnddate("1118");
        card1.setName("C R Gallen");
        requestTransaction.setCardFrom(card1);

        Card card2 = new Card();
        card2.setCardno("4444444444444448"); //visa
        card2.setCvv("123");
        card2.setEnddate("1118");
        card2.setName("C R Gallen");
        requestTransaction.setCardTo(card2);
        requestTransaction.setTimestamp(new Date());

        TransactionResult transactionResult = tapi.requestTransaction(requestTransaction);
    }
    
    
    

}
