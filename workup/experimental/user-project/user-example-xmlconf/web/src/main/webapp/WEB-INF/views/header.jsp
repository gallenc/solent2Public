<%-- 
    Document   : header
    Created on : Jan 4, 2020, 11:19:01 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<!-- start of header.jsp -->
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">
        <!--<link rel="canonical" href="https://getbootstrap.com/docs/3.3/examples/navbar/">-->

        <title>Navbar Template for Bootstrap</title>

        <!-- Bootstrap core CSS -->
        <link href="./resources/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="./resources/css/navbar.css" rel="stylesheet">

    </head>

    <body>

        <header>

            <!-- Static navbar -->
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">Project name</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">

                            <!-- this jstl should work but problems with multiple if statements -->
                            <!-- selected page = ${selectedPage} home ${'home'.equals(selectedPage) } about ${'about'.equals(selectedPage) } contact ${'contact'.equals(selectedPage) }-->
                            <!--<li <c:if test="${'home'.equals(selectedPage) }"> class="active"  </c:if> ><a href="${contextPath}/home">Home</a></li>--> 

                                <!-- this raw java code works !! -->
                            <li <% if ("home".equals(request.getAttribute("selectedPage"))) {%> class="active"  <% } %> ><a href="${contextPath}/home">Home</a></li> 
                            <li <% if ("about".equals(request.getAttribute("selectedPage"))) {%>  class="active"  <% } %> ><a href="${contextPath}/about">About</a></li> 
                            <li <% if ("contact".equals(request.getAttribute("selectedPage"))) {%>  class="active"  <% }%> ><a href="${contextPath}/contact">Contact</a></li>                          

                            <sec:authorize access="hasRole('ROLE_GLOBAL_ADMIN')">
                                <li class="dropdown" >
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> Admin <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="${contextPath}/users">Manage Users</a></li>
                                        <li><a href="${contextPath}/partys">Manage Partys</a></li>
                                    </ul>
                                </li>
                            </sec:authorize>

                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <c:if test="${pageContext.request.userPrincipal.name != null}">
                                <form id="logoutForm" method="POST" action="${contextPath}/logout">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </form>
                                <form id="profile" method="GET" action="${contextPath}/viewModifyUser">
                                    <input type="hidden" name="username" value="${pageContext.request.userPrincipal.name}"/>
                                </form>
                                <p class="text-muted"> Welcome ${pageContext.request.userPrincipal.name}&nbsp;&nbsp;
                                <a onclick="document.forms['logoutForm'].submit()">Logout</a><BR>
                                <a onclick="document.forms['profile'].submit()">User Profile</a></p>
                            </c:if>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>
        </header>
        <!-- end of header.jsp -->