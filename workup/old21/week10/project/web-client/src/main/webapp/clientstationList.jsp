<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Date"%>
<%@page import="org.solent.com528.project.impl.webclient.WebClientObjectFactory"%>
<%@page import="org.solent.com528.project.model.service.ServiceFacade"%>
<%@page import="org.solent.com528.project.model.dao.StationDAO"%>
<%@page import="org.solent.com528.project.model.dto.Station"%>


<%
    // used to place error message at top of page 
    String errorMessage = "";
    String message = "";

    // used to set html header autoload time. This automatically refreshes the page
    // Set refresh, autoload time every 20 seconds
    response.setIntHeader("Refresh", 20);

    // accessing service 
    ServiceFacade serviceFacade = (ServiceFacade) WebClientObjectFactory.getServiceFacade();
    StationDAO stationDAO = serviceFacade.getStationDAO();
    Set<Integer> zones = stationDAO.getAllZones();
    List<Station> stationList = new ArrayList<Station>();

    // accessing request parameters
    String actionStr = request.getParameter("action");
    String zoneStr = request.getParameter("zone");

    // return station list for zone
    if (zoneStr == null || zoneStr.isEmpty()) {
        stationList = stationDAO.findAll();
    } else {
        try {
            Integer zone = Integer.parseInt(zoneStr);
            stationList = stationDAO.findByZone(zone);
        } catch (Exception ex) {
            errorMessage = ex.getMessage();
        }
    }

    // basic error checking before making a call
    if (actionStr == null || actionStr.isEmpty()) {
        // do nothing

    } else if ("XXX".equals(actionStr)) {
        // put your actions here
    } else {
        errorMessage = "ERROR: page called for unknown action";
    }

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Client Station List</title>
    </head>
    <body>

        <H1>Ticket Machine Station List</H1>
        <!-- print error message if there is one -->
        <div style="color:red;"><%=errorMessage%></div>
        <div style="color:green;"><%=message%></div>

        <p>The time is: <%= new Date().toString()%> (note page is auto refreshed every 20 seconds)</p>

        <%
            for (Integer selectZone : zones) {
        %>
        <form action="./clientstationList.jsp" method="get">
            <input type="hidden" name="zone" value="<%= selectZone %>">
            <button type="submit" >Zone&nbsp;<%= selectZone %></button>
        </form> 
        <%
            }
        %>
        <p>Stations in <%= (zoneStr == null) ? "All Zones" : "Zone" + zoneStr%></p>
        <%
            for (Station station : stationList) {
        %>
        <p><%=station %></p>
        <%
            }
        %>

    </body>
</html>
