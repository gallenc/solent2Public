
# Java Web JSP and facade

This week we will begin to look at Java Server Pages Technology and how the services we create last week can be used as a controler service running behind a web page view using very basic JSP.

JSP's contain a mix of HTML and Java elements. 
Usually when you design a JSP, you start of with a raw HTML page, CSS and potentially javascript  which define the look and feel of the page. 
You then add  JSP specific elements which implement the dynamic behaviour.

## Task 1 Build compile and import the project
In order to work with this project, you should first build it using maven
```
cd webfacadeexample1
mvn clean install
```
We are doing the build first so that netbeans has the dependencies already downloaded when it opens the project.

Now you will need to import the poject into Netbeans using 'open existing project'

![alt text](../webfacadeexample2/images/NetbeansOpenProject.png "Figure NetbeansOpenProject.png" )

Remember to click 'open required projects' so that the parent project and its subprojects are all imported.

##  Task 2 Running the project

You can run the project using maven or directly in the Netbeans IDE.
Please note that you can't do both at the same time due to port clashes.
If you do run into problems restart Netbeans.

### To run the project using maven

Maven used the cargo plugin to start an embedded tomcat container.
You must run the following command after your build in the web directory

(Make sure you have turned off tomcat in netbeans so that the ports in use dont clash)

```
cd web
mvn org.codehaus.cargo:cargo-maven2-plugin:run
```
If you open a browser and browse to http://localhost:8080/basicfacadeweb/
you will see the index page of the application.

You can stop the browser using control c (ctrl-c).

Please note that when you run the example this way you will need to rebuild the project and restart tomcat
every time you make changes otherwise the changes will not be packaged in a war and run by tomcat.

### To run the project using  in Netbeans embedded Jetty server

If you right click on the webfacade-example1 project in netbeans and select 'run' netbeans will spin up a tomcat instance and launch your application.

If you are asked for username and password just use the default admin admin.
 
(You must make sure you have stopped any maven started tomcat before you do this.)

You will be able to see the application at http://localhost:8080/basicfacadeweb/

browse to  http://localhost:8080/basicfacadeweb/example1.jsp 



## Task 4 adding extra fields

See if you can add another field for each animal in order to to add it's address. Note you will need to change the animal model class and test this before adding to the JSP.



