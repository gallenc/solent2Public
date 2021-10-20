
# Shopping cart Exercise

If you examine the project [pom.xml](../webApplicationExercise2/pom.xml) you will see that it now has three modules. 
These are built in sequence in the pom.xml file in the modules section
```
    <modules>
        <module>model</module>
        <module>service</module>
        <module>web</module>
    </modules>
```
The model module has two packages containing the DTO data transfer object (ShoppingItem), and the service/facade interfaces (ShoppingCart and ShoppingService). 
```
org.solent.com504.oodd.cart.model.dto
org.solent.com504.oodd.cart.model.service
```
The service module has one package containing the implementations of the model interfaces (ShoppingCartImpl and ShoppingServiceImpl)
```
org.solent.com504.oodd.cart.service
```
and a test package to unit test these services using junit (ShoppingCartTest and ShoppingServiceTest)
```
org.solent.com504.oodd.cart.service.test
```
The image below shows how these packages are displayed in netbeans.

![alt text](../webApplicationExercise2/images/ShoppingCartModel.png "Figure ShoppingCartModel.png" )

You can also see that this corresponds to the objects described in the [UML model](../webApplicationExercise2/UMLmodel)

The service module also has a ServiceObjectFactory which allows us to create single instances of the service objects without having to know what the implimentation class is.
This provides a design pattern to get access to objects in our service library while at the same time allowing flexibility to swap a differnt implementation later if we want.

## Exercise

## Task 1 Build compile and import the project
In order to work with this project, you should first build it using maven
```
cd webfacadeexample2
mvn clean install
```
We are doing the build first so that netbeans has the dependencies already downloaded when it opens the project.

Now you will need to import the poject into Netbeans using 'open existing project'

![alt text](../webfacadeexample2/images/NetbeansOpenProject.png "Figure NetbeansOpenProject.png" )

Remember to click 'open required projects' so that the parent project and its subprojects are all imported.

##  Task 2 Running the project

You can run the project using maven or directly in the Netbeans IDE.
Please note that you can't do both at the same time due to port clashes.
If you do run into problems restart Netbeans.

### To run the project using maven

Maven can use the cargo plugin to start an embedded tomcat container.
The configuration in the /web/pom.xml is;
```
        <plugin>
          
                <!-- run embedded tomcat with mvn org.codehaus.cargo:cargo-maven2-plugin:run -->
                <!-- see https://stackoverflow.com/questions/41326911/maven-plugin-for-tomcat-9 -->
                <groupId>org.codehaus.cargo</groupId>
                <artifactId>cargo-maven2-plugin</artifactId>
                <version>1.7.6</version>
                <configuration>
                    <container>
                        <containerId>tomcat9x</containerId>
                        <type>embedded</type>
                    </container>
                </configuration>
            </plugin>
      
        </plugins>
```
You can run the sever after your build using the following command in the web directory

```
cd web
mvn cargo:run
```
If you open a browser and browse to http://localhost:8080/webApplicationExercise/
you will see the index page of the application.

You can stop the browser using control c (ctrl-c).

Please note that when you run the example this way you will need to rebuild the project and restart tomcat
every time you make changes otherwise the changes will not be packaged in a war and run by tomcat.

### To run the project using  in Netbeans embedded Tomcat server

If you right click on the webfacade-example1 project in netbeans and select 'run' netbeans will spin up a tomcat instance and launch your application.

If you are asked for username and password just use the default admin admin.
 
(You must make sure you have stopped any maven started tomcat before you do this.)

You will be able to see the application at http://localhost:8080/webApplicationExercise/

## Task 3 Complete the methods which need completed in the application

The application is not complete and you should complete the methods which are not yet implemented.

1. See if you can remove an item from the shopping cart - at the moment this throws a notImplemented exception in one of methods in ShoppingCartImpl.java.

2. See if you can calculate and display the total of all items in the shopping cart. Again you need to add functionality into ShoppingCartImpl.java

3. See if you can work out how to change the quantities of each item as a new one is added and calculate a total shopping cart value. (needs a little thought);

When you are finished your completed JSP should look like this image.

![alt text](../webApplicationExercise2/images/Exercise2jspimage1.png "Figure Exercise2jspimage1.png" )

## Optional Tasks

- See if you can add more comprehensive tests to the service layer

- See if you can add log messages using log4j whenever items are added to the cart or a cart is purchased.

- See if you can change the model so that the service (ShoppingService.java and ShoppingServiceImpl.java) can create an invoice. Can you display that invoice on another JSP invoice.jsp. 

Hint: change the service so that purchaseItems returs an Invoice object.

```
public boolean purchaseItems(ShoppingCart shoppingCart);

to

public Invoice purchaseItems(ShoppingCart shoppingCart);

```

# Congratulations

You are making progress in your understanding of java web technologies. 

You can now go on and try the  [shoppingcart-bootstrap](../session4/shoppingcart-bootstap) exercise

