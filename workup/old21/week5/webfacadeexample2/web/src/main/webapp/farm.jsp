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
    FarmFacade farmFacade = (FarmFacade) WebObjectFactory.getServiceFacade();
    List<String> supportedAnimalTypes = farmFacade.getSupportedAnimalTypes();

    // accessing request parameters
    String animalNameStr = request.getParameter("animalName");
    String animalTypeStr = request.getParameter("animalType");

    // basic error checking
    if (animalNameStr != null && animalTypeStr != null) {
        if (farmFacade.getAnimal(animalNameStr) != null) {
            errorMessage = "ERROR: you cannot have dupicate animal names";
        } else {
            farmFacade.addAnimal(animalTypeStr, animalNameStr);
        }
    }

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page Farm</title>
    </head>
    <body>
        <!-- works with http://localhost:8080/basicfacadeweb/farm.jsp?animalType=Cat&animalName=Mimi -->
        <H1>Simple Page for Farm</H1>
        <!-- print error message if there is one -->
        <div style="color:red;"><%=errorMessage%></div>
 
        <p>This is basically the same page we have been using in previous examples. </p>
        <%  if (animalNameStr != null || animalTypeStr != null) {
        %>
        <p>Creating new animal type= <%=animalTypeStr%> name= <%=animalNameStr%></p>
        <% }
        %>

        <H2>Supported Animal Types</H2>
        <table>
            <% for (String animalType : supportedAnimalTypes) {%>
            <tr>
                <td><%=animalType%></td>
                <td>
                    <form action="./farm.jsp">
                        <input type="hidden" name="animalType" value="<%=animalType%>">
                        Animal Name:  <input type="text" name="animalName">
                        <button type="submit" >Create <%=animalType%></button>
                    </form> 
                </td>
            </tr>
            <%
                }
            %>
        </table> 

        <H2>Animals on Farm</H2>
        <table border="1">
           <tr>
                <th>Type</th>
                <th>Name</th>
                <th>Sound</th>
            </tr>
            <% for (Animal animal : farmFacade.getAllAnimals()) {%>
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
