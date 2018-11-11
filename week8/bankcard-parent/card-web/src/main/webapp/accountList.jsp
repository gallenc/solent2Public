<%-- 
    Document   : accountList.jsp
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
    
    String deleteAccount = (String) request.getParameter("deleteAccount");
    String bankProvider = (String) request.getParameter("bankProvider");
    String accountNumber = (String) request.getParameter("accountNumber");
    
    if("true".equals(deleteAccount)){
        String issuerIdentificationNumber = bankApi.getIssuerIdentifierNumberForName(bankProvider);
        bankApi.deleteAccount(issuerIdentificationNumber, accountNumber);
    }

    if (bankProvider == null) {
        bankProvider = bankApi.getSupportedIssuerNames().get(0);
    }

    String issuerIdentificationNumber = bankApi.getIssuerIdentifierNumberForName(bankProvider);
    List<Account> accounts = bankApi.getAccountsForIssuer(issuerIdentificationNumber);

%>
<!DOCTYPE html>
<html>
    <head>
        <title>Bank Accounts</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>

    <body>

        <form name='selectBank' method="get">
            Bank Provider <select name='bankProvider' onchange="this.form.submit()">
                <% for (String issuerName : bankApi.getSupportedIssuerNames()) {
                        if (issuerName.equals(bankProvider)) {
                %>
                <option value='<%=issuerName%>' selected="selected" ><%=issuerName%></option>
                <%
                } else {
                %>
                <option value='<%=issuerName%>' ><%=issuerName%></option>
                <%
                        }
                    }
                %>
            </select>
        </form>
        <BR>

        <table style="width:100%">
            <tr>
                <th>Bank Provider</th>
                <th>Account Name</th>
                <th>Account Number</th>
                <th>Card Issue number</th>
                <th>Account Balance</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <%
                for (Account account : accounts) {
            %>
            <tr>
                <th><%=bankProvider%></th>
                <td><%=account.getName()%></td>
                <td><%=account.getIndividualAccountIdentifier()%></td> 
                <td><%=account.getCurrentCardIssueNumber()%></td>
                <td><%=account.getBalance()%></td>
                <td>
                    <form action="createOrModifyAccount.jsp">
                        <input type="hidden" name="createAccount" value="false">
                        <input type="hidden" name="accountNumber" value="<%=account.getIndividualAccountIdentifier()%>">
                        <input type="hidden" name="bankProvider" value="<%=bankProvider%>">
                        <input type="submit" value="Modify Account">
                    </form>
                </td>
                <td>
                    <form action="accountList.jsp">
                        <input type="hidden" name="deleteAccount" value="true" >
                        <input type="hidden" name="accountNumber" value="<%=account.getIndividualAccountIdentifier()%>">
                        <input type="hidden" name="bankProvider" value="<%=bankProvider%>">
                        <input type="submit" value="Delete Account">
                    </form>
                </td>
                <td>
                    <form action="createCard.jsp">
                        <input type="hidden" name="accountNumber" value="<%=account.getIndividualAccountIdentifier()%>">
                        <input type="hidden" name="bankProvider" value="<%=bankProvider%>">
                        <input type="submit" value="Create New Credit Card">
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <BR>
        <form action="createOrModifyAccount.jsp">
            <input type="hidden" name="createAccount" value="true">
            <input type="hidden" name="bankProvider" value="<%=bankProvider%>">
            <input type="submit" value="Create New Account">
        </form>
    </body>
</html>
