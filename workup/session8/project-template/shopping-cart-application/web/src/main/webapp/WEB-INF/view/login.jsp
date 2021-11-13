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
    <H1>Login</H1>
    <form action="./login" method="post">
        <input type="hidden" name="action" value="login">
        <p>Username <input type="text" name="username" ></input></p><BR>
        <p>Password <input type="text" name="password" ></input></p>
        <p><button type="submit" >Log In</button></p>
    </form> 
    <H1>Create a New Account</H1>
    <form action="./login" method="post">
        <input type="hidden" name="action" value="createNewAccount">
        <p>Username <input type="text" name="username" ></input></p><BR>
        <p>Password <input type="text" name="password" ></input></p>
        <p><button type="submit" >Create New Account</button></p>
    </form> 

</main>


<jsp:include page="footer.jsp" />
