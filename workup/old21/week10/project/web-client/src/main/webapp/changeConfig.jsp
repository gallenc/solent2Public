<%-- 
    Document   : changeConfig
    Created on : 16 Dec 2020, 00:39:54
    Author     : cgallen
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.solent.com528.project.impl.webclient.WebClientObjectFactory"%>
<%@page import="org.solent.com528.project.model.service.ServiceFacade"%>

<%
    // used to place error message at top of page 
    String errorMessage = "";
    String message = "";

    // accessing service 
    ServiceFacade serviceFacade = (ServiceFacade) WebClientObjectFactory.getServiceFacade();

    // accessing request parameters
    String actionStr = request.getParameter("action");
    String updateUuidStr = request.getParameter("updateUuid");

    if ("changeTicketMachineUuid".equals(actionStr)) {
        WebClientObjectFactory.setTicketMachineUuid(updateUuidStr);
    }

    String stationName = WebClientObjectFactory.getStationName();
    Integer stationZone = WebClientObjectFactory.getStationZone();
    String ticketMachineUuid = WebClientObjectFactory.getTicketMachineUuid();
    Date lastUpdate = WebClientObjectFactory.getLastClientUpdateTime();
    Date lastUpdateAttempt = WebClientObjectFactory.getLastClientUpdateAttempt();
    String lastUpdateStr = (lastUpdate==null) ? "not known" : lastUpdate.toString();
    String lastUpdateAttemptStr = (lastUpdateAttempt==null) ? "not known" : lastUpdateAttempt.toString();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Ticket Machine Configuration</title>
    </head>
    <body>

        <h1>Change Ticket Machine Configuration</h1>

        <table>
            <tr>
                <td>Last Update Attempt</td>
                <td><%=lastUpdateAttemptStr %></td>
                <td></td>
            </tr>
            <tr>
                <td>Last Successfull Update </td>
                <td><%=lastUpdateStr %></td>
                <td></td>
            </tr>
            <tr>
                <td>Station Name</td>
                <td><%=stationName%></td>
                <td></td>
            </tr>
            <tr>
                <td>Station Zone</td>
                <td><%=stationZone%></td>
                <td></td>
            </tr>
            <form action="./changeConfig.jsp" method="get">
                <tr>
                    <td>Ticket Machine Uuid</td>

                    <td><input type="text" size="36" name="updateUuid" value="<%=ticketMachineUuid%>"></td>
                    <td>
                        <input type="hidden" name="action" value="changeTicketMachineUuid">
                        <button type="submit" >Change Ticket Machine Uuid</button>
                    </td>

                </tr>
            </form>
        </table> 

    </body>
</html>
