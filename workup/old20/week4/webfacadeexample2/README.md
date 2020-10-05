
# New Project Layout

If you examine the project you will see that it now has additional modules. These are built in sequence in the pom.xml file in the modules section
```
    <modules>
        <module>model</module>
        <module>dao-simple</module>
        <module>dao-jaxb</module>
        <module>service</module>
        <module>web</module>
    </modules>
```
This week we will only look at using the simple DAO in the dao-simple module. 
The dao-jaxb module is empty.

The model has been refactored into three packages corresponding to the data transfer objects, the dao interfaces and the service/facade interfaces. 

The FarmFacade interface implements much the same methods as in previous exercises and will be accessed by the JSP's. 
However the implementation of the FarmFacade will now have to use the DAO implementation classes to access the data from the underlying persistance layer.
```
org.solent.com504.factoryandfacade.model.dto
    Animal.java
    AnimalList.java
    AnimalType.java

org.solent.com504.factoryandfacade.model.dao
 	AnimalDao.java
	AnimalTypeDao.java

org.solent.com504.factoryandfacade.model.service
    FarmFacade.java
    ServiceObjectFactory.java
```
The details of these interfaces are described in the [UML model](../webfacadeexample2/UMLfactoryandfacade/).
Each interface and dto also has some javadoc comments to describe their function.


# Building the project and doing exercises

## Task 1 Build compile and import the project
In order to work with this project, you should first build it using maven
```
cd webfacadeexample2
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

```
cd web
mvn org.codehaus.cargo:cargo-maven2-plugin:run
```
If you open a browser and browse to http://localhost:8080/basicfacadeweb/
you will see the index page of the application.

You can stop the browser using control c (ctrl-c).

Please note that when you run the example this way you will need to rebuild the project and restart tomcat
every time you make changes otherwise the changes will not be packaged in a war and run by tomcat.

### To run the project using  in Netbeans embedded Tomcat server

If you right click on the webfacade-example1 project in netbeans and select 'run' netbeans will spin up a tomcat instance and launch your application.

If you are asked for username and password just use the default admin admin.
 
(You must make sure you have stopped any maven started tomcat before you do this.)

You will be able to see the application at http://localhost:8080/basicfacadeweb/

## Task 3
Look at the provided implementation of the DAO classes in simple-dao.
These simple classes do not actually persist anything but store their data locally in ArrayList objects.
Review these clases and understand how they work. 
Look at the related tests and understand how they work

## Task 4
Implement the unimplemented methods in the service classes and write / modify tests to proove that the service methods work
FarmFacadeImpl.java
	ServiceObjectFactoryImpl.java

## Task 5
Last weeks JSP's which you developed should work with the service classes - but you will need to make some small modifications.
Modify the JSP's to work with the new service classs FarmFacadeImpl.java and ServiceObjectFactoryImpl.java in order to implement the same functionsality as last week.




