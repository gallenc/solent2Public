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
    <H1>Home</H1>
    <!-- print message / error message if there is one -->
    <div style="color:red;">${errorMessage}</div>
    <div style="color:green;">${message}</div>

    <H1>Transfer Money Between Accounts</H1>

    <form action="./home" method="post">
        <button class="btn ml-2 rounded" type="submit" >Submit Transaction</button>
        <input type="hidden" name="action" value="transferMoneyBetweenAccounts">
        <table class="table">
            <tr>
                <td>From Account Sort Code</td>
                <td><input type="text" size="36" name="fromSortCode" value="${fromSortCode}" ></td>
                <td><a href="./bankaccounts" class="btn ml-2 rounded">Select From Account</a></td>
            </tr>
            <tr>
                <td>From Account Number</td>
                <td><input type="text" size="36" name="fromAccountNo" value="${fromAccountNo}" ></td>
            </tr>
            <tr>
                <td>To Account Sort Code</td>
                <td><input type="text" size="36" name="toSortCode" value="${toSortCode}" ></td>
                <td><a href="./bankaccounts" class="btn ml-2 rounded">Select To Account</a></td>
            </tr>
            <tr>
                <td>To Account Number</td>
                <td><input type="text" size="36" name="toAccountNo" value="${toAccountNo}" ></td>
            </tr>
            <tr>
                <td>Amount £GBP</td>
                <td><input type="text" size="36" name="amount" value="${amount}" ></td>
            </tr>
        </table>

    </form>

    <form action="./showjsonrequest" method="post">
        <button class="btn ml-2 rounded" type="submit" >Show Json Transaction Request</button>
        <input type="hidden" name="fromSortCode" value="${fromSortCode}" >
        <input type="hidden" name="fromAccountNo" value="${fromAccountNo}" >
        <input type="hidden" name="toSortCode" value="${toSortCode}" >
        <input type="hidden" name="toAccountNo" value="${toAccountNo}" >
        <input type="hidden" name="amount" value="${amount}" >
    </form>

    <c:if test="${bankTransactionResult!=null}">
        <h2>Transaction Result</h2>
        <table class="table">
            <tr>
                <td>Status</td><td>${bankTransactionResult.status}</td>
            </tr>
            <tr>
                <td>Transaction Date</td><td>${bankTransactionResult.transactionDate}</td>
            </tr>
            <tr>
                <td>Transaction Id</td><td>${bankTransactionResult.transactionId}</td>
            </tr>
            <tr>
                <td>From Account</td><td>${bankTransactionResult.fromAccount.sortcode} ${bankTransactionResult.fromAccount.accountNo} ${bankTransactionResult.fromAccount.creditcard.cardnumber}</td>
            </tr>
            <tr>
                <td>To Account</td><td>${bankTransactionResult.toAccount.sortcode} ${bankTransactionResult.toAccount.accountNo} ${bankTransactionResult.toAccount.creditcard.cardnumber}</td>
            </tr>
            <tr>
                <td>Amount</td><td>${bankTransactionResult.amount}</td>
            </tr>
            <tr>
                <td>Message</td><td>${bankTransactionResult.message}</td>
            </tr>
        </table>
    </c:if>

</main>
<jsp:include page="footer.jsp" />
