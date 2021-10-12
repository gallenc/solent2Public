<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page import="org.solent.com504.project.model.user.dto.User"%>
<%@page import="org.solent.com504.project.model.user.dto.UserRoles"%>
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
                        <td><input type="text" name="number" value="${user.address.number}" /></td>
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
                        <td>latitude</td>
                        <td><input type="text" name="latitude" value="${user.address.latitude}" /></td>
                    </tr>
                    <tr>
                        <td>longitude</td>
                        <td><input type="text" name="longitude" value="${user.address.longitude}" /></td>
                    </tr>                      <tr>
                        <td>telephone</td>
                        <td><input type="text" name="telephone" value="${user.address.telephone}" /></td>
                    </tr>
                    <tr>
                        <td>mobile</td>
                        <td><input type="text" name="mobile" value="${user.address.mobile}" /></td>
                    </tr>
                    <tr>
                        <td>Roles</td>
                        <td>|<c:forEach var="role" items="${user.roles}"> ${role.name} |</c:forEach></td>
                        </tr>
                    </tbody>

                </table>
                <div>
                <sec:authorize access="hasRole('ROLE_GLOBAL_ADMIN')" >
                    <p>Manage User Status </p>
                    <table class="table">
                        <thead>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <div class="custom-control custom-switch">
                                        <!-- user.isEnabled= ${user.enabled} -->
                                        <input type="checkbox" class="custom-control-input" 
                                               id="userEnabled" name="userEnabled" 
                                               value="true" <c:if test="${user.enabled}">checked</c:if> > 
                                               <label class="custom-control-label" for="userEnabled">USER ENABLED</label>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <p>Manage User Roles </p>
                        <table class="table">
                            <thead>
                            </thead>
                            <tbody>
                            <c:forEach var="entry" items="${selectedRolesMap}">
                                <tr>
                                    <td>
                                        <div class="custom-control custom-switch">
                                            <input type="checkbox" class="custom-control-input" 
                                                   id="${entry.key}" name="selectedRoles" value="${entry.key}" ${entry.value} >
                                            <label class="custom-control-label" for="${entry.key}">${entry.key}</label>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </sec:authorize>  
            </div>
            <input type="hidden" name="username" value="${user.username}"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn" type="submit" >Update ${user.username}</button>
        </form>
        <sec:authorize access="hasRole('ROLE_GLOBAL_ADMIN')">
            <BR>
            <form action="./users">
                <button class="btn" type="submit" >Return To Users</button>
            </form> 
        </sec:authorize> 

    </div>

</main>

<jsp:include page="footer.jsp" />
