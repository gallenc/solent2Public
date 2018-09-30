
# Exercise 1 factoryAndFacade

In these exercises you will look at Java Collections and Generics, complete a simple factory method and implement and create tests for a simple facade interface

1. Setup
Import the [factoryandfacade](../factoryandfacade/factoryandfacade) maven project into your workspace.

2. Look at the ExampleCollectionsTest.java file. 
This file gives a very brief introduction to java Generics and Java collections. 
There are many tutorials on Collections and Generics on line.

For example to learn about Generics see https://docs.oracle.com/javase/tutorial/collections/

To learn more about Generics see
https://docs.oracle.com/javase/tutorial/java/generics/index.html

You would be well advised to study these in your own time.

The example class ullustates how the ArrayList is an implementation of the List interface which uses a java Array as the persistance mechanism. 
(The LinkedList is another implementation which uses linked lists of objects instead of an array).

You can use Lists to store any java object. 
Generics e.g. List<Animal> tell the compiler that you will only ever put Animal classes in the list. 
The compiler checks all usages of the list and casts the retreived objects automatically. 

Note that Generics are implemented using 'Type Erasure' which means that only the compiler knows about and checks the generic values. 
The compiled byte code looses all of this information and simply has the list of objects as if you hadn't specificed generics at all. 
This preserved backwards compatibility to java before 1.5 whre generics were introduced.

3. now look at the rest of the example code
If you do a mavne build you will see that the tests in FarmFacadeTest.java fail.
This is because you haven't implemented the FarmFacade interface

a) You will need to create a class called FarmFacadeImpl which implements FarmFacade.

b) you will need to modify AnimalObjectFactory so that it can create a FarmFacadeImpl.

c) You will need to implement the methods of FarmFacade in FarmFacadeImpl.
you should use an ArrayList to store all of the farm animals in your implementation.

d) You will need to add additional tests to FarmFacadeTest so that you are testing that you can add animals with names and retreive a list of the added animals.

# Exercise 2 factoryAndFacadeUML

1. Import the easyUML plugin into netbeans

2. import the factoryAndFacadeUML project and look at the class diagram

3. create a new java/maven project in your workspace and generte the code into this project from factoryAndFacadeUML (do not generate this code anywhere else!!!). 

4. add a Snake to the model and the object factory and generate the code. 
See how you can now copy the changes from this modified model into your existing factory and facade project.

Note you will need to clean up any erros caused by unimported types before you can do this.






