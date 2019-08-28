/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.service.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import solent.ac.uk.ood.examples.hotellock.model.CardKey;
import solent.ac.uk.ood.examples.hotellock.model.RestMessage;
import org.glassfish.jersey.filter.LoggingFilter;

/**
 * This class proxys the HotelReceptionService over a rest interface
 *
 * @author cgallen
 */
public class HotelReceptionRestClientImpl {

    private WebTarget target;
    private MediaType mediaType = null;

    /**
     * @param baseUrl the url which will be targeted for the rest api e.g. "http://localhost:8680"
     * @param mediaType the messages expected either MediaType.APPLICATION_JSON_TYPE or MediaType.APPLICATION_XML_TYPE
     */
    public HotelReceptionRestClientImpl(String baseUrl, MediaType mediaType) {
        Client client = ClientBuilder.newClient();
        client.register(new LoggingFilter()); // this logs the generated requestss
        target = client.target(baseUrl).path("rest/reception");
        this.mediaType = mediaType;
    }

    /**
     * Client to use rest interface to create card code
     * @param roomNumber
     * @param requestStartDateStr
     * @param requestEndDateStr
     * @return 
     */
    public String createCardCode(String roomNumber, String requestStartDateStr, String requestEndDateStr) {

        Response response = null;
        try {
            Invocation.Builder builder = target.path("createCardCode")
                    .queryParam("roomNumber", roomNumber)
                    .queryParam("requestStartDateStr", requestStartDateStr)
                    .queryParam("requestEndDateStr", requestEndDateStr)
                    .request(mediaType);
            response = builder.get();
        } catch (Exception e) {
            throw new RuntimeException("cannot run rest client to createCardCode: Exception:", e);
        }

        // get error message if available
        RestMessage restMessage = null;
        try {
            restMessage = response.readEntity(RestMessage.class);
        } catch (Exception e) {
        }

        if (response.getStatus() != 200) {
            String errorMessage = (restMessage == null) ? "no remote error message" : restMessage.getErrorMessage();
            throw new RuntimeException("response status:" + response.getStatus() + " remote error message: " + errorMessage);
        }

        // responded with an OK message now check actually have a value
        if (restMessage == null) {
            throw new RuntimeException("response status:" + response.getStatus() + " but no restMessage body ");
        }

        return restMessage.getCardCode();
    }

    /**
     * Client to use rest interface to read cardCode
     * @param cardCode
     * @return 
     */
    public CardKey readCard(String cardCode) {
        Response response = null;
        try {
            Invocation.Builder builder = target.path("readCard")
                    .queryParam("cardCode", cardCode)
                    .request(mediaType);
            response = builder.get();
        } catch (Exception e) {
            throw new RuntimeException("cannot run rest client to readCard: Exception:", e);
        }

        // get error message if available
        RestMessage restMessage = null;
        try {
            restMessage = response.readEntity(RestMessage.class);
        } catch (Exception e) {
        }

        if (response.getStatus() != 200) {
            String errorMessage = (restMessage == null) ? "no remote error message" : restMessage.getErrorMessage();
            throw new RuntimeException("response status:" + response.getStatus() + " remote error message: " + errorMessage);
        }

        // responded with an OK message now check actually have a value
        if (restMessage == null) {
            throw new RuntimeException("response status:" + response.getStatus() + " but no restMessage body ");
        }

        return restMessage.getCardkey();
    }

}
