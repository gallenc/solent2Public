/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.service.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import solent.ac.uk.ood.examples.hotellock.model.CardKey;
import solent.ac.uk.ood.examples.hotellock.model.HotelReceptionService;
import solent.ac.uk.ood.examples.hotellock.model.RestMessage;
import solent.ac.uk.ood.examples.hotellock.model.ServiceFactory;

/**
 *
 * @author cgallen
 */
@Path("/reception")
public class HotelReceptionRestImpl {

    // example: http://localhost:8680/rest/reception/createCardCode?roomNumber=123&startDate=27%2F10%2F18+15%3A46&endDate=28%2F10%2F18+14%3A46
    // or http://localhost:8680/rest/reception/createCardCode?roomNumber=123
    @GET
    @Path("/createCardCode")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createCardCode(@QueryParam("roomNumber") String roomNumber,
            @QueryParam("startDate") String requestStartDateStr,
            @QueryParam("endDate") String requestEndDateStr) {

        try {

            if (roomNumber == null) {
                throw new IllegalArgumentException("roomNumber request parameter must be set");
            }

            // date format used  to parse dates from strings
            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm");

            if (requestStartDateStr == null && requestEndDateStr == null) {
                // constants used to generate default time values if not set in request
                Date now = new Date();
                Date tomorrow = new Date(now.getTime() + 1000 * 60 * 60 * 24); // 1 day later
                requestStartDateStr = df.format(now);
                requestEndDateStr = df.format(tomorrow.getTime());
            } else if (requestStartDateStr == null || requestEndDateStr == null){
                throw new IllegalArgumentException("Either both startDate and endDate request parameter must be set or if none set, defaults of now and now+24hrs will be used");
            }

            // get hotelReceptionService from service factory
            ServiceFactory serviceFactory = new ServiceFactoryImpl();
            HotelReceptionService hotelReceptionService = serviceFactory.getHotelReceptionService();

            Date endDate = df.parse(requestEndDateStr);
            Date startDate = df.parse(requestStartDateStr);
            String codedCard = hotelReceptionService.createCardCode(roomNumber, startDate, endDate);

            RestMessage restMessage = new RestMessage();
            restMessage.setCardCode(codedCard);
            return Response.ok().entity(restMessage).build();

        } catch (Exception ex) {
            RestMessage restMessage = new RestMessage();
            restMessage.setErrorMessage(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(restMessage).build();
        }
    }

    // http://localhost:8680/rest/reception/readCard?cardCode=6E756C6C2C31363662356664326334302C31363662623233383834302C313A3561323337363531
    @GET
    @Path("/readCard")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response readCard(@QueryParam("cardCode") String cardCode) {

        try {
            if (cardCode == null) {
                throw new IllegalArgumentException("cardCode request parameter must be set");
            }
            // get hotelReceptionService from service factory
            ServiceFactory serviceFactory = new ServiceFactoryImpl();
            HotelReceptionService hotelReceptionService = serviceFactory.getHotelReceptionService();

            CardKey cardKey = hotelReceptionService.readCard(cardCode);

            RestMessage restMessage = new RestMessage();
            restMessage.setCardkey(cardKey);
            return Response.ok().entity(restMessage).build();

        } catch (Exception ex) {
            RestMessage restMessage = new RestMessage();
            restMessage.setErrorMessage(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(restMessage).build();
        }
    }

}
