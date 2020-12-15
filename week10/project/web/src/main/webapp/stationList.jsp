<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Date"%>
<%@page import="org.solent.com528.project.impl.web.WebObjectFactory"%>
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
    ServiceFacade serviceFacade = (ServiceFacade) WebObjectFactory.getServiceFacade();
    StationDAO stationDAO = serviceFacade.getStationDAO();
    Set<Integer> zones = stationDAO.getAllZones();
    List<Station> stationList = new ArrayList<Station>();

    // accessing request parameters
    String actionStr = request.getParameter("action");
    String stationName = request.getParameter("stationName");
    String zoneStr = request.getParameter("zone");

    Integer zone = 0;
    if (zoneStr != null) {
        zone = Integer.parseInt(zoneStr);
    } else {
        if (!zones.isEmpty()) {
            zone = zones.iterator().next();
        }
    }

    // return station list for zone
    if (zoneStr == null || zoneStr.isEmpty()) {
        stationList = stationDAO.findAll();
    } else {
        try {
            stationList = stationDAO.findByZone(zone);
        } catch (Exception ex) {
            errorMessage = ex.getMessage();
        }
    }

    // basic error checking before making a call
    if (actionStr == null || actionStr.isEmpty()) {
        // just display list

    } else if ("deleteAllStations".equals(actionStr)) {
        stationDAO.deleteAll();
        stationList = stationDAO.findAll();
        message = "all stations deleted";

    } else if ("deleteStation".equals(actionStr)) {
        Station station = stationDAO.findByName(stationName);
        if (station == null) {
            errorMessage = "ERROR: cannot delete unknown station name: " + stationName;
        } else {
            stationDAO.delete(station);
            zones = stationDAO.getAllZones();
            stationList = stationDAO.findByZone(zone);
            message = "station deleted: " + stationName;
        }
    } else {
        errorMessage = "ERROR: page called for unknown action";
    }

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Station List</title>
    </head>
    <body>

        <H1>Station List</H1>
        <!-- print error message if there is one -->
        <div style="color:red;"><%=errorMessage%></div>
        <div style="color:green;"><%=message%></div>

        <p>The time is: <%= new Date().toString()%> (note page is auto refreshed every 20 seconds)</p>

        <form action="./station.jsp" method="get">
            <input type="hidden" name="action" value="createStation">
            <button type="submit" >Create New Station</button>
        </form> 
        <br>
        <form action="./stationList.jsp" method="get">
            <input type="hidden" name="action" value="deleteAllStations">
            <button type="submit" style="color:red;">Delete All Stations</button>
        </form> 
        <br>
        <form action="./stationList.jsp" method="get">
            <button type="submit" >Show All Zones</button>
        </form> 

        <%
            for (Integer selectZone : zones) {
        %>
        <form action="./stationList.jsp" method="get">
            <input type="hidden" name="zone" value="<%= selectZone%>">
            <button type="submit" >Zone&nbsp;<%= selectZone%></button>
        </form> 
        <%
            }
        %>

        <p>Stations in <%= (zoneStr == null) ? "All Zones" : "Zone&nbsp;" + zoneStr%></p>

        <table border="1">
            <tr>
                <th>Station Name</th>
                <th>Station Zone</th>
            </tr>
            <%
                for (Station station : stationList) {
            %>
            <tr>
                <td size="36" ><%=station.getName()%></td>
                <td size="36" >Zone&nbsp;<%=station.getZone()%></td>
                <td>
                    <form action="./station.jsp" method="get">
                        <input type="hidden" name="stationName" value="<%=station.getName()%>">
                        <input type="hidden" name="zone" value="<%= zone%>">
                        <input type="hidden" name="action" value="modifyStation">
                        <button type="submit" >Modify Station</button>
                    </form> 
                </td>
                <td>
                    <form action="./stationList.jsp" method="get">
                        <input type="hidden" name="stationName" value="<%=station.getName()%>">
                        <input type="hidden" name="zone" value="<%= zone%>">
                        <input type="hidden" name="action" value="deleteStation">
                        <button type="submit" style="color:red;" >Delete Station</button>
                    </form> 
                </td>
            </tr>
            <%
                }
            %>
        </table> 
    </body>
</html>
