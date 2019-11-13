# practice exercises

This week we are doing exercises to practice creating diagrams and designs. 

See if you can complete the smart meter design in this weeks project

# For your project

I have provided a template project in week 7. I would suggest you use the following steps.

1. Get some basic functionality working end to end from client to server
Note that it is always best to write tests for each layer as you go along. This will prove your code is working.

2. Modelling

Make sure the UML and model projects have the correct objects for the use case.
Most of the objects have been provided but you may need to add some fields. 
You can generte extra fields and methods for objects and interfaces by estending the UML diragram

3. Rest interface - first attempt

Think about the rest and service interface for the basic operations between the server and the client.

At a minimum you need your service facade to have methods something like
```
arrivingOnSite(String username, String siteName)
leavingsite(String username, String siteName)

```
These correspond to buttons on the client app with fields to add the user name and site name. 

```
Arrived on site
Still on site
Leaving Site
(panic)
```

You could also have a panic button
```
panic(String username, String siteName)
```
Create enough of the interface on both the server and the client to allow you to send messages using tests and receive messages using logs.

Create JSP's for both sides of the interface to allow you to send and recieve the messages between the client and the server

4. Create JSP's and dummy service 
You can now create a service facade and JSP's to allow you to create and modify users and appointments
You can provide some dummy data to prove your JSP's work and the page flow is OK.

5.  Create DAO's
You should already have DAO's corresponding to the objects in your design. These might need extending depending on your model.

6.  Create service code
Create opertions on the facade which allow you to modify users and appointments. Use the DAO's to actually access the data.
