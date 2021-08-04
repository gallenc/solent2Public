<%-- 
    Document   : error
    Created on : 3 Aug 2021, 20:22:57
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
        <h1>Error Page</h1>
        <p>Application has encountered an error.</p>
        <p>${error}</p>
        <p>${status}</p>
        <p>Failed URL: ${requestUrl}</p>
        <p>Exception:  ${exception.message}</p>
        <p>Stack trace:</p>
        <p>${strStackTrace}</p>
</main>

<jsp:include page="footer.jsp" />
