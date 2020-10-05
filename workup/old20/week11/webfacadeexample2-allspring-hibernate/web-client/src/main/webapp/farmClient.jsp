<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="org.solent.com504.factoryandfacade.model.dto.Animal"%>
<%@page import="org.solent.com504.factoryandfacade.model.service.FarmFacade"%>
<%@page import="org.solent.com504.factoryandfacade.impl.webclient.WebObjectFactory"%>

<%
    // used to place error message at top of page 
    String errorMessage = "";

    // used to set html header autoload time. This automatically refreshes the page
    // Set refresh, autoload time every 20 seconds
    response.setIntHeader("Refresh", 20);

    FarmFacade farmFacade = (FarmFacade) WebObjectFactory.getServiceFacade();

    List<Animal> animals = new ArrayList();

    // if the client throws an error, we will show on page
    try {
        animals = farmFacade.getAllAnimals();
    } catch (Exception ex) {
        errorMessage = ex.getMessage();
    }

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Client Page Farm</title>
    </head>
    <body>
        <H1>Client Page for Farm</H1>
        <!-- print error message if there is one -->
        <div style="color:red;"><%=errorMessage%></div>

        <p>The refreshed time is: <%= new Date().toString()%> (note page is auto refreshed every 20 seconds)</p>

        <H2>Client received Animals on Farm</H2>
        <table border="1">
            <tr>
                <th>Type</th>
                <th>Name</th>
                <th>Sound</th>
            </tr>
            <% for (Animal animal : animals) {%>
            <tr>
                <td><%=animal.getAnimalType().getType()%></td>
                <td><%=animal.getName()%></td>
                <td><%=animal.getAnimalType().getSound()%></td>
            </tr>
            <%
                }
            %>
        </table> 

    </body>
</html>
