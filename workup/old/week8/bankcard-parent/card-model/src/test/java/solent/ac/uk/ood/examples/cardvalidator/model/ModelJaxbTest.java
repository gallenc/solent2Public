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
package solent.ac.uk.ood.examples.cardvalidator.model;

import java.io.File;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gallenc
 */
public class ModelJaxbTest {

    @Test
    public void testTransactionJaxb() {

        try {

            // test file we will write and read. 
            // Note in target so that will be deleted on each run and will not be saved to git
            File file = new File("target/testTransactionData.xml");
            System.out.println("writing test file to " + file.getAbsolutePath());

            // jaxb context needs jaxb.index file to be in same classpath
            // this contains a list of Jaxb annotated classes for the context to parse
            JAXBContext jaxbContext = JAXBContext.newInstance("solent.ac.uk.ood.examples.cardvalidator.model");

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // create lists of model which we can save and load to a file
            TransactionResult transactionResult = new TransactionResult();
            String debugInfo = "test result declined";
            transactionResult.setDebugInfo(debugInfo);
            transactionResult.setResultCode(ResultCode.DECLINED);

            Transaction transactionRequest = new Transaction();

            Double amount = 100.1;
            transactionRequest.setAmount(amount);

            Date date = new Date();
            transactionRequest.setDate(date);

            // card details only for testing
            CreditCard fromCard = new CreditCard();
            fromCard.setName("Jim Doe");
            fromCard.setCardnumber("5133881234567891");
            fromCard.setEndDate("0119");
            fromCard.setIssueNumber("01");
            fromCard.setCvv("000");

            transactionRequest.setFromCard(fromCard);

            // card details only for testing
            CreditCard toCard = new CreditCard();
            toCard.setName("Fred Blogs");
            toCard.setCardnumber("5133881234567891");
            toCard.setEndDate("0119");
            toCard.setIssueNumber("01");
            toCard.setCvv("000");
            transactionRequest.setToCard(toCard);
            transactionRequest.setTransactionId(1);

            transactionResult.setTransactionRequest(transactionRequest);

            // marshal the object lists to system out, a file and a stringWriter
            jaxbMarshaller.marshal(transactionResult, System.out);
            jaxbMarshaller.marshal(transactionResult, file);

            // string writer is used to compare received object
            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(transactionResult, sw1);

            // having written the file we now read in the file for test
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            TransactionResult receivedTransactionResult = (TransactionResult) jaxbUnMarshaller.unmarshal(file);

            StringWriter sw2 = new StringWriter();
            jaxbMarshaller.marshal(receivedTransactionResult, sw2);

            // check transmitted and recieved message are the same
            assertEquals(sw1.toString(), sw2.toString());

        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }
    }

    @Test
    public void testAccountJaxb() {

        try {

            // test file we will write and read. 
            // Note in target so that will be delted on each run and will not be saved to git
            File file = new File("target/testAccountData.xml");
            System.out.println("writing test file to " + file.getAbsolutePath());

            // jaxb context needs jaxb.index file to be in same classpath
            // this contains a list of Jaxb annotated classes for the context to parse
            JAXBContext jaxbContext = JAXBContext.newInstance("solent.ac.uk.ood.examples.cardvalidator.model");

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // create lists of model which we can save and load to a file
            AccountModelLists accountModelLists = new AccountModelLists();

            Account account1 = new Account();
            account1.setName("account1");
            account1.setBalance(101.1);
            account1.setCurrentCardIssueNumber(1);
            String individualAccountIdentifier1 = "123456789";
            account1.setIndividualAccountIdentifier(individualAccountIdentifier1);
            String issuerIdentificationNumber1 = "513388";
            account1.setIssuerIdentificationNumber(issuerIdentificationNumber1);
            accountModelLists.getAccountList().add(account1);

            Account account2 = new Account();
            account2.setName("account2");
            account2.setBalance(202.2);
            account2.setCurrentCardIssueNumber(1);
            String individualAccountIdentifier2 = "123456780"; // differnt acct no
            account2.setIndividualAccountIdentifier(individualAccountIdentifier2);
            String issuerIdentificationNumber2 = "513388"; // same issuer no
            account2.setIssuerIdentificationNumber(issuerIdentificationNumber2);

            accountModelLists.getAccountList().add(account2);

            // marshal the object lists to system out, a file and a stringWriter
            jaxbMarshaller.marshal(accountModelLists, System.out);
            jaxbMarshaller.marshal(accountModelLists, file);

            // string writer is used to compare received object
            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(accountModelLists, sw1);

            // having written the file we now read in the file for test
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            AccountModelLists receivedAccountModelLists = (AccountModelLists) jaxbUnMarshaller.unmarshal(file);

            StringWriter sw2 = new StringWriter();
            jaxbMarshaller.marshal(receivedAccountModelLists, sw2);

            // check transmitted and recieved message are the same
            assertEquals(sw1.toString(), sw2.toString());

        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }
    }

}
