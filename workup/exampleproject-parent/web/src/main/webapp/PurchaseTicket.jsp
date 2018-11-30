<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="solent.ac.uk.ood.examples.ticketkiosk.model.TicketMachineFacade"%>
<%@page import="solent.ac.uk.ood.examples.ticketkiosk.web.WebObjectFactory"%>
<%@page import="solent.ac.uk.ood.examples.ticketkiosk.service.ServiceFactory"%>
<%@page import="solent.ac.uk.ood.examples.ticketkiosk.model.Ticket"%>
<%@page import="solent.ac.uk.ood.examples.ticketkiosk.model.TicketItem"%>

<%
    TicketMachineFacade ticketMachineFacade = (TicketMachineFacade) session.getAttribute("ticketMachineFacade");

    // If the user session has no ticketMachineFacade, create a new one
    if (ticketMachineFacade == null) {
        ServiceFactory serviceFactory = WebObjectFactory.getServiceFactory();
        ticketMachineFacade = serviceFactory.getTicketMachineFacade();
        session.setAttribute("ticketMachineFacade", ticketMachineFacade);
    }

    // test if in admin role
    String role = (String) session.getAttribute("role");
    boolean admin = "admin".equals(role);
   
    // test if has rail card
    Boolean userHasCard = (Boolean) session.getAttribute("hasCard");
    if (userHasCard == null) {
        userHasCard = false;
    }

    String action = (String) request.getParameter("action");
    String destinationReq = (String) request.getParameter("destination");
    String ticketItemIdReq = (String) request.getParameter("ticketItemId");

%>

<html>
    <head>
        <title>Checkout your purchase</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <% if (admin) {
        %>
        <div>
            <form action="ListDestinations.jsp">
                <input type="hidden" name="role" value="customer">
                <input type="submit" value="Leave Administrator Mode" style="color:red;">
            </form>
        </div>
        <%
            }
        %>

        <h1>Thank you for purchasing your ticket</h1>
        <table >
            <tr>
                <td>Destination</td>
                <td>TODO destination</td>
            </tr>
            <tr>
                <td>Start Time</td>
                <td>TODO start time</td>
            </tr>
            <tr>
                <td>End Time</td>
                <td>TODO end time</td>
            </tr>
            <tr>
                <td>Price</td>
                <td>TODO Price</td>
            </tr>
            <tr>
                <td>Ticket Number</td>
                <td>TODO  Ticket Number</td>
            </tr>
            <tr>
                <td>Date Issued</td>
                <td>TODO  Date Issued</td>
            </tr>
        </table> 
        <BR>
        TODO MAKE THIS APPEAR ONLY IF RAILCARD DISCOUNT APPLIED
        <Div style="color:red;" >This Ticket can only be used if the bearer has a valid Rail Card</div>

        <BR>
        <form action="ListDestinations.jsp">
            <input type="hidden" name="action" value="list">
            <input type="hidden" name="hasCard" value="false">
            <input type="submit" value="Complete Purchase and return to Destination List">
        </form>
    </body>
</html>
