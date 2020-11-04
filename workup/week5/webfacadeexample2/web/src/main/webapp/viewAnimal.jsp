<%@page import="org.solent.com504.factoryandfacade.impl.web.WebObjectFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="org.solent.com504.factoryandfacade.model.dto.Animal"%>
<%@page import="org.solent.com504.factoryandfacade.model.service.FarmFacade"%>


<%
    // used to place error message at top of page 
    String errorMessage = "";
    String message = "";

    // accessing service 
    FarmFacade farmFacade = (FarmFacade) WebObjectFactory.getServiceFacade();
    List<String> supportedAnimalTypes = farmFacade.getSupportedAnimalTypes();

    // accessing request parameters
    String actionStr = request.getParameter("action");
    String animalNameStr = request.getParameter("animalName");
    String animalTypeStr = request.getParameter("animalType");

    // basic error checking before making a call
    if (actionStr == null) {
        // redirect back to home page
        response.sendRedirect("./farm2.jsp");
    } else if ("addAnimal".equals(actionStr)) {
        if (animalNameStr == null || animalNameStr.isEmpty() || animalTypeStr == null || animalTypeStr.isEmpty()) {
            errorMessage = "ERROR: animalType and animalName must both be set when adding animal.";
        } else {
            if (farmFacade.getAnimal(animalNameStr) != null) {
                errorMessage = "ERROR: you cannot have dupicate animal names (" + animalNameStr + ")";
            } else {
                farmFacade.addAnimal(animalTypeStr, animalNameStr);
                response.sendRedirect("./farm2.jsp");
            }
        }
    } else if ("createAnimal".equals(actionStr)) {
        if (animalTypeStr == null || animalTypeStr.isEmpty()) {
            errorMessage = "ERROR: animalType must be set when creating animal.";
        }
    } else {
        errorMessage = "ERROR: page called for unknown action";
    }

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page Farm</title>
    </head>
    <body>
        <H1>Creating new <%=animalTypeStr%> </H1>
        <!-- print error message if there is one -->
        <div style="color:red;"><%=errorMessage%></div>

        <form action="./viewAnimal.jsp" method="post">
            <input type="hidden" name="animalType" value="<%=animalTypeStr%>">
            <input type="hidden" name="action" value="addAnimal">
            Enter new Animal Name:  <input type="text" name="animalName">
            <button type="submit" >Create <%=animalTypeStr%></button>
        </form> 
        <BR>
        <form action="./farm2.jsp">
            <button type="submit" >Return To animal List</button>
        </form> 

    </body>
</html>
