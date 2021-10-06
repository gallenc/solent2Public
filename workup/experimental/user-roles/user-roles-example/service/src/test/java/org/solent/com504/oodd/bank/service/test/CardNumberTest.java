/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.bank.service.test;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.oodd.bank.model.dto.BankAccount;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.bank.model.dto.User;
import solent.ac.uk.ood.examples.cardcheck.CalculateLunnDigit;
import solent.ac.uk.ood.examples.cardcheck.CardValidationResult;
import solent.ac.uk.ood.examples.cardcheck.RegexCardValidator;
import solent.ac.uk.ood.examples.cardvalidator.impl.SupportedCardIssuerIdentificationNumbers;

/**
 *
 * @author cgallen
 */
public class CardNumberTest {

    @Test
    public void cardNumberTest() {

        for (String supportedIssuerBank : SupportedCardIssuerIdentificationNumbers.ISSUER_IDENTIFICATION_MAP.keySet()) {

            String iin = SupportedCardIssuerIdentificationNumbers.ISSUER_IDENTIFICATION_MAP.get(supportedIssuerBank);

            User user1 = new User();
            user1.setFirstName("craig");
            user1.setSecondName("gallen");
            user1.setAddress("Burnett Close");

            BankAccount account = new BankAccount();
            account.setSortcode(iin);
            account.setOwner(user1);

            // nextInt is normally exclusive of the top value, so add 1 to make it inclusive
            int randomNum = ThreadLocalRandom.current().nextInt(0, 99999999);
            

            String individualAccountIdentifier = String.format("%09d", randomNum); // 9 digits) 
                    
            if(SupportedCardIssuerIdentificationNumbers.AMERICAN_EXPRESS_LLOYDS_BANK_PLC.equals(supportedIssuerBank)){
                // this doesnt work properly - bug in amarican express values
                individualAccountIdentifier = String.format("%08d", randomNum); // 8 digits) 
            }

            
            account.setAccountNo(individualAccountIdentifier);

            CreditCard card = new CreditCard();

            String pan = iin + individualAccountIdentifier;

            String check = CalculateLunnDigit.calculateCheckDigit(pan);

            String ccNumber = pan + check;

            card.setCardnumber(ccNumber);
            card.setName(user1.getFirstName() + " " + user1.getSecondName());
            card.setCvv("123"); // use algorythm here
            card.setEndDate("11/21");

            CardValidationResult cvr = RegexCardValidator.isValid(card.getCardnumber());
            if (cvr.isValid()) {
                System.out.println("    VALID: " + cvr.getMessage() + " " + card + " supportedIssuerBank: " + supportedIssuerBank);
            } else {
                System.out.println("NOT VALID: " + cvr.getMessage() + " " + card + " supportedIssuerBank: " + supportedIssuerBank);
            }
        }
    }
}
