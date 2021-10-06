<%-- 
    Document   : jspexample2c-modify
    Created on : 6 Oct 2021, 11:22:22
    Author     : rgaud
--%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.solent.oodd.webexercise1.model.User" %>
<%
    String userIDString = request.getParameter("userID");
    int userID = Integer.parseInt(request.getParameter("userID"));
    String address = request.getParameter("newAddress"); 
    String name = request.getParameter("newName"); 
    
    
    String action = request.getParameter("action");
    List<User> users = (List<User>)session.getAttribute("users2");
    if(users == null){
        users = new ArrayList<User>();
        session.setAttribute("users2", users);
    }
    User userToModify = users.get(userID);
    
    
    if("modify".equals((action))){
        users.get(userID).setAddress(address);
        users.get(userID).setName(name);
        
        //session.setAttribute("users2", users);
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify User</title>
    </head>
    <body>
        <h1>Modify User</h1>
        
        <form action="./jspexample2c-modify.jsp" method="post">
            <p> user name <input type="text" name="newName" value="<%= userToModify.getName()%>"></p>
            <p> address <input type="text" name="newAddress" value="<%= userToModify.getAddress()%>"></p>
            <input type="hidden" name="action" value="modify">            
            <input type="hidden" name="userID" value="<%=userID%>">

            <button type="submit"> Modify User</button>
        </form>
            
        <form action="./jspexample2c.jsp" method="get">
            <button type="submit">Back to List</button>
        </form>
    </body>
</html>
