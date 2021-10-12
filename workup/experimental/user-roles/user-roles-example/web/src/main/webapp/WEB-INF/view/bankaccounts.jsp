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
    <div class="btn-group">
        <form action="./bankaccountview" method="post">
            <input type="hidden" name="action" value="create">
            <input type="hidden" name="supportedIssuerBank" value="VISA_BANK_OF_IRELAND_UK">
            First Name <input type="text" size="36" name="firstName" value="" >&nbsp;Second Name <input type="text" size="36" name="secondName" value="" >
            <button class="btn ml-2 rounded" type="submit" >Create New Account</button>
        </form>
    </div>

    <table class="table">

        <tr>
            <th>Sort Code</th>
            <th>Account Number</th>
            <th>Card Number</th>
            <th>Owner</th>
            <th>Balance</th>
            <th>Status</th>
        </tr>


        <c:forEach var="bankAccount" items="${bankAccounts}">
            <tr>
                <td><input type="text" size="20" name="sortCode" value="${bankAccount.sortcode}" readonly ></td>
                <td><input type="text" size="20" name="accountNo" value="${bankAccount.accountNo}" readonly ></td>
                <td><input type="text" size="20" name="cardNo" value="${bankAccount.creditcard.cardnumber}" readonly ></td>
                <td>${bankAccount.owner.firstName} ${bankAccount.owner.secondName}</td>
                <td>${bankAccount.balance}</td>
                <td>
                    <c:if test="${bankAccount.active}"><div style="color:green;">Active</div></c:if>
                    <c:if test="${! bankAccount.active}"><div style="color:red;">Deactivated</div></c:if>
                    </td>
                    <td>
                        <div class="btn-group">
                            <form action="./bankaccountview" method="post">
                                <input type="hidden"  name="sortCode" value="${bankAccount.sortcode}" >
                            <input type="hidden"  name="accountNo" value="${bankAccount.accountNo}">
                            <input type="hidden" name="action" value="view">
                            <button class="btn ml-2 rounded" type="submit" >View Account</button>
                        </form>
                        <form action="./home" method="post">
                            <input type="hidden"  name="sortCode" value="${bankAccount.sortcode}" >
                            <input type="hidden"  name="accountNo" value="${bankAccount.accountNo}">
                            <input type="hidden" name="action" value="selectFromAccount">
                            <button class="btn ml-2 rounded" type="submit" >Select From Account</button>
                        </form>
                        <form action="./home" method="post">
                            <input type="hidden"  name="sortCode" value="${bankAccount.sortcode}" >
                            <input type="hidden"  name="accountNo" value="${bankAccount.accountNo}">
                            <input type="hidden" name="action" value="selectToAccount">
                            <button class="btn ml-2 rounded" type="submit" >Select To Account</button>
                        </form>
                    </div> <!-- button group -->
                </td>
            </tr>

        </c:forEach>
    </table>

</main>
<jsp:include page="footer.jsp" />
