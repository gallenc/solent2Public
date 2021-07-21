# Introduction to Java Server Pages

One of the oldest but still widely used Java web technologies is Java Server Pages or JSP. 
A java web application server like tomcat can serve a mixture of html and JSP pages and any other content such as images or CSS or javascript which form part of a page.
JSPs are documents which contain a mixture of HTML and Java code.
The java code is used to dynamically change the html which is rendered by the server for each web request.
This is very similar to other web technologies such as PHP. 
The main difference from PHP is that instead of being interpreted each time they are called, 
JSPs are compiled into java code when they are first requested and cached for later use. 
This means that subsequent requests to a JSP page is very fast

# Exercise 1 - a simple web app

Before you do anything else, copy this folder ..\solent2Public\Week2 into your own practice area MyPracticeCourseWork\Week2
Remember you should do all of your own work in the MyPracticeCourseWork folder and do not change any other files so that you can update your work each week using the class examples. 

In this exercise we will use Netbeans to create a new empty maven web application project which we will use to explore JSP's.

## 1. Create a new web application project

a) select file > new Project > Java with Maven > Web Application

![alt text](../Week2/images/webcreate1.png "Figure webcreate1.png" )

b) Select where you will created the project. 

You need to make sure this is created in your forked repository under MyPracticeCourseWork\Week2

c) Fill in the project details as shown in the diagram 

The groupId and the artifactId uniquely identify the project within a maven repository. 
Usually the groupId contains a name space associated with your organisation or project.
You can see that Netbeans automatically creates a java package using the group id and artifact id.
Use the names I have provided.  

![alt text](../Week2/images/webcreate2.png "Figure webcreate2.png" )

d) click next and accept the default options for tomcat and javaEE

![alt text](../Week2/images/webcreate3.png "Figure webcreate3.png" )

## 2. Explore your project

If all goes well, you should now have a new project created in the location you specified and visible in the netbeans IDE.

Every maven project follows the same conventions for laying out a project but each IDE may display this information differently.
It is important to know where your files really are located in your project and how the IDE is displaying them.

Netbeans provides two views of projects; A project view and a files view

The Files view shows you exactly where each part of the project is created and stored on your computer
You can see that the java classes are stored in a nested tree of folders corresponding to the package name of each class.
![alt text](../Week2/images/webcreate4.png "Figure webcreate4.png" )

The Project View can make it a bit easier to navigate around the logical layout of the project.
You can see that the folder tree is collapsed into a package under Source Packages. 
![alt text](../Week2/images/webcreate5.png "Figure webcreate5.png" )

However this can can also be confusing. 

For instance in the project view you will see a folder labelled Web Pages. 
In the Files view this is actually held in the /src/main/webapp folder.

Similarly the pom.xml file is in the root of your project but is displated under Project Files in Netbeans.

## maven pom.xml
Every maven project has a pom.xml file which tells maven (and Netbeans) how to build, test and deploy your project.
This course uses maven because it gives us a degree of indepencence from the IDE we choose to use. 
In this example we will use the generated pom.xml file but we need to add a little extra information into the pom.xml

We have provided a .gitignore file in your repository which is designed to prevent any IDE specific artifacts from being committed to the repository.
This prevents the target directory being committed and also prevents the netbeans specific nb-configuration.xml file being committed when we save our project.

Netbeans will use the pom.xml to recreate this nb-configuration.xml file when it next imports your project. 

We need to add a little information into the pom.xml to tell netbeans which application server to deploy our application to. 
Add the following lines to the properties section of the pom.xml

```
    <properties>
        <endorsed.dir>${project.build.directory}/endorsed</endorsed.dir>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>

        <!-- You can copy and paste the single properties, into the pom.xml file and the Netbeans will pick them up. -->
        <org-netbeans-modules-maven-j2ee.netbeans_2e_hint_2e_j2eeVersion>1.5</org-netbeans-modules-maven-j2ee.netbeans_2e_hint_2e_j2eeVersion>
        <org-netbeans-modules-maven-j2ee.netbeans_2e_hint_2e_deploy_2e_server>Tomcat</org-netbeans-modules-maven-j2ee.netbeans_2e_hint_2e_deploy_2e_server>
    </properties>
```

## web application

The webapp folder (Web Pages) contains all of the web artifacts in our project.
Anything at the root of the webapp folder will be available for rendering.
Anything in the META-INF or WEB-INF folders is used by the web server but cannot be directly accessed using a web request.

Java web applications follow a general pattern but unfortunately the configuration files can change from server to server and between versions of Java EE targeted.
(For instance earlier applications may have a web.xml file while later versions use alternative configuration methods). 
Just be aware of this and perhaps use google to find example configurations for differnt servers as neccessary.

In this example, the META-INF/context.xml file tells the server what the root of our web application will be.
```
<?xml version="1.0" encoding="UTF-8"?>
<Context path="/webExercise1"/>
```
This tells the server that this web app will be available at http://localhost:8080/webExercise1

the server will always serve any index.html as an entry page to our application so

http://localhost:8080/webExercise1 will be the same as http://localhost:8080/webExercise1/index.html

## 3. Running your project.

a) To run the application, right click on the webExercise1 project and select 'run'.

The log output will tell you that tomcat is starting up adn that thed application is being deployed. 
Once deployed, the application will be available at http://localhost:8080/webExercise1/index.html
You should see 'hello world'.

b) add a new web page
You can add a new html page, simply be right clicking on Web Pages and select add Html. 
call the new page newhtml.html

You should be able to see this at http://localhost:8080/webExercise1/newhtml.html

You can edit this page and see the changes in the running browser.

(for more help on html see https://www.w3schools.com/html/)

## 4. Adding a JSP

We have seen how the application handles simple html now we will create a simple jsp.

Right click on Web Pages and select add JSP and name the file newjsp

![alt text](../Week2/images/webcreate6.png "Figure webcreate6.png" )

You should see a newjsp.jsp page with the following content.

```
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
```

You can view this page by browsing to http://localhost:8080/webExercise1/newjsp.jsp
You will see that the jsp looks very like a simple html file with the only difference being the  @page directive line.
Infact you can turn any html page into a jsp by changing its suffix to .jsp and adding the  @page directive line.
 
Often the development process is to create a mock web app with it's stubbed html pages and css. 
These are then turned into jsp's and the programming logic is added.

We mentioned that jsp's mix java code with html. Add the following line into the body of the page and refresh your browser.
```
```
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>the time is <%= new Date() %> </p>
    </body>
</html>
```
```
You should now see the date and time printed out and every time you refresh the page, the seconds should increment.

The <%= %> directive tells the server that java code between the <%= %> will return a string to include in the page.

the <%@ page import="java.util.Date" %> directive imports the class in a similar way to the java import directive in a java class file.


