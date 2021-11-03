<%-- 
    Document   : newjsp
    Created on : 3 Aug 2021, 13:11:36
    Author     : cgallen
--%>

<%@page import="org.solent.com504.oodd.cart.model.service.Invoice"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.solent.com504.oodd.cart.model.service.ShoppingService" %>
<%@ page import="org.solent.com504.oodd.cart.model.service.ShoppingCart" %>
<%@ page import="org.solent.com504.oodd.cart.model.dto.ShoppingItem" %>
<%@ page import="org.solent.com504.oodd.cart.web.WebObjectFactory"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <!-- Bootstrap CSS -->
        

        <jsp:include page="header.jsp" />
        
        <p>${message}<p>

            <!-- The .table class adds basic styling (light padding and only horizontal dividers) to a table: -->     
        <H1>Available Items</H1>
        <table class="table">

            <tr>
                <th>Item Name</th>
                <th>Price</th>                
                <th></th>
            </tr>
            <c:forEach items="${items}"  var="item" varStatus="status" >
                <tr>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td></td>
                <td>
                    <!-- post avoids url encoded parameters -->
                    <form action="./home" method="get">
                        <input type="hidden" name="itemName" value="${item.name}">
                        <input type="hidden" name="action" value="addItemToCart">
                        <input type="hidden" name="itemQuantity" value="1">
                        <button type="submit" >Add Item</button>
                    </form> 
                </td>
            </tr>
            </c:forEach>

        </table>

        <H1>Shopping cart</H1>
        <table class="table">

            <tr>
                <th>Item Name</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
            
            <c:forEach items="${cartItems}"  var="item" varStatus="status" >
            <tr>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td>${item.quantity}</td>
                <td>
                    <!-- post avoids url encoded parameters -->
                    <form action="./home" method="get">
                        <input type="hidden" name="itemUUID" value="${item.uuid}">
                        <input type="hidden" name="action" value="removeItemFromCart">
                        <button type="submit" >Remove Item</button>
                    </form> 
                </td>
            </tr>
            </c:forEach>
            
            
            <tr>
                <th>Total</th>
                <th>${totalValue}</th>
                <th></th>
            </tr>

        </table>
        <!-- post avoids url encoded parameters -->
        <form action="./home" method="get">
            <input type="hidden" name="action" value="purchaseItems">
            <button type="submit" >Purchase Items</button>
        </form> 
        
        <jsp:include page="footer.jsp" />
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  </body>
    </body>
</html>
