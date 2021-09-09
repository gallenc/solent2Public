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
    <H1>Bank Account</H1>
    <!-- print message / error message if there is one -->
    <div style="color:red;">${errorMessage}</div>
    <div style="color:green;">${message}</div>

    <form action="./bankaccountview" method="post">
        <input type="hidden" name="action" value="update">
        <div class="btn-group">
            <button class="btn ml-2 rounded" type="submit" >update account</button>
            <a href="./bankaccounts" class="btn ml-2 rounded">Return to account list</a>
        </div>
        <table class="table">
            <tr>
                <td>Account Active</td><td><input type="checkbox" name="accountactive" value="active" <c:if test="${bankAccount.active}">checked</c:if> ></td> <td></td>
                </tr>
                <tr>
                    <td>Balance £GBP</td><td><input type="text" size="36" name="balance" value="${bankAccount.balance}" ></td> <td></td>
            </tr>
            <tr>
                <td>First Name</td><td><input type="text" size="36" name="firstName" value="${bankAccount.owner.firstName}" ></td> <td></td>
            </tr>
            <tr>
                <td>Second Name</td><td><input type="text" size="36" name="secondName" value="${bankAccount.owner.secondName}" ></td> <td></td>
            </tr>
            <tr>
                <td>Address </td><td><input type="text" size="36" name="address" value="${bankAccount.owner.address}" ></td> <td></td>
            </tr>
            <tr>
                <td>Username </td><td><input type="text" size="36" name="username" value="${bankAccount.owner.username}" readonly ></td> <td></td>
            </tr>

            <tr>
                <td>Sort Code</td><td><input type="text" size="36" name="sortCode" value="${bankAccount.sortcode}" readonly ><td></td>
            </tr>
            <tr>
                <td>Account Number</td><td><input type="text" size="36" name="accountNo" value="${bankAccount.accountNo}" readonly ><td></td>
            </tr>
            <tr>
                <td>Card Number</td><td><input type="text" size="36" name="cardnumber" value="${bankAccount.creditcard.cardnumber}" readonly ><td></td>
            </tr>
            <tr>
                <td>Name On Card</td><td><input type="text" size="36" name="cardname" value="${bankAccount.creditcard.name}" readonly ><td></td>
            </tr>
            <tr>
                <td>End Date</td><td><input type="text" size="36" name="endDate" value="${bankAccount.creditcard.endDate}" readonly ><td></td>
            </tr>
            <tr>
                <td>Issue Number</td><td><input type="text" size="36" name="issueNumber" value="${bankAccount.creditcard.issueNumber}" readonly ><td></td>
            </tr>
            <tr>
                <td>cvv</td><td><input type="text" size="36" name="cvv" value="${bankAccount.creditcard.cvv}" readonly ><td></td>
            </tr>
        </table>
    </form> 

    <form action="./bankaccountview" method="post">
        <input type="hidden" name="action" value="updatePassword">
        <input type="hidden"  name="sortCode" value="${bankAccount.sortcode}">
        <input type="hidden"  name="accountNo" value="${bankAccount.accountNo}" >
        <div class="btn-group">
            <button class="btn ml-2 rounded" type="submit" >Update Password</button><br>
            Input new password <input type="text" size="36" name="password1" value="" ></p>&nbsp;Repeat new password<input type="text" size="36" name="password2" value="" >
        </div>
    </form>

</main>
<jsp:include page="footer.jsp" />
