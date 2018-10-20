<%-- 
    Document   : ReceptionPage
    Created on : Oct 20, 2018, 6:36:52 PM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel Reception Page</title>
    </head>
    <body>
        <h1>Welcome to the Hotel Reception</h1>

        <p>Use the following form to generate a new key</p>
        <form action="/ReceptionPage.jsp">
            Enter Room Number:<br>
            <input type="text" name="roomNumber" value="">
            <br>
            Enter Start Time<br>
            <input type="text" name="lastname" value="Mouse">
            <br>
            Enter End Time<br>
            <input type="submit" value="Submit">
        </form> 

        <p>Use the following form read a key card</p>
        <form action="/ReceptionPage.jsp">
            Enter Card Code:<br>
            <input type="text" name="cardCode" value="">
            <br>
            <input type="submit" value="Read Key Card">
        </form> 
         <br>
        <div id="result"></div>
    </body>
</html>
