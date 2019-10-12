#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%-- 
    Document   : AddOrModifyEntity
    Created on : Nov 30, 2018, 11:17:38 PM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="${package}.web.WebObjectFactory"%>
<%@page import="${package}.model.ServiceFactory"%>
<%@page import="${package}.model.ServiceFacade"%>
<%@page import="${package}.model.Entity"%>


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

    Entity entity = null;
    Integer entityId = null;

    if ("modifyEntity".equals(action)) {
        try {
            entityId = Integer.parseInt(entityIdReq);
            entity = serviceFacade.retrieveEntity(entityId);
        } catch (Exception e) {
            errorMessage = "problem finding entity " + e.getMessage();
        }
    } else if ("createEntity".equals(action)) {
        try {
            entity = new Entity();
        } catch (Exception e) {
            errorMessage = "problem finding entity " + e.getMessage();
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
        <title>Edit Entity</title>
    </head>
    <body>
        <% if ("createEntity".equals(action)) {
        %>
        <h1>Add New Entity</h1>
        <% } else {%>
        <h1>Modify Entity <%=entityId%></h1>
        <% }%>
        <form action="ListEntities.jsp">
            <table>
                <tr>
                    <th>Field</th>
                    <th>Current Value</th>
                    <th>New Value</th>
                </tr>
                <tr>
                    <td>Entity Id</td>
                    <td><%=entity.getId()%></td>
                    <td></td>
                </tr>
                <tr>
                    <td>field_A</td>
                    <td><%=entity.getField_A()%></td>
                    <td><input type="text" name="field_A" value ="<%=entity.getField_A()%>"></td>
                </tr>
                <tr>
                    <td>field_B</td>
                    <td><%=entity.getField_B()%></td>
                    <td><input type="text" name="field_B" value ="<%=entity.getField_B()%>"></td>
                </tr>
                <tr>
                    <td>field_C</td>
                    <td><%=entity.getField_C()%></td>
                    <td><input type="text" name="field_C" value ="<%=entity.getField_C()%>"></td>
                </tr>
            </table> 
            <BR>
            <% if ("createEntity".equals(action)) {
            %>
            <input type="hidden" name="action" value="createEntity">
            <input type="hidden" name="entityId" value="<%=entityId%>">
            <input type="submit" value="Create New Entity">
            <% } else if ("modifyEntity".equals(action)) {
            %>
            <input type="hidden" name="action" value="modifyEntity">
            <input type="hidden" name="entityId" value="<%=entityId%>">
            <input type="submit" value="Modify Entity">
            <% }%>
        </form>
        <form action="ListEntities.jsp">
            <input type="submit" value="Cancel and Return">
        </form>
    </body>
</html>
