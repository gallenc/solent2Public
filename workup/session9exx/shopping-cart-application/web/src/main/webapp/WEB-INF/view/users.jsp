<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="org.solent.com504.oodd.cart.model.dto.User"%>
<%@page import="org.solent.com504.oodd.cart.model.dto.UserRole"%>
<c:set var = "selectedPage" value = "admin" scope="request"/>
<jsp:include page="header.jsp" />
<!-- start of users.jsp selectedPage=${selectedPage}-->

<!-- Begin page content -->
<main role="main" class="container">

    <div>
        <h1>Manage Users</h1>
        <p>showing ${userListSize} users: </p>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Username</th>
                    <th scope="col">First Name</th>
                    <th scope="col">Second Name</th>
                    <th scope="col">Status</th>
                    <th scope="col">Role</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.firstName}</td>
                        <td>${user.secondName}</td>
                        <!-- user.enabled=${user.enabled}-->
                        <td><c:if test="${user.enabled}">ENABLED</c:if><c:if test="${!user.enabled}">DISABLED</c:if></td>
                        <td>${user.userRole}</td>
                        <td>
                            <form action="./viewModifyUser" method="GET">
                                <input type="hidden" name="username" value="${user.username}">
                                <button class="btn" type="submit" >Modify User</button>
                            </form> 
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
        <form action="./register" method="GET">
            <button class="btn" type="submit" >Add User</button>
        </form> 
    </div>
</main>

<jsp:include page="footer.jsp" />
