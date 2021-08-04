# Exercise 1 - a simple web app

Before you do anything else, copy this folder ..\solent2Public\Week2 into your own practice area MyPracticeCourseWork\Week2
Remember you should do all of your own work in the MyPracticeCourseWork folder and do not change any other files so that you can update your work each week using the class examples. 

In this exercise we will use Netbeans to create a new empty maven web application project which we will use to explore JSP's.

## 1. Create a new web application project

a) select file > new Project > Java with Maven > Web Application

![alt text](../session2/images/webcreate1.png "Figure webcreate1.png" )

b) Select where you will created the project. 

You need to make sure this is created in your forked repository under MyPracticeCourseWork\session2

c) Fill in the project details as shown in the diagram 

The groupId and the artifactId uniquely identify the project within a maven repository. 
Usually the groupId contains a name space associated with your organisation or project.
You can see that Netbeans automatically creates a java package using the group id and artifact id.
Use the names I have provided.  

![alt text](../session2/images/webcreate2.png "Figure webcreate2.png" )

d) click next and accept the default options for tomcat and javaEE

![alt text](../session2/images/webcreate3.png "Figure webcreate3.png" )

## 2. Explore your project

If all goes well, you should now have a new project created in the location you specified and visible in the netbeans IDE.

Every maven project follows the same conventions for laying out a project but each IDE may display this information differently.
It is important to know where your files really are located in your project and how the IDE is displaying them.

Netbeans provides two views of projects; A project view and a files view

The Files view shows you exactly where each part of the project is created and stored on your computer
You can see that the java classes are stored in a nested tree of folders corresponding to the package name of each class.

![alt text](../session2/images/webcreate4.png "Figure webcreate4.png" )

The Project View can make it a bit easier to navigate around the logical layout of the project.
You can see that the folder tree is collapsed into a package under Source Packages. 

![alt text](../session2/images/webcreate5.png "Figure webcreate5.png" )

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

![alt text](../session2/images/netbeansRun.png "Figure netbeansRun.png" )

The log output will tell you that tomcat is starting up and that the application is being deployed to tomcat. 

Once deployed, the application will be available at http://localhost:8080/webExercise1/index.html
You should see 'hello world'.

If you look at the tomcat tab under services you will see your application is deployed.

![alt text](../session2/images/netbeansTomcat.png "Figure netbeansTomcat.png" )

Finally, you can undeploy your application by right clicking on the webExercise1 project and select 'clean'.

b) Add a new web page
You can add a new html page, simply be right clicking on Web Pages and select add Html. 
call the new page newhtml.html

You should be able to see this at http://localhost:8080/webExercise1/newhtml.html

You can edit this page and see the changes in the running browser.

(for more help on html see https://www.w3schools.com/html/)

## 4. Adding a JSP

We have seen how the application handles simple html now we will create a simple jsp.

Right click on Web Pages and select add JSP and name the file newjsp

![alt text](../session2/images/webcreate6.png "Figure webcreate6.png" )

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

We mentioned that jsp's mix java code with html. Add the following lines so that the page looks like the example below and refresh your browser.

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

You should now see the date and time printed out and every time you refresh the page, the seconds should increment.

In JSPs  <% and %> are escape characters which indicate the intervening lines are JSP specific and are not included in the generated html.
Usually we write java code between these characters in a ISP page.

The <%= %> directive tells the server that java code between the <%= %> will return a string to include in the page.

the <%@ page import="java.util.Date" %> directive imports a class (in this case java.util.Date) in a similar way to the java import directive in a normal java class file.

## looking at the servelet
We said earlier that JSP files are converted into java code by tomcat (actually the jasper library in tomcat). 
This code is called a servelet and it is the generated servelet class which is then executed by tomcat to render the page.

In Netbeans it is possible to see the servelet java code which has been generated. 
While your program is running in tomcat, you can right click on the jsp and select 'view servelet'.

![alt text](../session2/images/webcreate7.png "Figure webcreate7.png" )

You should see java code something like the Example servlet generated from a JSP at the bottom of this page . 

We dont need to worry too much about the details of servelet code because it is generated for us, but it is worth noticing a few things to help us understadn how JSPs work.

The most important method begins with the line

```
 public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
```

This method takes the request object which contains the information extracted from the http GET or POST request. 
The method returns the response object which contains the HTTP html response which this jsp will return.

If you look inside the method you will see many lines beginning with out.write().
These lines contain the static web page content extracted from wihin the jsp.

```
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Hello World!</h1>\n");
      out.write("        <p>the time is ");
      out.print( new Date() );
      out.write(" </p>\n");

```
But note that the jsp line
```
        <p>the time is <%= new Date() %> </p>
```

is rendered 
```   
      out.write("        <p>the time is ");
      out.print( new Date() );
```
We can see how tomcat turns the jsp into servlet code and includes any code which we have written between the <% %> escape characters.

Now try [Exercise 1b](../session2/Exercise1b.md)

## Example servlet generated from JSP
```
/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.50
 * Generated at: 2021-07-22 08:28:39 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Date;

public final class newjsp_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.Date");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Hello World!</h1>\n");
      out.write("        <p>the time is ");
      out.print( new Date() );
      out.write(" </p>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

```

