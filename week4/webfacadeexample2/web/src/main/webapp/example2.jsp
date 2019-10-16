<%@page import="org.solent.com504.factoryandfacade.impl.web.WebObjectFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="org.solent.com504.factoryandfacade.model.dto.Animal"%>
<%@page import="org.solent.com504.factoryandfacade.model.service.FarmFacade"%>


<%

    FarmFacade farmFacade = (FarmFacade) WebObjectFactory.getServiceFacade();
    List<String> supportedAnimalTypes = farmFacade.getSupportedAnimalTypes();
   

    // accessing request parameters
    String animalNameStr = request.getParameter("animalName");
    String animalTypeStr = request.getParameter("animalType");

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page Farm</title>
    </head>

    <!-- works with http://localhost:8080/basicfacadeweb/example2.jsp?animalType=emue&animalName=Fred -->
    <%        if (animalNameStr != null || animalTypeStr != null) {

    %>
    <p>create animal type= <%=animalTypeStr %> name= <%=animalNameStr %></p>

    <%}
    %>

    <body>
        <p>Page for Farm</p>
        <p>Supported Animal Types</p>
        <table>
            <% for (String animalType : supportedAnimalTypes) {%>
            <tr>
                <td><%=animalType%></td>
            </tr>
            <%
                }
            %>
        </table> 

        <p>Animals on Farm</p>
        <table>
            <tr>
                <th>Type</th>
                <th>Name</th>
                <th>Sound</th>
            </tr>
            <% for (Animal animal : farmFacade.getAllAnimals()) {%>
            <tr>
                <td><%=animal.getAnimalType().getSound() %></td>
                <td><%=animal.getAnimalType().getType() %></td>
                <td><%=animal.getName()%></td>
            </tr>
            <%
                }
            %>
        </table> 

    </body>
</html>
