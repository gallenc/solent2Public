<%-- 
    Document   : jspexample2
    Created on : 6 Oct 2021, 10:21:30
    Author     : rgaud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    final String nameParameter = "userName";
    //Any valid java code can go between the escap sequences

    // here we are looking for the userName value in the web request which called this page
    //getParameter checks both Url parameters and POST data
    String name = request.getParameter(nameParameter);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Example 2</title>
    </head>
    <body>
        <h1>JSP Example 2: Form Request Examples</h1>
        
        Hello I think your name is <%=name %>
        
        <!-- Starting with href with ./ means you are referring to the root of this page -->
        
        <!-- get uses  url encoded parameters-->
        <form action="./jspexample2.jsp" method="get">
            <p>user name <input type="text" name="userName" value=""></p>
            <button type="submit" >add name using GET</button>
        </form>
        
        <!-- post avoids url encoded parameters-->
        <form action="./jspexample2.jsp" method="post">
            <p>user name <input type="text" name="userName" value="<%=name %>"></p>
            <button type="submit" >add name using POST</button>
        </form>
    </body>
</html>
