/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardcheck.service.rest.client.manual;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.cardcheck.model.Transaction;
import solent.ac.uk.ood.examples.cardcheck.model.TransactionResult;
import solent.ac.uk.ood.examples.cardcheck.service.TransactionApi;

/**
 *
 * @author cgallen
 */
public class TransactionApiRestClientImpl implements TransactionApi {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionApiRestClientImpl.class);

    private WebTarget target;

    private MediaType messageType = MediaType.APPLICATION_JSON_TYPE;

    /**
     *
     * @param targetUrl the url which will be targeted for the transaction api
     * e.g. "http://localhost:8680"
     */
    public TransactionApiRestClientImpl(String targetUrl) {
        Client client = ClientBuilder.newClient();
        target = client.target(targetUrl).path("rest");
    }

    /**
     *
     * @param targetUrl the url which will be targeted for the transaction api
     * @param messageType MediaType.APPLICATION_JSON_TYPE or MediaType.APPLICATION_JSON_TYPE
     * e.g. "http://localhost:8680"
     */
    public TransactionApiRestClientImpl(String targetUrl, MediaType messageType) {
        Client client = ClientBuilder.newClient();
        target = client.target(targetUrl).path("rest");
        this.messageType = messageType;
    }

    @Override
    public TransactionResult requestPreAuthorisation(Transaction requestTransaction) {
        try {
            Response response = target.path("requestPreAuthorisation").
                    request(messageType).post(Entity.entity(requestTransaction, messageType));
            return response.readEntity(TransactionResult.class);
        } catch (Exception e) {
            LOG.error("requestPreAuthorisation failed", e);
            return null;
        }
    }

    @Override
    public TransactionResult requestTransaction(Transaction requestTransaction) {

        try {
            Response response = target.path("requestTransaction")
                    .request(messageType)
                    .post(Entity.entity(requestTransaction, messageType));
            return response.readEntity(TransactionResult.class);
        } catch (Exception e) {
            LOG.error("requestTransaction failed", e);
            return null;
        }

    }

}
