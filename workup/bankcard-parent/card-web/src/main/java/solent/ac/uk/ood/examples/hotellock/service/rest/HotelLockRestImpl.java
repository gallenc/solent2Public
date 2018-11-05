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
import javax.ws.rs.PathParam;
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
@Path("/lock")
public class HotelLockRestImpl {

    // notice path param for room number
    // http://localhost:8680/rest/lock/unlockDoor/121/?cardCode=6E756C6C2C31363662356664326334302C31363662623233383834302C313A3561323337363531
    @GET
    @Path("/unlockDoor/{roomNumber}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response readCard(@PathParam("roomNumber") String roomNumber, @QueryParam("cardCode") String cardCode) {

        try {
            if (cardCode == null) {
                throw new IllegalArgumentException("cardCode request parameter must be set");
            }
            // TODO impliment this method correctly
            throw new UnsupportedOperationException("decode card is not implemented for for room number "+roomNumber);

        } catch (Exception ex) {
            RestMessage restMessage = new RestMessage();
            restMessage.setErrorMessage(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(restMessage).build();
        }
    }

}
