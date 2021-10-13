/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.creditcardchecker.client;


import java.util.logging.Level;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.logging.LoggingFeature;
import org.solent.ood.creditcardchecker.model.dto.CreditCard;
import org.solent.ood.creditcardchecker.model.dto.ReplyMessage;
import solent.ac.uk.ood.examples.cardcheck.CardValidationResult;

/**
 *
 * @author cgallen
 */
public class CardCheckerClient {

    final static Logger LOG = LogManager.getLogger(CardCheckerClient.class);

    String urlStr;

    public CardCheckerClient(String urlStr) {
        this.urlStr = urlStr;
    }

    public boolean getCheckCard(String cardNo) {
        LOG.debug("GET getCheckCard called");

        // sets up logging for the client       
        Client client = ClientBuilder.newClient(new ClientConfig().register(
                new LoggingFeature(java.util.logging.Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                        Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000)));

        // allows client to decode json
        client.register(JacksonJsonProvider.class);

        WebTarget webTarget = client.target(urlStr).path("/rest/api-v1/validateCard").queryParam("cardNo", cardNo);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.get();

        ReplyMessage replyMessage = response.readEntity(ReplyMessage.class);
        LOG.debug("Response status=" + response.getStatus() + " ReplyMessage: " + replyMessage);

        return replyMessage.getCardValidationResult().isValid();

    }

    public CardValidationResult postCheckCard(CreditCard creditCard) {
        LOG.debug("postCheckCard called: creditCard = "+creditCard);

        // sets up logging for the client       
        Client client = ClientBuilder.newClient(new ClientConfig().register(
                new LoggingFeature(java.util.logging.Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                        Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000)));

        // allows client to decode json
        client.register(JacksonJsonProvider.class);

        WebTarget webTarget = client.target(urlStr).path("/rest/api-v1/validateCard");

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.post(Entity.entity(creditCard, MediaType.APPLICATION_JSON));

        ReplyMessage replyMessage = response.readEntity(ReplyMessage.class);

        CardValidationResult result = replyMessage.getCardValidationResult();
        
        return result;
    }

}
