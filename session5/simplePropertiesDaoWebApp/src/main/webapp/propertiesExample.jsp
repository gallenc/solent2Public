<%-- 
    Document   : propertiesExampleJsp
    Created on : 27 Oct 2021, 01:41:23
    Author     : cgallen
--%>

<%@page import="org.solent.ood.simplepropertiesdaowebapp.dao.WebObjectFactory"%>
<%@page import="org.solent.ood.simplepropertiesdaowebapp.dao.PropertiesDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    PropertiesDao propertiesDao = WebObjectFactory.getPropertiesDao();

    String url = propertiesDao.getProperty("org.solent.ood.simplepropertiesdaowebapp.url");
    String username = propertiesDao.getProperty("org.solent.ood.simplepropertiesdaowebapp.username");
    String password = propertiesDao.getProperty("org.solent.ood.simplepropertiesdaowebapp.password");
    String message = "";
    
    String action = (String) request.getParameter("action");
    if ("updateProperties".equals(action)) {
        message = "updating properties";
        url = (String) request.getParameter("url");
        username = (String) request.getParameter("username");
        password = (String) request.getParameter("password");

        propertiesDao.setProperty("org.solent.ood.simplepropertiesdaowebapp.url", url);
        propertiesDao.setProperty("org.solent.ood.simplepropertiesdaowebapp.username", username);
        propertiesDao.setProperty("org.solent.ood.simplepropertiesdaowebapp.password", password);

    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Properties Example Jsp</title>
    </head>
    <body>
        <h1>Properties Example Jsp</h1>
        <p><%=message %></p>
        <form action="./propertiesExample.jsp" method="POST">
            <p>URL Property <input type="text" name="url" value="<%=url%>"></p>
            <p>Username Property <input type="text" name="username" value="<%=username%>"></p>
            <p>Password Property <input type="text" name="password" value="<%=password%>"></p>
            <input type="hidden" name="action" value="updateProperties">

            <button class="btn" type="submit" >Update Properties</button>
        </form> 
    </body>
</html>
