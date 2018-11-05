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

package solent.ac.uk.ood.examples.cardcheck.dao.jaxbimpl;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import solent.ac.uk.ood.examples.cardcheck.model.Account;
import solent.ac.uk.ood.examples.cardcheck.model.Bank;
import solent.ac.uk.ood.examples.cardcheck.model.BankModelLists;
import solent.ac.uk.ood.examples.cardcheck.model.Transaction;

/**
 * for Generic dao
 * https://www.baeldung.com/simplifying-the-data-access-layer-with-spring-and-java-generics
 * for jdbc dao https://github.com/danielniko/SimpleJspServletDB
 * https://danielniko.wordpress.com/2012/04/17/simple-crud-using-jsp-servlet-and-mysql/
 * 
 * https://dzone.com/articles/standalone-java-application-with-jersey-and-jetty
 * 
 *
 * @author gallenc
 */
public class ModelJaxbPersistor {

    private static final Logger LOG = LoggerFactory.getLogger(ModelJaxbPersistor.class);

    private final String dataFileLocation;

    private final File jaxbFile;

    // you must obtain lock before accessing objects and / or saving file
    public final Object Lock = new Object();

    private final JAXBContext jaxbContext;

    private final BankModelLists bankModelLists;

    public ModelJaxbPersistor(String dataFileLocation) {

        super();
        if (dataFileLocation == null) {
            throw new IllegalArgumentException("dataFile cannot be null");
        }

        try {

            // jaxb context needs jaxb.index jaxbFile to be in same classpath
            // this contains a list of Jaxb annotated classes for the context to parse
            jaxbContext = JAXBContext.newInstance("solent.ac.uk.ood.examples.cardcheck.model");

            // try to load dataFileLocation
            this.dataFileLocation = dataFileLocation;
            jaxbFile = new File(dataFileLocation);
            LOG.debug("using dataFile:" + jaxbFile.getAbsolutePath());

            if (jaxbFile.exists()) {
                // load jaxbFile
                Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
                bankModelLists = (BankModelLists) jaxbUnMarshaller.unmarshal(jaxbFile);
            } else {
                // create annd save an empty jaxbFile
                bankModelLists = new BankModelLists();
                save();
            }

        } catch (JAXBException ex) {
            throw new RuntimeException("problem creating persistor", ex);
        }

    }

    /**
     * saves data to datafile
     */
    public void save() {
        try {
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(bankModelLists, jaxbFile);
            if (LOG.isDebugEnabled()) {
                StringWriter sw1 = new StringWriter();
                jaxbMarshaller.marshal(bankModelLists, sw1);
                LOG.debug("saving xml to file:" + sw1.toString());
            }

        } catch (JAXBException ex) {
            throw new RuntimeException("problem creating persistor", ex);
        }
    }

    /**
     * @return the accountList
     */
    public List<Account> getAccountList() {
        List<Account> accountList = bankModelLists.getAccountList();
        return accountList;
    }

    /**
     * @return the bankList
     */
    public List<Bank> getBankList() {
        List<Bank> bankList = bankModelLists.getBankList();
        return bankList;
    }

    /**
     * @return the transactionList
     */
    public List<Transaction> getTransactionList() {
        List<Transaction> transactionList = bankModelLists.getTransactionList();
        return transactionList;
    }

    public Account copy(Account account) {
        try {
            StringWriter sw1 = new StringWriter();
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(account, sw1);

            StringReader sr1 = new StringReader(sw1.toString());
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            Account newAccount = (Account) jaxbUnMarshaller.unmarshal(sr1);
            return newAccount;
        } catch (JAXBException ex) {
            throw new RuntimeException("problem copying jaxb object", ex);
        }
    }

    public Bank copy(Bank bank) {
        try {
            StringWriter sw1 = new StringWriter();
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(bank, sw1);

            StringReader sr1 = new StringReader(sw1.toString());
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            Bank newBank = (Bank) jaxbUnMarshaller.unmarshal(sr1);
            return newBank;
        } catch (JAXBException ex) {
            throw new RuntimeException("problem copying jaxb object", ex);
        }
    }

    public Transaction copy(Transaction transaction) {
        try {
            StringWriter sw1 = new StringWriter();
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(transaction, sw1);

            StringReader sr1 = new StringReader(sw1.toString());
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            Transaction newTransaction = (Transaction) jaxbUnMarshaller.unmarshal(sr1);
            return newTransaction;
        } catch (JAXBException ex) {
            throw new RuntimeException("problem copying jaxb object", ex);
        }
    }

}
