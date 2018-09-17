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

import java.io.File;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.cardcheck.dao.jaxbimpl.ModelJaxbPersistor;
import solent.ac.uk.ood.examples.cardcheck.model.Account;
import solent.ac.uk.ood.examples.cardcheck.model.Bank;
import solent.ac.uk.ood.examples.cardcheck.model.Transaction;

/**
 *
 * @author cgallen
 */
public class ModelJaxbPersistorTest {

    private static final Logger LOG = LoggerFactory.getLogger(ModelJaxbPersistorTest.class);

    public static final String TEST_FILE_LOCATION = "target/testData.xml";
    public static final String TEST_FILE_LOCATION2 = "target/testData2.xml";

    // number of seperate threads which will add data to Persistor
    private Integer m_maxModificationThreads = 10;

    private ExecutorService executorService = null;
    


    @Test
    public void testPersistorLoadsAndSavesFile() {
        File testFile = new File(TEST_FILE_LOCATION);
        LOG.debug("using test file:" + testFile.getAbsolutePath());
        if (testFile.exists()) {
            LOG.debug("deleting old test file");
            testFile.delete();
        }
        assertFalse(testFile.exists());
        ModelJaxbPersistor modelJaxbPersistor = new ModelJaxbPersistor(TEST_FILE_LOCATION);
        assertTrue(testFile.exists());

        synchronized (modelJaxbPersistor.Lock) {
            modelJaxbPersistor.getAccountList().add(new Account());
            modelJaxbPersistor.getBankList().add(new Bank());
            modelJaxbPersistor.getTransactionList().add(new Transaction());
            modelJaxbPersistor.save();
        }

        // load new persistor and check that file was saved and loaded
        ModelJaxbPersistor modelJaxbPersistor2 = new ModelJaxbPersistor(TEST_FILE_LOCATION);
        synchronized (modelJaxbPersistor2.Lock) {
            assertEquals(1, modelJaxbPersistor2.getAccountList().size());
            assertEquals(1, modelJaxbPersistor2.getBankList().size());
            assertEquals(1, modelJaxbPersistor2.getTransactionList().size());
        }
        
        
        // check copy functions
        Bank bank = new Bank();
        Bank copyBank = modelJaxbPersistor.copy(bank);
        assertNotNull(copyBank);
        
        Account account = new  Account();
        Account copyAccount = modelJaxbPersistor.copy(account);
        assertNotNull(copyAccount);
        
        Transaction transaction = new Transaction();
        Transaction copyTransaction = modelJaxbPersistor.copy(transaction);
        assertNotNull(copyTransaction);

    }

    @Test
    public void testPersistorMultiThreaded() {
        LOG.debug("starting testPersistorMultiThreaded");
        File testFile = new File(TEST_FILE_LOCATION2);
        LOG.debug("using test file:" + testFile.getAbsolutePath());
        if (testFile.exists()) {
            LOG.debug("deleting old test file");
            testFile.delete();
        }
        assertFalse(testFile.exists());
        ModelJaxbPersistor modelJaxbPersistor = new ModelJaxbPersistor(TEST_FILE_LOCATION2);
        assertTrue(testFile.exists());

        executorService = Executors.newFixedThreadPool(m_maxModificationThreads);

        for (int i = 0; i < m_maxModificationThreads; i++) {
            String name = "persisterThread_" + i;
            executorService.execute(new PersistanceModifier(name, modelJaxbPersistor));
        }

        // wait for all threads to finish. Force finish if takes too long
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOG.warn("executor service still has threads running at termination");
        }
        
         // check that the correct number of transactions have been added
         // after all threads have finished. Note need to cast to Integer for assertEquals
         synchronized (modelJaxbPersistor.Lock) {
            assertEquals((Integer) m_maxModificationThreads, (Integer) modelJaxbPersistor.getAccountList().size());
            assertEquals((Integer) m_maxModificationThreads, (Integer) modelJaxbPersistor.getBankList().size());
            assertEquals((Integer) m_maxModificationThreads, (Integer) modelJaxbPersistor.getTransactionList().size());
        }

        LOG.debug("finishing testPersistorMultiThreaded");

    }

    /**
     * Class run in separate thread access data
     */
    private class PersistanceModifier implements Runnable {
        final Logger LOG2 = LoggerFactory.getLogger(PersistanceModifier.class);

        public final String _name;
        public final ModelJaxbPersistor _modelJaxbPersistor;

        public PersistanceModifier(String name, ModelJaxbPersistor modelJaxbPersistor) {
            this._name = name;
            this._modelJaxbPersistor = modelJaxbPersistor;
            LOG2.debug("PersistanceModifier for thread " + _name + " created");
        }

        public void run() {
            LOG2.debug("running thread " + _name + " modifying data");
            synchronized (_modelJaxbPersistor.Lock) {
                Account account= new Account();
                account.setOwner(_name);
                _modelJaxbPersistor.getAccountList().add(account);
                Bank bank= new Bank();
                bank.setName(_name);
                _modelJaxbPersistor.getBankList().add(bank);
                Transaction transaction= new Transaction();
                transaction.setTimestamp(new Date());
                transaction.setFrom(account);
                _modelJaxbPersistor.getTransactionList().add(transaction);
                _modelJaxbPersistor.save();
            }
            LOG2.debug("thread " + _name + " finished and exited");
        }

    }
}
