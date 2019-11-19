# Week 9 Using Spring MVC and Inversion of Control

## Spring
We have already introduced all of the Object Orientated Design and Java technology you will need to create an adequate working web application.
However, it would be fair to say that most professional java EE web application design uses a number of more advanced techniques and libraries which we will introduce more formally in the remainder of the course. 
Most of these techniques can be implemented using the Spring Framework but other libraries provide similar capabilities.

As you may have gathered, Spring is a complex framework but it is very well worth learning as it is widely used by java developers. 

In the final weeks of this course, we will introduce Spring to the extent that you will be equipped to learn further by yourself. 
Spring provides many tools but we will concentrate on 
* Inversion of Control using application contexts 
* Model View Controllers using SpringMVC
* Simplified DAO design using SpringData 

There are many tutorials on line and comprehensive documentation at https://docs.spring.io/spring/docs/5.2.1.RELEASE/spring-framework-reference/

Please note that many introductory Spring tutorials use [Spring Boot](https://spring.io/projects/spring-boot "spring-boot documentation") which is a fantastic rapid development framework for microservices. 
However I have deliberately avoided using Spring Boot in my examples because it hides a lot of the complexity of what Spring is actually doing for you which I think it is important for you to be aware of.

## Application Contexts and Object Factories
We introduced Object Factories early in the course, and you will have seen that they are widely used in libraries as a technique to create pre-configured graphs of objects which conform to a standardised interfaces. 
For instance, the logging frameworks tend to use static object factories to create logs. 
```
final static Logger LOG = LogManager.getLogger(HelloWorld.class);
```
In our application we have used object factories to allow us to chose between different implementations of each layer as they have successively been developed each week. For instance, we created data access objects using flat files (xml with JAXB), Databases (using JDBC and also JPA DAO's).
The upper layers could use any one of these implmentations depending on the configuration of the object factory for that layer. 

However too many object factories can become cumbersome and it can be difficult to trace through to see where objects are actually being created and configured in each layer.
Further more in a large application we would rather not have to modify and compile code to change which library we are using for each layer. 

Spring uses a technique called Inversion Of Control to allow us to avoid having multiple object factories.
The spring application context is a single object factory which creates, configures and 'wires' all of the classes in our application according to a single recipe which is often defined in an applicationContext.xml file. 

(Spring Boot has popularised an alternative application context configuration using java application configuration classes having annotations instead of an external xml configuration. 
This has its attractions but my preference is still to use an external XML configuration because it avoids re-compiling the application and it keeps all of the configuration in one place and not scattered through out the code).

In a spring application, there may be several application contexts.
In our example we have a main applicationContext which sets up the primary services.
We also have a web application context which configures the model view controller. 
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

## using Spring with existing code
Please note that often when we are introducing Spring to an existing application, we do not change everything at once.
It is useful to be able to initially use existing object factories which are known to work and replace them later.
In the example this week, this is the approach we have taken. 
We have 'refactored' code to allow us to use Spring but we have made the Spring configuration re-use the existing object factories so that we can change things in a controlled way without introducing new bugs.

# Model View Controllers using SpringMVC

![alt text](../week9/images/SpringMVC_Architecture.png "Figure SpringMVC_Architecture.png" )










