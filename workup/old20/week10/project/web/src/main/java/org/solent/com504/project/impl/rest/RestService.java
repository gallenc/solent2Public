/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.rest;

/**
 *
 * @author gallenc
 */
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.impl.web.WebObjectFactory;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.service.ServiceFacade;


/**
 * To make the ReST interface easier to program. All of the replies are
 * contained in ReplyMessage classes but only the fields indicated are populated
 * with each reply. All replies will contain a code and a debug message.
 * Possible replies are: List<String> replyMessage.getStringList() AnimalList
 * replyMessage.getAnimalList() int replyMessage.getCode()
 * replyMessage.getDebugMessage(); * @author cgallen
 */
@Path("/appointmentService")
public class RestService {

    // SETS UP LOGGING 
    // note that log name will be org.solent.com504.factoryandfacade.impl.rest.RestService
    final static Logger LOG = LogManager.getLogger(RestService.class);

    /**
     * this is a very simple rest test message which only returns a string
     *
     * http://localhost:8084/projectfacadeweb/rest/appointmentService/
     *
     * @return String simple message
     */
    @GET
    public String message() {
        LOG.debug("appointmentService called");
        return "Hello, rest!";
    }

    /**
     * get heartbeat
     *
     * http://localhost:8084/projectfacadeweb/rest/appointmentService/getHeartbeat
     *
     * @return list of all Animals in List<String> replyMessage.getStringList()
     */
    @GET
    @Path("/getHeartbeat")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllAnimals() {
        try {

            ServiceFacade serviceFacade = WebObjectFactory.getServiceFacade();
            ReplyMessage replyMessage = new ReplyMessage();
            LOG.debug("/getHeartbeat called");

            String heartbeat = serviceFacade.getHeartbeat();
            replyMessage.setDebugMessage(heartbeat);
            
            replyMessage.setCode(Response.Status.OK.getStatusCode());
            
            return Response.status(Response.Status.OK).entity(replyMessage).build();
            
        } catch (Exception ex) {
            LOG.error("error calling /getHeartbeat ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /getHeartbea " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

 

}
