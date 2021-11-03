# Exercise simple SpringMVC - migrating to Spring MVC

In this exercise we will be migrating a simple web app which we created in session2 to use springMVC.

Our starting point will be [webExercise1-springmvc](../simple-springmvc/webExercise1-springmvc ) which starts with the final answer to the exercises in Session 2

You should make sure you are working with a copy of this project in myPracticeCourseWork

As always, an example answer is provided [webExercise1-springmvc-answer](../simple-springmvc/webExercise1-springmvc-answer )
 but don't look at this until you have tried it yourself.

## Introduction

Up until this point we have been using simple JSP pages to implement our web site. 
As you know, JSP's allow you to mix html with java code in order to make a dynamic web site. 
In theory this allows us to separate the concerns of web design from the concerns of programming. 
Stubbed html template pages created by designers can be turned into JSP pages and the additional code added by programmers.

The objective of an MVC framework should be to separate the View which is primarily about style from the Model and Controller which are where the programming should be done.
Unfortunately as we have seen, even with a back end service to provide business functionality, there is still a lot of Java code required in the JSP. 
Effectively this means that some of the Controller logic is mixed up with the View which makes our program difficult to maintain particularly if we want to update the styling on an existing page.

SpringMVC achieves a better separation of View and Controller by introducing Controller classes which essentially wrap in separate methods the code which we have previously put at the top of our JSP.
The JSPs then become much simpler to maintain although this is at the expense of creating a Controller method for each JSP. 
Although making our design slightly more complex, this approach also future proof's our design by allowing us, if we wish, to replace JSP's with alternative view technologies such as Thymeleaf without needing to significantly modify our business logic.
(If you are interested see [Introduction to Thymeleaf in Spring ](https://www.baeldung.com/thymeleaf-in-spring-mvc) ).

## Examining our initial program

Start by opening the [webExercise1-springmvc](../simple-springmvc/webExercise1-springmvc ) project in netbeans and run the web module.

You should be able to open the simple application at http://localhost:8080/webExercise1/jspexample3d.jsp

This is the answer to Session2/Exercise 3: Add a 'Modify User' action. 
Where we asked you to add a 'Modify User' action which allows you to edit user attributes in a new page.

The pages should correspond to the following diagram;

![alt text](../simple-springmvc/images/jspexample3d.png "Figure jspexample3d.png" )

Before you do anything else make sure you understand how the two pages 
[jspexample3d.jsp](../simple-springmvc/webExercise1-springmvc/src/main/webapp/jspexample3d.jsp )
 and 
[jspexample3d-modify.jsp](../simple-springmvc/webExercise1-springmvc/src/main/webapp/jspexample3d-modify.jsp ) work together.
They are being served by Tomcat without any intervention from SpringMVC. 

Our objective will be to remove the business logic i.e. the java at the top of these pages into a separate Controller class.

## Examine the SpringMVC additions 
The [webExercise1-springmvc](../simple-springmvc/webExercise1-springmvc ) project already has some additional code to enable SpringMVC. 

The  [pom.xml](../simple-springmvc/webExercise1-springmvc/pom.xml ) file has been modified to include some additional spring dependencies
```
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>

        ...

        <!-- spring boot dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
        
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
        </dependency>
```

The following diagram shows the initial layout of the project

![alt text](../simple-springmvc/images/webExercise1-initiallayout.png "Figure webExercise1-initiallayout.png" )

You will see the original User.java classes and 2 JSP's jspexample3d.jsp and jspexample3d-modify.jsp

You will also see 3 new classes under the package
```
org.solent.oodd.webexercise1.spring.web
```
In this example we are using Spring annotation based configuration.

Annotations are additional descriptions or decorations added to a class which can be programatically read using java 'introspection'. 
Annotations begin with @ and Java defines a number of standard annotations such as @Override which tells the compiler that a super class method has been overridden. 
Here we override the toString() method present in all objects.
```
public class Myclass {

  @Override
  String toString() {
    return "this class is called Myclass";
  }
}
```

Spring defines a large number of Spring annotations which invoke features of spring whenever they are encountered in our program.
On start up Spring can scan the class path to find all the spring annotations in a program.

As an aside, this is a very good example of the 'Decorator Pattern' where the annotations tell Spring to 'decorate' the class with lots of additional functionality which isn't explicitly written in the class.
n addition you will see the 'constructor pattern', where Spring is injecting additional classes into a class in order to return a fully functional program.

The following two classes use annotations to configure and start spring in a very typical fashion.

[SpringBootJspApplication.java](../simple-springmvc/webExercise1-springmvc/src/main/java/org/solent/oodd/webexercise1/spring/web/SpringBootJspApplication.java ), 
[SpringBootJspConfiguration.java ](../simple-springmvc/webExercise1-springmvc/src/main/java/org/solent/oodd/webexercise1/spring/web/SpringBootJspConfiguration.java  )

The most important thing to note in [SpringBootJspApplication.java](../simple-springmvc/webExercise1-springmvc/src/main/java/org/solent/oodd/webexercise1/spring/web/SpringBootJspApplication.java ) is the scanBasePackages annotation.
This tells Spring which packages to look in for annotations and so where to find the @Controller class.
```
@SpringBootApplication(scanBasePackages = "org.solent.oodd.webexercise1.spring.web")
public class SpringBootJspApplication extends SpringBootServletInitializer {
```

## Examine and extend the MVCController.java 
The class where all of the MVC work is done is [MVCController.java](../simple-springmvc/webExercise1-springmvc/src/main/java/org/solent/oodd/webexercise1/spring/web/MVCController.java )
For now, just look at the top of the class
```
@Controller
@RequestMapping("/")
public class MVCController {

    // this redirects calls to the root of our application to index.html 
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model) {
        return "redirect:/index.html";
    }
```
Remember spring will scan all classes for annotations in the package
```
org.solent.oodd.webexercise1.spring.web
```

The @Controller annotation tells spring this is a MVC Controller class.

The @RequestMapping("/") annotation tells spring that the root url all of the request mappings will be "/".

In our application this corresponds to http://localhost:8080/webExercise1/ because the 
[context.xml](../simple-springmvc/webExercise1-springmvc/src/main/webapp/META-INF/context.xml )
contains
```
<?xml version="1.0" encoding="UTF-8"?>
<Context path="/webExercise1"/>
```

Within the MVCController we can define multiple request mapping methods. 

The first method simply redirects all requests to http://localhost:8080/webExercise1/ 
to the index page http://localhost:8080/webExercise1/index.html
Without this method, we would get a page not found 404 error.

Lets change our program so that the JSP's are all controlled by the controller and not just served by tomcat.
To do this;

1. move jspexample3d.jsp and jspexample3d-modify.jsp from webapp to webapp/WEB-INF/view
2. check you have a file resources/application.properties containing
```
# Tells SpringMVC where to find the JSP's for the view
spring.mvc.view.prefix=/WEB-INF/view/

# Tells SpringMVC that the views are jsp files
spring.mvc.view.suffix=.jsp
```
3. add the following methods to [MVCController.java](../simple-springmvc/webExercise1-springmvc/src/main/java/org/solent/oodd/webexercise1/spring/web/MVCController.java )
```
    // this simply calls the jspexample3d.jsp page (without any modifications) when /userlist is requested 
    @RequestMapping(value = "/userlist", method = {RequestMethod.GET, RequestMethod.POST})
    public String jspexample3d(Model model, HttpSession session) {
        return "jspexample3d";
    }

    // this simply calls the jspexample3d-modify.jsp page (without any modifications) when /userlist-modify is requested 
    @RequestMapping(value = "/userlist-modify", method = {RequestMethod.GET, RequestMethod.POST})
    public String jspexample3dModify(Model model, HttpSession session) {
        return "jspexample3d-modify";
    }
```

When we restart the application, we will find that our JSP's are no longer called by their name
(e.g. http://localhost:8080/webExercise1/jspexample3d.jsp)

but by their respective request mapping 
(e.g. http://localhost:8080/webExercise1/userlist)

4. Modify the index.xml and all the forms in the JSP's so that they now reference the correct new URLs.
For example
```
        <form action="./jspexample3d.jsp" method="get">
                        <input type="hidden" name="index" value="<%=idx%>">
                        <input type="hidden" name="action" value="removeUser">
                        <button type="submit" >remove</button>
        </form>
```
becomes
```
        <form action="./userlist" method="get">
                        <input type="hidden" name="index" value="<%=idx%>">
                        <input type="hidden" name="action" value="removeUser">
                        <button type="submit" >remove</button>
        </form>
```

We should now have a working application with the entry point http://localhost:8080/webExercise1/userlist

## Summary 

So far we have changed the program so that the JSPs are controlled by the MVCController.java class.
However we have not in any way removed the business logic from the JSPs themselves.
We now need to think about how we will simplify the JSP's and move the code from them into the MVCController class.

We will look at this next step in [Part2](../simple-springmvc/PART2.md)

