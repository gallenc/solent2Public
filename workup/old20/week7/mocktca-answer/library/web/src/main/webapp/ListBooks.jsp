<%-- 
    Document   : ListBooks
    Created on : Nov 30, 2018, 11:17:02 PM
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
	
	// get search values. Make sure null does not appear in search text fields.
	String searchTitle = (String) request.getParameter("searchTitle");
	if("".equals(searchTitle)) searchTitle=null;

	String searchAuthor = (String) request.getParameter("searchAuthor");
	if("".equals(searchAuthor)) searchAuthor=null;

	String searchIsbn = (String) request.getParameter("searchIsbn");
	if("".equals(searchIsbn)) searchIsbn=null;


	String errorMessage = "";
	
	if ("deleteBook".equals(action)) {
		try {
			Integer bookId = Integer.parseInt(bookIdReq);
			serviceFacade.deleteBook(bookId);
		} catch (Exception e) {
			errorMessage = "problem deleting Book " + e.getMessage();
		}
	} else if ("modifyBook".equals(action)) {
		try {
			Integer bookId = Integer.parseInt(bookIdReq);
			Book bookTemplate = new Book();
			bookTemplate.setId(bookId);
			bookTemplate.setTitle(bookTitleReq);
			bookTemplate.setAuthor(bookAuthorReq);
			bookTemplate.setIsbn(bookIsbnReq);
			Book book = serviceFacade.updateBook(bookTemplate);
			if (book == null) {
				errorMessage = "problem modifying Book. could not find bookId " + bookId;
			}
		} catch (Exception e) {
			errorMessage = "problem modifying Book " + e.getMessage();
		}
	} else if ("createBook".equals(action)) {
		try {
			Book bookTemplate = new Book();
			bookTemplate.setTitle(bookTitleReq);
			bookTemplate.setAuthor(bookAuthorReq);
			bookTemplate.setIsbn(bookIsbnReq);
			Book book = serviceFacade.createBook(bookTemplate);
			if (book == null) {
				errorMessage = "problem creating Book. Service returned null ";
			}
		} catch (Exception e) {
			errorMessage = "problem creating  Book " + e.getMessage();
		}
	} else if ("clearSearch".equals(action)) {
		searchTitle="";
		searchAuthor="";
		searchIsbn="";
	}
	
	List<Book> bookList =null;
	
	if ("searchBooks".equals(action)) {
		Book bookTemplate = new Book();
		bookTemplate.setTitle(searchTitle);
		bookTemplate.setAuthor(searchAuthor);
		bookTemplate.setIsbn(searchIsbn);
		bookList = serviceFacade.retrieveMatchingBooks(bookTemplate);
	} else {
		bookList = serviceFacade.retrieveAllBooks();
	}
 
%>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Book List</title>
</head>
<body>
	<!-- print error message if there is one -->
	<div style="color: red;"><%=errorMessage%></div>

	<h1>Book List</h1>
	<table>
		<tr>
			<th>id</th>
			<th>title</th>
			<th>author</th>
			<th>isbn</th>
			<th></th>
		</tr>
		<%
			for (Book book : bookList) {
		%>
		<tr>
			<td><%=book.getId()%></td>
			<td><%=((book.getTitle()==null) ? "":book.getTitle()) %></td>
			<td><%=((book.getAuthor()==null) ? "":book.getAuthor()) %></td>
			<td><%=((book.getIsbn()==null) ? "":book.getIsbn()) %></td>
			<td>
				<form action="AddOrModifyBook.jsp">
					<input type="hidden" name="action" value="modifyBook"> <input
						type="hidden" name="bookId" value="<%=book.getId()%>"> <input
						type="submit" value="Modify Book">
				</form>
				<form action="ListBooks.jsp">
					<input type="hidden" name="action" value="deleteBook"> <input
						type="hidden" name="bookId" value="<%=book.getId()%>"> <input
						type="submit" value="Delete Book">
				</form>
			</td>
		</tr>
		<%
			}
		%>

	</table>
	<BR>
	<form action="AddOrModifyBook.jsp">
		<input type="hidden" name="action" value="createBook"> <input
			type="submit" value="Create Book">
	</form>

	<h1>Search Criteria</h1>
	<p>Enter search criteria to find particular book. Search AND's fields. Empty fields are not included in search.</p>
	<form action="ListBooks.jsp">
		<table>
			<tr>
				<td>title</td>
				<td><input type="text" name="searchTitle" value ="<%=( (searchTitle==null) ? "":searchTitle) %>"></td>
			</tr>
			<tr>
				<td>author</td>
				<td><input type="text" name="searchAuthor" value ="<%=( (searchAuthor==null) ? "":searchAuthor) %>"></td>
			</tr>
			<tr>
				<td>isbn</td>
				<td><input type="text" name="searchIsbn" value ="<%=( (searchIsbn==null) ? "":searchIsbn) %>"></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="searchBooks">
		<input type="submit" value="Search">
	</form>
		<form action="ListBooks.jsp">
		<input type="hidden" name="action" value="clearSearch">
		<input type="submit" value="Clear Search">
	</form>




</body>
</html>
