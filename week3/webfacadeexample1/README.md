
# Java Web JSP and facade

This week we will begin to look at Java Server Pages Technology and how the services we create last week can be used as a controler service running behind a web page view using very basic JSP.

In order to work with this project, you should first build it using maven
```
cd webfacadeexample1
mvn clean install
```
We are doing the build first so that netbeans has the dependencies already downloaded when it opens the project.

Now you will need to import the poject into Netbeans using 'open existing project'

![alt text](../UMLfactoryandfacade/images/facadediagram2.png "Figure facadediagram2.png" )

Remember to click 'open required projects' so that the parent project and its subprojects are all imported.

# Task 1 Running the project

You can run the project using maven or directly in the Netbeans IDE.
Please note that you cant do both at the same time due to port clashes.
If you do run into problems restart Netbeans.

## to run the project using maven

Maven used the cargo plugin to start an embedded tomcat container.
You must run the following command after your build in the web directory

```
cd web
mvn org.codehaus.cargo:cargo-maven2-plugin:run
```
If you open a browser and browse to http://localhost:8080/basicfacadeweb/
you will see the index page of the application.

You can stop the browser using control c (ctrl-c).

Please note that when you run the example this way you will need to rebuild the project and restart tomcat
every time you make changes otherwisethe changes will not be packages in a war and run by tomcat.

# to run the project using Netbeans

If you right click on the webfacade-example1 project in netbeans and select 'run' netbeans will spin up a tomcat instance and launch your application.

If you are asked for username and password just use the default admin admin.

(You must make sure you have stopped any maven started tomcat before you do this.)

You will be able to see the application at http://localhost:8080/basicfacadeweb/

# Task 2

See if you can add a form to the example2.jsp so that a user can input a name of an animal and add it to the list.

note the lines
```
    // accessing request parameters
    String animalNameStr = request.getParameter("animalName");
    String animalTypeStr = request.getParameter("animalType");
```
These allow you to access url parameters or POST form parameters which are included in any page request

The following URL http://localhost:8084/basicfacadeweb/example2.jsp?animalType=emue&animalName=Fred

will add emue and fred to the page. You should be able to use this as a starting point for using the facade object to add animals to the farm.

# Task 3

See if you can add another field for each animal to add it's address. Note you will need to change the animal model class and test this before adding to the JSP.



