<%-- 
    Document   : jspexample3
    Created on : 12-Oct-2021, 12:44:57
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.solent.oodd.webexercise1.model.User" %>
<%
        List<User> users = (List<User>) session.getAttribute("usersList");
    if (users == null) {
        users = new ArrayList<User>();
        session.setAttribute("usersList", users);
    }

    String name = request.getParameter("userName");
    String address = request.getParameter("userAddress");
    String index = request.getParameter("index");

    String action = request.getParameter("action");

    if ("removeUser".equals(action)) {
        int i = Integer.parseInt(index);
        users.remove(i);
    } else if ("addUser".equals(action)) {
        User user = new User();
        user.setName(name);
        user.setAddress(address);
        users.add(user);
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Example 3d</title>
    </head>
    <body>
        <h1>Modify User table</h1>

        <table style="width:20%; border: 1px solid black;" > 
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Address</th>
                <th></th>
            </tr>
            <% for (int idx = 0; idx < users.size(); idx++) {
                    User user = users.get(idx);
            %>
            <tr>
                <td><%=idx + 1%></td>
                <td><%=user.getName()%></td>
                <td><%=user.getAddress()%></td>
                <td>
                    <form action="./jspexample3d.jsp" method="get">
                        <input type="hidden" name="index" value="<%=idx%>">
                        <input type="hidden" name="action" value="removeUser">
                        <button type="submit" >remove</button>
                    </form>
                    <form action="./jspexample3d-modify.jsp" method="get">
                        <input type="hidden" name="index" value="<%=idx%>">
                        <button type="submit" >modify</button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>

        <h2>add users</h2>
        <form action="./jspexample3d.jsp" method="get">
            <p>user name <input type="text" name="userName" value=""></p>
            <p>user address <input type="text" name="userAddress" value=""></p>
            <input type="hidden" name="action" value="addUser">
            <button type="submit" >add user to list</button>
        </form> 

        <br>
        <a href="./" >return</a>
    </body>
</html>
