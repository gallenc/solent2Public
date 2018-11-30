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

    // test if has rail card
    Boolean userHasCard = (Boolean) session.getAttribute("hasCard");
    if (userHasCard == null) {
        userHasCard = false;
    }

    // get request values
    String action = (String) request.getParameter("action");
    String destinationReq = (String) request.getParameter("destination");
    String ticketItemIdReq = (String) request.getParameter("ticketItemId");

    if ("deleteTicketItemId".equals(action)) {
        try {
            Integer ticketItemId = Integer.parseInt(ticketItemIdReq);
            ticketMachineFacade.deleteTicketItem(ticketItemId);
        } catch (Exception e) {
        }
    }

    List<TicketItem> destinationTicketItems = ticketMachineFacade.getTicketItemsForDestination(destinationReq);

%>

<html>
    <head>
        <title>Timetable</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <%  if (admin) {
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
        <h1>Todays Timetable for <%=destinationReq%><% if (userHasCard) { %> (For Customer with Rail Card) <% } else {%> (No Rail Card)<% } %></h1>
        <%
            if (destinationTicketItems.isEmpty()) {
        %>
        <div>No Journeys Available Today<BR></div>
            <%
            } else {
            %>
        <table >
            <tr>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Duration</th>
                <th>Price</th>
                <th>Notes</th>
                <th></th>
            </tr>
            <%
                for (TicketItem ticketItem : destinationTicketItems) {
            %>
            <tr>
                <td><%=ticketItem.getStart()%></td>
                <td><%=ticketItem.getEnd()%></td>
                <td><%=ticketItem.getDuration()%></td>
                <td><%=String.format("£ %.2f", ticketItem.getPrice())%></td>
                <td><% if (ticketItem.getRailCardAllowed()) { %>Rail Card Allowed<% } %></td>
                <td>
                    <%    if (admin) {
                    %>
                    <form action="ListTimetable.jsp">
                        <input type="hidden" name="action" value="deleteTicketItemId">
                        <input type="hidden" name="destination" value="<%=destinationReq%>">
                        <input type="hidden" name="ticketItemId" value="<%=ticketItem.getTicketItemId()%>">
                        <input type="submit" value="Delete" style="color:red;">
                    </form>
                    <%  }
                    %>
                    <% if (!userHasCard || (ticketItem.getRailCardAllowed() && userHasCard)) {%>
                    <form action="PurchaseTicket.jsp">
                        <input type="hidden" name="action" value="purchase">
                        <input type="hidden" name="ticketItemId" value="<%=ticketItem.getTicketItemId()%>">
                        <input type="submit" value="Purchase">
                    </form>
                    <% } %>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table> 
        <% if (admin) {
        %>
        <BR>
        <form action="AddOrModifyTimetableItem.jsp">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="destination" value="<%=destinationReq%>">
            <input type="submit" value="Add Timetable Item">
        </form>
        <BR>

        <form action="ListDestinations.jsp">
            <input type="hidden" name="action" value="deleteDestination">
            <input type="hidden" name="destination" value="<%=destinationReq%>">
            <input type="submit" value="Delete Destination (including all timetables) for <%=destinationReq%>" style="color:red;">
        </form>
        <%
            }
        %>
        <BR>
        <form action="ListDestinations.jsp">
            <input type="submit" value="Return to Destination List">
        </form>
    </body>
</html>
