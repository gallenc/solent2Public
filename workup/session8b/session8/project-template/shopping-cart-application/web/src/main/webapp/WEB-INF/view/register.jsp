<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
// request set in controller
//    request.setAttribute("selectedPage","contact");
%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <H1>Create a New Account</H1>
    <div style="color:red;">${errorMessage}</div>
    <div style="color:green;">${message}</div>


    <p>Username must be unique and password must be at least 8 characters</p>
    <form action="./register" method="POST">
        <input type="hidden" name="action" value="createNewAccount">
        <p>Username <input type="text" name="username" ></input></p><BR>
        <p>Password <input type="password" name="password" ></input></p>
        <p>Re Enter Password <input type="password" name="password2" ></input></p>
        <p><button type="submit" >Create New Account</button></p>
    </form> 

</main>


<jsp:include page="footer.jsp" />
