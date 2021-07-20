# Week 6 - Using JPA or JDBC for model persistance


# Exercises for week 6

## Set up
1. remember to merge your project with the upstream project
2. create a new 'week6' folder under your myPracticeCouseWork folder.
3. copy the contents of week6 into this folder and modify or add to the code here as needed. (NB only change any code in myPracticeCouseWork).

## Exercise 1 webfacadeexample2

Review the code in this completed example

Try the  [webfacadeexample2](../week6/webfacadeexample2) example

### End to end ReST API
The code has been modified to show an end to end solution where a second web 
application uses the ResT API to comunicate with the first web application.
Run both web applications and make sure you understand the flow of information in the app.

### JDBC and JPA Data Access layers
Both a JDBC based adn JPA based data access layer have been included.
Only the JPA layer is wired into the app but the JDBC layer is provded to show you the differnce JPA makes

## Exercise 2 jpaExample1

This simple project shows two JPA DAO's, one for a Person object and one for an Appointment object.

1. complete the DAO implementations and tests.

You should be able to see how to do this by looking at the [JPA dao code](../week6/webfacadeexample2/dao-jpa) in the [webfacadeexample2](../week6/webfacadeexample2) project.

2. complete a use case and robustness diagram for the following scenario. 

A doctors surgery has a number of doctors and patients. 
The receptionist needs to be able to add, delete and modify patients in the system
She also needs need to be able to book modify and cancel appointments with particular doctors for particular patients.

Given the two Patient and Appointment DAO's you now have, what methods should a serviceFacade provide to enable the upper layers?

3. The model classes have some JAXB annotations.
Write a test class for the model to ensure objects can be marshalled and unmarshalled using JAXB 
Remember you will need a jaxb.index file in each package.
