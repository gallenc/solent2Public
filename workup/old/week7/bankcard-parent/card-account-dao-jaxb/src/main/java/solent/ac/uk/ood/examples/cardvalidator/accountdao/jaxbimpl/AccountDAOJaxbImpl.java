/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.accountdao.jaxbimpl;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.cardvalidator.model.Account;
import solent.ac.uk.ood.examples.cardvalidator.model.AccountDAO;
import solent.ac.uk.ood.examples.cardvalidator.model.AccountModelLists;

/**
 *
 * @author cgallen
 */
public class AccountDAOJaxbImpl implements AccountDAO {

    private static final Logger LOG = LoggerFactory.getLogger(AccountDAOJaxbImpl.class);

    private static int INDIVIDUAL_ACCOUNT_IDENTIFIER_LENGTH = 8;

    private String dataFileLocation=null;

    private File jaxbFile = null;

    // you must obtain lock before accessing objects and / or saving file
    public final Object Lock = new Object();

    private JAXBContext jaxbContext = null;

    private AccountModelLists bankModelLists = null;

    // can only access constructor with dataFileLocation
    private AccountDAOJaxbImpl() {
    }

    public AccountDAOJaxbImpl(String dataFileLocation) {
        super();
        if (dataFileLocation == null) {
            throw new IllegalArgumentException("dataFile cannot be null");
        }
        this.dataFileLocation=dataFileLocation;
        load();
    }

    @Override
    public Account createAccount(String issuerIdentificationNumber, String name) {
        if (issuerIdentificationNumber == null) {
            throw new IllegalArgumentException("issuerIdentificationNumber must not be null");
        }

        synchronized (Lock) {
            Account newAccount = new Account();
            newAccount.setIssuerIdentificationNumber(issuerIdentificationNumber);
            newAccount.setBalance(0.0);
            newAccount.setName(name);

            Integer id = bankModelLists.getLatestAccountId() + 1;
            bankModelLists.setLatestAccountId(id);

            // this insures that the length of the individualAccountIdentifier is fixed even with leading zeros
            String individualAccountIdentifier = String.format("%0" + INDIVIDUAL_ACCOUNT_IDENTIFIER_LENGTH + "d", id);
            newAccount.setIndividualAccountIdentifier(individualAccountIdentifier);
            bankModelLists.getAccountList().add(newAccount);

            // write to file
            save();

            LOG.debug("created new account: " + newAccount);

            return newAccount;
        }
    }

    /**
     * Returns deleted value or null if not found
     *
     * @param issuerIdentificationNumber
     * @param individualAccountIdentifier
     * @return
     */
    @Override
    public boolean deleteAccount(String issuerIdentificationNumber, String individualAccountIdentifier) {
        if (issuerIdentificationNumber == null) {
            throw new IllegalArgumentException("issuerIdentificationNumber must not be null");
        }
        if (individualAccountIdentifier == null) {
            throw new IllegalArgumentException("individualAccountIdentifier must not be null");
        }
        synchronized (Lock) {
            List<Account> accountList = new ArrayList<Account>();
            boolean deleted = false;
            for (Account account : bankModelLists.getAccountList()) {
                if (account.getIssuerIdentificationNumber().equals(issuerIdentificationNumber)
                        && account.getIndividualAccountIdentifier().equals(individualAccountIdentifier)) {
                    deleted = true;
                    LOG.debug("deleteAccount("
                            + issuerIdentificationNumber + " , " + individualAccountIdentifier
                            + " ) deleted " + account);
                } else {
                    accountList.add(copy(account));
                }
            };
            // if account is deleted update bankModelLists and save file
            if (deleted) {
                bankModelLists.setAccountList(accountList);
                save();
                return true;
            } else {
                // else do not update and return false
                return false;
            }
        }
    }

    @Override
    public Account updateAccount(Account accountUpdate) {
        String issuerIdentificationNumber = accountUpdate.getIssuerIdentificationNumber();
        String individualAccountIdentifier = accountUpdate.getIndividualAccountIdentifier();
        if (issuerIdentificationNumber == null) {
            throw new IllegalArgumentException("issuerIdentificationNumber must not be null");
        }
        if (individualAccountIdentifier == null) {
            throw new IllegalArgumentException("individualAccountIdentifier must not be null");
        }
        synchronized (Lock) {
            for (Account account : bankModelLists.getAccountList()) {
                if (account.getIssuerIdentificationNumber().equals(issuerIdentificationNumber)
                        && account.getIndividualAccountIdentifier().equals(individualAccountIdentifier)) {
                    // found match now update with non null fields from accountUpdate
                    if (accountUpdate.getName() != null) {
                        account.setName(accountUpdate.getName());
                    }
                    if (accountUpdate.getBalance() != null) {
                        account.setBalance(accountUpdate.getBalance());
                    }
                    if (accountUpdate.getCurrentCardIssueNumber() != null) {
                        account.setCurrentCardIssueNumber(accountUpdate.getCurrentCardIssueNumber());
                    }
                    // save updated list
                    save();
                    // return newly updated account
                    return copy(account);
                }
            };
            // return null if nothing updated
            return null;
        }
    }

    @Override
    public Account retrieveAccount(String issuerIdentificationNumber, String individualAccountIdentifier) {
        if (issuerIdentificationNumber == null) {
            throw new IllegalArgumentException("issuerIdentificationNumber must not be null");
        }
        if (individualAccountIdentifier == null) {
            throw new IllegalArgumentException("individualAccountIdentifier must not be null");
        }
        synchronized (Lock) {
            for (Account account : bankModelLists.getAccountList()) {
                if (account.getIssuerIdentificationNumber().equals(issuerIdentificationNumber)
                        && account.getIndividualAccountIdentifier().equals(individualAccountIdentifier)) {
                    return copy(account);
                }
            };
            return null;
        }
    }

    @Override
    public List<Account> getAccountsForIssuer(String issuerIdentificationNumber) {
        if (issuerIdentificationNumber == null) {
            throw new IllegalArgumentException("issuerIdentificationNumber must not be null");
        }
        synchronized (Lock) {
            List<Account> accountList = new ArrayList<Account>();
            for (Account account : bankModelLists.getAccountList()) {
                if (account.getIssuerIdentificationNumber().equals(issuerIdentificationNumber)) {
                    accountList.add(copy(account));
                }
            };
            return accountList;
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        synchronized (Lock) {
            List<Account> accountList = new ArrayList<Account>();
            for (Account account : bankModelLists.getAccountList()) {
                accountList.add(copy(account));
            };
            return accountList;
        }
    }

    /**
     * copies new account data transfer objects to create detached object and so avoid problems with indirect object modification
     *
     * @param account
     * @return independent copy of account
     */
    private Account copy(Account account) {
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

    /**
     * loads jaxb file at beginning of program
     */
    private void load() {

        try {

            // jaxb context needs jaxb.index jaxbFile to be in same classpath
            // this contains a list of Jaxb annotated classes for the context to parse
            jaxbContext = JAXBContext.newInstance("solent.ac.uk.ood.examples.cardvalidator.model");

            // try to load dataFileLocation
            jaxbFile = new File(dataFileLocation);
            LOG.debug("using dataFile:" + jaxbFile.getAbsolutePath());

            if (jaxbFile.exists()) {
                LOG.debug("dataFile exists loading:" + jaxbFile.getAbsolutePath());
                // load jaxbFile
                Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
                bankModelLists = (AccountModelLists) jaxbUnMarshaller.unmarshal(jaxbFile);
            } else {
                // create annd save an empty jaxbFile
                LOG.debug("dataFile does not exist creating new " + jaxbFile.getAbsolutePath());

                bankModelLists = new AccountModelLists();

                // make directories if dont exist
                jaxbFile.getParentFile().mkdirs();

                // save empty data to new file
                save();
            }

        } catch (JAXBException ex) {
            throw new RuntimeException("problem creating persistor", ex);
        }

    }

    /**
     * saves data to datafile on updates
     */
    private void save() {
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
            throw new RuntimeException("problem persisting accountDao", ex);
        }
    }

}
