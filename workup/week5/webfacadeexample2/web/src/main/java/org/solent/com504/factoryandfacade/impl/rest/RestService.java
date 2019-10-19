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
import org.solent.com504.factoryandfacade.impl.web.WebObjectFactory;
import org.solent.com504.factoryandfacade.model.dto.ReplyMessage;
import org.solent.com504.factoryandfacade.model.service.FarmFacade;

@Path("/farmService")
public class RestService {

    // http://localhost:8084/basicfacadeweb/rest/farmService/
    @GET
    public String message() {
        return "Hello, rest!";
    }

    //http://localhost:8084/basicfacadeweb/rest/farmService/
    @GET
    @Path("/getAllAnimals")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllAnimals() {
        try {

            FarmFacade serviceFacade = WebObjectFactory.getServiceFacade();
            ReplyMessage replyMessage = new ReplyMessage();

            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            //LOG.debug("/retrievematching entityTemplate: " + entityTemplate);
            //replyMessage.setCode(Response.Status.OK.getStatusCode());
            //return Response.status(Response.Status.OK).entity(replyMessage).build();
        } catch (Exception ex) {
            //LOG.error("error calling /retrievematching ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /getAllAnimals " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    // http://localhost:8084/basicfacadeweb/rest/farmService/addAnimal
    @POST
    @Path("/addAnimal")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addAnimal(@QueryParam("animalType") String animalType, @QueryParam("name") String name) {
        try {
            FarmFacade serviceFacade = WebObjectFactory.getServiceFacade();
            ReplyMessage replyMessage = new ReplyMessage();

            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            //LOG.debug("/retrievematching entityTemplate: " + entityTemplate);
            //replyMessage.setCode(Response.Status.OK.getStatusCode());
            //return Response.status(Response.Status.OK).entity(replyMessage).build();
        } catch (Exception ex) {
            //LOG.error("error calling /retrievematching ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /addAnimal " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    // http://localhost:8084/basicfacadeweb/rest/farmService/getAnimalsOfType
    @GET
    @Path("/getAnimalsOfType")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAnimalsOfType(@QueryParam("animalType") String animalType) {
        try {
            FarmFacade serviceFacade = WebObjectFactory.getServiceFacade();
            ReplyMessage replyMessage = new ReplyMessage();

            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            //LOG.debug("/retrievematching entityTemplate: " + entityTemplate);
            //replyMessage.setCode(Response.Status.OK.getStatusCode());
            //return Response.status(Response.Status.OK).entity(replyMessage).build();
        } catch (Exception ex) {
            //LOG.error("error calling /retrievematching ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /getAnimalsOfType " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    // http://localhost:8084/basicfacadeweb/rest/farmService/getAnimal
    @GET
    @Path("/getAnimal")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAnimal(@QueryParam("animalName") String name) {
        try {
            FarmFacade serviceFacade = WebObjectFactory.getServiceFacade();
            ReplyMessage replyMessage = new ReplyMessage();

            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            //LOG.debug("/retrievematching entityTemplate: " + entityTemplate);
            //replyMessage.setCode(Response.Status.OK.getStatusCode());
            //return Response.status(Response.Status.OK).entity(replyMessage).build();
        } catch (Exception ex) {
            //LOG.error("error calling /retrievematching ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /getAnimal " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    // http://localhost:8084/basicfacadeweb/rest/farmService/removeAnimal POST not GET
    @POST
    @Path("/removeAnimal")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response removeAnimal(@QueryParam("animalName") String name) {
        try {
            FarmFacade serviceFacade = WebObjectFactory.getServiceFacade();
            ReplyMessage replyMessage = new ReplyMessage();

            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            //LOG.debug("/retrievematching entityTemplate: " + entityTemplate);
            //replyMessage.setCode(Response.Status.OK.getStatusCode());
            //return Response.status(Response.Status.OK).entity(replyMessage).build();
        } catch (Exception ex) {
            //LOG.error("error calling /retrievematching ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /removeAnimal " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    // http://localhost:8084/basicfacadeweb/rest/farmService/getSupportedAnimalTypes
    @GET
    @Path("/getSupportedAnimalTypes")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSupportedAnimalTypes() {
        try {
            FarmFacade serviceFacade = WebObjectFactory.getServiceFacade();
            ReplyMessage replyMessage = new ReplyMessage();

            List<String> stringList = serviceFacade.getSupportedAnimalTypes();

            replyMessage.setStringList(stringList);

            //LOG.debug("/retrievematching entityTemplate: " + entityTemplate);
            replyMessage.setCode(Response.Status.OK.getStatusCode());
            return Response.status(Response.Status.OK).entity(replyMessage).build();
        } catch (Exception ex) {
            //LOG.error("error calling /retrievematching ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /getSupportedAnimalTypes " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

}
