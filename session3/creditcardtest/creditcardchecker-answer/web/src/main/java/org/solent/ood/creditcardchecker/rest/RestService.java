/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.creditcardchecker.rest;

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
import org.solent.ood.creditcardchecker.model.dto.CreditCard;
import org.solent.ood.creditcardchecker.model.dto.ReplyMessage;
import solent.ac.uk.ood.examples.cardcheck.CardValidationResult;
import solent.ac.uk.ood.examples.cardcheck.RegexCardValidator;

/**
 * To make the ReST interface easier to program. All of the replies are contained in ReplyMessage classes but only the fields indicated are populated with each
 * reply. All replies will contain a code and a debug message. Possible replies are: List<String> replyMessage.getStringList() AnimalList
 * replyMessage.getAnimalList() int replyMessage.getCode() replyMessage.getDebugMessage(); * @author cgallen
 */
@Path("/api-v1")
public class RestService {

    // SETS UP LOGGING 
    // note that log name will be org.solent.ood.creditcardchecker.rest.RestService
    final static Logger LOG = LogManager.getLogger(RestService.class);

    /**
     * this is a very simple rest test message which only returns a string
     *
     * http://localhost:8080/creditcardchecker-web/rest/api-v1
     *
     * @return String simple message
     */
    @GET
    public String test() {

        LOG.debug("rest test called");
        return "Hello, rest!";
    }

    /**
     * http://localhost:8080/creditcardchecker-web/rest/api-v1/validateCard?cardNo=4444444444444448
     * @return 
     */
    @GET
    @Path("/validateCard")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getValidateCard(@QueryParam("cardNo") String cardNo) {
        try {
            LOG.debug("GET /validateCard called cardNo:" + cardNo);
            ReplyMessage replyMessage = new ReplyMessage();
            CardValidationResult result = RegexCardValidator.isValid(cardNo);
            replyMessage.setCardValidationResult(result);
            if (result.isValid()) {
                replyMessage.setCode(Response.Status.OK.getStatusCode());
                return Response.status(Response.Status.OK).entity(replyMessage).build();
            } else {
                replyMessage.setCode(Response.Status.BAD_REQUEST.getStatusCode());
                return Response.status(Response.Status.BAD_REQUEST).entity(replyMessage).build();
            }

        } catch (Exception ex) {
            LOG.error("error calling GET /validateCard ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setMessage("error calling GET /validateCard " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    /**
     * POST http://localhost:8080/creditcardchecker-web/rest/api-v1/validateCard payload 
     * { "name" : "test user1", "endDate" : "11/21", "cardnumber" :
     * "5133880000000012", "cvv" : "123", "issueNumber" : "01" }
     */
    @POST
    @Path("/validateCard")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postValidateFullCard(CreditCard creditCard) {
        try {
            LOG.debug("/validateCard called creditCArd:" + creditCard);

            ReplyMessage replyMessage = new ReplyMessage();
            CardValidationResult result = RegexCardValidator.isValid(creditCard.getCardnumber());
            replyMessage.setCardValidationResult(result);

            if (result.isValid()) {
                replyMessage.setCode(Response.Status.OK.getStatusCode());

                return Response.status(Response.Status.OK).entity(replyMessage).build();
            } else {
                replyMessage.setCode(Response.Status.BAD_REQUEST.getStatusCode());
                return Response.status(Response.Status.BAD_REQUEST).entity(replyMessage).build();
            }

        } catch (Exception ex) {
            LOG.error("error calling POST /validateCard ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setMessage("error calling POST /validateCard " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

}
