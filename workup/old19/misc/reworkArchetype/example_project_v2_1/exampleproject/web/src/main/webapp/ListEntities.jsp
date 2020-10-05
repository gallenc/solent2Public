<%-- 
    Document   : ListEntities
    Created on : Nov 30, 2018, 11:17:02 PM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="solent.ac.uk.ood.examples.web.WebObjectFactory"%>
<%@page import="solent.ac.uk.ood.examples.model.ServiceFactory"%>
<%@page import="solent.ac.uk.ood.examples.model.ServiceFacade"%>
<%@page import="solent.ac.uk.ood.examples.model.Entity"%>

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
    String entityIdReq = (String) request.getParameter("entityId");
    String entityField_AReq = (String) request.getParameter("field_A");
    String entityField_BReq = (String) request.getParameter("field_B");
    String entityField_CReq = (String) request.getParameter("field_C");

    String errorMessage = "";
    if ("deleteEntity".equals(action)) {
        try {
            Integer entityId = Integer.parseInt(entityIdReq);
            serviceFacade.deleteEntity(entityId);
        } catch (Exception e) {
            errorMessage = "problem deleting Entity " + e.getMessage();
        }
    } else if ("modifyEntity".equals(action)) {
        try {
            Integer entityId = Integer.parseInt(entityIdReq);
            Entity entityTemplate = new Entity();
            entityTemplate.setId(entityId);
            entityTemplate.setField_A(entityField_AReq);
            entityTemplate.setField_B(entityField_BReq);
            entityTemplate.setField_C(entityField_CReq);
            Entity entity = serviceFacade.updateEntity(entityTemplate);
            if (entity == null) {
                errorMessage = "problem modifying Entity. could not find entityId " + entityId;
            }
        } catch (Exception e) {
            errorMessage = "problem modifying Entity " + e.getMessage();
        }
    } else if ("createEntity".equals(action)) {
        try {
            Entity entityTemplate = new Entity();
            entityTemplate.setField_A(entityField_AReq);
            entityTemplate.setField_B(entityField_BReq);
            entityTemplate.setField_C(entityField_CReq);
            Entity entity = serviceFacade.createEntity(entityTemplate);
            if (entity == null) {
                errorMessage = "problem creating Entity. Service returned null ";
            }
        } catch (Exception e) {
            errorMessage = "problem creating  Entity " + e.getMessage();
        }
    } 

    List<Entity> entityList = serviceFacade.retrieveAllEntities();

%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Entity List</title>
    </head>
    <body>
        <!-- print error message if there is one -->
        <div style="color:red;"><%=errorMessage%></div>
        <h1>Entity List</h1>
        <table>
            <tr>
                <th>id</th>
                <th>field_A</th>
                <th>field_B</th>
                <th>field_C</th>
                <th></th>
            </tr>
            <%  for (Entity entity : entityList) {
            %>
            <tr>
                <td><%=entity.getId()%></td>
                <td><%=entity.getField_A()%></td>
                <td><%=entity.getField_B()%></td>
                <td><%=entity.getField_C()%></td>
                <td>
                    <form action="AddOrModifyEntity.jsp">
                        <input type="hidden" name="action" value="modifyEntity">
                        <input type="hidden" name="entityId" value="<%=entity.getId()%>">
                        <input type="submit" value="Modify Entity">
                    </form>
                    <form action="ListEntities.jsp">
                        <input type="hidden" name="action" value="deleteEntity">
                        <input type="hidden" name="entityId" value="<%=entity.getId()%>">
                        <input type="submit" value="Delete Entity">
                    </form>
                </td>
            </tr>
            <% }%>

        </table> 
        <BR>
        <form action="AddOrModifyEntity.jsp">
            <input type="hidden" name="action" value="createEntity">
            <input type="submit" value="Create Entity">
        </form>
    </body>
</html>
