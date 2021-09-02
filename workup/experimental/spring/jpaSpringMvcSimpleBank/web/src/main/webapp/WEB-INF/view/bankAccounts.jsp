<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page import="solent.ac.uk.ood.examples.cardvalidator.impl.SupportedCardIssuerIdentificationNumbers"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
// request set in controller
//request.setAttribute("selectedPage", "home");
%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <H1>Bank Accounts</H1>
    <!-- print message / error message if there is one -->
    <div style="color:red;">${errorMessage}</div>
    <div style="color:green;">${message}</div>


    <form action="./bankaccountview" method="post">
        <input type="hidden" name="action" value="create">
        <input type="hidden" name="supportedIssuerBank" value="VISA_BANK_OF_IRELAND_UK">
        <button type="submit" >Create New Account</button>
    </form>


    <table class="table">

        <tr>
            <th>Sort Code</th>
            <th>Account Number</th>
            <th>Owner</th>
            <th>Balance</th>
            <th>Status</th>
        </tr>


        <c:forEach var="bankAccount" items="${bankAccounts}">
            <form action="./bankaccountview" method="post">
                <tr>
                    <td><input type="text" size="36" name="accountNo" value="${bankAccount.accountNo}" readonly ></td>
                    <td><input type="text" size="36" name="sortCode" value="${bankAccount.sortcode}" readonly ></td>
                    <td>${bankAccount.owner.firstName} ${bankAccount.owner.secondName}</td>
                    <td>${bankAccount.balance}</td>
                    <td>${bankAccount.active}</td>
                    <td>
                        <input type="hidden" name="action" value="view">
                        <button type="submit" >View Account</button>
                    </td>
                </tr>
            </form>

        </c:forEach>
    </table>

</main>
<jsp:include page="footer.jsp" />
