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

    // If the user session has no bankApi, create a new one
    if (ticketMachineFacade == null) {
        ServiceFactory serviceFactory = WebObjectFactory.getServiceFactory();
        ticketMachineFacade = serviceFactory.getTicketMachineFacade();
        session.setAttribute("ticketMachineFacade", ticketMachineFacade);
    }

    // test if in admin role
    String role = (String) session.getAttribute("role");
    boolean admin = "admin".equals(role);

    String action = (String) request.getParameter("action");
    String destinationReq = (String) request.getParameter("destination");
    String ticketIdReq = (String) request.getParameter("ticketId");
%>

<html>
    <head>
        <title>Add Destination</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <%    if (admin) {
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

        <h1>Add Destination</h1>
        <form action="ListDestinations.jsp">
            <input type="text" name="destination" value="">
            <BR>
            <BR>
            <input type="hidden" name="action" value="addDestination">
            <input type="submit" value="Add Destination">
        </form>
        <BR>
        <form action="ListDestinations.jsp">
            <input type="hidden" name="action" value="cancel">
            <input type="submit" value="Cancel and return to destination list">
        </form>
    </body>
</html>
