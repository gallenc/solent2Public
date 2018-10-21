# Week 5 - Creating a Web UI using JSP  

This week we will introduce Java Server Pages and show how they can be used to create a web UI.
To do this we will add a web ui to the doorlock project we worked on as a TCA last week.

In the completed examples TCA, we added MAIN.java classes to provide a simple Command Line Interface for our project. 
Like the tests, these CLI classes used the same service layer to provide the business logic for the application.

Figure 1 below illustrates that we can also use this service layer to drive a web UI using Java Server Pages (JSPs).

## Java Server Pages
Java Server Pages are a complex technology but fortunately, we only need to understand a small subset to make a working application.

Will only do a very brief introduction here. 

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

![alt text](../week5/drawio/WebContainers.png "Figure WebContainers.png")

## Web Application Servers and Jetty
Many web application servers are available including Apache Tomcat http://tomcat.apache.org/ and Eclipse jetty https://www.eclipse.org/jetty/

In theory war's should be interchangeable between application servers and work just as well. 
Unfortunately there are subtle differences between servers in the web.xml and other configuration files so each war needs to b targeted on a particular server. 

In our project we will be using jetty as our application server. 

We are using the maven jetty plugin (https://www.eclipse.org/jetty/documentation/9.4.x/jetty-maven-plugin.html) to very quickly download and spin up jetty instances for testing our code. 

## Exercises

### Exercise 1 - try running a hotel reception JSP in jetty

move week5 code into your myPracticeCourseWork folder.

You will see that there is a new module called lock-web

Build the compete project using mvn clean install on the hotellock-parent project

Remove all previous versions of the hotellock-parent project and sub projects and import the new hotellock-parent project in to netbeans.
Note that the version number has moved up to 0.2-SNAPSHOT

Look at the structure of lock-web as folders and as it appears as a project in netbeans.
You will see a new source folder called webapp
```
lock-web
   src
      main
        java
        resources
        webapp
           WEB-INF
                web.xml
           DoorLockPage.jsp
           Receptionpage.jsp
           index.html
```

Now cd into the lock-web project and run the command
```
mvn jetty:run
```
You will see output suggesting that a jetty web server is downloading and starting up.

In a firefox browser navigate to http://localhost:8680/

The jetty web server will serve up the static index.html which is a welcome page

Click on 'To generate a key card click here' to open a new tab which is rendering the java server page ReceptionPage.jsp

Try generating and reading a card using the forms on this page.

### Exercise 2 Run the application in a stand alone jetty instance
The previous exercise ran the web application using maven in an embedded jetty server. 
In production, we would use a stand alone server and not maven to deploy the app. 
We will now show you how to run the web application in a stand alone jetty server.

Download the jetty .zip file from here https://www.eclipse.org/jetty/download.html and unpack it into a folder on your U: drive.

You will also need three Log4J jars
```
log4j-api-2.11.0.jar  log4j-core-2.11.0.jar  log4j-slf4j-impl-2.11.0.jar
```
 which you can download from here.
https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api/2.11.0

https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j-impl/2.11.0

https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core/2.11.0

place all three log4j jars in the unpacked demo-base\lib\ext\ folder
```
jetty-distribution-9.4.12.v20180830\demo-base\lib\ext
                log4j-api-2.11.0.jar
                log4j-core-2.11.0.jar
                log4j-slf4j-impl-2.11.0.jar
```

now start the web application server from the demo-base folder
```
cd demo-base
java -jar ../start.jar
```
You will see lots of output until the server starts up.

Browse to http://localhost:8080 where you will see some demo applications deployed

Now, having compiled the complete hotellock-parent project using mvn clean install, copy the war file from
```
... solent2Public\week5\hotellock-parent\lock-web\target\lock-web-0.2-SNAPSHOT.war
```
into the jetty demo-base\webapp directory
```
jetty-distribution-9.4.12.v20180830\demo-base\webapps\lock-web-0.2-SNAPSHOT.war
```

After lots of deployment information, you should see that the container has deployed the app.

To run the application browse to http://localhost:8080/lock-web-0.2-SNAPSHOT/

### Exercise 3 Complete the Door Lock JSP code

Read carefully the code in the ReceptionPage.jsp and make sure you understand it.

Note that the session object is used to store both the room number and the hotelReceptionService once for all page requests in the session.

The page contains two web forms which are submitted by buttons; one for generating a new card and one for reading a card. 

The request parameters are read by the JSP to see whether it is to run code to create a new card or just read a card and the request parameters are used to provide input to the card generation process..

Using ReceptionPage.jsp as an example, try to add JSP code to the DoorLockPage.jsp to allow you to set the door number and open the door with the correct key code.