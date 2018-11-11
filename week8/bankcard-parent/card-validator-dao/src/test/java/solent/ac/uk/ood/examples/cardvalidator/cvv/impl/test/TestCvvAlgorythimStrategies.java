/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.cvv.impl.test;

import org.junit.Test;
import static org.junit.Assert.*;
import solent.ac.uk.ood.examples.cardvalidator.cvv.impl.TrivialCvvAlgorythimStrategy;
import solent.ac.uk.ood.examples.cardvalidator.model.CreditCard;
import solent.ac.uk.ood.examples.cardvalidator.model.CvvAlgorythimStrategy;

/**
 *
 * @author cgallen
 */
public class TestCvvAlgorythimStrategies {

    private static final String VALID_MASTERCARD_1 = "5500005555555559";

    // very trivial test - you need to implement and test several  better algorythims
    @Test
    public void testTrivialCvvAlgorythimStrategy() {

        CreditCard card = new CreditCard();
        card.setCardnumber(VALID_MASTERCARD_1);
        card.setEndDate("0120");
        card.setIssueNumber("01");
        card.setName("Fred Bloggs");
        
        CvvAlgorythimStrategy cvvStrategy = new TrivialCvvAlgorythimStrategy();
        CreditCard cvvCard =  cvvStrategy.addCvv(card);
        assertTrue(cvvStrategy.checkCvv(card));

    }

}
