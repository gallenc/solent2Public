/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.service.rest.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import org.solent.com504.project.model.dto.Actor;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.dto.Role;
import org.solent.com504.project.model.service.ServiceFacade;

/**
 *
 * @author gallenc
 */
public class ServiceRestClientImpl implements ServiceFacade {

    final static Logger LOG = LogManager.getLogger(ServiceRestClientImpl.class);

    String baseUrl = "http://localhost:8084/projectfacadeweb/rest/appointmentService";

    public ServiceRestClientImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    // used to provide logging for client messages
    protected static ClientConfig createClientConfig() {
        ClientConfig config = new ClientConfig();
        config.register(new LoggingFeature(java.util.logging.Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));
        return config;
    }

    @Override
    public String getHeartbeat() {
        LOG.debug("getHeartbeat() Called");

        Client client = ClientBuilder.newClient(createClientConfig());
        WebTarget webTarget = client.target(baseUrl).path("getHeartbeat");

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        ReplyMessage replyMessage = response.readEntity(ReplyMessage.class);
        LOG.debug("Response status=" + response.getStatus() + " ReplyMessage: " + replyMessage);

        if (replyMessage == null) {
            return null;
        }

        return replyMessage.getDebugMessage();

    }

    @Override
    public List<Actor> findByRole(Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
