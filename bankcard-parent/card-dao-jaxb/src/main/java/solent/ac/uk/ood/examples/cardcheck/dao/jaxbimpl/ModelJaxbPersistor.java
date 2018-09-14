/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardcheck.dao.jaxbimpl;

import java.io.File;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import solent.ac.uk.ood.examples.cardcheck.dao.AccountDAO;
import solent.ac.uk.ood.examples.cardcheck.dao.BankDAO;
import solent.ac.uk.ood.examples.cardcheck.dao.TransactionDAO;
import solent.ac.uk.ood.examples.cardcheck.model.Account;
import solent.ac.uk.ood.examples.cardcheck.model.Bank;
import solent.ac.uk.ood.examples.cardcheck.model.BankModelLists;
import solent.ac.uk.ood.examples.cardcheck.model.Card;
import solent.ac.uk.ood.examples.cardcheck.model.Transaction;

/**
 * for Generic dao https://www.baeldung.com/simplifying-the-data-access-layer-with-spring-and-java-generics
 * for jdbc dao https://github.com/danielniko/SimpleJspServletDB
 * https://danielniko.wordpress.com/2012/04/17/simple-crud-using-jsp-servlet-and-mysql/
 * @author gallenc
 */
public class ModelJaxbPersistor {


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

 
            // convert received marshalled file to string and check the same as original
            StringWriter sw2 = new StringWriter();
            jaxbMarshaller.marshal(receivedBankModelLists, sw2);

 
        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }

    }

    // account dao tasks
    
    public Account getAccountById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<Account> getAllAccounts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Account addAccount(Account account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void deleteAccount(Account account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void deleteAccountById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    public Account updateAccount(Account account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Account createAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public Bank getBankById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public List<Bank> getAllBanks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public Bank addBank(Bank bank) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void deleteBank(Bank bank) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void deleteBankById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public Bank updateBank(Bank bank) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public Bank createBank() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public Transaction getTransactionById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public List<Transaction> getAllTransactions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public Transaction addTransaction(Transaction transaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void deleteTransaction(Transaction transaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void deleteTransactionById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public Transaction updateTransaction(Transaction transaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public Transaction createTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
