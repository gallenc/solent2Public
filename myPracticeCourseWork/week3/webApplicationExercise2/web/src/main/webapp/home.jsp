<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.solent.com504.oodd.cart.model.service.ShoppingService" %>
<%@ page import="org.solent.com504.oodd.cart.model.service.ShoppingCart" %>
<%@ page import="org.solent.com504.oodd.cart.model.dto.ShoppingItem" %>
<%@ page import="org.solent.com504.oodd.cart.web.WebObjectFactory"%>
<%
    request.setAttribute("selectedPage", "home");
    String message="";

    ShoppingService shoppingService = WebObjectFactory.getShoppingService();

    ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
    if (shoppingCart == null) {
        shoppingCart = WebObjectFactory.getNewShoppingCart();
        session.setAttribute("shoppingCart", shoppingCart);
    }

    String action = (String) request.getParameter("action");
    String itemName = (String) request.getParameter("itemName");
    String itemUuid = (String) request.getParameter("itemuuid");

    if ("addItemToCart".equals(action)) {
        message = "adding "+itemName + " to cart";
        ShoppingItem shoppingItem = shoppingService.getNewItemByName(itemName);
        message = "adding "+itemName + " to cart : "+shoppingItem;
        shoppingCart.addItemToCart(shoppingItem);
    }
    if ("removeItemFromCart".equals(action)) {
        message = "removing "+itemName + " from cart";
        shoppingCart.removeItemFromCart(itemUuid);
    } else {
        message = "action="+action;
    }

%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <H1>Home</H1>
    <p><%=message %><p>

    <!-- The .table class adds basic styling (light padding and only horizontal dividers) to a table: -->     
    <H1>Available Items</H1>
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
            <td>
                <!-- post avoids url encoded parameters -->
                <form action="./home.jsp" method="get">
                    <input type="hidden" name="itemName" value="<%=item.getName() %>">
                    <input type="hidden" name="action" value="addItemToCart">
                    <button type="submit" >Add Item</button>
                </form> 
            </td>
        </tr>
        <% }%>

    </table>

    <H1>Shopping cart</H1>
    <table class="table">

        <tr>
            <th>Item Name</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>

        <% for (ShoppingItem item : shoppingCart.getShoppingCartItems()) {%>
        <tr>
            <td><%=item.getName()%></td>
            <td><%=item.getPrice()%></td>
            <td><%=item.getQuantity()%></td>
            <td>
                <!-- post avoids url encoded parameters -->
                <form action="./home.jsp" method="get">
                    <input type="hidden" name="itemUUID" value="<%=item.getUuid()%>">
                    <input type="hidden" name="action" value="removeItemFromCart">
                    <button type="submit" >Remove Item</button>
                </form> 
            </td>
        </tr>
        <% }%>

    </table>



</main>
<jsp:include page="footer.jsp" />
