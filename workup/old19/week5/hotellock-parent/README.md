# Hotel Lock TCA project version 0.1

## Scenario
Most Hotels now have card lock systems whereby guest are issued with a card which allows access to their room for the duration of their stay.

Hotel Receptionists use a card writer to issue key cards to guests. 
They must enter the room number and stay dates to format a guest card.

The card writer can also issue wild card keys to staff which can open any room.
Staff keys are also only valid for a set duration.

The card writer adds an issue number and encodes all cards card with a secret key known only to the card lock system at the hotel.

The card writer records in a local file that the card has been issued including an issue number so that a security trace can be done for unauthorised card use.

Each room lock is supplied with its given room number when it is installed.

Each room lock has a secret key algorithm which allows it to decode a given key card and then check that the card dates and room number are valid. 

Staff key cards can open any room but only if the dates are valid.

Each time a card is used an entry is logged in a local file by the lock whether or not the lock is activated. 
This allows auditing of unauthorised used of cards.

## Tasks

In this exercise you will complete the design of such a lock system.

You will be provided with a UML drawing which documents the boundary interfaces which the system must expose or which are provided for you.

As part of this exercise you will write code to implement or use these boundary interfaces and also tests to prove that all the use cases have been implemented.

### Design documentation
You should first document the use case using a use case diagram and draw-io (https://www.draw.io/).

You should start with the template drawio drawings provided and save both the drawio-xml and image files to your project so that they are picked up by the README.MD file and displayed on git.

You should save your use case diagram as a draw-io xml file and images in the lock uml project (template files are provided).

You should then create a robustness diagram to expand the use case and show how processes interact with the boundary interfaces provided.

Your documentation should reflect your actual implementation and you may modify your documentation as you proceed.

### Implementation

You should then proceed to implement the various methods required for the use cases and also tests to prove that your implementation is working correctly.

Read the Project Structure section below for more information on what is provided and how to complete your code.

Skeleton code is provided for you and you may also reuse any examples from previous classes.

If you choose to use any other external examples, code or libraries you MUST ATTRIBUTE THE WORK and ENCORPORATE THE RELAVENT LICENCE both in licence comments at the top of the relevant class where the work is used and in the README for the code.

## Marking Scheme
 
We will allocate 50% of marks to design and documentation and 50% to implementation. 
Do as much as you can of all the tasks to maximise your overall score. So don't spend too much time on any one task. 

### design and documentation (50%)

| No | Task | Mark |
| --- | --- | --- |
| 1 | complete usecase diagram | 10% |
| 2 | complete robustness diagram | 25% |
| 3 | add meaningful comments to javadoc for your code and tests | 10% |
| 4 | ensure markdown reflects your diagrams and describes usage of your code | 5% |


### Implementation (50%)

| No | Task | Mark |
| --- | --- | --- |
|  | implement hotel reception service | 10% |
|  | implement hotel reception service tests | 10% |
|  | implement hotel reception service Main method with command line UI | 5% |
|  | implement hotel lock service | 10% |
|  | implement hotel lock service tests | 10% |
|  | implement hotel lock service Main method with command line UI | 5% |


# Project Structure

The following notes provide additional help with understanding the project structure within which you are to work.

This (hotellock-parent)folder contains a partially completed project which you will copy to your own work folder and complete as part of this exercise.

In the hotellock-parent project, run maven with 
```
mvn clean install
```
The project should build but some tests may fail until you have completed the exercise. 

Import the hotellock-parent project and its maven sub projects into netbeans.
Also import the lock-uml project.

The hotellock-parent is a multi-module maven project which means that the parent project causes the sub projects to be built in the order determined by the following section in the pom.xml
```
    <modules>
        <module>lock-model</module>
        <module>lock-secretkey</module>
        <module>lock-reception</module>
        <module>lock-roomlock</module>
    </modules>
```

### lock-uml

The lock-uml project contains the easyUML uml model, use cases, robustness diagrams and class diagrams for this project.
Do not change the class diagram but do use the robustness diagrams and use case diagram templates. 
Save your completed use cases and robustness diagrams here both as xml and as images so that they show up in git.

### lock-model
The lock-model project contains model classes and interfaces which have been generated and then expanded from the card-uml class diagrams.
You should implement your code using interfaces from this model.
DO NOT change this module.

### lock-secretkey
An implementation of a lock secret key algorythm is provided for you to use in lock-secretkey project. Look at the tests to understand yow this works.

DO NOT change this module.

### lock-reception
The lock-reception project will contain code to support the Receptionist in issuing and verifying issued cards. 

Note how the pom.xml in lock-reception pulls in lock-model and lock-secretkey jars as libraries which lock-reception uses.

Write tests which simulate the actions of the receptionist.

Write a Main class which asks for start date, end date, room number and issues a cardCode containing all the card data including an issue number.

You should use log4j2 to log when a card is issued to a file in the lock-reception target directory (See section on Log4J2 below).

### lock-doorlock

The lock-doorlock project will contain code to open the door and log which cards have been used with the lock. 

Write tests which simulate the actions of the hotel guest.

Write a Main class which asks for a card code for a given door number and opens the door if the cardCode is correct.

Note how the pom.xml in lock-doorlock pulls in lock-reception as only a TEST DEPENDENCY.

This will allow you to generate cards and test them against your lock as part of your integration tests.

You should use log4j2 to log when a card is used to a file in the lock-doorlock target directory (See section on Log4J2 below).

### Logging with log4j2

lock-reception contains a test class LoggingMessagesTest.java which will show you how to use Log4j with a number of examples. 

The log4j2.xml config file has already been set up for you in lock-reception/src/main/resources.

In each class you want to log, you need to add this code at the top of the class
```
public static final Logger LOG = LogManager.getLogger(ThisClassName.class);
```
 and / or the following for logging transactions 
```
public static final Logger TRANSACTIONLOG = LogManager.getLogger("transaction-log");
```
You can write general log message for debugging purposes to LOG.debug("some message")

These messages will appear in /target/app-perf.log

You can write card usage or issuing messages to TRANSACTIONLOG.info("door opened");

These messages will appear in /target/transactionLog.log

