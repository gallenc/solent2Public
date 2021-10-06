# Exercise 1c further examples - session objects

These exercises follow on from [Exercise 1b](../session2/Exercise1b.md) and use the same project you created.

## 5. Session objects 

Up until now our examples have shown a 'stateless' application.
By this we mean, the application has not stored any information and the response has been determined entirely by the input variables supplied by the calling form.
We are now going to begin looking at a stateful application where the server maintains a state on behalf of the user.

In any stateful web application, as each new user arrives, the web server establishes a 'session' for the user. 
Usually the web server initialises and maintains the session by sending a 'cookie' to the user's browser. 
This way, every time a new request comes from that browser, the web server knows which session the request is associated with.
Sessions are maintained for as long as a user is interacting with a site. 
Usually sessions will time out after a few minutes if there is no user activity.

Java Server Pages can access a 'session object' called 'session' which is unique to the particular browser/user accessing the page. 
Session objects can be used to store session related information which only needs to exist for the duration of the session. 
For instance, if a user logs in to a site, the session object may contain the 'user permissions or roles' associated with the logged in user.
That way we can control which parts of a page a user sees depending upon their permissions. 

In our next example, we are going to store a list of names in the session object.
Our JSP will add and remove names from the list but because the list is stored in the session, each user will have a unique list and will not be able to see anyone else's list.

In your existing project, create a new JSP called jspexample2.jsp and paste in the following content.

```
<%-- 
    Document   : jspexample3
    Created on : 22-Jul-2021, 11:16:28
    Author     : admin
    THIS EXAMPLE SHOWS HOW OBJECTS CAN BE STORED IN THE SESSION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    // retrieve the stored users list from the session
    List<String> users = (List<String>) session.getAttribute("users");
    if (users == null) {
        users = new ArrayList<String>();
        session.setAttribute("users", users);
    }

    String name = request.getParameter("userName");

    // find what action to perform on the page
    String action = request.getParameter("action");

    if ("removeUser".equals(action)) {
        users.remove(name);
    } else if ("addUser".equals(action)) {
        users.add(name);
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Example 3</title>
    </head>
    <body>
        <h1>JSP Example 3: User list</h1>

        <h2>user list</h2>
        <% for (String user : users) {%>
        <p><%=user%></p>
        <%
            }
        %>
        <h2>commands</h2>
        <form action="./jspexample3.jsp" method="get">
            <p>user name <input type="text" name="userName" value=""></p>
            <input type="hidden" name="action" value="addUser">
            <button type="submit" >add name to list</button>
        </form> 
        <br>
        <form action="./jspexample3.jsp" method="get">
            <p>user name <input type="text" name="userName" value=""></p>
            <input type="hidden" name="action" value="removeUser">
            <button type="submit" >remove name from list</button>
        </form> 


    </body>
</html>

```
## Testing the page

Open a browser at http://localhost:8080/webExercise1/jspexample3.jsp

Add a few names to the list by typing a name in the 'add user name' field and pressing the 'add user' button.

Now copy one of the names from the list into the 'remove user name' field and press the 'remove user' button.

The user list is stored in the user session and the JSP adds or removes a name from the list depending on which button is pressed.

Try opening http://localhost:8080/webExercise1/jspexample3.jsp in a different browser to the one you are using by default. (Edge, Chrome or Firefox).
You should see the same page but with no entries in the names list.
This is because the differnt browser has established a new unique session with its own names list as if you are a different user.

## The List Objects

At the top of the page in the java section delimited by the <% %> escape characters, you will see the code which drives this page.

You will see we are importing a List interface and its implementation ArrayList from the standard java collections library
```
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
```
If you ever want to know what a standard class does you can look for it's javadoc online.

The List interface is described here

https://docs.oracle.com/javase/8/docs/api/index.html?java/util/List.html

A list is simply a store of objects ordered by the order in which they are inserted.

And we can see by looking at the javadoc that there are several different Known Implementing Classes including the ArrayList which we are using. 
An ArrayList is simply a List implemented using a java Array
https://docs.oracle.com/javase/8/docs/api/index.html?java/util/ArrayList.html

If you are interested you can learn more about java arrays here https://www.w3schools.com/java/java_arrays.asp

and Java Lists here https://www.w3schools.com/java/java_arraylist.asp

Looking at the List interface we can see it has a number of methods including add and remove which we will use in our example.

Raw lists can store any java Object but we want to make sure that only Strings are ever stored in our list.

We can use this by adding 'Generics' to our List definition as below.

```
List<String> users = new ArrayList<String>();
```
This tells the compiler that our users List will only ever contain String objects and will be implemented using the ArrayList implementation.

This is a very basic description of the complex topic of generics but all we need to know for now.
You can learn more about generics here https://www.baeldung.com/java-generics

## Storing the list in the session Object

Whenever a user first browses to our jspexample3.jsp page, we need to create a new list of names and store this in the session object.
Every subsequent visit to the page should retrieve the list from the session object.

Sessions store and retrieve objects using a name value pair. 
The session object doesn't know or care what the object is, so we need to tell our program how to use the retrieved object. 
When we retrieve an object from a session, we need to 'cast' it to the correct object type before we can use it.
The following segment tells the compiler that the Object retrieved from the session is actually a List of Strings.
This is called casting an object. 
Note that an object can only be casted to another type if it implements the same interfaces as the casted type.
```
(List<String>)
```

In this code we are checking if the users list already exists in the session, in which case we use it.
But if it doesn't already exist, we create a new one and save it in the session.

```
    // retrieve the stored users list from the session
    List<String> users = (List<String>) session.getAttribute("users");
    if (users == null) {
        users = new ArrayList<String>();
        session.setAttribute("users", users);
    }
```

## Displaying a list of names

The following code is used to display all of the strings in the user list.
You will see that we are mixing pure java code with html by using the <% %> operators 
```
<% for (String user : users) { %>
        <p><%=user%></p>
        <%
            }
        %>
```
You can see that we are using a for loop to iterate through all of the strings in the users list.
We are using html to format how the user is printed.

## The Action variable

Pages can have more than one function so every time we call a page, we need to tell it what to do. 

Here we have created a web attribute string called 'action' which is used to select whether to add or remove a name from the List.

```
    String name = request.getParameter("userName");

    // find what action to perform on the page
    String action = request.getParameter("action");

    if ("removeUser".equals(action)) {
        users.remove(name);
    } else if ("addUser".equals(action)) {
        users.add(name);
    }
```

You can see further down the page that there are two forms with 'hidden' text fields called action. 
```
        <form action="./jspexample3.jsp" method="get">
            <p>user name <input type="text" name="userName" value=""></p>
            <input type="hidden" name="action" value="addUser">
            <button type="submit" >add name to list</button>
        </form> 
        <br>
        <form action="./jspexample3.jsp" method="get">
            <p>user name <input type="text" name="userName" value=""></p>
            <input type="hidden" name="action" value="removeUser">
            <button type="submit" >remove name from list</button>
        </form> 
```
Depending on which button is pressed, the jspexample3.jsp page is called with the action 'addUser' or 'removeUser'.

## Exercise 1: Adding multiple remove buttons
It is a bit tedious having to cut and paste the name of an item to remove. 
Can you create a page like the figure below which provides a separate button to delete each item.
Clue - create a table and make the remove button one cell in the table.

![alt text](../session2/images/jspexample3b.png "Figure jspexample3b.png" )

## Exercise 2: giving the user an address attribute
Usually we need more than just the name of a user. 
In this next exercise we will create a copy of the above JSP and modify it to deal with a User object instead of a String 
Can you create a page like the figure below which has additional address fields for each User.

![alt text](../session2/images/jspexample3c.png "Figure jspexample3c.png" )

Note to do this you will need to create a User object in a new package called 'org.solent.oodd.webexercise1.model' 

```
package org.solent.oodd.webexercise1.model;

/**
 *
 * @author admin
 */
public class User {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}

```

You will need to add a new User import 

```
<%@ page import="org.solent.oodd.webexercise1.model.User" %>
```
And modify the users List and JSP to use it.
```
List<User> users = new ArrayList<User>();
```
Instead of using the simple iterator you will need to use the list index explicitly in the page
```
            <% for (int idx = 0; idx < users.size(); idx++) {
                    User user = users.get(idx);
            %>
            ...
            <tr>
                <td><%=idx + 1%></td>
                <td><%=user.getName()%></td>
                <td><%=user.getAddress()%></td>
            </tr>
            <%
                }
            %>

```

## Exercise 3: Add a 'Modify User' action.
Can you add a 'Modify User' action which allows you to edit user attributes in a new page.

![alt text](../session2/images/jspexample3d.png "Figure jspexample3d.png" )



