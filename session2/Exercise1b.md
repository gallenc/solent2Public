# Exercise 1b further examples

These exercises follow on from [Exercise 1](../session2/Exercise1.md) and use the same project you created.

## 5. Using forms to create requests

Create a new JSP in your webapp directory and call it jspexample2.jsp

Copy and paste the following content into the JSP.

```
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
// any valid java code can go between the escape sequences

// here we are looking for the userName value in the web request which called this page
    String name = request.getParameter("userName");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Example 2</title>
    </head>
    <body>
        <h1>JSP Example 2: Form Request Examples</h1>

        Hello I think your name is <%=name %>

        <!-- starting the href with ./ means you are referring relative to the root of this page -->

        <!-- get uses url encoded parameters -->
        <form action="./jspexample2.jsp" method="get">
            <p>user name <input type="text" name="userName" value=""></p>
            <button type="submit" >add name using GET</button>
        </form> 
        <br>

        <!-- post avoids url encoded parameters -->
        <form action="./jspexample2.jsp" method="post">
            <!-- here we are pre filling the value with the name variable -->
            <p>user name <input type="text" name="userName" value="<%=name %>"></p>
            <button type="submit" >add name using POST</button>
        </form> 
    </body>
</html>

```

You can view this page by browsing to http://localhost:8080/webExercise1/jspexample2.jsp

Try entering a name and pressing the 'add name using GET' button.

You should see 'Hello I think your name is null' change to 'hello I think your name is -your name-'

The URL at the top of the page should also have changed to http://localhost:8080/webExercise1/jspexample2.jsp?userName=-your name-

If you look at the first form segment in the jsp, you will see
```
        <form action="./jspexample2.jsp" method="get">
            <p>user name <input type="text" name="userName" value=""></p>
            <button type="submit" >add name using GET</button>
        </form> 
```
The action is making a call to this same jsp using ./jspexample2.jsp 

The ./ indicates the call is relative to the root url of our project and translates to http://localhost:8080/webExercise1/jspexample2.jsp

the method="get" directive tells the browser to use an HTTP GET call and this means that all of the input parameters within the form will be encoded in the URL.
So the text field <input type="text" name="userName"> becomes a URL encoded parameter jspexample2.jsp?userName=-your name-

The second form segment is similar except for the method="post".
In this case HTTP POST method is used and the parameters are not included in the URL. 
This is more secure for passwords etc which you don't want to be visible in the URL.

In the second form segment, we also pre-fill the form with the name value which was posted when the page was last updated.
```
<p>user name <input type="text" name="userName" value="<%=name %>"></p>
```

At the top of the JSP you will see java code which extracts the expected userName parameter from the incoming request object (POST or GET)
```
<%
    String name = request.getParameter("userName");
%>
````
When the page is first called, userName will not be a request parameter, so the name variable will be null.

If you enter a value in the user name field and submit using the button, the value will be picked up in the name variable and used in the page.

## 6. Add some fields into the page
As an exercise, try changing the page in order to add the user's address as an additional input field.

Try the exercise an compare your solution to the answer [jspexample2b.jsp](../session2/webExercise1-answer/src/main/webapp/jspexample2b.jsp)

Now try  [Exercise 1c](../session2/Exercise1c.md)

