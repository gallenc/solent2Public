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

package solent.ac.uk.ood.examples.cardcheck.model.test;

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
import solent.ac.uk.ood.examples.cardcheck.model.Account;
import solent.ac.uk.ood.examples.cardcheck.model.Bank;
import solent.ac.uk.ood.examples.cardcheck.model.BankModelLists;
import solent.ac.uk.ood.examples.cardcheck.model.Card;
import solent.ac.uk.ood.examples.cardcheck.model.ResultCodes;
import solent.ac.uk.ood.examples.cardcheck.model.Transaction;
import solent.ac.uk.ood.examples.cardcheck.model.TransactionResult;

/**
 *
 * @author gallenc
 */
public class ModelJaxbTest {


    @Test
    public void testJaxb() {

        try {

            // test file we will write and read. 
            // Note in target so that will be delted on each run and will not be saved to git
            File file = new File("target/testData.xml");
            System.out.println("writing test file to " + file.getAbsolutePath());

            // jaxb context needs jaxb.index file to be in same classpath
            // this contains a list of Jaxb annotated classes for the context to parse
            JAXBContext jaxbContext = JAXBContext.newInstance("solent.ac.uk.ood.examples.cardcheck.model");

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


            // create lists of model which we can save and load to a file
            BankModelLists bankModelLists = new BankModelLists();

            // note that bankModelLists already has ArrayLists defined so we just need to
            // access this to add our new objects
            List<Account> accounts = bankModelLists.getAccountList();
            List<Bank> banks = bankModelLists.getBankList();
            List<Transaction> transactions = bankModelLists.getTransactionList();

            // create 2 account objects and add to accounts list
            Account account1 = new Account();
            account1.setAccountNo("12345678");
            account1.setBalance(100.5);
            account1.setId(1);
            String owner1 = "C R Gallen";
            account1.setOwner(owner1);

            Card card1 = new Card();
            card1.setCardno("4444444444444448"); //visa
            card1.setCvv("123");
            card1.setEnddate("1118");
            card1.setName(owner1);
            account1.setCard(card1);

            accounts.add(account1);

            // add account with card
            Account account2 = new Account();
            account2.setAccountNo("910111213");
            account2.setBalance(100.5);
            account2.setId(1);
            String owner2 = "C R Gallen";
            account2.setOwner(owner2);

            Card card2 = new Card();
            card2.setCardno("5500005555555559"); // mastercard
            card2.setCvv("123");
            card2.setEnddate("1118");
            card2.setName(owner2);
            account2.setCard(card2);

            accounts.add(account2);

            // create two bank objects and add to list
            // add bank1
            Bank bank1 = new Bank();
            bank1.setBincode(owner2);
            bank1.setId(1);
            bank1.setName("First Bank");
            bank1.setSortcode("11-12-13");

            banks.add(bank1);

            // add bank2
            Bank bank2 = new Bank();
            bank2.setBincode(owner2);
            bank2.setId(2);
            bank2.setName("Second Bank");
            bank2.setSortcode("13-14-15");

            banks.add(bank2);

            // create two transaction objects and add to list
            // add transaction1
            Transaction transaction1 = new Transaction();
            transaction1.setAmount(30.5);
            transaction1.setFrom(account2);
            transaction1.setTo(account2);
            transaction1.setTransactionId(2);
            Date timestamp1 = new Date();
            transaction1.setTimestamp(timestamp1);
            transaction1.setId(2);
            transaction1.setCardFrom(card2);
            transaction1.setCardTo(card2);

            transactions.add(transaction1);

            // add transaction2
            Transaction transaction2 = new Transaction();
            transaction2.setAmount(30.5);
            transaction2.setFrom(account2);
            transaction2.setTo(account2);
            transaction2.setTransactionId(1);
            Date timestamp2 = new Date();
            transaction2.setTimestamp(timestamp2);
            transaction2.setId(1);
            transaction2.setCardFrom(card2);
            transaction2.setCardTo(card2);

            transactions.add(transaction2);

            // marshal the object lists to system out, a file an a stringWriter
            jaxbMarshaller.marshal(bankModelLists, System.out);
            jaxbMarshaller.marshal(bankModelLists, file);

            // string writer is used to compare received object
            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(bankModelLists, sw1);

            // having written the file we now read in the file for test
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            BankModelLists receivedBankModelLists = (BankModelLists) jaxbUnMarshaller.unmarshal(file);

            //spot check both the same size of lists
            assertEquals(bankModelLists.getAccountList().size(), receivedBankModelLists.getAccountList().size());
            assertEquals(bankModelLists.getBankList().size(), receivedBankModelLists.getBankList().size());
            assertEquals(bankModelLists.getTransactionList().size(), receivedBankModelLists.getTransactionList().size());

            // convert received marshalled file to string and check the same as original
            StringWriter sw2 = new StringWriter();
            jaxbMarshaller.marshal(receivedBankModelLists, sw2);
            
            // if both strings the same we have received the same file as we wrote
            assertEquals(sw1.toString(), sw2.toString());

        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling",e);
        }

    }
    
    @Test
    public void testTransactionResult() {
        
                try {
         JAXBContext jaxbContext = JAXBContext.newInstance("solent.ac.uk.ood.examples.cardcheck.model");

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                       
                        // add account with card
            Account account2 = new Account();
            account2.setAccountNo("910111213");
            account2.setBalance(100.5);
            account2.setId(1);
            String owner2 = "C R Gallen";
            account2.setOwner(owner2);

            Card card2 = new Card();
            card2.setCardno("5500005555555559"); // mastercard
            card2.setCvv("123");
            card2.setEnddate("1118");
            card2.setName(owner2);
            account2.setCard(card2);
            
            Transaction transaction1 = new Transaction();
            transaction1.setAmount(30.5);
            transaction1.setFrom(account2);
            transaction1.setTo(account2);
            transaction1.setTransactionId(2);
            Date timestamp1 = new Date();
            transaction1.setTimestamp(timestamp1);
            transaction1.setId(2);
            transaction1.setCardFrom(card2);
            transaction1.setCardTo(card2);
            
                      TransactionResult tr = new TransactionResult();
            
            tr.setResultCode(ResultCodes.DECLINED);
            
            tr.setTransaction(transaction1);
            tr.setDebugInformation("no debug info");
            
            jaxbMarshaller.marshal(tr, System.out);
            
            
            
        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling",e);
        }
    }
}
