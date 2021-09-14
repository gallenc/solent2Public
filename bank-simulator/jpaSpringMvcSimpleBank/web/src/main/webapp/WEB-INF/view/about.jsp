<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
// request set in controller
//    request.setAttribute("selectedPage","about");
%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <H1>About</H1>
</main>

<p>This bank simulator allows you to create bank accounts and use a simple ReST interface to perform credit card transactions between accounts. 
The application is completely open with no logins or login security. DO NOT attempt to use any real credit card data with this application.<br>
Three header tabs allow you to navigate the app.<br>

Home provides a form where you can set up and execute a transactino between two accounts. This also allow you to execute a json transction against the ReST api<br>

Bank Accounts allows you to list and modify existing accounts and add new accounts<br>

Bank Transactions allows you to see recent transactions<br>

Clear Data allows you to remove all accounts and transaction data from the application<br>

By default this application creates two accounts. The account number and credit card associated with these accounts changes every time you reset the application <br>

</p>








<jsp:include page="footer.jsp" />
