/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.secretkey.test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;
import org.junit.Test;
import static org.junit.Assert.*;
import solent.ac.uk.ood.examples.hotellock.model.CardKey;
import solent.ac.uk.ood.examples.hotellock.model.SecretKeyProvider;
import solent.ac.uk.ood.examples.hotellock.secretkey.SecretKeyProviderImpl;

/**
 *
 * @author cgallen
 */
public class TestSecretKeyProviderImpl {

    @Test
    public void testSecretKeyProvider() {
        
        String roomNumber = "100a";
        int issueNumber = 01;
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 1000 * 60 * 60 * 24); // 1 day later

        CardKey cardKey = new CardKey();
        cardKey.setRoomNumber(roomNumber);
        cardKey.setIssueNumber(issueNumber);
        cardKey.setStartDate(startDate);
        cardKey.setEndDate(endDate);
        System.out.println(cardKey);

        SecretKeyProvider keyProvider = new SecretKeyProviderImpl();
        String cardString = keyProvider.encodeCard(cardKey);

        System.out.println("encodedCard=" + cardString);
        CardKey decodedCardKey = keyProvider.decodeCard(cardString);

        System.out.println(decodedCardKey);
        
        assertEquals(cardKey.toString(), decodedCardKey.toString());

    }
}
