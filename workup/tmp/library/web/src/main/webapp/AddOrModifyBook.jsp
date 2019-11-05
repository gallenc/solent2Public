<%-- 
    Document   : AddOrModifyBook
    Created on : Nov 30, 2018, 11:17:38 PM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="solent.ac.uk.ood.examples.exam.web.WebObjectFactory"%>
<%@page import="solent.ac.uk.ood.examples.exam.model.ServiceFactory"%>
<%@page import="solent.ac.uk.ood.examples.exam.model.ServiceFacade"%>
<%@page import="solent.ac.uk.ood.examples.exam.model.Book"%>


<%

    ServiceFacade serviceFacade = (ServiceFacade) session.getAttribute("serviceFacade");

    // If the user session has no bankApi, create a new one
    if (serviceFacade == null) {
        ServiceFactory serviceFactory = WebObjectFactory.getServiceFactory();
        serviceFacade = serviceFactory.getServiceFacade();
        session.setAttribute("ServiceFacade", serviceFacade);
    }

    // get request values
    String action = (String) request.getParameter("action");
    String bookIdReq = (String) request.getParameter("bookId");
    String bookTitleReq = (String) request.getParameter("title");
    String bookAuthorReq = (String) request.getParameter("author");
    String bookIsbnReq = (String) request.getParameter("isbn");

    String errorMessage = "";

    Book book = null;
    Integer bookId = null;

    if ("modifyBook".equals(action)) {
        try {
            bookId = Integer.parseInt(bookIdReq);
            book = serviceFacade.retrieveBook(bookId);
        } catch (Exception e) {
            errorMessage = "problem finding book " + e.getMessage();
        }
    } else if ("createBook".equals(action)) {
        try {
            book = new Book();
        } catch (Exception e) {
            errorMessage = "problem finding book " + e.getMessage();
        }
    } else {
        errorMessage = "cannot recognise action: " + action;
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Edit Book</title>
    </head>
    <body>
        <% if ("createBook".equals(action)) {
        %>
        <h1>Add New Book</h1>
        <% } else {%>
        <h1>Modify Book <%=bookId%></h1>
        <% }%>
        <form action="ListBooks.jsp">
            <table>
                <tr>
                    <th>Field</th>
                    <th>Current Value</th>
                    <th>New Value</th>
                </tr>
                <tr>
                    <td>Book Id</td>
                    <td><%=book.getId()%></td>
                    <td></td>
                </tr>
                <tr>
                    <td>title</td>
                    <td><%=((book.getTitle()==null) ? "":book.getTitle()) %></td>
                    <td><input type="text" name="title" value ="<%=((book.getTitle()==null) ? "":book.getTitle()) %>"></td>
                </tr>
                <tr>
                    <td>author</td>
                    <td><%=((book.getAuthor()==null) ? "":book.getAuthor()) %></td>
                    <td><input type="text" name="author" value ="<%=((book.getAuthor()==null) ? "":book.getAuthor()) %>"></td>
                </tr>
                <tr>
                    <td>isbn</td>
                    <td><%=((book.getIsbn()==null) ? "":book.getIsbn()) %></td>
                    <td><input type="text" name="isbn" value ="<%=((book.getIsbn()==null) ? "":book.getIsbn()) %>"></td>
                </tr>
            </table> 
            <BR>
            <% if ("createBook".equals(action)) {
            %>
            <input type="hidden" name="action" value="createBook">
            <input type="hidden" name="bookId" value="<%=bookId%>">
            <input type="submit" value="Create New Book">
            <% } else if ("modifyBook".equals(action)) {
            %>
            <input type="hidden" name="action" value="modifyBook">
            <input type="hidden" name="bookId" value="<%=bookId%>">
            <input type="submit" value="Modify Book">
            <% }%>
        </form>
        <form action="ListBooks.jsp">
            <input type="submit" value="Cancel and Return">
        </form>
    </body>
</html>
