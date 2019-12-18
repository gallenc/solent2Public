<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="org.solent.com504.factoryandfacade.model.dto.Animal"%>
<%@page import="org.solent.com504.factoryandfacade.model.service.FarmFacade"%>


<%

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page Farm</title>
    </head>
    <body>
        <H1>Creating new ${animalType} </H1>
        <!-- print error message if there is one -->
        <div style="color:red;">${errorMessage}</div>

        <form action="./addAnimal" method="post">
            <input type="hidden" name="animalType" value="${animalType}">
            Enter new Animal Name:  <input type="text" name="animalName">
            <button type="submit" >Create ${animalType}</button>
        </form> 
        <BR>
        <form action="./farmhome">
            <button type="submit" >Return To animal List</button>
        </form> 

    </body>
</html>
