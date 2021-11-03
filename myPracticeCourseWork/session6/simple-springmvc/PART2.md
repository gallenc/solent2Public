# Exercise simple SpringMVC Part 2 - migrating to Spring MVC

This exercise follows from [Part 1](../simple-springmvc/README.md)

## migrating code to the Controller from the jsp

Having simply moved our JSP's as views under the control of the MVCController, we now want to move the code from these pages into the controller.

### passing data to the JSP using the Model object
You will have noticed that every request mapping method has a 'Model model' variable in its signature.
This model object is used to pass objects from the method into the view.
objects can be added to the model as named attrubutes using
```
model.addAttribute("name", -object-);
```

For instance the following code would create a List of users containing a user 'Craig' and add them to the model as an attribute  named 'users'.
```
    @RequestMapping(value = "/userlist", method = {RequestMethod.GET, RequestMethod.POST})
    public String jspexample3d(Model model) {

       List<User> users = new ArrayList<User>;
       User user1 = new User();
       user1.setName("Craig");

       model.addAttribute("users", users);

   return "jspexample3d";
}
```
In JSP's these model objects are actually passed in the request to the view JSP. 
So we could access the users list programatically and print out the list as a table using
```
    <table>
    <%
    List<User> users = (List<User>) request.getAttribute("users");

    for (int idx = 0; idx < users.size(); idx++) {
         User user = users.get(idx);

    %>
       <tr>
          <td><%=idx + 1%></td>
          <td><%=user.getName()%></td>
          <td><%=user.getAddress()%></td>
       </tr>
    <%
        }
    %>
    </table>

```
However we are trying to avoid using any Java in the page so we need another way to access this list of users.

The usual way to do this is to use the Java Server Tag Library (JSTL) which provides a set of html tags to access variables passed into the page.
You can learn about all the tags provided by JSTL in the [w3schools standard tag library tutorial](https://www.w3schools.in/jsp/standard-tag-library/).
We will only cover here the principle tags needed for our pages.

To use JSTL, we need to have the library imported in our POM
```
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
        </dependency>
```
and the following line in our JSP definition
```
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
```
Printing out the above table is then defined thus
```
   <table>
         <c:forEach items="${users}"  var="user" varStatus="status" >
                <tr>
                    <td>${status.count}</td>
                    <td>${user.name}</td>
                    <td>${user.address}</td>
                </tr>
          </c:forEach>
   </table>
```
You can see that any object injected into the page using the model can be accessed using the escape sequence ${-name-} where -name- corresponds to the added attribute name in
```
model.addAttribute("-name-", object);
```
We can loop through a list of objects using the  <c:forEach directive and each object can be referenced using the name in the var definition.

Any fields in an injected object can be accessed using the field name e.g. ${user.name}, ${user.address} (provided there are corresponding getters if the field is private).

Finally the <c:forEach loop defines a status object which can give us the ${status.count} (starting at 1) or the ${status.index} (starting at 0) corresponding to the index of hte object in the list.

We will see below that these JSTL constructs allow us to remove all java from the page. 

You should look at the [w3schools standard tag library tutorial](https://www.w3schools.in/jsp/standard-tag-library/) to see more definitions including conditional if, then, else tags.

### migrating access to web parameters

Having seen how to pass objects to the view,we now need to see how to get data from the request. 

If you look at the code at the top of [jspexample3d.jsp](../simple-springmvc/webExercise1-springmvc/src/main/webapp/jspexample3d.jsp ) you will see that form data is accessed from the request object using
```
    String name = request.getParameter("userName");
    String address = request.getParameter("userAddress");
    String index = request.getParameter("index");

    // find what action to perform on the page
    String action = request.getParameter("action");
```
It is always possible to access the HttpSession session and  HttpRequest request objects from a Controller method if they are included in the method signature.
So we could move these lines into the controller by simply adding a HttpRequest request in the method signature e.g.
```
    @RequestMapping(value = "/userlist", method = {RequestMethod.GET, RequestMethod.POST})
    public String jspexample3d(Model model, HttpSession session, HttpRequest request) {

        String name = request.getParameter("userName");
        String address = request.getParameter("userAddress");
        String index = request.getParameter("index");
        String action = request.getParameter("action");
        ...
        return "jspexample3d";
    }
```
However the best way to do in SpringMVC this is using the @RequestParam annotation
```
    @RequestMapping(value = "/userlist", method = {RequestMethod.GET, RequestMethod.POST})
    public String jspexample3d(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "userName", required = false) String name,
            @RequestParam(name = "userAddress", required = false) String address,
            @RequestParam(name = "index", required = false) String index,
            Model model,
            HttpSession session) {
```
This @RequestParam annotation tells SpringMVC to populate fields in the method with data from the request. 
The 'required = false' says that this URL may be called without the query paramater in which case the corresponding variable will be null.
If we omitted the 'required = false' or used 'required = true', the method will throw an error if the parameter is missing from the web call.

## completed jspexample3d.jsp page and corresponding request mapping method
Putting all of this together, we can move the code out of the jspexample3d.jsp JSP into the request mapping method like so;
```
   // now provides the business logic and a model to the jspexample3d.jsp when /userlist is requested 
    @RequestMapping(value = "/userlist", method = {RequestMethod.GET, RequestMethod.POST})
    public String jspexample3d(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "userName", required = false) String name,
            @RequestParam(name = "userAddress", required = false) String address,
            @RequestParam(name = "index", required = false) String index,
            Model model,
            HttpSession session) {

        // retrieve the stored users list from the session
        List<User> users = (List<User>) session.getAttribute("usersList");
        if (users == null) {
            users = new ArrayList<User>();
            session.setAttribute("usersList", users);
        }

        if ("removeUser".equals(action)) {
            int i = Integer.parseInt(index);
            users.remove(i);
        } else if ("addUser".equals(action)) {
            User user = new User();
            user.setName(name);
            user.setAddress(address);
            users.add(user);
        }
        
        model.addAttribute("users", users);
        
        return "jspexample3d";
    }
```
Note that we are still storing the user list in the session and the actual business logic is the same as it was in the JSP.

The corresponding jspexample3d.jsp page becomes
```
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
// removed java code
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Example 3d</title>
    </head>
    <body>
        <h1>JSP Example 3d: Modify User table</h1>

        <table style="width:20%; border: 1px solid black;" > 
            <tr>
                <th>No</th>
                <th>Name</th>
                <th>Address</th>
                <th></th>
            </tr>
            <c:forEach items="${users}"  var="user" varStatus="status" >

                <tr>
                    <td>${status.count}</td>
                    <td>${user.name}</td>
                    <td>${user.address}</td>
                    <td>
                        <form action="./userlist" method="get">
                            <input type="hidden" name="index" value="${status.index}">
                            <input type="hidden" name="action" value="removeUser">
                            <button type="submit" >remove</button>
                        </form>
                        <form action="./userlist-modify" method="get">
                            <input type="hidden" name="index" value="${status.index}">
                            <button type="submit" >modify</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <h2>add users</h2>
        <form action="./userlist" method="get">
            <p>user name <input type="text" name="userName" value=""></p>
            <p>user address <input type="text" name="userAddress" value=""></p>
            <input type="hidden" name="action" value="addUser">
            <button type="submit" >add user to list</button>
        </form> 

        <br>
        <a href="./" >back to index page</a>
    </body>
</html>

```

## Exercise complete migrating the JSP's to use SpringMVC

a) Copy the changes above into the code for [jspexample3d.jsp](../simple-springmvc/webExercise1-springmvc/src/main/webapp/jspexample3d.jsp )

b) And then also migrate the second page [jspexample3d-modify.jsp](../simple-springmvc/webExercise1-springmvc/src/main/webapp/jspexample3d-modify.jsp ) to SpringMVC  

After you have made these changes, test that your program works as expected.


## Error Page
The last thing to notice in the [MVCController.java](../simple-springmvc/webExercise1-springmvc/src/main/java/org/solent/oodd/webexercise1/spring/web/MVCController.java )
is the error handling at the bottom of the Controller.
```
   /**
     * This just throws a runtime exception to show error handler working
     */
    @RequestMapping(value = "/xxxhome", method = {RequestMethod.GET, RequestMethod.POST})
    public String xxxCart(
            Model model,
            HttpSession session) {

        if (true) {
            throw new RuntimeException("error caused by request to /xxxhome");
        }
        return "home";
    }

    /*
     * Default exception handler, catches all exceptions, redirects to friendly
     * error page. Does not catch request mapping errors
     */
    @ExceptionHandler(Exception.class)
    public String myExceptionHandler(final Exception e, Model model, HttpServletRequest request) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        final String strStackTrace = sw.toString(); // stack trace as a string
        String urlStr = "not defined";
        if (request != null) {
            StringBuffer url = request.getRequestURL();
            urlStr = url.toString();
        }
        model.addAttribute("requestUrl", urlStr);
        model.addAttribute("strStackTrace", strStackTrace);
        model.addAttribute("exception", e);
        //logger.error(strStackTrace); // send to logger first
        return "error"; // default friendly exception message for user
    }
```
If a Controller has no exception handler defined, the default exception handler is called if the page throws an error.

However your can define one or more exception handlers in a Controller for different exceptions using the @ExceptionHandler() annotation.
Each exception handler calls a view and in the above example myExceptionHandler calls the error.jsp page.
Note that we can pass information to the error page through the model.

myExceptionHandler  is annotated with  @ExceptionHandler(Exception.class) which means that any exception thrown which extends Exception will call this method.
You could narrow this to a specific exception and have a differnt page for differnt errors. This gives you a lot of flexibility as to how you report exceptions to users or log them internally.

A simple request mapping method @RequestMapping(value = "/xxxhome" is defined to allow you to generate an exception and see the result. 
browse to http://localhost:8080/webExercise1/xxxhome and you will see this throws an exception which is printed out using the error.jsp page.

## Congratulations
You have managed to migrate your simple applicatio to using springMVC. 
Hopefully you can see that this gives mugh greater separatino between the programming and the design of a web site.

Now proceed to [Exercise 2 shoppingcart-springmvc](../../session6/shoppingcart-springmvc) in which you will migrate your shopping cart code into springMVC 
