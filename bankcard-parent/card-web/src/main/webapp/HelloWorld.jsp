<%@page import="java.util.Date"%>
<%@page import="com.Utils"%>
<%@page import="solent.ac.uk.ood.examples.cardcheck.service.rest.AppObjectFactory"%>"
<%@page import="solent.ac.uk.ood.examples.cardcheck.model.Transaction"%>"
<%@page import="solent.ac.uk.ood.examples.cardcheck.model.TransactionResult"%>"
<%@page import="solent.ac.uk.ood.examples.cardcheck.service.ServiceObjectFactory"%>"
<%@page import="solent.ac.uk.ood.examples.cardcheck.service.TransactionApi"%>"
<%@page import="solent.ac.uk.ood.examples.cardcheck.model.Card"%>"

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Title</title>
    </head>
    <body>
        this works with http://localhost:8680/HelloWorld.jsp
        <p>Hello World!</p>

        Test data    : <%=Utils.getTestData()%>

        more test data 
        <%
            ServiceObjectFactory serviceObjectFactory = AppObjectFactory.getServiceObjectFactory();
            TransactionApi tapi = serviceObjectFactory.getTransactonApi();

            Transaction requestTransaction = new Transaction();
            Double amount = 100.1;
            requestTransaction.setAmount(amount);
            Card cardFrom = new Card();
            Card cardTo = new Card();
            requestTransaction.setCardFrom(cardFrom);
            requestTransaction.setCardTo(cardTo);

           TransactionResult tr = tapi.requestPreAuthorisation(requestTransaction);
          
           
        %>

        <br /> Result Code = <%=tr.getResultCode()%>
        <br /> Transaction id = <%=tr.getTransaction().getTransactionId()%>

        <br /> 
        Today        : <%=new Date()%>

    </body>
</html>
