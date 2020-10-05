#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ${package}.web.rest.client;

import ${package}.model.Entity;
import ${package}.model.ReplyMessage;
import ${package}.model.RestInterface;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.filter.LoggingFilter;

/**
 * This class implements the RestInterface as a ReST client which interacts with ExampleProjectRestImpl
 *
 * @author cgallen
 */
public class RestClientJerseyImpl implements RestInterface {

    private WebTarget target;
    private MediaType mediaType = null;

    /**
     * @param baseUrl the url which will be targeted for the rest api e.g. "http://localhost:8680"
     * @param mediaType the messages expected either MediaType.APPLICATION_JSON_TYPE or MediaType.APPLICATION_XML_TYPE
     */
    public RestClientJerseyImpl(String baseUrl, MediaType mediaType) {
        Client client = ClientBuilder.newClient();
        client.register(new LoggingFilter()); // this logs the generated requestss
        target = client.target(baseUrl).path("rest/example");
        this.mediaType = mediaType;
    }

    @Override
    public ReplyMessage retrieveMatchingEntites(Entity entityTempate) {

        Response response = null;
        ReplyMessage replyMessage = null;
        try {

            Invocation.Builder builder = target.path("/retrievematching").request(mediaType);
            response = builder.post(javax.ws.rs.client.Entity.entity(entityTempate, mediaType));

            // read reply message
            replyMessage = response.readEntity(ReplyMessage.class);
            // get error message if available
            if (response.getStatus() != 200) {
                String errorMessage = (replyMessage == null) ? "no remote error message" : replyMessage.getDebugMessage();
                throw new RuntimeException("response status:" + response.getStatus() + " remote error message: " + errorMessage);
            }

            // responded with an OK message now check actually have a value
            if (replyMessage == null) {
                throw new RuntimeException("response status:" + response.getStatus() + " but no restMessage body ");
            }

            return replyMessage;
        } catch (Exception e) {
            throw new RuntimeException("cannot run rest client to /retrievematching: Exception:", e);
        }

    }

    @Override
    public ReplyMessage retrieveEntity(Integer id) {
        Response response = null;
        ReplyMessage replyMessage = null;
        try {
            Invocation.Builder builder = target.path("retrieve")
                    .queryParam("id", id)
                    .request(mediaType);
            response = builder.get();

            replyMessage = response.readEntity(ReplyMessage.class);

            // get error message if available
            if (response.getStatus() != 200) {
                String errorMessage = (replyMessage == null) ? "no remote error message" : replyMessage.getDebugMessage();
                throw new RuntimeException("response status:" + response.getStatus() + " remote error message: " + errorMessage);
            }

            // responded with an OK message now check actually have a value
            if (replyMessage == null) {
                throw new RuntimeException("response status:" + response.getStatus() + " but no restMessage body ");
            }
            
            return replyMessage;

        } catch (Exception e) {
            throw new RuntimeException("cannot run rest client to retrieveEntity: Exception:", e);
        }

        
    }
}
