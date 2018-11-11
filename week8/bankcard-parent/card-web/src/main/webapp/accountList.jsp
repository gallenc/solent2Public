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
                <option value='VISA_BANK_OF_IRELAND_UK' >VISA_BANK_OF_IRELAND_UK</option>
                <option value='VISA_NAT_WEST' selected="selected" >VISA_NAT_WEST</option>
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
            <tr>
                <th>Bank Provider</th>
                <td>Account Name</td>
                <td>Account Number</td> 
                <td>Card Issue number</td>
                <td>Account Balance</td>
                <td>
                    <form action="createOrModifyAccount.html">
                        <input type="hidden" value="accountName">
                        <input type="hidden" value="accountNumber">
                        <input type="hidden" value="bankProvider">
                        <input type="submit" value="Modify Account">
                    </form>
                </td>
                <td>
                    <form action="createCard.html">
                        <input type="hidden" value="accountName">
                        <input type="hidden" value="accountNumber">
                        <input type="hidden" value="bankProvider">
                        <input type="submit" value="Create New Credit Card">
                    </form>
                </td>
            </tr>
        </table>

    </body>
</html>
