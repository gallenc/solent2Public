<%-- 
    Document   : jspexample2
    Created on : 6 Oct 2021, 10:38:30
    Author     : rgaud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.solent.oodd.webexercise1.model.User" %>


<%
    //retrieve the stored users list from the session

    List<User> users = (List<User>)session.getAttribute("users2");
    
    if(users == null){
        users = new ArrayList<User>();
        session.setAttribute("users2", users);
    }
    
    String name = request.getParameter("userName");
    String address = request.getParameter("address");
    String action = request.getParameter("action");
    
    if("removeUser".equals(action)){
        for (int idx = 0; idx < users.size(); idx++) {
            if(users.get(idx).getName().equals(name)
                    && users.get(idx).getAddress().equals(address)){
                users.remove(idx);
                break;
            }
        }
        session.setAttribute("users2", users);
    }
    else if("addUser".equals((action))){
        User user = new User();
        user.setName(name);
        user.setAddress(address);
        users.add(user);
        session.setAttribute("users2", users);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Example 2</title>
    </head>
    <body>
        <h1>JSP Example 3</h1>
        
        <h2> user list</h2>
        <table>
        <tr>
            <th>No</th>
            <th>Name</th>
            <th>Address</th>
            <th>Action</th>
        </tr>
        <% for (int idx = 0; idx < users.size(); idx++) {
                    User user = users.get(idx);
        %>
        <tr>
            <td><%=idx + 1%></td>
            <td><%=user.getName()%></td>
            <td><%=user.getAddress()%></td>
            <td>
                <form action="./jspexample2c.jsp" method="get">
                    <input type="hidden" name="userName" value="<%=user.getName()%>">
                    <input type="hidden" name="address" value="<%=user.getAddress()%>">
                    <input type="hidden" name="action" value="removeUser">
                    <button type="submit" >Remove</button>
                </form>
                <form action="./jspexample2c-modify.jsp" method="get">
                    <input type="hidden" name="userID" value="<%=idx%>">
                    <button type="submit" >Modify</button>
                </form>
            </td>
        </tr>
        <%}%>
        </table>

        
        <h2>commands</h2>
        <form action="./jspexample2c.jsp" method="post">
            <p> user name <input type="text" name="userName" value=""></p>
            <p> address <input type="text" name="address" value=""></p>
            <input type="hidden" name="action" value="addUser">
            <button type="submit"> add name to list</button>
        </form>
        <br>
        <form action="./jspexample2c.jsp" method="post">
            <p> user name <input type="text" name="userName" value=""></p>
            <p> address <input type="text" name="address" value=""></p>
            <input type="hidden" name="action" value="removeUser">
            <button type="submit"> remove name to list</button>
        </form>
    </body>
</html>
