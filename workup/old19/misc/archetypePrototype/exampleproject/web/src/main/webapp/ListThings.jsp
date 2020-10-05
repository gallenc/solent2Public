<%-- 
    Document   : ListThings
    Created on : Nov 30, 2018, 11:17:02 PM
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
    if ("deleteThing".equals(action)) {
        try {
            Integer thingId = Integer.parseInt(thingIdReq);
            serviceFacade.deleteThing(thingId);
        } catch (Exception e) {
            errorMessage = "problem deleting Thing " + e.getMessage();
        }
    } else if ("modifyThing".equals(action)) {
        try {
            Integer thingId = Integer.parseInt(thingIdReq);
            Thing thingTemplate = new Thing();
            thingTemplate.setId(thingId);
            thingTemplate.setField_A(thingField_AReq);
            thingTemplate.setField_B(thingField_BReq);
            thingTemplate.setField_C(thingField_CReq);
            Thing thing = serviceFacade.updateThing(thingTemplate);
            if (thing == null) {
                errorMessage = "problem modifying Thing. could not find thingId " + thingId;
            }
        } catch (Exception e) {
            errorMessage = "problem modifying Thing " + e.getMessage();
        }
    } else if ("createThing".equals(action)) {
        try {
            Thing thingTemplate = new Thing();
            thingTemplate.setField_A(thingField_AReq);
            thingTemplate.setField_B(thingField_BReq);
            thingTemplate.setField_C(thingField_CReq);
            Thing thing = serviceFacade.createThing(thingTemplate);
            if (thing == null) {
                errorMessage = "problem creating Thing. Service returned null ";
            }
        } catch (Exception e) {
            errorMessage = "problem creating  Thing " + e.getMessage();
        }
    } 

    List<Thing> thingList = serviceFacade.retrieveAllThings();

%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Thing List</title>
    </head>
    <body>
        <!-- print error message if there is one -->
        <div style="color:red;"><%=errorMessage%></div>
        <h1>Thing List</h1>
        <table>
            <tr>
                <th>id</th>
                <th>field_A</th>
                <th>field_B</th>
                <th>field_C</th>
                <th></th>
            </tr>
            <%  for (Thing thing : thingList) {
            %>
            <tr>
                <td><%=thing.getId()%></td>
                <td><%=thing.getField_A()%></td>
                <td><%=thing.getField_B()%></td>
                <td><%=thing.getField_C()%></td>
                <td>
                    <form action="AddOrModifyThing.jsp">
                        <input type="hidden" name="action" value="modifyThing">
                        <input type="hidden" name="thingId" value="<%=thing.getId()%>">
                        <input type="submit" value="Modify Thing">
                    </form>
                    <form action="ListThings.jsp">
                        <input type="hidden" name="action" value="deleteThing">
                        <input type="hidden" name="thingId" value="<%=thing.getId()%>">
                        <input type="submit" value="Delete Thing">
                    </form>
                </td>
            </tr>
            <% }%>

        </table> 
        <BR>
        <form action="AddOrModifyThing.jsp">
            <input type="hidden" name="action" value="createThing">
            <input type="submit" value="Create Thing">
        </form>
    </body>
</html>
