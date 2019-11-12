<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="org.solent.com504.factoryandfacade.model.dto.Animal"%>
<%@page import="org.solent.com504.factoryandfacade.model.service.FarmFacade"%>
<%@page import="org.solent.com504.factoryandfacade.impl.web.WebObjectFactory"%>

<%
    // used to place error message at top of page 
    //String errorMessage = "";
    //String message = "";

    // used to set html header autoload time. This automatically refreshes the page
    // Set refresh, autoload time every 20 seconds
    response.setIntHeader("Refresh", 20);

    // accessing service 
    // FarmFacade farmFacade = (FarmFacade) WebObjectFactory.getServiceFacade();
    // List<String> supportedAnimalTypes = farmFacade.getSupportedAnimalTypes();
    // accessing request parameters
    //  String actionStr = request.getParameter("action");
    //  String animalNameStr = request.getParameter("animalName");
    //   String animalTypeStr = request.getParameter("animalType");
    // basic error checking before making a call
    //  if (actionStr == null || actionStr.isEmpty()) {
    // do nothing
    //  } else if ("deleteAnimal".equals(actionStr)) {
    //     if (animalNameStr == null || animalNameStr.isEmpty()) {
    //         errorMessage = "ERROR: animalName must be set when deleting animal.";
    //     } else {
    //         message = "Deleting animal name=" + animalNameStr;
    //         farmFacade.removeAnimal(animalNameStr);
    //     }
    // } else {
    //      errorMessage = "ERROR: page called for unknown action";
    //  }

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page Farm</title>
    </head>
    <body>
        <!-- works with http://localhost:8080/basicfacadeweb/farm2.jsp?animalType=Cat&animalName=Mimi -->
        <H1>Spring MVC page for Farm</H1>
        <!-- print error message if there is one -->
        <div style="color:red;">${errorMessage}</div>
        <div style="color:green;">${message}</div>

        <p>The time is: <%= new Date().toString()%> (note page is auto refreshed every 20 seconds)</p>
        <H2>Supported Animal Types</H2>
        <table>

            <c:forEach var="animalType" items="${supportedAnimalTypes}">
                <tr>
                    <td>${animalType}</td>
                    <td>
                        <!-- post avoids url encoded parameters -->
                        <form action="./createAnimal" method="post">
                            <input type="hidden" name="animalType" value="${animalType}">
                            <button type="submit" >Create ${animalType}</button>
                        </form> 
                    </td>
                </tr>
            </c:forEach>
        </table> 

        <H2>Animals on Farm</H2>
        <table border="1">
            <tr>
                <th>Type</th>
                <th>Name</th>
                <th>Address</th>
                <th>Sound</th>
                <th></th>
            </tr>
            <c:forEach var="animal" items="${animalsList}">
                <tr>
                    <td>${animal.animalType.type}</td>
                    <td>${animal.name}</td>
                    <td>${animal.address}</td>
                    <td>${animal.animalType.sound}</td>
                    <td>
                        <form action="./deleteAnimal" method="post">
                            <input type="hidden" name="animalName" value="${animal.name}">
                            <button type="submit" >Delete</button>
                        </form> 
                    </td>
                </tr>
            </c:forEach>
        </table> 

    </body>
</html>
