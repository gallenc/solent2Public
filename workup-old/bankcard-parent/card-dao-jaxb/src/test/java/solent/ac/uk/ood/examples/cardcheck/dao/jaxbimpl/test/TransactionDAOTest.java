/* ***************************************************************************
 * Copyright 2018 Craig Gallen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ****************************************************************************/

package solent.ac.uk.ood.examples.cardcheck.dao.jaxbimpl.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.cardcheck.dao.TransactionDAO;
import solent.ac.uk.ood.examples.cardcheck.dao.DaoObjectFactory;

import solent.ac.uk.ood.examples.cardcheck.model.Transaction;
import solent.ac.uk.ood.examples.cardcheck.model.Card;

/**
 *
 * @author cgallen
 */
public class TransactionDAOTest {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionDAOTest.class);

    private static DaoObjectFactory daoObjectFactory;
    private static TransactionDAO transactionDAO;
    private static List<Transaction> testTransactions;

    @BeforeClass
    public static void before() {
        TestController.init();
        daoObjectFactory = TestController.getDaoObjectFactory();
        transactionDAO = daoObjectFactory.getTransactionDAO();

        // test add 5 transactions
        testTransactions = new ArrayList<Transaction>();
        for (int i = 0; i < 5; i++) {
            Transaction templateTransaction = transactionDAO.create();

            Card cardFrom = new Card();
            cardFrom.setCardno("4444444444444448"); //visa // todo write card generator
            cardFrom.setCvv("123");
            cardFrom.setEnddate("1118");
            String owner1 = "Owner_" + i;
            cardFrom.setName(owner1);
            templateTransaction.setCardFrom(cardFrom);

            Card cardTo = new Card();
            cardTo.setCardno("4444444444444448"); //visa // todo write card generator
            cardTo.setCvv("123");
            cardTo.setEnddate("1118");
            String owner2 = "Owner_" + i;
            cardTo.setName(owner2);

            templateTransaction.setCardTo(cardTo);
            templateTransaction.setTimestamp(new Date());
            Integer randomNum = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE - 1);
            templateTransaction.setTransactionId(randomNum);

            templateTransaction.setAmount(new Double(110.5 + i * 5));

            // persist transaction object
            Transaction newTransaction = transactionDAO.add(templateTransaction);
            testTransactions.add(newTransaction);
        }
    }

    @Test
    public void testgetById() {
        for (Transaction testTransaction : testTransactions) {
            Transaction rxTransaction = transactionDAO.getById(testTransaction.getId());
            assertNotNull(rxTransaction);
            assertEquals(testTransaction.getId(), rxTransaction.getId());
        }
    }

    @Test
    public void testgetAll() {
        List<Transaction> all = transactionDAO.getAll();
        assertTrue(all.size() > 0);
    }

    @Test
    public void testaddAndDelete() {
        Transaction templateTransaction = transactionDAO.create();

        Card cardFrom = new Card();
        cardFrom.setCardno("4444444444444448"); //visa // todo write card generator
        cardFrom.setCvv("123");
        cardFrom.setEnddate("1118");
        String owner1 = "Owner_100";
        cardFrom.setName(owner1);
        templateTransaction.setCardFrom(cardFrom);

        Card cardTo = new Card();
        cardTo.setCardno("4444444444444448"); //visa // todo write card generator
        cardTo.setCvv("123");
        cardTo.setEnddate("1118");
        String owner2 = "Owner_200";
        cardTo.setName(owner2);

        templateTransaction.setCardTo(cardTo);
        templateTransaction.setTimestamp(new Date());
        Integer randomNum = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE - 1);
        templateTransaction.setTransactionId(randomNum);

        templateTransaction.setAmount(new Double(110.5));
        Transaction addedTransaction = transactionDAO.add(templateTransaction);

        Transaction rxTransaction = transactionDAO.getById(addedTransaction.getId());
        assertEquals(addedTransaction.getId(), rxTransaction.getId());

        transactionDAO.delete(addedTransaction);

        // should return as already deleted
        transactionDAO.deleteById(addedTransaction.getId());

        Transaction rxTransaction2 = transactionDAO.getById(addedTransaction.getId());
        assertNull(rxTransaction2);

    }

    @Test
    public void testupdate() {
        for (Transaction testTransaction : testTransactions) {
            testTransaction.setAmount(1000.5);
            transactionDAO.update(testTransaction);
        }
        for (Transaction testTransaction : testTransactions) {
            Transaction rxTransaction = transactionDAO.getById(testTransaction.getId());
            assertEquals(new Double(1000.5), rxTransaction.getAmount());
        }

    }

    @Test
    public void testcreate() {
        Transaction transaction = transactionDAO.create();
        assertNotNull(transaction);
    }
}
