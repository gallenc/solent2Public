<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var = "selectedPage" value = "" scope="request" />
<!-- start of about.jsp selectedPage=${selectedPage}-->
<jsp:include page="header.jsp" />

<!-- Begin page content -->
<main role="main" class="container">
    <div class="jumbotron">
        <a href="./home">
            <p>You do not have permission to to access this resource.</p>
            <p>Click to return to home page.</p>
        </a>
    </div>
</main>

<jsp:include page="footer.jsp" />
