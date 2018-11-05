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
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.cardcheck.dao.BankDAO;
import solent.ac.uk.ood.examples.cardcheck.dao.DaoObjectFactory;
import solent.ac.uk.ood.examples.cardcheck.model.Bank;


/**
 *
 * @author cgallen
 */
public class BankDAOTest {
    private static final Logger LOG = LoggerFactory.getLogger(BankDAOTest.class);

    private static DaoObjectFactory daoObjectFactory;
    private static BankDAO bankDAO;
    private static List<Bank> testBanks;

    @BeforeClass
    public static void before() {
        TestController.init();
        daoObjectFactory = TestController.getDaoObjectFactory();
        bankDAO = daoObjectFactory.getBankDAO();

        // test add 5 banks
        testBanks = new ArrayList<Bank>();
        for (int i = 0; i < 5; i++) {
            Bank templateBank = bankDAO.create();
            String bincode = "12345"+i;
            templateBank.setBincode(bincode);
            //templateBank.setId(i);
            templateBank.setName("TheBank_"+1);
            String sortcode="22-33-4"+i;
            templateBank.setSortcode(sortcode);

            // persist bank object
            Bank newBank = bankDAO.add(templateBank);
            testBanks.add(newBank);
        }
    }

    @Test
    public void testgetById() {
        for (Bank testBank : testBanks) {
            Bank rxBank = bankDAO.getById(testBank.getId());
            assertNotNull(rxBank);
            assertEquals(testBank.getId(), rxBank.getId());
        }
    }

    @Test
    public void testgetAll() {
        List<Bank> all = bankDAO.getAll();
        assertTrue(all.size() > 0);
    }

    @Test
    public void testaddAndDelete() {
        Bank templateBank = bankDAO.create();
            String bincode = "555555";
            templateBank.setBincode(bincode);
            //templateBank.setId(i);
            templateBank.setName("TheBank_"+1);
            String sortcode="22-33-55";
            templateBank.setSortcode(sortcode);

        Bank addedBank = bankDAO.add(templateBank);

        Bank rxBank = bankDAO.getById(addedBank.getId());
        assertEquals(addedBank.getId(), rxBank.getId());

        bankDAO.delete(addedBank);

        // should return as already deleted
        bankDAO.deleteById(addedBank.getId());

        Bank rxBank2 = bankDAO.getById(addedBank.getId());
        assertNull(rxBank2);

    }

    @Test
    public void testupdate() {
        int i=1;
        for (Bank testBank : testBanks) {
            testBank.setName("AnotherBank"+i++);
            bankDAO.update(testBank);
        }
        for (Bank testBank : testBanks) {
            Bank rxBank = bankDAO.getById(testBank.getId());
            assertEquals(testBank.getName(), rxBank.getName());
        }

    }

    @Test
    public void testcreate() {
        Bank bank = bankDAO.create();
        assertNotNull(bank);
    }
}
