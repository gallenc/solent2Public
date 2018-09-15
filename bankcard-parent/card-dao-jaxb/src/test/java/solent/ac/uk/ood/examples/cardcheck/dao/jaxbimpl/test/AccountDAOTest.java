/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardcheck.dao.jaxbimpl.test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import solent.ac.uk.ood.examples.cardcheck.dao.AccountDAO;
import solent.ac.uk.ood.examples.cardcheck.dao.DaoObjectFactory;
import solent.ac.uk.ood.examples.cardcheck.model.Account;
import solent.ac.uk.ood.examples.cardcheck.model.Card;

/**
 *
 * @author cgallen
 */
public class AccountDAOTest {

    private static DaoObjectFactory daoObjectFactory;
    private static AccountDAO accountDAO;
    private static List<Account> testAccounts;

    @BeforeClass
    public static void before() {
        TestController.init();
        daoObjectFactory = TestController.getDaoObjectFactory();
        accountDAO = daoObjectFactory.getAccountDAO();

        // test add 5 accounts
        testAccounts = new ArrayList<Account>();
        for (int i = 0; i < 5; i++) {
            Account templateAccount = accountDAO.create();
            templateAccount.setAccountNo("12345678" + i);
            templateAccount.setBalance(100.5);
            String owner1 = "C R Gallen";
            templateAccount.setOwner(owner1);
            Card card1 = new Card();
            card1.setCardno("4444444444444448"); //visa // todo write card generator
            card1.setCvv("123");
            card1.setEnddate("1118");
            card1.setName(owner1);
            templateAccount.setCard(card1);

            // persist account object
            Account newAccount = accountDAO.add(templateAccount);
            testAccounts.add(newAccount);
        }
    }

    @Test
    public void testgetById() {
        for (Account testAccount : testAccounts) {
            Account rxAccount = accountDAO.getById(testAccount.getId());
            assertNotNull(rxAccount);
            assertEquals(testAccount.getId(), rxAccount.getId());
        }
    }

    @Test
    public void testgetAll() {
        List<Account> all = accountDAO.getAll();
        assertTrue(all.size() > 0);
    }

    @Test
    public void testaddAndDelete() {
        Account templateAccount = accountDAO.create();
        templateAccount.setAccountNo("9999999");
        templateAccount.setBalance(100.5);
        String owner1 = "C R Gallen";
        templateAccount.setOwner(owner1);
        Card card1 = new Card();
        card1.setCardno("4444444444444448"); //visa // todo write card generator
        card1.setCvv("123");
        card1.setEnddate("1118");
        card1.setName(owner1);
        templateAccount.setCard(card1);

        Account addedAccount = accountDAO.add(templateAccount);

        Account rxAccount = accountDAO.getById(addedAccount.getId());
        assertEquals(addedAccount.getId(), rxAccount.getId());

        accountDAO.delete(addedAccount);

        // should return as already deleted
        accountDAO.deleteById(addedAccount.getId());

        Account rxAccount2 = accountDAO.getById(addedAccount.getId());
        assertNull(rxAccount2);

    }

    @Test
    public void testupdate() {
        for (Account testAccount : testAccounts) {
            testAccount.setBalance(1000.5);
            accountDAO.update(testAccount);
        }
        for (Account testAccount : testAccounts) {
            Account rxAccount = accountDAO.getById(testAccount.getId());
            assertEquals(new Double(1000.5), rxAccount.getBalance());
        }

    }

    @Test
    public void testcreate() {
        Account account = accountDAO.create();
        assertNotNull(account);
    }
}
