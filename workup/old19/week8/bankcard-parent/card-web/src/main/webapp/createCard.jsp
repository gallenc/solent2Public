<%-- 
    Document   : createCard.jsp
    Created on : Nov 11, 2018, 3:25:57 PM
    Author     : cgallen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="solent.ac.uk.ood.examples.cardvalidator.model.BankApi"%>
<%@page import="solent.ac.uk.ood.examples.cardvalidator.model.CardFactoryDAO"%>
<%@page import="solent.ac.uk.ood.examples.cardvalidator.model.ServiceObjectFactory"%>
<%@page import="solent.ac.uk.ood.examples.cardvalidator.model.TransactionApi"%>
<%@page import="solent.ac.uk.ood.examples.cardvalidator.cardservice.ServiceObjectFactoryImpl"%>
<%@page import="solent.ac.uk.ood.examples.cardvalidator.cardservice.web.WebObjectFactory"%>
<%@page import="solent.ac.uk.ood.examples.cardvalidator.model.Account"%>
<%@page import="java.util.List"%>

<%
String bankProvider = (String) request.getParameter("bankProvider");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create Credit Card</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>

    <body>
        <h1>Newly Created Credit Card</h1>

        <table>
            <tr>
                <td>Card Name</td><td></td>               
            </tr>
            <tr>
                <td>Expiry Date</td><td></td>
            </tr>
            <tr>
                <td>Card Number</td><td></td>
            </tr>
            <tr>
                <td>cvv</td><td></td>
            </tr>
            <tr>
                <td>Issue Number</td><td></td>
            </tr>
        </table>
        <BR>
        <form action="accountList.jsp">
            <input type="hidden" name="bankProvider" value="<%=bankProvider%>">
            <input type="submit" value="Return to Account List">
        </form>
    </body>
</html>
