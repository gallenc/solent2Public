<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.solent.com504.oodd.week2.model.ShoppingService" %>
<%@ page import="org.solent.com504.oodd.week2.model.ShoppingCart" %>
<%@ page import="org.solent.com504.oodd.week2.model.ShoppingItem" %>
<%@page import="org.solent.com504.oodd.week2.web.WebObjectFactory"%>
<%
    request.setAttribute("selectedPage", "home");

    ShoppingService shoppingService = WebObjectFactory.getShoppingService();

    ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
    if (shoppingCart == null) {
        shoppingCart = WebObjectFactory.getNewShoppingCart();
        session.setAttribute("shoppingCart", shoppingCart);
    }

%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <H1>Home</H1>

    <!-- The .table class adds basic styling (light padding and only horizontal dividers) to a table: -->     
    <table class="table">

        <tr>
            <th>Item Name</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>

        <% for (ShoppingItem item : shoppingService.getAvailableItems()) {%>
        <tr>
            <td><%=item.getName()%></td>
            <td><%=item.getPrice()%></td>
            <td><%=item.getQuantity()%></td>
        </tr>
        <% }%>

    </table>
</main>
<jsp:include page="footer.jsp" />
