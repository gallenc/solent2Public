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
import solent.ac.uk.ood.examples.cardvalidator.model.CreditCard;
import solent.ac.uk.ood.examples.cardvalidator.model.CreditCardFactoryAndValidator;

/**
 *
 * @author cgallen
 */
public class CreateAndVerifyNewCreditCardTest {

    @Test
    public void createAndVerifyCardTest() {

        CardFactoryDAO cfdao = new CardFactoryDAOImpl();

        String issuerName = cfdao.getSupportedIssuerNames().get(0); // get first issuerName
        System.out.println("test using issuer:" + issuerName);

        String accountNumber = "123456789"; // 9 digits + iin + 1 = 16

        String issuerIdentifierNumber = cfdao.getIssuerIdentifierNumberForName(issuerName);

        CreditCardFactoryAndValidator ccFactoryandValidator = cfdao.getCreditCardFactoryAndValidator(issuerIdentifierNumber);
        String name = "Fred Blogs";
        String endDate = "0119";
        String issueNumber = "01";
        CreditCard newCreditcard = ccFactoryandValidator.createCreditCard(accountNumber, name, endDate, issueNumber);

        System.out.println(" new creditcard created:" + newCreditcard);

        // decode and verify creditcard
        String receivedIin = newCreditcard.getIssuerIdentificationNumber();

        CreditCardFactoryAndValidator checkValidator = cfdao.getCreditCardFactoryAndValidator(receivedIin);

        boolean lunnIsValid = checkValidator.cardNumberLunnIsValid(newCreditcard);
        boolean cvvIsValid = checkValidator.cvvIsValid(newCreditcard);

        assertTrue(lunnIsValid);
        assertTrue(cvvIsValid);

    }
}
