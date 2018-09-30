
# Exercise 1 factoryAndFacade

In these exercises you will look at Java Collections and Generics, complete a simple factory method and implement and create tests for a simple facade interface

### 1. Setup
   Import the [factoryandfacade](../factoryandfacade/factoryandfacade) maven project into your workspace.

### 2.Look at the ExampleCollectionsTest.java file.

This file gives a very brief introduction to java Generics and Java collections. There are many tutorials on Collections and Generics on line. 

For example to learn about Collections see
 https://docs.oracle.com/javase/tutorial/collections/ 

To learn more about Generics see
https://docs.oracle.com/javase/tutorial/java/generics/index.html

You would be well advised to study these in your own time.

The example class illustrates how the ArrayList is an implementation of the List interface which uses a java Array as the persistence mechanism. 
(The LinkedList is another implementation which uses linked lists of objects instead of an array).

You can use Lists to store any java object. 
Generics can be added to a declaration (e.g. List<Animal>) to tell the compiler that you will only ever put Animal classes in the list. 
The compiler checks all usages of the list and casts the retrieved objects automatically. 

Note that Generics are implemented using 'Type Erasure' which means that only the compiler knows about and checks the generic values. 
The compiled byte code looses all of this information and simply has the list of objects as if you hadn't specified generics at all. 
This preserves backwards compatibility to java versions before 1.5 when generics were introduced.

### 3. now look at the rest of the example code
If you do a maven build of the project you will see that the tests in FarmFacadeTest.java fail.
This is because you haven't implemented the FarmFacade interface

a) You will need to create a class called FarmFacadeImpl which implements FarmFacade.

b) you will need to modify AnimalObjectFactory so that it can create a FarmFacadeImpl.

c) You will need to implement the methods of FarmFacade in FarmFacadeImpl.java You should use an ArrayList to store all of the farm animals in your implementation.

d) You will need to add additional tests to FarmFacadeTest.java so that you are testing that you can add animals with names and can retrieve a list of the added animals.

# Exercise 2 factoryAndFacadeUML
This exercise will show you how to create a UML class diagram in Netbeans and generate skeleton code from it which you can import into your model.

### 1. Import the easyUML plugin into netbeans
To create UML diagrams in Netbeans we need to install the easyUML Plugin.

Select Tools>Plugins> and select Available Plugins tab.

Search for easyUML and select and press the install button. You will need to restart the IDE.

![alt text](../master/images/easyUMLPlugin.png "Figure easyUMLPlugin.png")

While you are there, you might also want to install the following plugins; 
* Markdown support - makes it easier to edit github markdown
* Java EE base - we will use this later in the course

### 2. Import the factoryAndFacadeUML project 

File>OpenProject navigate to UMLfactoryandfacade  and select to open

in the open UMLfactoryandfacade project select Class Diagrams>factoryandfacadeClassDiagram.cdg

You will see the UML diagram we have been using previously

You can export this diagram as a png image for your documentation.

### 3.Generate code from the diagram

One of gthe key benifits of UML is its ability to support the generation of code in any language from the diagram. 
How each UML CASE tool generates code is different for each tool. 
Some tools are better or more flexible than others.
The easyUML tool in Netbeans provides a degree of java code generation although it is not perfect. 
You will need to manually correct some errors before you can use the generated code.
However the tools is still very useful to help create and document the structure of your project.

To generate code, we first need to create a temporary project in which we will place the code. 
We DO NOT want to generate code into our main project because we don't want to overwrite our previous work and we will need to modify the generated classes before use.

Create a NEW TEMPORARY java/maven project in your workspace (not in your git repo).
By default this will be called something like mavenproject1

Generate the code into this project from factoryAndFacadeUML

Right click the factoryandfacadeClassDiagram.cfg and select easyUML Generate Code

From the drop down list select to generate code into your temporary project (e.g. mavenproject1). 
DO NOT generate this code anywhere else!!!. 

Look at the generated code in your temporary project. 
You will see that this code cannot compile due to compilation errors.
If you click on each of the errors and then key alt+Return, the IDE will help with suggestions to fix the errors.
You will need to add imports for unknown types such as java.util.List class types
You will also need to add un-implemented methods for classes implementing the Animal interface.

Once you have fixed the generated classes, you can see that you could then move them into a model project which you will be persisting to git. 
Once the move is done, you can delete the temporary project from the IDE.

You should be able to see that this process allows you to use a UML class diagram to  visualise and document your design and generate a starting point for your concrete model classes. 

### 4. Extending the model with a Duck class

Add a Duck class which extends Animal to the model.

Add the Duck class to the object factory and also the facade ( addDuck(String name)).

Generate the code into your temporary project and use the generated code to show what changes you need to make to your factoryandfacade model

See how you can now copy the differences from this modified model into your existing factory and facade project.

(Note you will need to clean up any errors caused by un-imported types before you can do this).

Modify your implementation classes and add tests to check that Duck is used properly.






