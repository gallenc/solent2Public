<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
    </head>
    <body>
        <p>${message}<p>

        <H1>Available Items</H1>
        <table>

            <tr>
                <th>Item Name</th>
                <th>Price</th>
                <th></th>
            </tr>

            <c:forEach var="item" items="${availableItems}">

                <tr>
                    <td>${item.name}</td>
                    <td>${item.price}</td>
                    <td></td>
                    <td>
                        <!-- post avoids url encoded parameters -->
                        <form action="./home" method="get">
                            <input type="hidden" name="itemName" value="${item.name}">
                            <input type="hidden" name="action" value="addItemToCart">
                            <button type="submit" >Add Item</button>
                        </form> 
                    </td>
                </tr>

            </c:forEach>
        </table>

        <H1>Shopping cart</H1>
        <table>

            <tr>
                <th>Item Name</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>

            <c:forEach var="item" items="${shoppingCartItems}">

                <tr>
                    <td>${item.name}</td>
                    <td>${item.price}</td>
                    <td>${item.quantity}</td>
                    <td>
                        <!-- post avoids url encoded parameters -->
                        <form action="./home" method="post">
                            <input type="hidden" name="itemUUID" value="${item.uuid}">
                            <input type="hidden" name="itemName" value="${item.name}">
                            <input type="hidden" name="action" value="removeItemFromCart">
                            <button type="submit" >Remove Item</button>
                        </form> 
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td>TOTAL</td>
                <td>${shoppingcartTotal}</td>
            </tr>
        </table>

    </body>
</html>