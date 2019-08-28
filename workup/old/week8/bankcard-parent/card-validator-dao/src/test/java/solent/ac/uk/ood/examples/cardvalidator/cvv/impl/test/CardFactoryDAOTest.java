/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.cvv.impl.test;

import org.junit.Test;
import static org.junit.Assert.*;
import solent.ac.uk.ood.examples.cardvalidator.impl.CardFactoryDAOImpl;
import solent.ac.uk.ood.examples.cardvalidator.model.CardFactoryDAO;
import solent.ac.uk.ood.examples.cardvalidator.model.CreditCardFactoryAndValidator;
import solent.ac.uk.ood.examples.cardvalidator.impl.SupportedCardIssuerIdentificationNumbers;

/**
 *
 * @author cgallen
 */
public class CardFactoryDAOTest {

    @Test
    public void testCardFactoryDAO() {

        CardFactoryDAO cfDao = new CardFactoryDAOImpl();

        assertNotNull(cfDao.getCreditCardFactoryAndValidator(SupportedCardIssuerIdentificationNumbers.AMERICAN_EXPRESS_LLOYDS_BANK_PLC));
        assertNotNull(cfDao.getCreditCardFactoryAndValidator(SupportedCardIssuerIdentificationNumbers.MASTERCARD_LLOYDS_BANK_PLC));
        assertNotNull(cfDao.getCreditCardFactoryAndValidator(SupportedCardIssuerIdentificationNumbers.MASTERCARD_TSB_BANK));
        assertNotNull(cfDao.getCreditCardFactoryAndValidator(SupportedCardIssuerIdentificationNumbers.VISA_BANK_OF_IRELAND_UK));
        assertNotNull(cfDao.getCreditCardFactoryAndValidator(SupportedCardIssuerIdentificationNumbers.VISA_NAT_WEST));

        try {
            cfDao.getCreditCardFactoryAndValidator("123456");
            fail("DAO should not return an invalid factory");
        } catch (Exception ex) {
        }
    }

    // this test checks we can find all the issuer names and numbers
    @Test
    public void testCardFactoryDAO2() {

        CardFactoryDAO cfDao = new CardFactoryDAOImpl();

        for (String issuerName : cfDao.getSupportedIssuerNames()) {
            String issuerNumber = cfDao.getIssuerIdentifierNumberForName(issuerName);
            CreditCardFactoryAndValidator ccfv = cfDao.getCreditCardFactoryAndValidator(issuerNumber);
            assertNotNull(ccfv);
        }
    }

}
