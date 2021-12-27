/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.dao.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.solent.com504.oodd.cart.dao.impl.InvoiceRepository;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.model.dto.User;
import org.solent.com504.oodd.cart.dao.impl.UserRepository;
import org.solent.com504.oodd.cart.model.dto.Invoice;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the OrderServiceConfig class
@ContextConfiguration(classes = DAOTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
@Transactional
public class InvoiceRepositoryTest {

    private static final Logger LOG = LogManager.getLogger(InvoiceRepositoryTest.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingItemCatalogRepository shoppingItemCatalogRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Test
    public void testInvoice() {
        LOG.debug("****************** starting test");

        invoiceRepository.deleteAll();

        User user1 = new User();
        user1.setFirstName("craig");
        user1.setSecondName("gallen");
        user1 = userRepository.save(user1);
        assertEquals(1, userRepository.count());

        Invoice invoice1 = new Invoice();
        invoice1.setAmountDue(100.0);
        invoice1.setDateOfPurchase(new Date());
        invoice1.setPurchaser(user1);

        invoice1 = invoiceRepository.save(invoice1);
        assertEquals(1, invoiceRepository.count());

        // do catalog item
        ShoppingItem shoppingItem1 = new ShoppingItem();
        shoppingItem1.setName("item 1");
        shoppingItem1.setPrice(100.1);
        shoppingItem1.setQuantity(1);
        shoppingItem1.setUuid(UUID.randomUUID().toString());
        shoppingItem1 = shoppingItemCatalogRepository.save(shoppingItem1);

        List<ShoppingItem> purchasedItems = new ArrayList<ShoppingItem>();

        invoice1.setPurchasedItems(purchasedItems);
        invoice1 = invoiceRepository.save(invoice1);

        Optional<Invoice> optional = invoiceRepository.findById(invoice1.getId());
        Invoice foundInvoice = optional.get();

        LOG.debug("found Invoice : " + foundInvoice);

        LOG.debug("****************** test complete");
    }

}
