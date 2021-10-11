/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.rest;

/**
 *
 * @author gallenc
 */
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

import java.util.List;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.party.service.PartyService;
import org.solent.com504.project.model.service.ServiceFacade;
import org.solent.com504.project.model.user.dto.Role;
import org.solent.com504.project.model.user.dto.User;
import org.solent.com504.project.model.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * To make the ReST interface easier to program. All of the replies are contained in ReplyMessage classes but only the fields indicated are populated with each
 * reply. All replies will contain a code and a debug message. Possible replies are: List<String> replyMessage.getStringList() AnimalList
 * replyMessage.getAnimalList() int replyMessage.getCode() replyMessage.getDebugMessage(); * @author cgallen
 */
@Component // component allows resource to be picked up
@Path("/solent-api/party/v1/")
public class UserRestService {

    // SETS UP LOGGING 
    // note that log name will be org.solent.com504.project.impl.rest.RestService
    final static Logger LOG = LogManager.getLogger(UserRestService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PartyService partyService;
    
        /**
     * this is a very simple rest test message which only returns a string
     *
     * http://localhost:8080/project-web/rest/solent-api/party/v1/party
     *
     * @return String simple message
     */
    // swagger annotations
    @Operation(
            tags = {"party/test"},
            summary = "all this does is ask for a text 'hello world' response",
            description = "Returns text hello world",
            responses = {
                @ApiResponse(description = "hello world message",
                        content = @Content(mediaType = "text/plain"))
            })

    @GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    @Produces({MediaType.TEXT_PLAIN})
    public String message() {
        LOG.debug("project-web called");
        return "Hello, rest!";
    }

    @Operation(summary = "Find list of users",
            tags = {"party/user"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns user list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "404", description = "not found"),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })
    @GET
    @Path("/user")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional(readOnly = true)
    public Response getUsers(@Context UriInfo uriInfo) {
        try {

            ReplyMessage replyMessage = new ReplyMessage();
            LOG.debug("/getUsers called");

            if (userService == null) {
                throw new RuntimeException("userService==null and has not been initialised");
            }

            List<User> userList = userService.findAll();

            String requestPath = uriInfo.getAbsolutePath().toASCIIString();

            List<User> unboundList = unbindUserList(userList, requestPath);

            replyMessage.setUserList(unboundList);
            replyMessage.setSize(unboundList.size());

            replyMessage.setCode(Response.Status.OK.getStatusCode());

            return Response.status(Response.Status.OK).entity(replyMessage).build();

        } catch (Exception ex) {
            LOG.error("error calling /user getUsers ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /user getUsers " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    @Operation(summary = "Find user by username",
            tags = {"party/user"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns user list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "404", description = "not found"),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })
    @GET
    @Path("/user/{username}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getUser(@PathParam("username") String username, @Context UriInfo uriInfo) {
        try {

            ReplyMessage replyMessage = new ReplyMessage();
            LOG.debug("/getUser called username=" + username);

            if (userService == null) {
                throw new RuntimeException("userService==null and has not been initialised");
            }

            User user = userService.findByUsername(username);
            if (user == null) {
                replyMessage.setCode(Response.Status.NOT_FOUND.getStatusCode());
                replyMessage.setDebugMessage("username not found " + username);
                replyMessage.setUserList(new ArrayList());
                return Response.status(Response.Status.NOT_FOUND).entity(replyMessage).build();
            }

            String requestPath = uriInfo.getAbsolutePath().toASCIIString();

            List<User> unboundList = unbindUserList(Arrays.asList(user), requestPath);
            replyMessage.setUserList(unboundList);

            replyMessage.setCode(Response.Status.OK.getStatusCode());

            return Response.status(Response.Status.OK).entity(replyMessage).build();

        } catch (Exception ex) {
            LOG.error("error calling /user/{username} getUser username=" + username, ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /user/{username} getUser username=" + username + " error:" + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    @Operation(summary = "Find all parties",
            tags = {"party/party"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns party list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })
    @GET
    @Path("/party")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional(readOnly = true)
    public Response getPartys(@Context UriInfo uriInfo) {
        try {

            ReplyMessage replyMessage = new ReplyMessage();
            LOG.debug("/getPartys called");

            if (partyService == null) {
                throw new RuntimeException("partyService==null and has not been initialised");
            }

            List<Party> partyList = partyService.findAll();

            String requestPath = uriInfo.getAbsolutePath().toASCIIString();

            // converting to set
            Set<Party> unboundList = unbindPartyList(new LinkedHashSet(partyList), requestPath);

            // converting to list
            replyMessage.setPartyList(new ArrayList(unboundList));
            replyMessage.setSize(unboundList.size());

            replyMessage.setCode(Response.Status.OK.getStatusCode());

            return Response.status(Response.Status.OK).entity(replyMessage).build();

        } catch (Exception ex) {
            LOG.error("error calling /party getPartys ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /party getPartys " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    // swagger annotations
    @Operation(summary = "Find party by uuid",
            tags = {"party/party"},
            responses = {
                @ApiResponse(responseCode = "200", description = "successful operation returns party list with one entry", content = @Content(
                        schema = @Schema(implementation = ReplyMessage.class))),
                @ApiResponse(responseCode = "404", description = "not found"),
                @ApiResponse(responseCode = "500", description = "internal server error")
            })
    @GET
    @Path("/party/{uuid}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional(readOnly = true)
    public Response getParty(@PathParam("uuid") String uuid, @Context UriInfo uriInfo) {
        try {
            ReplyMessage replyMessage = new ReplyMessage();
            LOG.debug("/party  getParty  called  uuid=" + uuid);

            if (partyService == null) {
                throw new RuntimeException("partyService==null and has not been initialised");
            }

            Party party = partyService.findByUuid(uuid);
            if (party == null) {
                replyMessage.setCode(Response.Status.NOT_FOUND.getStatusCode());
                replyMessage.setDebugMessage("party uuid not found " + uuid);
                replyMessage.setPartyList(new ArrayList());
                return Response.status(Response.Status.NOT_FOUND).entity(replyMessage).build();
            }

            String requestPath = uriInfo.getAbsolutePath().toASCIIString();

            // converting to set
            Set<Party> unboundList = new LinkedHashSet();
            if (party != null) {
                unboundList = unbindPartyList(new LinkedHashSet(Arrays.asList(party)), requestPath);
            }

            // converting to list
            replyMessage.setPartyList(new ArrayList(unboundList));
            replyMessage.setSize(unboundList.size());

            replyMessage.setCode(Response.Status.OK.getStatusCode());

            return Response.status(Response.Status.OK).entity(replyMessage).build();

        } catch (Exception ex) {
            LOG.error("error calling /party getParty uuid=" + uuid, ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /party getParty uuid=" + uuid + " " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    /**
     * clones new uses and unbinds from entitymanager
     *
     * @param userList
     * @return
     */
    List<User> unbindUserList(List<User> userList, String requestPath) {
        List<User> unboundList = new ArrayList();

        //decouples values from dao
        for (User user : userList) {
            User newUser = new User();
            unboundList.add(newUser);

            // add absolute path href for user
            String userName = user.getUsername();

            String href = requestPath.substring(0, requestPath.indexOf("/solent-api/party/v1/")) + "/solent-api/party/v1/user" + "/" + userName;
            LOG.debug("setting href for username:" + userName + " href=" + href);
            newUser.setHref(href);

            newUser.setAddress(user.getAddress());
            newUser.setEnabled(user.getEnabled());
            newUser.setFirstName(user.getFirstName());
            newUser.setSecondName(user.getSecondName());
            newUser.setUsername(userName);
            newUser.setId(user.getId());

            Set<Party> newParties = unbindPartyList(user.getParties(), requestPath);
            newUser.setParties(newParties);
            Set<Role> roles = new LinkedHashSet();
            for (Role role : user.getRoles()) {
                Role newRole = new Role();
                newRole.setName(role.getName());
                roles.add(newRole);
            }
            newUser.setRoles(roles);
        }
        return unboundList;
    }

    /**
     * clones new uses and unbinds from entitymanager
     *
     * @param partyList
     * @return
     */
    Set<Party> unbindPartyList(Set<Party> partyList, String requestPath) {
        Set<Party> unboundParty = new LinkedHashSet();

        //decouples values from dao
        for (Party party : partyList) {
            Party newParty = new Party();
            unboundParty.add(newParty);

            // add absolute path href for user
            String uuid = party.getUuid();

            String href = requestPath.substring(0, requestPath.indexOf("/solent-api/party/v1/")) + "/solent-api/party/v1/party" + "/" + uuid;
            LOG.debug("setting href for party uuid:" + uuid + " href=" + href);
            newParty.setHref(href);

            newParty.setUuid(uuid);
            newParty.setAddress(party.getAddress());
            newParty.setEnabled(party.getEnabled());
            newParty.setFirstName(party.getFirstName());
            newParty.setId(party.getId());
            newParty.setSecondName(party.getSecondName());
            newParty.setPartyRole(party.getPartyRole());
            newParty.setPartyStatus(party.getPartyStatus());
            newParty.setPartyRole(party.getPartyRole());
        }
        return unboundParty;
    }



}
