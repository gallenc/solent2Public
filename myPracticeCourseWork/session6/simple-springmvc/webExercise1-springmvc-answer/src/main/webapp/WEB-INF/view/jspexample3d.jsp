<%-- 
    Document   : jspexample2
    Created on : 22-Jul-2021, 11:16:28
    Author     : admin
    THIS EXAMPLE USES USER OBJECT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
// removed java code
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Example 3d</title>
    </head>
    <body>
        <h1>JSP Example 3d: Modify User table</h1>

        <table style="width:20%; border: 1px solid black;" > 
            <tr>
                <th>No</th>
                <th>Name</th>
                <th>Address</th>
                <th></th>
            </tr>
            <c:forEach items="${users}"  var="user" varStatus="status" >

                <tr>
                    <td>${status.count}</td>
                    <td>${user.name}</td>
                    <td>${user.address}</td>
                    <td>
                        <form action="./userlist" method="get">
                            <input type="hidden" name="index" value="${status.index}">
                            <input type="hidden" name="action" value="removeUser">
                            <button type="submit" >remove</button>
                        </form>
                        <form action="./userlist-modify" method="get">
                            <input type="hidden" name="index" value="${status.index}">
                            <button type="submit" >modify</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <h2>add users</h2>
        <form action="./userlist" method="get">
            <p>user name <input type="text" name="userName" value=""></p>
            <p>user address <input type="text" name="userAddress" value=""></p>
            <input type="hidden" name="action" value="addUser">
            <button type="submit" >add user to list</button>
        </form> 

        <br>
        <a href="./" >back to index page</a>
    </body>
</html>
