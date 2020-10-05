/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.reception.test;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import solent.ac.uk.ood.examples.hotellock.model.CardKey;
import solent.ac.uk.ood.examples.hotellock.model.HotelReceptionService;
import solent.ac.uk.ood.examples.hotellock.model.SecretKeyProvider;
import solent.ac.uk.ood.examples.hotellock.reception.HotelReceptionServiceImpl;
import solent.ac.uk.ood.examples.hotellock.secretkey.SecretKeyProviderImpl;

/**
 *
 * @author cgallen
 */
public class HotelReceptionServiceImplTest {

    public static final Logger LOG = LogManager.getLogger(HotelReceptionServiceImplTest.class);

    @Test
    public void testHotelReceptionService() {

        String roomNumber = "100a";
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 1000 * 60 * 60 * 24); // 1 day later

        //set up hotel reception service
        HotelReceptionService hotelReceptionService = new HotelReceptionServiceImpl();
        SecretKeyProvider secretKeyProvider = new SecretKeyProviderImpl();
        hotelReceptionService.setSecretKeyProvider(secretKeyProvider);
 
        // encode new card
        String cardCode = hotelReceptionService.createCardCode(roomNumber, startDate, endDate);

        // decode card 
        CardKey keyCard = hotelReceptionService.readCard(cardCode);

        // check that decoded values equal encoded values
        assertEquals(roomNumber, keyCard.getRoomNumber());
        assertEquals(startDate, keyCard.getStartDate());
        assertEquals(endDate, keyCard.getEndDate());

        // now check that issue number changes each time card is issued
        // issue same card but issue number should be differnt
        String cardCode2 = hotelReceptionService.createCardCode(roomNumber, startDate, endDate);
        // decode card 2
        CardKey keyCard2 = hotelReceptionService.readCard(cardCode2);
        
        assertNotEquals(keyCard.getIssueNumber(), keyCard2.getIssueNumber());

    }
}
