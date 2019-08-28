# Bankcard project version 0.1

This folder contains a partially completed project which you will copy to your own work folder and complete in class.

It is a multi-module maven project which means that the parent project cause the sub projects to be built in the order determined by the following section in the pom.xml
```
    <modules>
        <module>card-checker</module>
        <module>card-model</module>
        <module>card-validator</module>
    </modules>
```
The card-checker is a library which checks creditcard numbers and is provided for your to use in your project. 
See the readme in card-checker for more information.
DO NOT change this module.

The card-uml project contains the uml model, use cases, robustness diagrams and class diagrams for this project.
These will be discussed in class.
DO NOT change this module.

The card-model contains model classes and interfaces which have been generated and then modified from the card-uml class diagrams.
DO NOT change this module.

The card-main project contains the implementation classes which you will modify and extend. 
Note how the pom.xml in card-main pulls in card-model and card-checker jars as libraries which card-main uses.

in the bankcard-parent project, run maven with 
```
mvn clean install
```
The project will build but the tests will fail until you have completed the exercise. 

The instructions in the card-main readme will tell you what to do to complete this exercise.
