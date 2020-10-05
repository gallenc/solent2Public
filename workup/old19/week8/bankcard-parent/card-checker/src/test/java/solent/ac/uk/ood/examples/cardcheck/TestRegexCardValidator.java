/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardcheck;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of various cards
 * @author gallenc
 */
public class TestRegexCardValidator {

    public static final String VALID_VISA_1 = "4444444444444448";
    public static final String VALID_MASTERCARD_1 = "5500005555555559";
    public static final String VALID_AMEX_1 = "371449635398431";
    public static final String VALID_DINERS_1 = "36438936438936";
    public static final String VALID_DISCOVER_1 = "6011016011016011";
    public static final String VALID_JCB_1 = "3566003566003566";
    public static final String LHUN_FAIL_1 = "1111111111111111";

    public static final String INVALID_STRING = "abcdabcdabcdabcd";

    @Test
    public void testCardVisa() {
        CardValidationResult result = RegexCardValidator.isValid(VALID_VISA_1);
        printResult(result);
        assertTrue(result.isValid());
        assertEquals(result.getCardType(),CardCompany.VISA );
    }

    @Test
    public void testCardMastercard() {
        CardValidationResult result = RegexCardValidator.isValid(VALID_MASTERCARD_1);
        printResult(result);
        assertTrue(result.isValid());
        assertEquals(result.getCardType(),CardCompany.MASTERCARD );
    }

    @Test
    public void testCardAmex() {

        CardValidationResult result = RegexCardValidator.isValid(VALID_AMEX_1);
        printResult(result);
        assertTrue(result.isValid());
        assertEquals(result.getCardType(),CardCompany.AMEX );

    }

    @Test
    public void testCardDiners() {
        CardValidationResult result = RegexCardValidator.isValid(VALID_DINERS_1);
        printResult(result);
        assertTrue(result.isValid());
        assertEquals(result.getCardType(),CardCompany.DINERS );
    }

    @Test
    public void testCardDiscover() {
        CardValidationResult result = RegexCardValidator.isValid(VALID_DISCOVER_1);
        printResult(result);
        assertTrue(result.isValid());
        assertEquals(result.getCardType(),CardCompany.DISCOVER);

    }

    @Test
    public void testCardJcb() {
        CardValidationResult result = RegexCardValidator.isValid(VALID_JCB_1);
        printResult(result);
        assertTrue(result.isValid());
        assertEquals(result.getCardType(),CardCompany.JCB );

    }

    @Test
    public void testCardWrongString() {
        CardValidationResult result = RegexCardValidator.isValid(INVALID_STRING);
        printResult(result);
        assertFalse(result.isValid());
    }

    @Test
    public void testCardFailLhun() {
        CardValidationResult result = RegexCardValidator.isValid(LHUN_FAIL_1);
        printResult(result);
        assertFalse(result.isValid());

    }

    /**
     * Check a card number and print the result
     *
     * @param cardIn
     */
    private void printResult(CardValidationResult result) {
        System.out.println(result.isValid() + " : " + (result.isValid() ? result.getCardType().getIssuerName() : "") + " : " + result.getMessage());
    }
}
