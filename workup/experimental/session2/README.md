# Introduction to Java Server Pages

One of the oldest but still widely used Java web technologies is Java Server Pages or JSP. 
A java web application server like tomcat can serve a mixture of html and JSP pages and any other content such as images or CSS or javascript which form part of a page.
JSPs are documents which contain a mixture of HTML and Java code.
The java code is used to dynamically change the html which is rendered by the server for each web request.
This is very similar to other web technologies such as PHP. 
The main difference from PHP is that instead of being interpreted each time they are called, 
JSPs are compiled into java code when they are first requested and cached for later use. 
This means that subsequent requests to a JSP page are very fast.

## Installing tomcat server on Netbeans
Before we can have a play with JSP's we need to install the Tomcat server in your netbeans environment.
Tomcat and Netbeans are installed in your university machine but they are not linked. 
You need to supply the link to your own account.

1. On Netbeans, right click on the Services tab. 
2. Then select ADD SERVER.
3. Choose 'Tomcat or TomEE'
3. Fill in the form as shown in the figure below

![alt text](../session2/images/installTomcatservernetbeans.png "Figure installTomcatservernetbeans.png" ) 

The base directory for tomcat CATALINA_HOME is C:\devel\tomcat\apache-tomcat-9.0.48
This is where tomcat code is installed.

On your own machine, you won't need to define a working directory but on the University machines you need to supply a default working directory CATALINA_BASE for which you have write permissions so that you can start and use tomcat from netbeans. 

set CATALINA_BASE  to C:\Users\YOUR USER NAME\devel\catalinabase

Select 'create user if user does not exist' 
username admin
password admin

Select finish you should see a new server show up under servers

Now right click on Servers > 'Apache Tomcat or TomEE' and select Start

You should be able to browse to a tomcat generated web page at http://localhost:8080/

## Exercises

The following exercises will introduce you to writing your own JSPs. 

[Exercise 1](../session2/Exercise1.md) is a simple introduction to JSP's. Try this first.

[Exercise 1b](../session2/Exercise1b.md) takes the example exercise further looking at the request object.

[Exercise 1c](../session2/Exercise1c.md) takes the example exercise further looking at the session object.

Try these exercises on your own first then look at the example answers here [webExercise1-answer](../session2/webExercise1-answer)