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

    // get request parameters
    String action = (String) request.getParameter("action");
    String destinationReq = (String) request.getParameter("destination");
    String ticketId = (String) request.getParameter("ticketId");
    
    String useRailCard = (String) request.getParameter("useCard");
    boolean railCardAllowed = "true".equals(useRailCard);
    
    // logic for user has rail card
    Boolean userHasCard = (Boolean) session.getAttribute("hasCard");
    if(userHasCard==null) userHasCard = false;

    boolean incorrect = false;

    if ("addTimetableItem".equals(action)) {
        try {
            String start = (String) request.getParameter("startTime");
            String end = (String) request.getParameter("endTime");
            String price = (String) request.getParameter("price");
            TicketItem ticketItem = new TicketItem();
            ticketItem.setDestination(destinationReq);
            ticketItem.setStart(start);
            ticketItem.setEnd(end);
            ticketItem.setPrice(Double.parseDouble(price));
            ticketItem.setRailCardAllowed(railCardAllowed);
            ticketMachineFacade.createTicketItem(ticketItem);

            // redirect back to list timetable page if success
            response.sendRedirect("ListTimetable.jsp?destination=" + destinationReq);
        } catch (Exception e) {
            incorrect = true;
        }
    }

%>

<html>
    <head>
        <title>Timetable Item</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="js/timepicker.min.css">
        <script src="js/timepicker.min.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function (event) {
                timepicker.load({
                    interval: 1,
                    defaultHour: 0
                });
            });
        </script>
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


        <h1>Add or Modify Timetable Item</h1>
        <p>Enter the price in £ pounds sterling, and the time of departure and arrival.
            <BR>
            Clicking on the time fields will allow you to select hours followed by minutes from a drop down menu.
            <BR>
            Be careful that you enter the arrival time as after the departure time.
        </p>
        <form action="AddOrModifyTimetableItem.jsp">
            <%    if (incorrect) {
            %>
            <div style="color:red;">Incorrect format of entry. Try again.</div>
            <%    }
            %>
            <table>
                <tr>
                    <td>Destination</td>
                    <td><%=destinationReq%></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td>£&nbsp;<input type="text" name="price" value="0.0"></td>
                </tr>
                <tr>
                    <td>
                        Allowed Discounts
                    </td>
                    <td>
                        <input type="radio" name="useCard" value="true" <% if (railCardAllowed) {%> checked="checked" <% } %> /> Can use Rail Card<br>
                        <input type="radio" name="useCard" value="false" <% if (!railCardAllowed) {%> checked="checked" <% }%>  /> Cannot use Rail Card<br>
                    </td>
                </tr>
                <tr>
                    <td>Start Time (HH:MM)</td>
                    <td>
                        <div>
                            <input type="text" name="startTime" placeholder="Time" data-toggle="timepicker">
                        </div>

                    </td>
                </tr>
                <tr>
                    <td>End Time (HH:MM)</td>
                    <td>
                        <div>
                            <input type="text" name="endTime"  placeholder="Time" data-toggle="timepicker">
                        </div>
                    </td>
                </tr>
            </table>
            <BR>
            <input type="hidden" name="destination" value="<%=destinationReq%>">
            <input type="hidden" name="action" value="addTimetableItem">
            <input type="submit" value="Add Timetable Item">
        </form>
        <%
            }
        %>

        <BR>
        <form action="ListTimetable.jsp">
            <input type="hidden" name="action" value="list">
            <input type="hidden" name="destination" value="<%=destinationReq%>">
            <input type="submit" value="Return to Timetable for <%=destinationReq%>">
        </form>
    </body>

</html>
