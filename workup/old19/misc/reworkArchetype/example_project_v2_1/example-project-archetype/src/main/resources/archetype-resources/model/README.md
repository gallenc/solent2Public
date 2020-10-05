# Model

This project contains common objects and interfaces derived from the uml project

# Creating this model

### Create your initial model objects in easyUML. 
the uml project is used to document your design and also to help generate code.
The easyUML tool allows you to create a class diagram containing interfaces, classes and enums in UML which will help document your design.

Make sure you consider all 3 layers in your design; Entities, DAO, Service Facade, Web UI and Rest.

Make sure you set the package for your initial classes. 
This will determine the java package in which they are generated

### Generate initial model code

1. Create a new empty java maven project in netbeans to temporarily store your generated code.

2. Right click on the Class Diagram and select easyUML generate Code. 

3. Select the empty java project which you created above in which to place the generated code. CAREFUL -  Do NOT generate directly to your actual model project.

4. Check that the generated code is all in the correct package (i.e. not the default package).

5. Add any needed imports to allow the code to compile correctly e.g java.util.List

6. If the code is OK, copy the packages into your model project. You may need to do this round-trip several times. If you ever update your model code, make sure it is reflected in the diagram or update the diagram to generate new code. Note that you will be adding annotations and additional methods to the model so you want to use the UML generated code as a guide - not overwrite your work.

### Add additional methods, and annotations to your model

All of the fields in your model should be private and you should use 'getters and setters' to access them. For example
```
public class Property {

    private String name;
...

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
```

The IDE can easily generate these for you by right clicking in the class and selecting 'insert code...' .

You may wish to add additional convenience methods to your Entity objects. 
For example override the default toString() buy using the IDE to generate the code. 

You may also want to add checks to any setters to make sure the correct format of date is being entered. 
For instance you might want to check the format of a date string and throw an error if incorrect. 
This is called defensive programming.

You will also want to add any relevant annotations to the model objects. 
For instance JAXB (in more advanced designs you might use JPA, Spring, JAX-RS or EJB annotations). 

### Making the model work with JAXB
You will need to make you model work with JAXB for your ReST interface and also if you are going to write a JAXB based DAO implementation.

#### JAXB annotations
For a simple case the following JAXB annotations can be used
For simple pojo entities, all fields will be picked up if you use 

```
import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entity {

}
```

For Lists use the following (change the name="xx" segments to match the name you want to appear in the resulting xml or json)
```
    @XmlElementWrapper(name = "properties")
    @XmlElement(name = "property")
    private List<Property> properties = new ArrayList<Property>();
```
Note that we have initialised properties as an ArrayList so that we can consistently access the list model object without worrying about it being null later.

#### jaxb.index
You must also list all of your entity classes in a package in a file called jaxb.index which is also in the same package (i.e. the same folder) as your classes.

Note that maven expects any non java files to be in the resources folder.
you must recreate the package folder hierarchy in the resources folder and place the jaxb.index file there.

It is also good practice to test that your JAXB annotations are OK using a simple test within the model project. This will really avoid problems later. 
(An example test is provided by the archetype).

### Add javadoc to your model
Your model project is now the starting point for your design. 
If you refactor your model, you may need to refactor other things in the rest of the design but the IDE will help you do this.
Having all the model classes and interfaces in one place makes it much easier to understand and modify your project.
It also allows multiple people to work on different components which are designed against the same model.

You should add Javadoc comments to all of the methods and entities in your model. 
This will help document the project and help other to understand what the behaviour of your implementation classes should be.

Note you can generate your model's javadoc using 
```
mvn javadoc:generate. 
```
The resulting html javadoc will be in the target/site folder.



