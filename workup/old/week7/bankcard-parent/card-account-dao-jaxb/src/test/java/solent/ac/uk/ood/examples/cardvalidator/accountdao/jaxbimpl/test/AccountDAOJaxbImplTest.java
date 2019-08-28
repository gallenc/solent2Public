/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.accountdao.jaxbimpl.test;

import java.io.File;
import java.util.List;
import org.junit.Test;
import solent.ac.uk.ood.examples.cardvalidator.model.AccountDAO;
import solent.ac.uk.ood.examples.cardvalidator.accountdao.jaxbimpl.AccountDAOJaxbImpl;
import solent.ac.uk.ood.examples.cardvalidator.impl.SupportedCardIssuerIdentificationNumbers;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.cardvalidator.model.Account;

/**
 *
 * @author cgallen
 */
public class AccountDAOJaxbImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(AccountDAOJaxbImplTest.class);

    public final String TEST_DATA_FILE_LOCATION = "target/testAccountDaoData.xml";

    @Test
    public void testDAOJaxb() {

        // delete test file at start of test
        File file = new File(TEST_DATA_FILE_LOCATION);
        file.delete();
        assertFalse(file.exists());

        // create dao
        AccountDAO accountDAO = new AccountDAOJaxbImpl(TEST_DATA_FILE_LOCATION);

        assertTrue(file.exists());

        // create new account1 provider 1
        String providerIIN1 = SupportedCardIssuerIdentificationNumbers.VISA_BANK_OF_IRELAND_UK;
        String name1 = "account1";
        Account account1 = accountDAO.createAccount(providerIIN1, name1);
        assertNotNull(account1);
        LOG.debug("created account 1 : " + account1);

        // create new account2 provider 2
        String providerIIN2 = SupportedCardIssuerIdentificationNumbers.MASTERCARD_LLOYDS_BANK_PLC;
        String name2 = "account2";
        Account account2 = accountDAO.createAccount(providerIIN2, name2);
        assertNotNull(account2);
        LOG.debug("created account 2 : " + account2);

        // create new account3 provider 1
        String name3 = "account3";
        Account account3 = accountDAO.createAccount(providerIIN1, name3);
        assertNotNull(account3);
        LOG.debug("created account 3 : " + account3);

        // find all accounts
        List<Account> createdAccounts = accountDAO.getAllAccounts();
        assertEquals(3, createdAccounts.size());

        // find all accounts for provider 1
        List<Account> provider1Accounts = accountDAO.getAccountsForIssuer(providerIIN1);
        assertEquals(2, provider1Accounts.size());
        assertEquals(providerIIN1, provider1Accounts.get(0).getIssuerIdentificationNumber());
        assertEquals(providerIIN1, provider1Accounts.get(1).getIssuerIdentificationNumber());

        // find all accounts for provider 2
        List<Account> provider2Accounts = accountDAO.getAccountsForIssuer(providerIIN2);
        assertEquals(1, provider2Accounts.size());
        assertEquals(providerIIN2, provider2Accounts.get(0).getIssuerIdentificationNumber());

        // find account 2
        Account retrievedAcct2 = accountDAO.retrieveAccount(account2.getIssuerIdentificationNumber(),
                account2.getIndividualAccountIdentifier());

        assertEquals(account2.getName(), retrievedAcct2.getName());
        assertEquals(account2.getBalance(), retrievedAcct2.getBalance());

        // modify account 2 and check modified
        account2.setBalance(300.3);
        account2.setName("newAccount2Name");
        Account updatedAccount2 = accountDAO.updateAccount(account2);
        assertNotNull(updatedAccount2);

        Account retUpdatedAcct2 = accountDAO.retrieveAccount(account2.getIssuerIdentificationNumber(),
                account2.getIndividualAccountIdentifier());
        assertNotNull(retUpdatedAcct2);
        assertEquals(account2.getName(), retUpdatedAcct2.getName());
        assertEquals(account2.getBalance(), retUpdatedAcct2.getBalance());

        // delete account 2 and check deleted
        boolean deleted = accountDAO.deleteAccount(account2.getIssuerIdentificationNumber(),
                account2.getIndividualAccountIdentifier());
        assertTrue(deleted);

        List<Account> finalAccounts = accountDAO.getAllAccounts();
        assertEquals(2, finalAccounts.size());

        Account deletedAcct2 = accountDAO.retrieveAccount(account2.getIssuerIdentificationNumber(),
                account2.getIndividualAccountIdentifier());
        assertNull(deletedAcct2);

    }

}
