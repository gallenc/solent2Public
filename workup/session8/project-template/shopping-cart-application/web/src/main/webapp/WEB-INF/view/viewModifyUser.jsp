<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="org.solent.com504.oodd.cart.model.dto.User"%>
<%@page import="org.solent.com504.oodd.cart.model.dto.UserRole"%>
<c:set var = "selectedPage" value = "users" scope="request"/>
<jsp:include page="header.jsp" />

<!-- Begin page content -->
<main role="main" class="container">

    <div>
        <H1>User Details</H1>
        <!-- print error message if there is one -->
        <div style="color:red;">${errorMessage}</div>
        <div style="color:green;">${message}</div>

        <form action="./viewModifyUser" method="post">
            <table class="table">
                <thead>
                </thead>

                <tbody>
                    <tr>
                        <td>User ID</td>
                        <td>${user.id}</td>
                    </tr>
                    <tr>
                        <td>username</td>
                        <td>${user.username}</td>
                    </tr>
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" name="firstName" value="${user.firstName}" /></td>
                    </tr>
                    <tr>
                        <td>Second Name</td>
                        <td><input type="text" name="secondName" value="${user.secondName}" /></td>
                    </tr>
                    <tr>
                        <td>House Number</td>
                        <td><input type="text" name="number" value="${user.address.houseNumber}" /></td>
                    </tr>
                    <tr>
                        <td>Address Line 1</td>
                        <td><input type="text" name="addressLine1" value="${user.address.addressLine1}" /></td>
                    </tr>
                    <tr>
                        <td>Address Line 2</td>
                        <td><input type="text" name="addressLine2" value="${user.address.addressLine2}" /></td>
                    </tr>
                    <tr>
                        <td>city</td>
                        <td><input type="text" name="county" value="${user.address.city}" /></td>
                    </tr>
                    <tr>
                        <td>county</td>
                        <td><input type="text" name="county" value="${user.address.county}" /></td>
                    </tr>
                    <tr>
                        <td>country</td>
                        <td><input type="text" name="country" value="${user.address.country}" /></td>
                    </tr>
                    <tr>
                        <td>postcode</td>
                        <td><input type="text" name="postcode" value="${user.address.postcode}" /></td>
                    </tr>
                    <tr>
                        <td>telephone</td>
                        <td><input type="text" name="telephone" value="${user.address.telephone}" /></td>
                    </tr>
                    <tr>
                        <td>mobile</td>
                        <td><input type="text" name="mobile" value="${user.address.mobile}" /></td>
                    </tr>

                </tbody>

            </table>
                    
            <c:if test="${user.userRole !='ADMINISTRATOR'}">
                <p>User Status and role </p>
                <table class="table">
                    <thead>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Role</td>
                            <td>${user.userRole}</td>
                        </tr>
                        <tr>
                            <td>enabled</td>
                            <td><c:if test="${user.enabled}">ENABLED</c:if><c:if test="${!user.enabled}">DISABLED</c:if></td>
                            </tr>
                        </tbody>
                    </table>
            </c:if>

            <c:if test="${user.userRole =='ADMINISTRATOR'}">
                <p>Manage User Status and role </p>
                <table class="table">
                    <thead>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Role</td>
                            <td>
                                <select class="form-control" name="userRole" >
                                    <c:forEach var="value" items="${UserRole.values()}">
                                        <option value="${value}" <c:if test="${user.userRole == value}"> selected  </c:if>>${value}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>enabled</td>
                            <td>
                                <select class="form-control" name="userEnabled" >
                                    <option value="true" <c:if test="${user.enabled}"> selected  </c:if> >ENABLED</option>
                                    <option value="false" <c:if test="${!user.enabled}"> selected  </c:if> >DISABLED</option>
                                    </select>
                                </td>
                            </tr>
                        </tbody>
                    </table>
            </c:if>

            <input type="hidden" name="username" value="${user.username}"/>
            <button class="btn" type="submit" >Update ${user.username}</button>
        </form>
        <c:if test="${user.userRole =='ADMINISTRATOR'}">
            <BR>
            <form action="./users">
                <button class="btn" type="submit" >Return To Users</button>
            </form> 
            </sec:authorize></c:if> 

        </div>

    </main>

<jsp:include page="footer.jsp" />
