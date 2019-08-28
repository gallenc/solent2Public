/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.secretkey;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import solent.ac.uk.ood.examples.hotellock.model.CardKey;
import solent.ac.uk.ood.examples.hotellock.model.SecretKeyProvider;

/**
 *
 * @author cgallen
 */
public class SecretKeyProviderImpl implements SecretKeyProvider {

    @Override
    public  String encodeCard(CardKey cardKey) {
        String result = null;
        String roomNumber = cardKey.getRoomNumber();
        String start = Long.toHexString(cardKey.getStartDate().getTime());
        String end = Long.toHexString(cardKey.getEndDate().getTime());
        String issueNumber = Integer.toHexString(cardKey.getIssueNumber());

        String csv = roomNumber + "," + start + "," + end + "," + issueNumber;
        String hash = Integer.toHexString(csv.hashCode());
        csv = csv + ":" + hash;

        byte[] bytes;
        try {
            bytes = csv.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("problem encoding card", ex);
        }
        result = DatatypeConverter.printHexBinary(bytes);
        return result;

    }

    @Override
    public  CardKey decodeCard(String cardString) {
        byte[] bytes2 = DatatypeConverter.parseHexBinary(cardString);
        String s = null;
        try {
            s = new String(bytes2, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("problem decoding card", ex);
        }

        String[] hashsplit = s.split(":");
        String csv = hashsplit[0];
        String hashStr = hashsplit[1];

        if (!Integer.toHexString(csv.hashCode()).equals(hashStr)) {
            throw new IllegalArgumentException("illegal hash - cannot decode card");
        }

        String[] split = csv.split(",");

        String roomNumber = split[0];
        String start = split[1];
        String end = split[2];
        String issueNumber = split[3];

        Date startDate = new Date(Long.parseLong(start, 16));
        Date endDate = new Date(Long.parseLong(end, 16));
        
        CardKey cardKey = new CardKey();
        cardKey.setEndDate(endDate);
        cardKey.setStartDate(startDate);
        cardKey.setIssueNumber(Integer.parseInt(issueNumber,16));
        cardKey.setRoomNumber(roomNumber);

        return cardKey;
    }

}
