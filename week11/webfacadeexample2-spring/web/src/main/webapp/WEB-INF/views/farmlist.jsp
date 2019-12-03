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
    <!-- this style definition controlls the dropdown at the bottom of the page. It could be in a separate css file -->
    <style>

        main {
            width: 600px;
            margin: 20px auto;
            padding: 10px 0;
            box-shadow: 0 3px 5px rgba(0,0,0,0.3);
        }
        input {
            display: none;
            visibility: hidden;
        }
        label {
            display: block;
            padding: 0.5em;
            text-align: left;
            border-bottom: 1px solid #CCC;
            color: #666;
        }
        label:hover {
            color: #000;
        }
        label::before {
            font-family: Consolas, monaco, monospace;
            font-weight: bold;
            font-size: 15px;
            content: "+";
            vertical-align: text-top;
            display: inline-block;
            width: 20px;
            height: 20px;
            margin-right: 3px;
        }
        #expand {
            height: 0px;
            overflow: hidden;
            transition: height 0.5s;
        }
        section {
            padding: 0 20px;
        }
        #toggle:checked ~ #expand {
            height: 1000px;
        }
        #toggle:checked ~ label::before {
            content: "-";
        }
    </style>
    <body>
        <!-- works with http://localhost:8080/basicfacadeweb/farm2.jsp?animalType=Cat&animalName=Mimi -->
        <H1>Spring MVC page for Farm</H1>
        <!-- print error message if there is one -->
        <div style="color:red;">${errorMessage}</div>
        <div style="color:green;">${message}</div>

        <p>The time is: <%= new Date().toString()%> (note page is auto refreshed every 20 seconds)</p>


        <H2>Supported Animal Types (using JSTL)</H2>
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

        <br>

        <!-- for explanation of css see https://codepen.io/peternguyen/pen/hICga  CSS Expand/Collapse SectionA Pen By Naut Hnil  -->  
        <input id="toggle" type="checkbox" >
        <label for="toggle">Click to show detail of request variables</label>
        <div id="expand">

            <section>
                <p>This will help you understand what the JSTL tag library is doing. 
                    Here we use embedded java instead of jstl to render the supported animal types</p>

                <%
                    List<String> supportedAnimalTypes = (List<String>) request.getAttribute("supportedAnimalTypes");
                %>
                <H2>Supported Animal Types (using embedded java) </H2>
                <table>
                    <% for (String animalType : supportedAnimalTypes) {%>
                    <tr>
                        <td><%=animalType%></td>
                        <td>
                            <!-- post avoids url encoded parameters -->
                            <form action="./createAnimal" method="post">
                                <input type="hidden" name="animalType" value="<%=animalType%>">
                                <button type="submit" >Create <%=animalType%></button>
                            </form> 
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table> 
                <h3>Request variables include MVC Model parameters</h3>
                <p>This will help you understand what the Spring Model is doing. 
                    All of the Model variables are injected into the request object.</p>
                <table>
                    <tr><th>request key</th><th>request value</th></tr>
                    <tr><td></td></tr>
                    <%
                        java.util.Enumeration<String> reqEnum = request.getAttributeNames();
                        while (reqEnum.hasMoreElements()) {
                            String s = reqEnum.nextElement();
                            out.print("<tr><td>" + s + "</td>");
                            out.println("<td>" + request.getAttribute(s) + "</td></tr>");

                        }
                    %>
                </table>
            </section>
        </div>

    </body>
</html>
