# Session 5

This week is mainly for consolidation of previous work but I wanted to introduce a simple Data Access Object Pattern (DAO).
DAO's are objects which are use to access an underlying persistance mechanism.
DAO's can access a database but i this case we are accessing a properties file through a Java Properties object.

You can see the javadoc for Properties here https://docs.oracle.com/javase/8/docs/api/java/util/Properties.html

You can learn about using Properties files here https://mkyong.com/java/java-properties-file-examples/

## example applicaton
A simple web applicaton is provided which loads and saves properties to the tomcat temp directory. 

In the example code you will see how a java Properties object is being used to load a properties file when the application starts and save an updated file when the properties are changed using the jsp.

## Exercise 1

See if you can add additional credit card information to the properties file and update these properties on the web page

## Exercise 2

See if you can modify the DAO to load a default.properties file from the class path if the application.properties file doesn't exist in the tomcat temp location.

Tip see the section on loading from the classpath in https://mkyong.com/java/java-properties-file-examples/

Any properties file placed in the /src/home/resources directory will be visable to the application on the class path.
