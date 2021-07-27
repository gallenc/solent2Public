<%-- 
    Document   : jspexample2
    Created on : 22-Jul-2021, 11:16:28
    Author     : admin
    THIS EXAMPLE SHOWS HOW OBJECTS CAN BE STORED IN THE SESSION OBJECT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    // retreive the dsdtored users list from the session
    List<String> users = (List<String>) session.getAttribute("users");
    if (users == null) {
        users = new ArrayList<String>();
        session.setAttribute("users", users);
    }

    String name = request.getParameter("userName");

    // find what action to perform on the page
    String action = request.getParameter("action");

    if ("removeUser".equals(action)) {
        users.remove(name);
    } else if ("addUser".equals(action)) {
        users.add(name);
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Example 3b</title>
    </head>
    <body>
        <h1>JSP Example 3b: User table</h1>

        <table style="width:20%; border: 1px solid black;" > 
            <tr>
                <th>Name</th>
                <th></th>
            </tr>
            <% for (String user : users) {%>
            <tr>
                <td><%=user%></td>
                <td>
                    <form action="./jspexample3b.jsp" method="get">
                        <input type="hidden" name="userName" value="<%=user%>">
                        <input type="hidden" name="action" value="removeUser">
                        <button type="submit" >remove</button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>

        <h2>add users</h2>
        <form action="./jspexample3b.jsp" method="get">
            <p>user name <input type="text" name="userName" value=""></p>
            <input type="hidden" name="action" value="addUser">
            <button type="submit" >add name to list</button>
        </form>
        
        <br>
        <a href="./" >back to index page</a>
    </body>
</html>
