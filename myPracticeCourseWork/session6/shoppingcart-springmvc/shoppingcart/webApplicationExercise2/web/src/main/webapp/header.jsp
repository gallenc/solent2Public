<%-- 
    Document   : header
    Created on : 20 Oct 2021, 11:32:31
    Author     : rgaud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <!-- Static navbar -->
        <!--<nav class="navbar navbar-default">
            <!-- contains the nav bar elements
        </nav>-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
              <ul class="navbar-nav">
                <li class="nav-item <% if ("home".equals(request.getAttribute("selectedPage"))) {%> active  <% } %> ">
                  <a class="nav-link" href="home.jsp">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item <% if ("about".equals(request.getAttribute("selectedPage"))) {%> active  <% } %>">
                  <a class="nav-link" href="about.jsp">About</a>
                </li>
                <li class="nav-item <% if ("about".equals(request.getAttribute("selectedPage"))) {%> active  <% } %>">
                  <a class="nav-link" href="contact.jsp">Contact</a>
                </li>
              </ul>
            </div>
          </nav>
    </body>
</html>
