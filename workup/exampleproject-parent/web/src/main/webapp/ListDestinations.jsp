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

    // find user session type
    // only this page can change role
    String roleReq = (String) request.getParameter("role");
    if (roleReq != null && ("customer".equals(roleReq) || "admin".equals(roleReq))) {
        session.setAttribute("role", roleReq);
    }
    String role = (String) session.getAttribute("role");
    boolean admin = "admin".equals(role);

    // get request variables
    String action = (String) request.getParameter("action");
    String destinationReq = (String) request.getParameter("destination");
    String ticketIdReq = (String) request.getParameter("ticketId");

    // logic for user has rail card
    Boolean userHasCard = (Boolean) session.getAttribute("hasCard");
    if (userHasCard == null) {
        userHasCard = false;
    }

    String userHasCardReq = (String) request.getParameter("hasCard");
    if (userHasCardReq != null) {
        userHasCard = "true".equals(userHasCardReq);
        session.setAttribute("hasCard", userHasCard);
    }

    // add or delete destination if requested to
    if ("addDestination".equals(action)) {
        if (!destinationReq.isEmpty()) {
            ticketMachineFacade.addDestination(destinationReq);
        }
    } else if ("deleteDestination".equals(action)) {
        ticketMachineFacade.deleteDestination(destinationReq);
    }

    Set<String> destinations = ticketMachineFacade.getDestinations();

%>

<html>
    <head>
        <title>Destinations</title>
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
        <h1>Rail Card</h1>
        <div>
            <p>Using a Rail Card will discount your fare.
                <BR>However you may only use a railcard on certain journeys.</p>
            <form action="ListDestinations.jsp">
                <input onchange='this.form.submit();' type="radio" name="hasCard" value="true" <% if (userHasCard) {%> checked="checked" <% } %> /> I Have a Rail Card<br>
                <input onchange='this.form.submit();' type="radio" name="hasCard" value="false" <% if (!userHasCard) {%> checked="checked" <% }%>  /> I do not Have a Rail Card<br>
            </form>
        </div>

        <h1>Select Destination</h1>
        <%
            if (destinations.isEmpty()) {
        %>
        <div>No Destinations Available</div>
        <%
            }
        %>
        <table>
            <%  Iterator<String> iterator = destinations.iterator();
                while (iterator.hasNext()) {
            %>
            <tr>
                <%  for (int i = 0; i < 4; i++) {
                %>

                <%
                    if (iterator.hasNext()) {
                        String destination = iterator.next();
                %>
                <td> 
                    <form action="ListTimetable.jsp">
                        <input type="hidden" name="action" value="list">
                        <input type="hidden" name="destination" value="<%=destination%>">
                        <input type="submit" value="<%=destination%>">
                    </form>
                </td>
                <%
                    }
                %>

                <%}
                %>
            </tr>
            <% }
            %>
        </table> 
        <BR>
        <%    if (admin) {
        %>
        <BR>
        <form action="AddDestination.jsp">
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add Destination">
        </form>
        <%    }
        %>
    </body>
</html>
