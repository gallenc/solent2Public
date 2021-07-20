# Practice Exercises - smart meter design

This week we are doing exercises to practice creating diagrams and designs. 

See if you can complete the smart meter design in this weeks project

# For your project

I have provided a template project in week 7. 
I would suggest you get bit of your system working to test your ideas as you to. 
You can work on different bits in parallel to work out your design

1. Modelling

FOR NEXT WEEK 

Create a UML class diagram to describe the principle objects in your project.

Create a use case diagram WITH accompanying description of each use case to describe what the system will need to do

Create a robustness diagram to describe the page flow and data flow between the objects and services

2. Get some basic UI functionality working

Generate your model code from the class diagram and make sure you can marshal with jaxb

Create 'dummy JSP's' using the objects and services to illustrate the page flow

You can now create a service facade and JSP's to allow you to create and modify stations and ticket machines and schedules

You can provide some dummy data to prove your JSP's work and the page flow is OK.

3. Write your DAO's and services to implement the model

Note that it is always best to write tests for each layer as you go along. This will prove your code is working.

4. Rest interface - first attempt

Get some basic functionality working end to end from client to server

Think about the rest and service interface for the basic operations between the server and the client.

At a minimum you need your service facade to have methods something like
```
TicketMachine getTicketMachineConfiguration(String uuid)

```
Create enough of the interface on both the server and the client to allow you to send messages using tests and receive messages using logs.

Create JSP's for both sides of the interface to allow you to send and receive the messages between the client and the server

5.  Create DAO's
Use the existing DAO's as an example and create your own for the objects you need.

6.  Create service code
Create operations on the facade which allow you to ticket machines. Use the DAO's to actually access the data.
