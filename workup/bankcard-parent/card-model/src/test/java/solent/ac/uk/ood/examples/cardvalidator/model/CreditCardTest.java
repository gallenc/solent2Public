/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cgallen
 */
public class CreditCardTest {

    private CreditCard card = new CreditCard();

    @Test
    public void testCArdNumbers() {

        // tests which throw card error if not number
        try {
            card.setCardnumber("1234A23412341234");
            fail("incorrect card number");
        } catch (Exception ex) {
        }

        // tests which throw card error if cvv incorrect format
        try {
            card.setCvv("1X3");
            fail("incorrect cvv check");
        } catch (Exception ex) {
        }
        try {
            card.setCvv("12345"); // NB amex can be 4 characters
            fail("incorrect cvv check");
        } catch (Exception ex) {
        }

        // tests which throw card error if not number
        try {
            card.setEndDate("12Y9");
            fail("end date");
        } catch (Exception ex) {
        }
        // tests which throw card error if not number
        try {
            card.setEndDate("123456");
            fail("end date");
        } catch (Exception ex) {
        }
        // tests which throw card error if not number
        try {
            card.setEndDate("1399");
            fail("end date");
        } catch (Exception ex) {
        }
        // tests which throw card error if not number
        try {
            card.setEndDate("0099");
            fail("end date");
        } catch (Exception ex) {
        }

        // tests which throw card error if not number
        try {
            card.setIssueNumber("0a");
            fail("incorrect issue no check");
        } catch (Exception ex) {
        }
        try {
            card.setIssueNumber("111");
            fail("incorrect issue no check");
        } catch (Exception ex) {
        }

        card.setCardnumber("1234123412341234");
        
        assertEquals("123412", card.getIssuerIdentificationNumber() );
        assertEquals("341234123",card.getIndividualAccountIdentifier());
                
    }
}
