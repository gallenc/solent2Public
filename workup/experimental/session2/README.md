# Introduction to Java Server Pages

One of the oldest but still widely used Java web technologies is Java Server Pages or JSP. 
A java web application server like tomcat can serve a mixture of html and JSP pages and any other content such as images or CSS or javascript which form part of a page.
JSPs are documents which contain a mixture of HTML and Java code.
The java code is used to dynamically change the html which is rendered by the server for each web request.
This is very similar to other web technologies such as PHP. 
The main difference from PHP is that instead of being interpreted each time they are called, 
JSPs are compiled into java code when they are first requested and cached for later use. 
This means that subsequent requests to a JSP page are very fast.

## Overview: Java Server Pages on web containers
Java Server Pages are a complex technology but fortunately, we only need to understand a small subset to make a working application.

We will only do a very brief introduction here. 

There are many JSP tutorials on line (e.g. https://docs.oracle.com/javaee/1.3/tutorial/doc/JSPIntro11.html) and you are strongly recommended to spend some time familiarising yourself with JSP's after class

Figure 2 explains the architecture of a Web Container which hosts JSPs. 
A Web Container allows multiple Web Applications to coexist on the same server. 
Each web application is contained in a zip file called a Web Application Resource file with the suffix .war.

A war contains static web resources (such as index.html or css files), dynamic web resources (JSPs) and an xml deployment descriptor called web.xml.
It may also contain a number of precompiled classes and/or a /lib directory which can contain jar archives.

The contents, libraries and class path of each war are treated completely independently with separate class loaders by the web container.

Figure 3 illustrates the message flow and object creation in handling a web request.

Java Server Pages are very similar in concept to PHP web pages. 
Each page contains a mix of HTML elements and escaped java code.
When a jsp is first accessed, the web container converts it 'on the fly' to a java file called a servelet which is then compiled into a runnable class. 

The servelet is given access to a session object and a request object. 

The session object lasts for the duration of the session i.e. from the first request until the browser session is closed down or times out.
Session objects are used to store objects which will be used across multiple page requests.

The request object contains the parameters of the HTTP request and is used by the code in the jsp to create a response. 

The servelet is expected to return a response object which will contain the correctly coded HTML web page to be returned to the client browser.

![alt text](../session2/drawio/WebContainers.png "Figure WebContainers.png")

## Web Application Servers and Tomcat
Many web application servers are available including Apache Tomcat http://tomcat.apache.org/ and Eclipse jetty https://www.eclipse.org/jetty/

In theory war's should be interchangeable between application servers and work just as well. 
Unfortunately there are subtle differences between servers in the web.xml and other configuration files so each war needs to b targeted on a particular server. 

In our project we will be using Apache Tomcat as our application server. 

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