## Shopping Cart Bootstrap exercise

This course is not primarily teaching web design but it will be a very useful exercise for you to work out how your jsp based programs can benefit from using web design libraries.
Bootstrap was originally developed at Twitter ( [Bootstrap history](https://en.wikipedia.org/wiki/Bootstrap_(front-end_framework)) ).
Bootstrap is a HTML, CSS & JS Library that focuses on simplifying the development of informative web pages (as opposed to web apps). 
The primary purpose of adding it to a web project is to apply Bootstrap's choices of colour, size, font and layout to that project.

The intention of this exercise is to apply the [Bootstrap 3.3 navbar example](https://getbootstrap.com/docs/3.3/examples/navbar/) to our project.
This is in order to change the style of the application from a very basic funcionality only page;

![alt text](../shoppingcart-bootstrap/images/Exercise2jspimage1.png "Figure Exercise2jspimage1.png" )

To this navbar style;

![alt text](../shoppingcart-bootstrap/images/bootstrapjspimage1.png "Figure bootstrapjspimage1.png" )


## simple Bootstrap enabled web app

Before we try and apply bootstrap to our shopping cart, lets look at how to implement a simple set of pages with a navbar.

Import the provided [webApplicationExercise-bootstrap](../shoppingcart-bootstrap/webApplicationExercise-bootstrap)
project into netbeans. 

Run the application as a web app in tomcat and browse to http://localhost:8080/webApplicationExercise/home.jsp

You will see the app has implemented the nav bar style and when a nav bar button is pressed a corresponding jsp renders the content.

Lets consider how this is achieved.

The webapp has the following layout

![alt text](../shoppingcart-bootstrap/images/bootstrapLayout1.png "Figure bootstrapLayout1.png" ) 

You will see that the webapp contains a number of jsp's css and js resources and a bootstrap-starter.html page.
This page is included just to get us started. 
Once we have embedded the content in our jsp's it should be removed.

Run the application as a web app in tomcat and browse to http://localhost:8080/webApplicationExercise/bootstrap-starter.html

This should render a sample page identical to that when you browse to the [Bootstrap 3.3 navbar example](https://getbootstrap.com/docs/3.3/examples/navbar/) 

In fact, the first step to create this app was to download the navbar example content including the css and js links from getbootstrap.com and install it in the web app. 

Look at the source of the [bootstrap-starter.html](../shoppingcart-bootstrap/webApplicationExercise-bootstrap/src/main/webapp/bootstrap-starter.html).

The page is essentially in 3 sections. 
(For simplicity, only the key html is shown). 

1. Header - page preamble and nav bar
```
<!DOCTYPE html>
<html lang="en">
    <head>
    <!-- contains css and javascript references and page matadata -->
    </head>
    <body>
        <div class="container">

        <!-- Static navbar -->
        <nav class="navbar navbar-default">
            <!-- contains the nav bar elements -->

                      <ul class="nav navbar-nav">
                            <!-- this is the active page - active needs to change depending on the selected page -->
                            <li class="active"><a href="#">Home</a></li>
                            
                            <li><a href="#">About</a></li>
                            <li><a href="#">Contact</a></li>
                      </ul>


        </nav>
        
```

2. Body - the main body of the page
```
<!-- Begin page content (note the example page uses a jumbotron instead) -->
<main role="main" class="container">
    <H1>Home</H1>
</main>

```

3. Footer - contains the document footer and core javascript
```
 <footer class="footer">
            <div class="container">
                <p class="text-muted">Place sticky footer content here.</p>
            </div>
        </footer>

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <!--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
            <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>-->
        <script src="./resources/js/jquery.min.js"></script>
        <script src="./resources/js/bootstrap.min.js"></script>

    </body>
</html>
```

In order to use this template, we need to embed it in the html code of our JSP's. 
However it makes more sense to split the pages into a common header.jsp and footer.jsp and have separate body jsp's depending on the content; about.jsp, contact.jsp, main.jsp etc.

To do this we make use of the jsp:include directive. See for instance [home.jsp](../shoppingcart-bootstrap/webApplicationExercise-bootstrap/src/main/webapp/home.jsp).
```
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.setAttribute("selectedPage","home");
%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <H1>Home</H1>
</main>
<jsp:include page="footer.jsp" />

```
The line
```
request.setAttribute("selectedPage","home"); 
```
tells the header that it is being included in the home page and should set the home header tab active.

If we look at the [header.jsp](../shoppingcart-bootstrap/webApplicationExercise-bootstrap/src/main/webapp/header.jsp )
we can see that a little bit of java code is selecting which header is active based on the selectedPage attribute.
```
<ul class="nav navbar-nav">
   <li <% if ("home".equals(request.getAttribute("selectedPage"))) {%> class="active"  <% } %> ><a href="./home.jsp">Home</a></li> 
   <li <% if ("about".equals(request.getAttribute("selectedPage"))) {%>  class="active"  <% } %> ><a href="./about.jsp">About</a></li> 
   <li <% if ("contact".equals(request.getAttribute("selectedPage"))) {%>  class="active"  <% }%> ><a href="./contact.jsp">Contact</a></li>                          
</ul>
```

## Exercise 1 - implement this bootstrap style in your application

Having seen how the navigation bar works and how to use it with JSP's you should now be able to apply this style to the shopping cart application you previously made.

1. Copy your previous application into this weeks folder and import it into netbeans
2. Copy all the JSP's except home.jsp from the [webApplicationExercise-bootstrap](../shoppingcart-bootstrap/webApplicationExercise-bootstrap/) project into your project. 
3. Check that the css and js resources are present.
4. Now look at your home.jsp and consider how to change it to fit into the framework.
5. If you have added extra pages for receipts etc you should also adapt them to use the framework

As always, an example answer is provided [webApplicationExercise3-answer](../shoppingcart-bootstrap/webApplicationExercise3-answer )
 but dont look at this until you have tried it yourself.

## Congratulations

You have come along way in understanding how applications can be built using JSP technology. 
The starting point is usually the web design process which generates the look and feel of the app using stubbed pages and css etc. 
These can then be turned into JSP pages and the business logic added to animate the app.
As we have seen, often frameworks like bootstrap are used to simplify this process and give the application a standard 'look and feel'.

In our next exercise we are going to look at another framework, SpringMVC which can further separate the concerns of the design process from the development process.
