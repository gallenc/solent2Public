<%-- 
    Document   : AddOrModifyThing
    Created on : Nov 30, 2018, 11:17:38 PM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="solent.ac.uk.ood.examples.web.WebObjectFactory"%>
<%@page import="solent.ac.uk.ood.examples.model.ServiceFactory"%>
<%@page import="solent.ac.uk.ood.examples.model.ServiceFacade"%>
<%@page import="solent.ac.uk.ood.examples.model.Thing"%>


<%

    ServiceFacade serviceFacade = (ServiceFacade) session.getAttribute("serviceFacade");

    // If the user session has no bankApi, create a new one
    if (serviceFacade == null) {
        ServiceFactory serviceFactory = WebObjectFactory.getServiceFactory();
        serviceFacade = serviceFactory.getServiceFacade();
        session.setAttribute("ServiceFacade", serviceFacade);
    }

    // get request values
    String action = (String) request.getParameter("action");
    String thingIdReq = (String) request.getParameter("thingId");
    String thingField_AReq = (String) request.getParameter("field_A");
    String thingField_BReq = (String) request.getParameter("field_B");
    String thingField_CReq = (String) request.getParameter("field_C");

    String errorMessage = "";

    Thing thing = null;
    Integer thingId = null;

    if ("modifyThing".equals(action)) {
        try {
            thingId = Integer.parseInt(thingIdReq);
            thing = serviceFacade.retrieveThing(thingId);
        } catch (Exception e) {
            errorMessage = "problem finding thing " + e.getMessage();
        }
    } else if ("createThing".equals(action)) {
        try {
            thing = new Thing();
        } catch (Exception e) {
            errorMessage = "problem finding thing " + e.getMessage();
        }
    } else {
        errorMessage = "cannot recognise action: " + action;
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Edit Thing</title>
    </head>
    <body>
        <% if ("createThing".equals(action)) {
        %>
        <h1>Add New Thing</h1>
        <% } else {%>
        <h1>Modify Thing <%=thingId%></h1>
        <% }%>
        <form action="ListThings.jsp">
            <table>
                <tr>
                    <th>Field</th>
                    <th>Current Value</th>
                    <th>New Value</th>
                </tr>
                <tr>
                    <td>Thing Id</td>
                    <td><%=thing.getId()%></td>
                    <td></td>
                </tr>
                <tr>
                    <td>field_A</td>
                    <td><%=thing.getField_A()%></td>
                    <td><input type="text" name="field_A" value ="<%=thing.getField_A()%>"></td>
                </tr>
                <tr>
                    <td>field_B</td>
                    <td><%=thing.getField_B()%></td>
                    <td><input type="text" name="field_B" value ="<%=thing.getField_B()%>"></td>
                </tr>
                <tr>
                    <td>field_C</td>
                    <td><%=thing.getField_C()%></td>
                    <td><input type="text" name="field_C" value ="<%=thing.getField_C()%>"></td>
                </tr>
            </table> 
            <BR>
            <% if ("createThing".equals(action)) {
            %>
            <input type="hidden" name="action" value="createThing">
            <input type="hidden" name="thingId" value="<%=thingId%>">
            <input type="submit" value="Create New Thing">
            <% } else if ("modifyThing".equals(action)) {
            %>
            <input type="hidden" name="action" value="modifyThing">
            <input type="hidden" name="thingId" value="<%=thingId%>">
            <input type="submit" value="Modify Thing">
            <% }%>
        </form>
        <form action="ListThings.jsp">
            <input type="submit" value="Cancel and Return">
        </form>
    </body>
</html>
