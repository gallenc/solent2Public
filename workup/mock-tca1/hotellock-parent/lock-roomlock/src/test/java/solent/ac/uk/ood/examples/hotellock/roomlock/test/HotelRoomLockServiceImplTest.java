/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.roomlock.test;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import solent.ac.uk.ood.examples.hotellock.model.HotelReceptionService;
import solent.ac.uk.ood.examples.hotellock.model.HotelRoomLockService;
import solent.ac.uk.ood.examples.hotellock.model.SecretKeyProvider;
import solent.ac.uk.ood.examples.hotellock.reception.HotelReceptionServiceImpl;
import solent.ac.uk.ood.examples.hotellock.roomlock.HotelRoomLockServiceImpl;
import solent.ac.uk.ood.examples.hotellock.secretkey.SecretKeyProviderImpl;

/**
 *
 * @author cgallen
 */
public class HotelRoomLockServiceImplTest {

    public static final Logger LOG = LogManager.getLogger(HotelRoomLockServiceImplTest.class);

    @Test
    public void testRoomLock() {

        // test room numbers
        String roomNumber1 = "100a";
        String roomNumber2 = "200b";

        // set up room lock 100a service to validate cards
        HotelRoomLockService hotelRoomLockService_room100a = new HotelRoomLockServiceImpl();
        SecretKeyProvider lockSecretKeyProvider = new SecretKeyProviderImpl();
        hotelRoomLockService_room100a.setSecretKeyProvider(lockSecretKeyProvider);
        // roomNumber1 should unlock door
        hotelRoomLockService_room100a.setRoomNumber(roomNumber1);

        //set up hotel reception service to generate cards
        HotelReceptionService hotelReceptionService = new HotelReceptionServiceImpl();
        SecretKeyProvider secretKeyProvider = new SecretKeyProviderImpl();
        hotelReceptionService.setSecretKeyProvider(secretKeyProvider);

        // check check hotel lock service rejects invlaid key code
        String rubbishCardCode = "sdggffdf";
        assertFalse(hotelRoomLockService_room100a.unlockDoor(rubbishCardCode));

        // encode new card
        Date startDate = new Date(new Date().getTime() - 1000 * 60 * 5); // five minutes ago
        Date endDate = new Date(startDate.getTime() + 1000 * 60 * 60 * 24); // 1 day later

        // card for room 100a
        String cardCode1 = hotelReceptionService.createCardCode(roomNumber1, startDate, endDate);

        // check cardCode1 opens door
        assertTrue(hotelRoomLockService_room100a.unlockDoor(cardCode1));

        // card for room 200b
        String cardCode2 = hotelReceptionService.createCardCode(roomNumber2, startDate, endDate);

        // check cardCode2 does not open room 100a door
        assertFalse(hotelRoomLockService_room100a.unlockDoor(cardCode2));

        // card for room 100a tomorrow
        Date todaysDate = new Date();
        startDate = new Date(todaysDate.getTime() + 1000 * 60 * 60 * 24); // start date is tomorrow
        endDate = new Date(startDate.getTime() + 1000 * 60 * 60 * 24);   // 1 day later
        String cardCode3 = hotelReceptionService.createCardCode(roomNumber1, startDate, endDate);

        // check cardCode3 does not open the door
        assertFalse(hotelRoomLockService_room100a.unlockDoor(cardCode3));

        // card for room 100a yesterday
        startDate = new Date(todaysDate.getTime() - 1000 * 60 * 60 * 24); // start date is yesterday
        endDate = new Date(startDate.getTime() + 1000 * 60 * 60 * 23);   // 1 day later - 1 hour
        String cardCode4 = hotelReceptionService.createCardCode(roomNumber1, startDate, endDate);

        // check cardCode4 does not open the door
        assertFalse(hotelRoomLockService_room100a.unlockDoor(cardCode4));

        // create new door lock a
        // set up room lock service to validate cards
        HotelRoomLockService hotelRoomLockService_room200a = new HotelRoomLockServiceImpl();
        SecretKeyProvider lockSecretKeyProvider2 = new SecretKeyProviderImpl();
        hotelRoomLockService_room200a.setSecretKeyProvider(lockSecretKeyProvider);
        hotelRoomLockService_room200a.setRoomNumber(roomNumber2);

        // roomNumber2 should unlock door
        assertTrue(hotelRoomLockService_room200a.unlockDoor(cardCode2));

    }
}
