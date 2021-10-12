<%-- 
    Document   : jspexample2
    Created on : 22-Jul-2021, 11:16:28
    Author     : admin
    THIS EXAMPLE SHOWS HOW TO HANDLE RESQUESTS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
// any valid java code can go between the excape sequences

// here we are looking for the userName value in the web request which called this page
    String name = request.getParameter("userName");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Example 2</title>
    </head>
    <body>
        <h1>JSP Example 2: Form Request Examples</h1>

        Hello I think your name is <%=name%>

        <!-- starting the href with ./ means you are referring relative to the root of this page -->

        <form action="./jspexample2.jsp" method="get">
            <p>user name <input type="text" name="userName" value=""></p>
            <button type="submit" >add name using GET</button>
        </form> 
        <br>
        <!-- post avoids url encoded parameters -->
        <form action="./jspexample2.jsp" method="post">
            <!-- here we are pre filling the value with the name variable -->
            <p>user name <input type="text" name="userName" value="<%=name%>"></p>
            <button type="submit" >add name using POST</button>
        </form> 

        <br>
        <a href="./" >back to index page</a>
    </body>
</html>
