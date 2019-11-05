# COM504  MOCK TCA

# Assessment Task 

## Getting Started
You will be given access to a private git repository on Github which contains the exercise code. (The scenario is also included in the git repository README.md)

DO NOT FORK THIS REPOSITORY ON GITHUB

You will have 2 hours to complete this assessment
.
You may, if you find it useful, access your previous work through git or use any other on line resources which help you complete this exercise.
You are required to use the class PC's and you should set up your machine to use maven as described previously in class. (For your convenience a maven-setup project is also provided in this tca folder).

You will be given read access to a private github repository containing all of the code you need for this exercise.

You should check out this code from github, compile it with maven and open it in Netbeans before the assessment begins.

You should also run the web app using the instructions in library-web section below and check you can see it working on your browser (firefox).

If you have any problems getting started, the instructions are not clear or you get seriously stuck because of a technical problem not related to this exercise, talk to your tutor.

Save your work as you go along and do a git commit to your local repository on your machine as you complete each stage in the exercise.

At the end of the assessment, you should commit your work to your LOCAL git repository. You will be asked to zip and copy this to SOL - not to your own github repository.

DO NOT FORK THIS REPOSITORY ON GITHUB

### Assessment
For this assessment you will be required to:
1. Read the scenario
2. Complete the use case and robustness diagrams
3. Complete the provided code demonstrating you understand how a complex object oriented project is constructed.

The full exercise begins in the section below; Library Project 

### Finishing up
At the end of the allocated time you will be asked to stop work.

At that point, you should commit your work to your LOCAL git repository using the following commands
```
git add --all
git commit -m 'YOUR NAME end of TCA TODAYS DATE'
```
You will now tag your commit
```
git tag -a TCA1 -m 'YOUR NAME end of TCA TODAYS DATE'
```
Where YOUR NAME should be replaced with your name and TODAY'S DATE should be replaced with the current date.

Your TCA work is now tagged in your own local repository which you will zip and commit.
To make saving the project manageable, clean out all target folders before zipping by using
```
mvn clean
```
Zip your cleaned git project, Name the zip file WITH YOUR NAME and submit it to sol. e.g. myname-COM504TCA.zip

## Assessment criteria
 
We will allocate 50% of marks to design and documentation and 50% to implementation. 
There is no requirement to complete the documentation before you complete the code. 
However any documentation should reflect your implementation. 
Do as much as you can of all the tasks to maximise your overall score. 
So don't spend too much time on any one task.

### Design and documentation (Library UML) (50%)

1	Complete use-case diagram	20%

2	Complete robustness diagram	25%

4	Ensure markdown references and shows your diagrams (gif) and describes usage of your code	5%

### Implementation (50%)

5	Complete JSP for searching for a book	 (50%)

#Project Brief : Library Project version 0.1

## Scenario

A librarian is required to update a book catalogue for a library. 
Every book in the catalogue is identifies by its title, author and ISBN number
The web interface allows the librarian to;

Search for books by title, author and ISBN. These fields are ANDed together and must have an exact match to retrieve a book.

Add a new book

Find, select and Delete a book

find select and Modify a book

## Tasks

In this exercise you will complete the design documentation and implementation of such a simple library catalogue.

All the draw.io diagrams and easyUML models are in library-uml

You are provided with a UML class diagram which documents the boundary interfaces which the system must use and which are implemented for you.

The classes in the UML class diagram have been created in the library-model

You are provided with implementation code which supports the service described by the model.

In this exercise you will be asked to complete the jsps in the web interface to allow a librarian to add / delete or /search and update a book. 
This interface is partly implemented

## Part 1 - Design documentation
You are required to document the use case using a UML use case diagram with draw.io (https://www.draw.io/). You must save the drawing as both an image and a drawio.xml

It is STRONGLY suggested that you save your work as you go along to avoid any disasters.

You should start with the template draw.io drawings provided in the library-uml project.

You should save your use case diagram as a draw-io xml file along with images in the library-uml project (template files are provided).

You should then create a robustness diagram to expand the use case and show how JSPs and processes interact with the boundary interfaces provided.

When you have finished, save both the drawio-xml and image files to your project so that they can be picked up by the README.MD file and displayed on git.

Your documentation should reflect your actual implementation and you may modify your documentation as you proceed.

## Part 2 - Implementation

You should then proceed to implement the various methods required for the use cases.

Read the Project Structure section below for more information on what is provided and how to complete your code.

Skeleton code is provided for you and you may also reuse any examples from previous classes.

(If you choose to use any other external examples, code or libraries you MUST ATTRIBUTE THE WORK and incorporate the relevant LICENCE both in licence comments at the top of the relevant class where the work is used and in the README for the code).

### library-service

The ServiceFacadeImpl.java mostly proxies methods from the BookDAO which have already been tested in the library-dao-jaxb module.
So there is no need to repeat tests of the proxied methods.

### library-web
The web application is assembled in the ibrary-web project. To run this application build the main project and then run the tomcat server in netbeans

The application should be available at http://localhost:8084

Most of the web application has been implemented and it's behaviour can be used to help you complete the use case and robustness diagrams.

However you need to complete the implementation of the ListBooks.jsp page.

### Project Structure

The following notes provide additional help with understanding the project structure within which you are to work.

This (library-parent) folder contains a partially completed project which you will complete as part of this exercise.

In the library-parent project, run maven with
```
mvn clean install
```

The project should build.

Import the library-parent project and its maven sub projects into netbeans. Also import the library-uml project.

The library-parent is a multi-module maven project which means that the parent project causes the sub projects to be built in the order determined by the following section in the pom.xml

```
    <modules>
        <module>model</module> 
        <module>dao-jaxb</module> 
        <module>service</module> 
        <module>web</module> 
    </modules>
```
### library-uml

The library-uml project contains the easyUML uml model, use cases, robustness diagrams and class diagrams for this project. 
Do not change the class diagram but do use the robustness diagrams and use case diagram templates. 
Save your completed use cases and robustness diagrams here both as xml and as gif images so that they show up in the README.md in git.

### library-model

The library-model project contains model classes and interfaces which have been generated and then expanded from the library-uml class diagrams. 
You should implement your code using interfaces from this model. DO NOT change this module.

### library-dao-jaxb

An implementation of the BookDAO is provided for you to use in the library-dao-jaxb project.

Look at the tests to understand how the DAO can be used in your service.

DO NOT change this module.

### library-service

The library-service project provides an implementation of the ServiceFacade which is a proxy for the BooksDAO.

### library-web

The library-web project contains partly completed JSP's.

Some of this JSP is implemented for you however you must complete the JSP Complete the JSP's to allow a librarian to search the book catalogue.

