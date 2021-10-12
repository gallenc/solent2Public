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
    <H1>Contact</H1>
</main>

<p>This project is hosted in support of the Solent University <a href="https://learn.solent.ac.uk/enrol/index.php?id=44050" target="_blank" rel="noopener noreferrer">COM523 Object Orientated Design and Development course</a>
  </p>
<p>The source code is maintained by Dr Craig Gallen in github 
<a href="https://github.com/gallenc/solent2Public/tree/master/bank-simulator" target="_blank" rel="noopener noreferrer">bank-simulator</a>
</p>
<p>Please note that this is a simulator for education purposes only and does not represent any attempt to replicate a commercial credit card gateway or gateway standard</p> 

<jsp:include page="footer.jsp" />
