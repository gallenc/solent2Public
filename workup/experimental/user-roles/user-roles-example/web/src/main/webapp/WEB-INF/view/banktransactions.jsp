<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
// request set in controller
//request.setAttribute("selectedPage", "home");
%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <H1>Bank / Card Transactions</H1>
    <!-- print message / error message if there is one -->
    <div style="color:red;">${errorMessage}</div>
    <div style="color:green;">${message}</div>

    <H1>Transactions</H1>
    <table class="table">

        <tr>
            <th>Time</th>
            <th>Transaction Id</th>
            <th>From Account</th>
            <th>To Account</th>
            <th>Amount</th>
        </tr>

        <c:forEach var="transaction" items="${bankTransactions}">
            <tr>
                <td>${transaction.transactionDate}</td>
                <td>${transaction.transactionId}</td>
                <td>Sort Code: ${transaction.fromAccount.sortcode} Account No: ${transaction.fromAccount.accountNo}<br>Card No: ${transaction.fromAccount.creditcard.cardnumber}</td>
                <td>Sort Code: ${transaction.toAccount.sortcode} Account No: ${transaction.toAccount.accountNo}<br>Card No: ${transaction.toAccount.creditcard.cardnumber}</td>
                <td>${transaction.amount}</td>
            </tr>
        </c:forEach>
    </table>


</main>
<jsp:include page="footer.jsp" />
