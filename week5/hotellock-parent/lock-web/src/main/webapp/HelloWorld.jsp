<%@page import="java.util.Date"%>
<%@page import="com.Utils"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Title</title>
    </head>
    <body>
        this works with http://localhost:8680/HelloWorld.jsp
        <p>Hello World!</p>

        Test data    : <%=Utils.getTestData()%>

        more test data 
        <%

          
           
        %>

        <br /> Result Code = <% %>
        <br /> Transaction id = <% %>

        <br /> 
        Today        : <%=new Date()%>

    </body>
</html>
