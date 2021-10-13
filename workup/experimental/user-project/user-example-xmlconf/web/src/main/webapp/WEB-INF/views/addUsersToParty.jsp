<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="org.solent.com504.project.model.user.dto.User"%>
<%@page import="org.solent.com504.project.model.user.dto.UserRoles"%>
<c:set var = "selectedPage" value = "admin" scope="request"/>
<jsp:include page="header.jsp" />
<!-- start of users.jsp selectedPage=${selectedPage}-->

<!-- Begin page content -->
<main role="main" class="container">

    <div>
        <h1>Add Users to Party ${partyuuid}</h1>
        <p>showing ${userListSize} users: </p>
        <form action="./viewModifyParty" method="POST">
            <input type="hidden" name="partyuuid" value="${partyuuid}"/>
            <input type="hidden" name="action" value="addUsersToParty"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn" type="submit" >Update Party uuid ${partyuuid}</button>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Username</th>
                        <th scope="col">First Name</th>
                        <th scope="col">Second Name</th>
                        <th scope="col">Status</th>
                        <th scope="col">Roles</th>
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
                            <td><c:forEach var="role" items="${user.roles}"> | ${role.name} |<br></c:forEach></td>
                                <td>
                                    <div class="custom-control custom-switch">
                                        <input type="checkbox" class="custom-control-input" 
                                               id="addUsers" name="addUsers" 
                                               value="${user.username}"  > 
                                    <label class="custom-control-label" for="partyEnabled">Add User</label>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </form> 
    </div>
</main>

<jsp:include page="footer.jsp" />
