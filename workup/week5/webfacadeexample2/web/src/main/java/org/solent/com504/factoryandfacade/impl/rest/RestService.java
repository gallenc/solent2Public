/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.rest;

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
import org.solent.com504.factoryandfacade.impl.web.WebObjectFactory;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.dto.ReplyMessage;
import org.solent.com504.factoryandfacade.model.service.FarmFacade;

/**
 * To make the ReST interface easier to program. All of the replies are
 * contained in ReplyMessage classes but only the fields indicated are populated
 * with each reply. All replies will contain a code and a debug message.
 * Possible replies are: List<String> replyMessage.getStringList() AnimalList
 * replyMessage.getAnimalList() int replyMessage.getCode()
 * replyMessage.getDebugMessage(); * @author cgallen
 */
@Path("/farmService")
public class RestService {

    // SETS UP LOGGING 
    // note that log name will be org.solent.com504.factoryandfacade.impl.rest.RestService
    final static Logger LOG = LogManager.getLogger(RestService.class);

    /**
     * this is a very simple rest test message which only returns a string
     *
     * http://localhost:8084/basicfacadeweb/rest/farmService/
     *
     * @return String simple message
     */
    @GET
    public String message() {

        LOG.debug("farmService called");
        return "Hello, rest!";
    }

    /**
     * Get all animals on the farm
     *
     * http://localhost:8084/basicfacadeweb/rest/farmService/
     *
     * @return list of all Animals in List<String> replyMessage.getStringList()
     */
    @GET
    @Path("/getAllAnimals")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllAnimals() {
        try {

            FarmFacade serviceFacade = WebObjectFactory.getServiceFacade();
            ReplyMessage replyMessage = new ReplyMessage();
            LOG.debug("/getAllAnimals called");

            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            //replyMessage.setCode(Response.Status.OK.getStatusCode());
            //return Response.status(Response.Status.OK).entity(replyMessage).build();
        } catch (Exception ex) {
            LOG.error("error calling /getAllAnimals ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /getAllAnimals " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    /**
     * Creates an Animal of a given AnimalType with a name and adds the animal
     * to the list of animals stored by this farm. Note only one animal can be
     * added with the same name
     *
     * http://localhost:8084/basicfacadeweb/rest/farmService/addAnimal
     *
     * @param animalType type of animal ( which must have been created by the
     * AnimalTypeDao)
     * @param name name to give this animal - does not have to be unique
     * @return an animal of a given type with the supplied name which has been
     * stored. Animal will also have been given an id.
     * @ returns error code if animal name is duplicated. All animal names must
     * be unique. AnimalList replyMessage.getAnimalList().get(0) contains
     * returned animal
     */
    @POST
    @Path("/addAnimal")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addAnimal(@QueryParam("animalType") String animalType, @QueryParam("animalName") String animalName) {
        try {
            LOG.debug("/addAnimal called animalType=" + animalType + "animalName=" + animalName);

            FarmFacade serviceFacade = WebObjectFactory.getServiceFacade();
            ReplyMessage replyMessage = new ReplyMessage();

            Animal addedAnimal = serviceFacade.addAnimal(animalType, animalName);
            replyMessage.getAnimalList().getAnimals().add(addedAnimal);

            replyMessage.setCode(Response.Status.OK.getStatusCode());

            return Response.status(Response.Status.OK).entity(replyMessage).build();

        } catch (Exception ex) {
            LOG.error("error calling /addAnimal ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /addAnimal " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    /**
     * returns all animals in farm of a given type
     *
     * http://localhost:8084/basicfacadeweb/rest/farmService/getAnimalsOfType
     *
     * @param animalType type name for the animal type
     * @return returns a list of all animals of a given type AnimalList
     * replyMessage.getAnimalList() contains returned animals
     */
    @GET
    @Path("/getAnimalsOfType")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAnimalsOfType(@QueryParam("animalType") String animalType) {
        try {
            LOG.debug("/getAnimalsOfType called animalType=" + animalType);

            FarmFacade serviceFacade = WebObjectFactory.getServiceFacade();
            ReplyMessage replyMessage = new ReplyMessage();

            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            //LOG.debug("/retrievematching entityTemplate: " + entityTemplate);
            //replyMessage.setCode(Response.Status.OK.getStatusCode());
            //return Response.status(Response.Status.OK).entity(replyMessage).build();
        } catch (Exception ex) {
            LOG.error("error calling /getAnimalsOfType ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /getAnimalsOfType " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    /**
     * returns the first animal in the list which has a given name Note that
     * names should be unique so only one animal of this name in list.
     *
     * http://localhost:8084/basicfacadeweb/rest/farmService/getAnimal *
     *
     * @param animalName name of animal to retrieve
     * @return ReplyMesssage AnimalList replyMessage.getAnimalList()
     */
    @GET
    @Path("/getAnimal")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAnimal(@QueryParam("animalName") String animalName) {
        try {
            LOG.debug("/getAnimal called animalName=" + animalName);
            FarmFacade serviceFacade = WebObjectFactory.getServiceFacade();
            ReplyMessage replyMessage = new ReplyMessage();

            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            //LOG.debug("/retrievematching entityTemplate: " + entityTemplate);
            //replyMessage.setCode(Response.Status.OK.getStatusCode());
            //return Response.status(Response.Status.OK).entity(replyMessage).build();
        } catch (Exception ex) {
            LOG.error("error calling /getAnimal ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /getAnimal " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    /**
     * remove the first animal in the list which has a given name Note HTTP post
     * must be used
     * http://localhost:8084/basicfacadeweb/rest/farmService/removeAnimal POST
     * not GET
     *
     * @param animalName
     * @return ReplyMessage OK if deleted animal
     */
    @POST
    @Path("/removeAnimal")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response removeAnimal(@QueryParam("animalName") String animalName) {
        try {

            LOG.debug("/removeAnimal called animalName=" + animalName);

            FarmFacade serviceFacade = WebObjectFactory.getServiceFacade();
            ReplyMessage replyMessage = new ReplyMessage();

            throw new UnsupportedOperationException("Not supported yet.");

            //LOG.debug("/retrievematching entityTemplate: " + entityTemplate);
            //replyMessage.setCode(Response.Status.OK.getStatusCode());
            //return Response.status(Response.Status.OK).entity(replyMessage).build();
        } catch (Exception ex) {
            LOG.error("error calling /removeAnimal ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /removeAnimal " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    /**
     * returns a list of strings describing the supported animal types
     *
     * http://localhost:8084/basicfacadeweb/rest/farmService/getSupportedAnimalTypes
     *
     * @return ReplyMessage List<String> replyMessage.getStringList()
     */
    @GET
    @Path("/getSupportedAnimalTypes")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSupportedAnimalTypes() {
        try {
            LOG.debug("/getSupportedAnimalTypes called");

            FarmFacade serviceFacade = WebObjectFactory.getServiceFacade();
            ReplyMessage replyMessage = new ReplyMessage();

            List<String> stringList = serviceFacade.getSupportedAnimalTypes();

            replyMessage.setStringList(stringList);

            //LOG.debug("/retrievematching entityTemplate: " + entityTemplate);
            replyMessage.setCode(Response.Status.OK.getStatusCode());
            return Response.status(Response.Status.OK).entity(replyMessage).build();
        } catch (Exception ex) {
            LOG.error("error calling /getSupportedAnimalTypes ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /getSupportedAnimalTypes " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

}
