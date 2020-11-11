/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.service.rest.client;

import java.util.ArrayList;
import java.util.List;
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
import org.glassfish.jersey.filter.LoggingFilter;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.dto.ReplyMessage;
import org.solent.com504.factoryandfacade.model.service.FarmFacade;

/**
 *
 * @author gallenc
 */
public class FarmRestClientImpl implements FarmFacade {

    final static Logger LOG = LogManager.getLogger(FarmRestClientImpl.class);

    String baseUrl = "http://localhost:8080/basicfacadeweb/rest/farmService";

    public FarmRestClientImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public List<Animal> getAllAnimals() {
        LOG.debug("getAllAnimals Called");
        List<Animal> animalList = null;

        Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        WebTarget webTarget = client.target(baseUrl).path("getAllAnimals");

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        ReplyMessage replyMessage = response.readEntity(ReplyMessage.class);
        LOG.debug("Response status=" + response.getStatus() + " ReplyMessage: " + replyMessage);

        animalList = replyMessage.getAnimalList().getAnimals();

        return animalList;
    }

    @Override
    public Animal addAnimal(String animalType, String animalName) {
        LOG.debug("client addAnimal Called animalType=" + animalType + " animalName=" + animalName);

        Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        WebTarget webTarget = client.target(baseUrl).path("addAnimal");

        // this is how we construct html FORM variables
        MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
        formData.add("animalType", animalType);
        formData.add("animalName", animalName);

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.post(Entity.form(formData));

        ReplyMessage replyMessage = response.readEntity(ReplyMessage.class);
        LOG.debug("Response status=" + response.getStatus() + " ReplyMessage: " + replyMessage);

        if (!replyMessage.getAnimalList().getAnimals().isEmpty()) {
            return replyMessage.getAnimalList().getAnimals().get(0);
        }
        return null;
    }

    @Override
    public List<String> getSupportedAnimalTypes() {
        LOG.debug("client getSupportedAnimalTypes called");
        List<String> supportedAnimalTypes = null;

        Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        WebTarget webTarget = client.target(baseUrl).path("getSupportedAnimalTypes");

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        ReplyMessage replyMessage = response.readEntity(ReplyMessage.class);
        LOG.debug("Response status=" + response.getStatus() + " ReplyMessage: " + replyMessage);

        supportedAnimalTypes = replyMessage.getStringList();

        return supportedAnimalTypes;
    }

@Override
    public List<Animal> getAnimalsOfType(String animalType) {
        LOG.debug("client getAnimalsOfType Called animalType=" + animalType);

        Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        
        // note that this adds url params in a urlencoded safe way
        WebTarget webTarget = client.target(baseUrl).path("getAnimalsOfType").queryParam("animalType", animalType);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        Response response = webTarget.request().get();

        ReplyMessage replyMessage = response.readEntity(ReplyMessage.class);
        LOG.debug("Response status=" + response.getStatus() + " ReplyMessage: " + replyMessage);

        return replyMessage.getAnimalList().getAnimals();

    }


    @Override
    public Animal getAnimal(String animalName) {
        LOG.debug("client getAnimal Called  animalName=" + animalName);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAnimal(String animalName) {
        LOG.debug("client removeAnimal Called animalName=" + animalName);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
