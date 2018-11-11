<%-- 
    Document   : createOrModifyAccount.jsp
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
    BankApi bankApi = (BankApi) session.getAttribute("bankApi");

    // If the user session has no bankApi, create a new one
    if (bankApi == null) {
        ServiceObjectFactory serviceObjectFactory = WebObjectFactory.getServiceObjectFactory();
        bankApi = serviceObjectFactory.getBankApi();
        session.setAttribute("bankApi", bankApi);
    }

    String bankProvider = (String) request.getParameter("bankProvider");
    String issuerIdentificationNumber = bankApi.getIssuerIdentifierNumberForName(bankProvider);
    String accountNumber = (String) request.getParameter("accountNumber");
    String accountName = (String) request.getParameter("accountName");
    String balanceStr = (String) request.getParameter("balance");

    Double balance = null;
    if (balanceStr != null && !balanceStr.isEmpty()) {
        try {
            balance = Double.parseDouble(balanceStr);
        } catch (Exception e) {
        }
    }

    String createAccount = (String) request.getParameter("createAccount");

    Account account = null;
    // no existing account - so create account
    if ("true".equals(createAccount)) {
        account = bankApi.createAccount(issuerIdentificationNumber, "SET ACCOUNT NAME");
    } else {
        account = bankApi.retrieveAccount(issuerIdentificationNumber, accountNumber);
        boolean update = false;
        // only update if values present
        if (accountName != null && !accountName.isEmpty()) {
            update = true;
            account.setName(accountName);
        }
        if (balance != null) {
            update = true;
            account.setBalance(balance);
        }
        if (update) {
            account = bankApi.updateAccount(account);
        }
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create Credit Card</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>

    <body>
        <% if ("true".equals(createAccount)) {
        %>
        <h2>Account Created</h2>
        <%
        } else {
        %>
        <h2>Account Updated</h2>
        <%
            }
        %>
        <form action="createOrModifyAccount.jsp">
            <table>
                <tr>
                    <td>Bank</td><td><%=bankProvider%></td>               
                </tr>
                <tr>
                    <td>Issuer Identification Number</td><td><%=account.getIssuerIdentificationNumber()%></td>               
                </tr>
                <tr>
                    <td>Individual Account Identifier</td><td><%=account.getIndividualAccountIdentifier()%></td>
                </tr>
                <tr>
                    <td>Name</td><td><input type="text" name="accountName" value="<%=account.getName()%>"></td>
                </tr>
                <tr>
                    <td>Card Issue Number</td><td><%=account.getCurrentCardIssueNumber()%></td>
                </tr>
                <tr>
                    <td>Balance</td><td><input type="text" name="balance"value="<%=account.getBalance()%>"></td>
                </tr>
            </table>
            <BR>
            <input type="hidden" name="accountNumber" value="<%=account.getIndividualAccountIdentifier()%>">
            <input type="hidden" name="bankProvider" value="<%=bankProvider%>">
            <input type="hidden" name="createAccount" value="false">
            <input type="submit" value="Update Account">
            <BR>
        </form>
        <BR>
        <button onclick="window.location.href = 'accountList.jsp'">Return to Account List</button>

    </body>
</html>
