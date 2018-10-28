/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.service.rest.client.test.manual;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.ws.rs.core.MediaType;
import org.junit.Test;
import static org.junit.Assert.*;
import solent.ac.uk.ood.examples.hotellock.model.CardKey;
import solent.ac.uk.ood.examples.hotellock.service.rest.client.HotelReceptionRestClientImpl;

/**
 *
 * @author cgallen
 */
public class HotelReceptionRestClientImplTest {

    String baseUrl = "http://localhost:8680";
    MediaType mediaType = MediaType.APPLICATION_XML_TYPE;

    @Test
    public void testCreateCard() {

        HotelReceptionRestClientImpl client = new HotelReceptionRestClientImpl(baseUrl, mediaType);
        String roomNumber = "101";
        String requestStartDateStr = "27/10/18 16:00";
        String requestEndDateStr = "28/10/18 15:00";
        // date format used  to parse dates from strings
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm");

        String generatedCardCode = client.createCardCode(roomNumber, requestStartDateStr, requestEndDateStr);

        System.out.println("generatedCardCode:" + generatedCardCode);

        CardKey cardKey = client.readCard(generatedCardCode);
        System.out.println("returned card key:" + cardKey);

        assertEquals(roomNumber, cardKey.getRoomNumber());

        String receivedStartDateStr = df.format(cardKey.getStartDate());
        String receivedEndDateStr = df.format(cardKey.getEndDate());

        System.out.println("requestStartDateStr:" + requestStartDateStr
                + " receivedStartDateStr:" + receivedStartDateStr);
        System.out.println("requestEndDateStr:" + requestEndDateStr
                + " receivedEndDateStr:" + receivedEndDateStr);

       // assertEquals(requestStartDateStr, receivedStartDateStr);
       // assertEquals(requestEndDateStr, receivedEndDateStr);

    }

}
