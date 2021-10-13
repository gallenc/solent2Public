<%-- 
    Document   : checkcard
    Created on : 28 Jul 2021, 17:13:43
    Author     : cgallen
--%>

<%@page import="solent.ac.uk.ood.examples.cardcheck.CardCompany"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="solent.ac.uk.ood.examples.cardcheck.CardValidationResult" %>
<%@ page import="solent.ac.uk.ood.examples.cardcheck.RegexCardValidator" %>

<%
    String creditcardno = request.getParameter("creditcardno");    
    String action = request.getParameter("action");
    
    Boolean isValid = false;
    CardCompany cardType = null;
    String validationString = "";
    
    if ("validate".equals(action)) {
        CardValidationResult result = RegexCardValidator.isValid(creditcardno);
        isValid = result.isValid();       
        cardType = result.getCardType();
    }
    
    if(isValid & "validate".equals(action)){
        validationString = "This card number is valid and the card type is: " + cardType.name();
    }
    else if(!isValid & "validate".equals(action)){
        validationString = "This card number is not valid";
    }
    
    // TODO CREATE LOGIC TO CHECK A CARD HERE
    // TIP - LOOK AT THE CODE IN TestRegexCardValidator.java
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Check Credit Card</h1>
   
        <form action="./checkcard.jsp" method="post">
            <label for="fname">Credit Card Number:</label><br>
            <input type="hidden" name="action" value="validate">
            <input type="text" id="creditcardno" name="creditcardno" value="<%=creditcardno%>"><br>
            <input type="submit" value="Submit">
        </form> 
        <p><%=validationString%></p> 
           

    </body>
</html>
