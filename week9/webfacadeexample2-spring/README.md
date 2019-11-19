
# Project using Spring MVC

This version of the project is beginning to introduce SpringMVC as a model view controller for the JSP's.
This project illustates a partial migration to Spring where elements of the legacy code still remain but are integrated with the new code using Spring.
This represents a realistic situation where new technology is introduced in controlled stages and the existing code is 'refactored' to use the new framework.

In this example, we have kept the original JSP's and Object Factorys but have introduced new Spring MVC based JSP's running along side. 
This means that you can compare the old and new code to see how the new functionality implements the same features as the old.
The old JSP's continue to work along side the new.

## Changes in this code

The majority of the changes in this example are in the web module as shown below 
```
web
   src
      main
         java
            org.solent.com504.factoryandfacade.impl.rest
                RestApp.java
                RestService.java      // NEW ANNOTATIONS FOR BEANS
            org.solent.com504.factoryandfacade.impl.web
                HelloWorld.java       // NEW - SIMPLE CLASS TO HELP EXPLAIN/DEBUG APPLCATIONCONTEXTS
                ViewController.java   // NEW - SPRING MVC CONTROLLER
                WebObjectFactory.java
      resources
         applicationContext.xml       // NEW - ROOT APPLICATION CONTEXT
         log4j2.xml
         mvc-dispatcher-servelet.xml // NEW - CHILD APPLICATION CONTEXT FOR MVC DISPATCHER
   webapp
      WEB-INF
         views  // NEW JSP'S WHICH ARE ONLY VIEWS WITH NO BUSINESS LOGIC
            farmlist.jsp
            reviewAnimal.jsp
         web.xml // CONFIGURATION TO SET UP MVC DISPATCHER SERVLET
      images
         Village-Farm.svg
      farm.jsp
      farm2.jsp
      index.jsp
      viewAnimal.jsp


```
The project's pom.xml files have also been updated to include some new depencencies.

## Spring application context

The first major change is the introduction of a spring application context which is intended to replace all references to ObjectFactories in the new code. 
When we remove the old code, we will remove all of the object factories but in the interim, the spring code uses the object factories to access the underlying lbraries.

## Spring MVC
A new class, 

### Jersey
The version of jersey has been updated to allow it to work along side Spring MVC. 
The pom.xml depencency jars for the later version of Jersey have been changed but the code is substantially the same.











