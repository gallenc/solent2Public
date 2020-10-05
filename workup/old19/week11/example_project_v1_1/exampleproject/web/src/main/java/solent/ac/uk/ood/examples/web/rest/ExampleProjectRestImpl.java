/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.web.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.model.Entity;
import solent.ac.uk.ood.examples.model.ReplyMessage;
import solent.ac.uk.ood.examples.model.ServiceFacade;
import solent.ac.uk.ood.examples.web.WebObjectFactory;

/**
 *
 * @author cgallen
 */
@Path("/example")
public class ExampleProjectRestImpl {

    private static final Logger LOG = LoggerFactory.getLogger(ExampleProjectRestImpl.class);

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

    // GET localhost:8680/rest/example/retrieve?id=9
    @GET
    @Path("/retrieve")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response retrieve(@QueryParam("id") Integer id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("id request parameter must be set");
            }
            ReplyMessage replyMessage = new ReplyMessage();

            ServiceFacade serviceFacade = WebObjectFactory.getServiceFactory().getServiceFacade();
            Entity entity = serviceFacade.retrieveEntity(id);
            if (entity != null) {
                LOG.debug("/retrieve id=" + id + " found entity :" + entity);
                replyMessage.getEntityList().getEntities().add(entity);

                replyMessage.setCode(Response.Status.OK.getStatusCode());
                return Response.status(Response.Status.OK).entity(replyMessage).build();
            } else {
                LOG.debug("/retrieve id=" + id + " found no entity :");
                replyMessage.setDebugMessage("/retrieve id=" + id + " found no entity");
                replyMessage.setCode(Response.Status.OK.getStatusCode());
                return Response.status(Response.Status.OK).entity(replyMessage).build();
            }

        } catch (Exception ex) {
            LOG.error("error calling /retrieve ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /retrieve " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }

    }
}
