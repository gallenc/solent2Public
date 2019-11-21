
# Project using Spring MVC

This version of the project is beginning to introduce SpringMVC as a model view controller for the JSP's.
This project illustates a partial migration to Spring where elements of the legacy code still remain but are integrated with the new code using Spring.
This represents a realistic situation where new technology is introduced in controlled stages and the existing code is 'refactored' to use the new framework.

In this example, we have kept the original JSP's and Object Factorys but have introduced new Spring MVC based JSP's running along side. 
This means that you can compare the old and new code to see how the new functionality implements the same features as the old.
The old JSP's continue to work along side the new.

## running the application
As usual, build the whole project using 
```
mvn clean install
```
Then open the project and all its subprojects in Netbeans and run both the webfacadeexample2-web and webfacadeexample2-web-client projects in the embedded tomcat server.
Please note that there will be a number of errors with the words
```
ClassFormatException: Invalid byte tag in constant pool: 19
```
Ignore these exceptions as they are caused by the old version of Tomcat embedded in Netbeans 8.2

The webfacadeexample2-web-client project is substantially unchanged but a number of changes have been made to the webfacadeexample2-web project as described below.

Browse to http://localhost:8084/basicfacadeweb/ 

You will see new lines in the index page
```
More Complex Farm Example using Spring MVC
click on farmhome to open application using Spring MVC 
```
The springMVC version of the application is accessed from http://localhost:8084/basicfacadeweb/mvc/farmhome

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
                HelloWorld.java       // NEW - SIMPLE CLASS TO HELP EXPLAIN/DEBUG APPLCATIONCONTEXTS- this just logs a message when created.
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

The first major change is the introduction of a spring application context [applicationContext.xml](../../week9/webfacadeexample2-spring/web/src/main/resources/applicationContext.xml )
which is intended to replace all references to ObjectFactories in the new code. 
When we remove the old code, we will remove all of the object factories but in the interim, the spring code uses the object factories to access the underlying lbraries.


```
applicationContext.xml
...

    <!-- this allows annotations to be picked up by configuration -->
    <context:annotation-config/>
    <context:component-scan base-package="org.solent.com504.factoryandfacade.impl.rest" />
     
    <!-- this allows a factory to generate a singleton bean -->
    <bean id="serviceFacade" class="org.solent.com504.factoryandfacade.impl.web.WebObjectFactory" factory-method="getServiceFacade"></bean> 
     
    <!-- this just activates and destroys a bean as an example -->
    <bean id="helloWorld" class="org.solent.com504.factoryandfacade.impl.web.HelloWorld" init-method="init" destroy-method="destroy">
        <property name="message" value="main applicationContext" />
    </bean>
```
The first lines in the file cause spring to scan all of the java files in the ```org.solent.com504.factoryandfacade.impl.rest``` package.

The scanner looks for spring annotations such as @Component and @Resource(name="serviceFacade").

When a @Component annotated class is identified, the @Resource(name="serviceFacade") causes Spring to antomatically 'inject' the serviceFacade bean.

### Jersey
The version of jersey has been updated to allow it to work along side Spring MVC. 
The pom.xml depencency jars for the later version of Jersey have been changed but the code is substantially the same.

The [RestService.java](../../week9/webfacadeexample2-spring/web/src/main/java/org/solent/com504/factoryandfacade/impl/rest/RestService.java ) 
class has been updated to use spring to inject the serviceFacade service.

In the RestService.java @Component causes Spring to initialise and register an instance of the RestService s a spring bean.

The @Resource(name="serviceFacade") causes Spring to antomatically 'inject' the serviceFacade bean into the rest service. This means that the RestService class no longer needs to reference the WebObjectFactory directly.

```
@Component // component allows resource to be picked up
@Path("/farmService")
public class RestService {

    // SETS UP LOGGING 
    // note that log name will be org.solent.com504.factoryandfacade.impl.rest.RestService
    final static Logger LOG = LogManager.getLogger(RestService.class);

    // This serviceFacade object is automacally injected by Spring
    @Resource(name="serviceFacade")
    FarmFacade serviceFacade = null;   
    
```

The definition for the serviceFacade bean is in the applicationContext.xml file.
Because we are still using the legacy WebObjectFactory in the old jsp code, we have a bean definition in the applicationContext.xml which uses the same WebObjectFactory. You will see that this calls the getServiceFacade() method in the WebObjectFactory to return a reference to a singleton serviceFacade object.

Please compare 
[week9 RestService.java](../../week9/webfacadeexample2-spring/web/src/main/java/org/solent/com504/factoryandfacade/impl/rest/RestService.java ) 
with 
[week6 RestService.java](../../week6/webfacadeexample2/web/src/main/java/org/solent/com504/factoryandfacade/impl/rest/RestService.java ) 

You will see that with the WebObjectFatory removed, the code is simplified in the RestService and the WebObjectFactory is no longer needed.

## Spring MVC
A key problem with the way we have implemented the JSP files so far is that we have a strong linkage between the view and controller functions. 
Ideally we want to take all of the java code out of the header of the JSP's and put it in a seperate controller.

A new class, [ViewController.java](../../week9/webfacadeexample2-spring/web/src/main/java/org/solent/com504/factoryandfacade/impl/web/ViewController.java ) has been introduced which acts as the Controller in the Model View Controller implementation.

This controller replaces the controlling java in the jsp's and simply uses the JSP's as view technology with limited control functionality.

An excerpt of this controller class is shown below. In many ways it is very simlilar to hte JAX-RS jersey service we looked at earlier.

The @Controller annotation identifies this class as a controller in the MVC.

@RequestMapping("/mvc") means that the controller will process URL's with /mvc in the classpath.
This is very similar to the @Path annotation in JAX-RS

The @Resource(name = "serviceFacade") annotation injects the farmFacade service bean created by the application context (as discussed above).

The @RequestMapping("/farmhome") annotation maps the call to the farmhome method in the controller. Again this is very like JAX-RS.

```
@Controller
@RequestMapping("/mvc")
public class ViewController {

    final static Logger LOG = LogManager.getLogger(ViewController.class);

    {
        LOG.debug("ViewController created");
    }

    // This serviceFacade object is injected by Spring
    @Resource(name = "serviceFacade")
    FarmFacade serviceFacade = null;

    @RequestMapping("/farmhome")
    public String farmhome(Model m,
            @RequestParam(value = "animalName", required = false) String animalName,
            @RequestParam(value = "animalType", required = false) String animalType) {

        LOG.debug("farmhome called animalType=" + animalType + " animalName=" + animalName);

        List<Animal> animalsList = serviceFacade.getAllAnimals();
        List<String> supportedAnimalTypes = serviceFacade.getSupportedAnimalTypes();
        m.addAttribute("animalsList", animalsList);
        m.addAttribute("supportedAnimalTypes", supportedAnimalTypes);

        // add error / response messages to page
        String errorMessage = "";
        String message = "";
        m.addAttribute("errorMessage", errorMessage);
        m.addAttribute("message", message);

        // render view with jsp
        return "farmlist";
    }
```
If you compare the old 
[farm2.jsp](../../week9/webfacadeexample2-spring/web/src/main/webapp/farm2.jsp ) 
with the new replacement
[farmlist.jsp](../../week9/webfacadeexample2-spring/web/src/main/webapp/WEB-INF/views/farmlist.jsp ) 
you will see that much of the code for controling the jsp has been moved into the above farmhome() method.

(There is also some extra css and example code at the bottom of the page to illustrate how the jsp view works but this is not central to the simplification of the page.)

In the controller, each request method populates a Model object which contains attributes to be passed into the JSP based view.
The model attributes are passed to the JSP view as request attributes and each request attribute has a name which can be referenced within the page. 

The choice of JSP to return as a view is determined by the return line in each controller method. For example
```
  return "farmlist";
```
The controller must be told which JSP's to use, and this is set up in the web application context [mvc-dispatcher-servlet.xml](../../week9/webfacadeexample2-spring/web/src/main/resources/mvc-dispatcher-servlet.xml ) 

### JSP and JSTL
If you run the web application in Tomcat and browse to http://localhost:8084/basicfacadeweb/mvc/farmhome you will see the first page of the application. 
At the bottom of the page you can toggle 'Click to show detail of request variables' which will show you all of the variables in the request object which includes the Model objects the controller has injected.

The page illustrates two ways in which you could reference these model objects in your view.

We could use java directly in the page to access the injected variables. For example;
```
<%
  List<String> supportedAnimalTypes = (List<String>) request.getAttribute("supportedAnimalTypes");
%>
```
Is a declaration which could be used in the page much as before.

However, an alternative which avoids writing any java in the page definition would be to use a markup language called JSP - Standard Tag Library (JSTL). 

I have included some basic tags in the page to illustrate the point
```
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
....
            <c:forEach var="animalType" items="${supportedAnimalTypes}">
                <tr>
                    <td>${animalType}</td>
                    <td>
                        <!-- post avoids url encoded parameters -->
                        <form action="./createAnimal" method="post">
                            <input type="hidden" name="animalType" value="${animalType}">
                            <button type="submit" >Create ${animalType}</button>
                        </form> 
                    </td>
                </tr>
            </c:forEach>
        </table> 
```
Note that the JSTL jar must also be defined in the pom.xml
```
        <dependency>
            <groupId>jstl</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
```
You can find out more about JSTL here https://www.tutorialspoint.com/jsp/jsp_standard_tag_library.htm 











