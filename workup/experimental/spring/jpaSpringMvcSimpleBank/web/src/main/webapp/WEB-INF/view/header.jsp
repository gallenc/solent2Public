<%-- 
    Document   : header
    Created on : Jan 4, 2020, 11:19:01 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

        <title>Bank Simulator</title>

        <!-- Bootstrap core CSS -->
        <link href="./resources/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="./resources/css/navbar.css" rel="stylesheet">

    </head>

    <body>

        <div class="container">

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
                        <a class="navbar-brand" href="#">Simple Bank Simulator</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li <% if ("home".equals(request.getAttribute("selectedPage"))) {%> class="active"  <% } %> ><a href="./home">Home</a></li> 

                            <!-- Add your tabs here -->
                            <li <% if ("bankaccounts".equals(request.getAttribute("selectedPage"))) {%>  class="active"  <% }%> ><a href="./bankaccounts">Bank Accounts</a></li>                          
                            <li <% if ("banktransactions".equals(request.getAttribute("selectedPage"))) {%>  class="active"  <% }%> ><a href="./banktransactions">Transactions</a></li>                          

                            <li <% if ("about".equals(request.getAttribute("selectedPage"))) {%>  class="active"  <% } %> ><a href="./about">About</a></li> 
                            <li <% if ("contact".equals(request.getAttribute("selectedPage"))) {%>  class="active"  <% }%> ><a href="./contact">Contact</a></li>                          
                            <li  ><a style="color:red;" href="./reset">Clear All Data</a></li> 
                        </ul>
                        <ul class="nav navbar-nav navbar-right">

                        </ul>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>