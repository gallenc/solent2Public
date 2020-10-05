# Week 9 Using Spring MVC and Inversion of Control

## Spring
We have already introduced all of the Object Orientated Design and Java technology you will need to create an adequate working web application.

However, it would be fair to say that most professional java EE web application designers uses a selection of more advanced techniques and libraries which we will introduce more formally through the remainder of the course. 
Most of these techniques can be implemented using the Spring Framework but other libraries provide similar capabilities.

As you may have gathered, Spring is a complex framework but it is very well worth learning as it is widely used by java developers. 

In the final weeks of this course, we will introduce enough Spring so that you will be equipped to learn further by yourself. 
Spring provides many tools but we will concentrate on;
* Inversion of Control using Application Contexts and java Annotations
* Model View Controllers using SpringMVC
* Simplified DAO design using SpringData 

There are many tutorials on line and comprehensive documentation at https://docs.spring.io/spring/docs/5.2.1.RELEASE/spring-framework-reference/

Please note that many introductory Spring tutorials use [Spring Boot](https://spring.io/projects/spring-boot "spring-boot documentation") which is a fantastic rapid development framework for microservices. 
However I have deliberately avoided using Spring Boot in my examples because it obscures a lot of the complexity of what Spring is actually doing under the hood which I think it is important for you to be aware of.

## Application Contexts and Object Factories
We introduced Object Factories early in the course, and you will have seen that they are widely used in libraries as a technique to create pre-configured graphs of objects which conform to standardised interfaces. 
For instance, the logging frameworks tend to use static object factories to create logs; 
```
final static Logger LOG = LogManager.getLogger(HelloWorld.class);
```
In our application we have used Object Factories to allow us to chose between different implementations of each successive software layer developed each week. 
For instance, we created data access objects using flat files (xml with JAXB), Databases (using JDBC and also JPA DAO's).
The upper layers could use any one of these implmentations depending on the configuration of the Object Factory for that layer. 

However too many object factories can become cumbersome making it difficult to trace through code to see where objects are actually being created and configured for each layer.
Further more in a large application we would rather not have to modify and compile code to change which library we are using for each layer. 

Spring uses a technique called Inversion Of Control to allow us to avoid having multiple Object  Factories.
The spring application context is a single Object Factory which creates, configures and 'wires' all of the classes in our application according to a common recipe which is often defined in an applicationContext.xml file. 

(Spring Boot has popularised an alternative application context configuration using java application configuration classes having annotations instead of an external xml configuration. 
This has its attractions but my preference is still to use an external XML configuration because it avoids re-compiling the application and it keeps all of the configuration in one place and not scattered through out the code).

In a spring application, there may be several application contexts.
In our example we have a main applicationContext which sets up the primary services used by all layers.
We also have a web application context which configures the model view controller for the web layer. 
This web application context inherits all of the objects in the main application context as shown below.

![alt text](../week9/images/mvc-context-hierarchy.png "Figure mvc-context-hierarchy.png" )

## Inversion of Control Example
The primary benifit of inversion of control is that individual classes do not need to worry about finding or creating the objects on which they rely. 
Instead the controller (application context) sets up all of the relationships on the classes behalf when the class is created.
The controller may also cleanly shut down all of the classes when the application is shut down.

As an example, look at the simple 
[HelloWorld.java](../week9/webfacadeexample2-spring/web/src/main/java/org/solent/com504/factoryandfacade/impl/web/HelloWorld.java )
class which is part of the example web applicaton.
```
public class HelloWorld {

    final static Logger LOG = LogManager.getLogger(HelloWorld.class);
    
    private String message="";

    public void setMessage(String message) {
        this.message = message;
    }

    public void init() {
        LOG.debug("init() Application context started HelloWorld "+message);
    }

    public void destroy() {
        LOG.debug("destroy() Application context stopped HelloWorld "+message);

    }

}
```

Look also at the 
[applicationContext.xml](../week9/webfacadeexample2-spring/web/src/main/resources/applicationContext.xml ) 
file which describes how the HelloWorld class is initialised
```
   <bean id="helloWorld" class="org.solent.com504.factoryandfacade.impl.web.HelloWorld" init-method="init" destroy-method="destroy">
        <property name="message" value="main applicationContext" />
    </bean>
```
You will see that Spring sets the value of the property message to the string value 'main applicationContext'

After setting all of the properties, Spring will call the init-method which will print out a message.
In the logs we will see
```
11:04:24.883 [localhost-startStop-1] DEBUG org.solent.com504.factoryandfacade.impl.web.HelloWorld - init() Application context started HelloWorld main applicationContext
```
When the application shuts down ( i.e. tomcat stops or the war is undeployed), Spring will call the destroy-method which also prints out a message. 

Obviously, we would use these create and destroy methods for more useful purposes.

## Using Spring with existing code
Please note that often when we are introducing Spring to an existing application, we do not change everything at once.
It is useful to be able to initially continue to use existing Object Factories which are known to work and replace them later.
In the example this week, this is the approach we have taken. 
We have 'refactored' code to allow us to use Spring but we have made the Spring configuration re-use the existing Object Factories so that we can change things in a controlled way without introducing new bugs.

## web.xml
In our example, the 
[web.xml](../week9/webfacadeexample2-spring/web/src/main/webapp/WEB-INF/web.xml ) file is used to initialise the application when the WAR is deployed. 
This creates the Spring Application Contexts and registers the Spring Dispatcher servelet with the tomcat web container. 
With newer web containers (Servelet spec 3.0+) it is possible to avoid the use of web.xml configuration. 
However we have kept the web.xml because it makes it simpler to understand what is happening when the application boots.

# Model View Controllers using SpringMVC

A very commonly used design pattern for user interfaces is the Model View Controller. 
The rationale for this pattern is that our web designers are typically artist or designers and not software developers.
Therefore we would like to make it possible for them to work with CSS and HTML etc without having to understand very much about the programming of the application.
This enables the 'seperation of concerns' between differnt contributing skills.

In addition, we have seen that it is benificial to create our application as a set of interfces (facades) on top of the business logic. 
This allows us to create a 'service' representing core business logic which can be consistently used by any UI or machine to machine (i.e. ReST interface) layer above the service.

To a certain extent we have been able to seperate the design and business logic by using JSP technology which imbeds java within HTML.
However in our examples, the JSP's still contain a lot of java code and dont really exhibit 'seperation of concerns'.

The ideal world is illustrated below where we have a Model-View-View Model design. 

![alt text](../week9/images/330px-MVVMPattern.png "Figure 330px-MVVMPattern.png" )

The main Model is maintained and controlled by the underlying service layer. 

However having a view model which is specifically for the presentation layer could be very useful. 
In this design, the 'View' becomes very simple JSP's which display a View Model that is maintained by a controller.
Because the JSP's are simple, they are easy to maintain by a non-developer.
The developers develop the controller which is concerned with the 'page flow' and the data presented to each page in the form of a model

This sounds complex but infact Spring makes it quite simple.
![alt text](../week9/images/SpringMVC_Architecture.png "Figure SpringMVC_Architecture.png" )

Within spring we define a view controller which is very like the JAX-RS ReST interface we worked on earlier. 
However instead of returning XML, each method in the view controller returns a view (jsp) populated with a model containing the data.

In Spring our web application has a Dispatcher servelet which decides which method in a controller to call based upon the URL of the request. 
The Controller chooses which JSP view to use as a view and returns a model containing data to use with that page.

This effectively takes all of the code out of the JSP.

An additional step is to use the [JSP Standard Tag Library (JSTL)](https://www.javatpoint.com/jstl "thymeleaf-in-spring-mvc")

It also has the advantage that we can use view technologies other than JSP's such as [thymleaf](https://www.baeldung.com/thymeleaf-in-spring-mvc "thymeleaf-in-spring-mvc") which are popular in Spring boot.

This completes our introduction to SpringMVC.

There are many on line tutorials to help you understand the updated code

In the final session we will be looking at SpringDATA can be used to enhance the JPA based DAO's

# Exercise

1. review the example project
The example project [webfacadeexample2-spring](../week9/webfacadeexample2-spring/ ) has been updated to use SpringMVC.

Go through this project to see if you can understand the changes which have been made.

2. add spring mvc to the client-web module
This will be challenging but see if you can modify the client-web module to also use Spring MVC. 

You will need to;
* add a Controller class, 
* add new views/*.jsp files
* add mvc-dispatcher-servlet.xml applicationContext and dispatcher configurations in web.xml
* make sure you have the right dependencies in the pom.xml file.



