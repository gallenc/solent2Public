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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class RestService {

  @GET
  public String message() {
    return "Hello, rest!";
  }
  
   @POST
    @Path("/retrievematching")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response retrieveMatchingEntites(Entity entityTemplate) {

        try {
            if (entityTemplate == null) {
                throw new IllegalArgumentException("entityTemplate request parameter must be set");
            }
            ReplyMessage replyMessage = new ReplyMessage();

            ServiceFacade serviceFacade = WebObjectFactory.getServiceFactory().getServiceFacade();
            List<Entity> eList = serviceFacade.retrieveMatchingEntities(entityTemplate);

            LOG.debug("/retrievematching entityTemplate: " + entityTemplate 
                    + " found " + eList.size() + "entities");

            replyMessage.getEntityList().getEntities().addAll(eList);

            replyMessage.setCode(Response.Status.OK.getStatusCode());

            return Response.status(Response.Status.OK).entity(replyMessage).build();

        } catch (Exception ex) {
            LOG.error("error calling /retrievematching ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /retrievematching " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }
}