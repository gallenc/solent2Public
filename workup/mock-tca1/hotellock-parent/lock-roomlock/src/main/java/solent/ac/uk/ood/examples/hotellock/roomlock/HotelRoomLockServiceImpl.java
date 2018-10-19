/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.roomlock;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solent.ac.uk.ood.examples.hotellock.model.CardKey;
import solent.ac.uk.ood.examples.hotellock.model.HotelRoomLockService;
import solent.ac.uk.ood.examples.hotellock.model.SecretKeyProvider;

/**
 *
 * @author cgallen
 */
public class HotelRoomLockServiceImpl implements HotelRoomLockService {

    public static final Logger LOG = LogManager.getLogger(HotelRoomLockServiceImpl.class);
    public static final Logger TRANSACTIONLOG = LogManager.getLogger("transaction-log");

    SecretKeyProvider secretKeyProvider = null;
    String roomNumber = null;

    @Override
    public boolean unlockDoor(String cardCode) {

        CardKey decodedCardKey = null;

        try {
            decodedCardKey = secretKeyProvider.decodeCard(cardCode);
        } catch (Exception ex) {
            LOG.error("cannot decode card ", ex);
            TRANSACTIONLOG.warn("room number '" + roomNumber + "' door access attempted with unreadable cardCode: " + cardCode);
        }

        if (decodedCardKey == null) {
            return false;
        }

        if ((decodedCardKey.getRoomNumber() == null) || (!decodedCardKey.getRoomNumber().equals(roomNumber))) {
            TRANSACTIONLOG.warn("room number '" + roomNumber + "' illegal card access attempted with card details: " + decodedCardKey);
            return false;
        }

        Date startDate = decodedCardKey.getStartDate();
        Date endDate = decodedCardKey.getEndDate();

        Date now = new Date();
        if (startDate != null && now.getTime() < startDate.getTime()) {
            TRANSACTIONLOG.warn("room number '" + roomNumber
                    + "' illegal card access attempted before card start time card details: " + decodedCardKey);
            return false;
        }

        if (endDate != null && now.getTime() > endDate.getTime()) {
            TRANSACTIONLOG.warn("room number '" + roomNumber
                    + "' illegal card access attempted after card end time card details: " + decodedCardKey);
            return false;
        }

        TRANSACTIONLOG.info("room number '" + roomNumber
                + "'  card access successfully opened door: " + decodedCardKey);
        return true;
    }

    @Override
    public void setSecretKeyProvider(SecretKeyProvider secretKeyProvider) {
        this.secretKeyProvider = secretKeyProvider;
    }

    @Override
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

}
