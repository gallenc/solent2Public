<%@page import="java.util.UUID"%>
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
<%@page import="org.solent.com528.project.model.dao.TicketMachineDAO"%>
<%@page import="org.solent.com528.project.model.dto.TicketMachine"%>


<%
    // used to place error message at top of page 
    String errorMessage = "";
    String message = "";

    // used to set html header autoload time. This automatically refreshes the page
    // Set refresh, autoload time every 20 seconds
    // response.setIntHeader("Refresh", 20);
    // accessing service 
    ServiceFacade serviceFacade = (ServiceFacade) WebObjectFactory.getServiceFacade();
    StationDAO stationDAO = serviceFacade.getStationDAO();
    TicketMachineDAO ticketMachineDAO = serviceFacade.getTicketMachineDAO();

    // accessing request parameters
    String actionStr = request.getParameter("action");
    String zoneStr = request.getParameter("zone");
    String stationName = request.getParameter("stationName");
    String updateStationName = request.getParameter("updateStationName");
    String updateZone = request.getParameter("updateZone");
    String ticketMachineUuid = request.getParameter("ticketMachineUuid");

    Station station = null;

    List<TicketMachine> ticketMachineList = ticketMachineDAO.findByStationName(stationName);

    // check operations
    if ("createStation".equals(actionStr)) {
        stationName = UUID.randomUUID().toString();
        // new station has a uuid
        station = new Station();
        station.setName(stationName);
        // zone zero stations are new stations not assigned to a zone
        station.setZone(0);
        station = stationDAO.save(station);
        message = "new station " + station.getName() + " created. Please update station name.";

    } else if ("modifyStation".equals(actionStr)) {
        if (stationName == null) {
            errorMessage = "station name must be a parameter for modifyStation";
        } else {
            station = stationDAO.findByName(stationName);
            if (station == null) {
                errorMessage = "cannot find station with name: " + stationName;
            }
        }

    } else if ("updateStationName".equals(actionStr)) {
        if (stationName == null) {
            errorMessage = "stationName must be a parameter for updateStationName";
        } else {
            station = stationDAO.findByName(stationName);
            if (updateStationName == null || updateStationName.isEmpty()) {
                errorMessage = "cannot update station with empty station name";
            } else {

                if (station == null) {
                    errorMessage = "Cannot update station. Cannot find station with station name :  " + stationName;
                } else if (stationDAO.findByName(updateStationName) != null) {
                    errorMessage = "Cannot update station " + stationName + " because name already exists:  " + updateStationName;
                } else {
                    station.setName(updateStationName);
                    station = stationDAO.save(station);
                    message = "station name updated FROM: " + stationName + "    TO: " + updateStationName;
                }
            }
        }

    } else if ("updateStationZone".equals(actionStr)) {
        if (stationName == null || stationName.isEmpty()) {
            errorMessage = "cannot update station zone with empty station name";
        } else {
            station = stationDAO.findByName(stationName);
            if (station == null) {
                errorMessage = "Cannot update station zone. Station name does not exist:  " + stationName;
            } else {
                try {
                    Integer updateZoneInt = Integer.parseInt(updateZone);
                    station.setZone(updateZoneInt);
                    station = stationDAO.save(station);
                    message = "Station " + stationName + " zone updated to Zone " + updateZone;
                } catch (NullPointerException | NumberFormatException ex) {
                    errorMessage = "Cannot update station. Cannot parse zone:  " + updateZone;
                }

            }
        }

    } else if ("removeTicketMachine".equals(actionStr)) {
        if (stationName == null) {
            errorMessage = "stationName must be a parameter for removeTicketMachine";
        } else {
            station = stationDAO.findByName(stationName);
            if (ticketMachineUuid == null || ticketMachineUuid.isEmpty()) {
                errorMessage = "cannot remove ticket machine. uuid is empty  " + ticketMachineUuid;
            } else {
                TicketMachine ticketMachine = ticketMachineDAO.findByUuid(ticketMachineUuid);
                if (ticketMachine == null) {
                    errorMessage = "cannot remove ticket machine. uuid does not exist  " + ticketMachineUuid;
                } else {
                    // this removes station from ticketMachine
                    ticketMachine.setStation(null);
                    ticketMachineDAO.save(ticketMachine);
                }
            }
        }
    } else if ("addTicketMachine".equals(actionStr)) {
        if (stationName == null) {
            errorMessage = "stationName must be a parameter for removeTicketMachine";
        } else {
            station = stationDAO.findByName(stationName);
            // this removes station from ticketMachine
            // TODO  - do stuff to add ticket machine
            errorMessage = "add ticket machine not implemented";
        }

    } else {
        errorMessage = "ERROR: page called for unknown action you must have an action query perameter";
    }

    // just to make sure JSP has a station to render
    if (station == null) {
        station = new Station();
    }

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Station List</title>
    </head>
    <body>

        <H1>Station <%=stationName%></H1>
        <!-- print error message if there is one -->
        <div style="color:red;"><%=errorMessage%></div>
        <div style="color:green;"><%=message%></div>

        <br>
        <form action="./stationList.jsp" method="get">
            <button type="submit" >Return to Station List</button>
        </form> 
        <br>
        <!-- if you used method post the url parameters would be hidden -->
        <form action="./station.jsp" method="get">
            <p>Station Name: <input type="text" size="36" name="updateStationName" value="<%=station.getName()%>">
                <input type="hidden" name="stationName" value="<%=station.getName()%>">
                <input type="hidden" name="action" value="updateStationName">
                <button type="submit" >Update Station Name</button>
            </p>
        </form>
        <form action="./station.jsp" method="get">
            <p>Zone:       <input type="text" size="2" name="updateZone" value="<%=station.getZone()%>">
                <input type="hidden" name="stationName" value="<%=station.getName()%>">
                <input type="hidden" name="action" value="updateStationZone">
                <button type="submit" >Update Station zone</button>
            </p>
        </form> 

        <%
            for (TicketMachine ticketMachine : ticketMachineList) {
        %>
        <%= ticketMachine.getUuid()%>
        <form action="./station.jsp" method="get">
            <input type="text" size="36" name="ticketMachineUuid" value="<%= ticketMachine.getUuid()%> readonly ">
            <input type="hidden" name="stationName" value="<%=station.getName()%>">
            <input type="hidden" name="action" value="removeTicketMachine">
            <button type="submit" >remove ticket machine from station</button>
        </form> 
        <%
            }
        %>
        <br>
        <form action="./station.jsp" method="get">
            <input type="hidden" name="action" value="addTicketMachine">
            <input type="hidden" name="stationName" value="<%=station.getName()%>">
            <button type="submit" >add ticket machine to station</button>
        </form> 

    </body>
</html>
