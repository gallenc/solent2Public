# hotel Lock TCA project version 0.1

## Scenario
Most Hotels now have card lock systems whereby guest are issued with a card which allows access to their room for the duration of their stay. 
Hotel Receptionists use a card writer to issue key cards to guests. 
They must enter the room number and stay dates to issue a guest card.
The card writer can also issue wild card keys to staff which can open any room.
Staff keys are also only valid for a set duration.
The card writer adds an issue number and encodes the card with a secret key known only to the hotel.

The card writer records in a local file that the card has been issued with an issue number so that a security trace can be done for unauthorised card use.

Each lock is supplied with its given room number when it is installed.
Each room lock has a secret key algorithm which allows it to decode a given key card and then check that the card dates and room number are valid. 
Staff key cards can open any room but only if the dates are valid.

Each card use is logged locally by the lock whether or not the lock is activated. 
This allows auditing of unauthorised used of cards.

## Task

In this exercise you will complete the design of such a lock system.

You will be provided with a UML drawing which documents the boundary interfaces which the system must expose or which are provided for you.

You should first document the use case using a use case diagram and draw-io (https://www.draw.io/).
You should save your use case diagram as a draw-io xml file and image in the lock uml project (template files are provided).

You should then create a robustness diagram to expand the use case and show how processes interact with the boundary interfaces provided.

You should then proceed to implement the various methods required for the use cases and also tests to prove that your implementation is working correctly.


# Project Structure

This folder contains a partially completed project which you will copy to your own work folder and complete in class.


It is a multi-module maven project which means that the parent project cause the sub projects to be built in the order determined by the following section in the pom.xml
```
    <modules>
        <module>lock model</module>
        <module>lock-secretkey</module>
        <module>lock-receptionk</module>
        <module>lock-roomlock</module>
    </modules>
```

An implementation of a lock secret key algorythm is provided for you to use in lock-secretkey project. Look at the tests to understand yow this works.

DO NOT change this module.

The card-uml project contains the uml model, use cases, robustness diagrams and class diagrams for this project.
do not change the class diagram but do use the robustness diagrams and use case diagram templates. Save your completed use cases and robustness diagrams here both as xml and as images so that they show up in git.

The card-model contains model classes and interfaces which have been generated and then modified from the card-uml class diagrams.
DO NOT change this module.

The card-main project contains the implementation classes which you will modify and extend. 
Note how the pom.xml in card-main pulls in card-model and card-checker jars as libraries which card-main uses.

in the bankcard-parent project, run maven with 
```
mvn clean install
```
The project will build but the tests will fail until you have completed the exercise. 

The instructions in the card-main README will tell you what to do to complete this exercise.
