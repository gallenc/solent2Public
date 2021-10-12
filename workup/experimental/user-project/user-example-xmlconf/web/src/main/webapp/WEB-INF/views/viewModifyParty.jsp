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
<c:set var = "selectedPage" value = "partys" scope="request"/>
<jsp:include page="header.jsp" />

<!-- Begin page content -->
<main role="main" class="container">

    <div>
        <H1>Party Details</H1>
        <!-- print error message if there is one -->
        <div style="color:red;">${errorMessage}</div>
        <div style="color:green;">${message}</div>

        <form action="./viewModifyParty" method="post">
            <div>
                <input type="hidden" name="partyuuid" value="${party.uuid}"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button class="btn" type="submit" >Update Party uuid ${party.uuid}</button>
            </div>
            <table class="table">
                <thead>
                </thead>

                <tbody>
                    <tr>
                        <td>Party ID</td>
                        <td>${party.id}</td>
                    </tr>
                    <tr>
                        <td>uuid</td>
                        <td>${party.uuid}</td>
                    </tr>
                    <tr>
                        <td>Party Role</td>
                        <td>
                            <select name="partyRole">
                                <c:forEach var="entry" items="${availablePartyRolesMap}">
                                    <option value="${entry.key}" ${entry.value} >${entry.key}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" name="firstName" value="${party.firstName}" /></td>
                    </tr>
                    <tr>
                        <td>Second Name</td>
                        <td><input type="text" name="secondName" value="${party.secondName}" /></td>
                    </tr>
                    <tr>
                        <td>Building Number</td>
                        <td><input type="text" name="number" value="${party.address.number}" /></td>
                    </tr>
                    <tr>
                        <td>Address Line 1</td>
                        <td><input type="text" name="addressLine1" value="${party.address.addressLine1}" /></td>
                    </tr>
                    <tr>
                        <td>Address Line 2</td>
                        <td><input type="text" name="addressLine2" value="${party.address.addressLine2}" /></td>
                    </tr>
                    <tr>
                        <td>County</td>
                        <td><input type="text" name="county" value="${party.address.county}" /></td>
                    </tr>
                    <tr>
                        <td>Country</td>
                        <td><input type="text" name="country" value="${party.address.country}" /></td>
                    </tr>
                    <tr>
                        <td>Postcode</td>
                        <td><input type="text" name="postcode" value="${party.address.postcode}" /></td>
                    </tr>
                    <tr>
                        <td>Latitude</td>
                        <td><input type="text" name="latitude" value="${party.address.latitude}" /></td>
                    </tr>
                    <tr>
                        <td>Longitude</td>
                        <td><input type="text" name="longitude" value="${party.address.longitude}" /></td>
                    </tr>                      <tr>
                        <td>Telephone</td>
                        <td><input type="text" name="telephone" value="${party.address.telephone}" /></td>
                    </tr>
                    <tr>
                        <td>Mobile</td>
                        <td><input type="text" name="mobile" value="${party.address.mobile}" /></td>
                    </tr>
                    <tr>
                        <td>Users</td>
                        <td><c:forEach var="user" items="${party.users}">| ${user.username} |</c:forEach></td>
                        </tr>
                    </tbody>

                </table>
                <div>
                <sec:authorize access="hasRole('ROLE_GLOBAL_ADMIN')" >
                    <p>Manage Party Status </p>
                    <table class="table">
                        <thead>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <div class="custom-control custom-switch">
                                        <!-- party.isEnabled= ${party.enabled} -->
                                        <input type="checkbox" class="custom-control-input" 
                                               id="partyEnabled" name="partyEnabled" 
                                               value="true" <c:if test="${party.enabled}">checked</c:if> > 
                                               <label class="custom-control-label" for="partyEnabled">PARTY ENABLED</label>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                </form>

        </sec:authorize> 
        <sec:authorize access="hasRole('ROLE_GLOBAL_ADMIN')" >
            <p>Manage Users Associated with Party </p>
            <table class="table">
                <thead>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${party.users}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.firstName}</td>
                            <td>${user.secondName}</td>
                            <td><c:forEach var="role" items="${user.roles}"> | ${role.name} |<br></c:forEach></td>
                                <td>
                                    <form action="./viewModifyParty" method="POST">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <input type="hidden" name="partyuuid" value="${party.uuid}"/>
                                    <input type="hidden" name="removeUsername" value="${user.username}">
                                    <button class="btn" type="submit" >Remove User</button>
                                </form> 
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div>
                <form action="./addUsersToParty" method="POST">
                    <input type="hidden" name="partyuuid" value="${party.uuid}"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button class="btn" type="submit" >Add Users</button>
                </form>
            </div>
            <div>
                <BR>
                <form action="./partys" method="GET">
                    <button class="btn" type="submit" >Return To Partys</button>
                </form> 
            </div>
        </sec:authorize> 

    </div>

</main>

<jsp:include page="footer.jsp" />
