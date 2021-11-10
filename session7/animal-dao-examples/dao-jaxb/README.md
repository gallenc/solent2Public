# XML File DAO implementation

This module implements the AnimalDao using the JAXB api to serialise java objects as XML files.

If you build the dao-jaxb module, you will see that the tests persist the list of animals as a file in the target directory

```
/target/testDaoFile.xml

<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<animalList>
    <alist>
        <animal>
            <id>1</id>
            <name>name_1</name>
            <address>address_1</address>
            <animalType>
                <sound>woof</sound>
                <type>Dog</type>
            </animalType>
        </animal>
        <animal>
            <id>2</id>
            <name>name_2</name>
            <address>address_2</address>
            <animalType>
                <sound>meow</sound>
                <type>Cat</type>
            </animalType>
        </animal>
        <animal>
            <id>3</id>
            <name>name_3</name>
            <address>address_3</address>
            <animalType>
                <sound>moo</sound>
                <type>Cow</type>
            </animalType>
        </animal>
    </alist>
    <currentMaxId>3</currentMaxId>
</animalList>

```

For a tutorial on Jaxb see https://www.baeldung.com/jaxb

Note that Jaxb requires model classes to be annotated with Jaxb annotations as described below.

## JAXB Java Architecture for XML Binding

Before we go any further, we need to understand how we can turn java objects into a human readable XML represenation.

JAXB is a standard java library used to 'marshal' and 'unmarshall' java objects to and from XML.
 
JAXB is specified in JSR 222 https://jcp.org/en/jsr/detail?id=222

Many tutorials are available on line to help you learn JAXB however we will only be using a very 
small part of the specification.

In order to use JAXB, you need to annotate the classes in your model.

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
You will need to make you model work with JAXB for your ReST interface and also 
if you are going to write a JAXB based DAO implementation.

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

## Marshalling JAXB classes
If you look in the example model, you will see a test class which tests that the model can be marshaled.
This test writes and then reads back a file from a set of model classes.
The key code is
```
JAXBContext jaxbContext = JAXBContext.newInstance("org.solent.com504.factoryandfacade.model.dto");
```
Which is a factory which reads the packages in which the JAXB model classes are stored. 
Note that there must also be a corresponding jaxb.index file which lists the JAXB model classes in the package.

```
package org.solent.com504.factoryandfacade.test.dto.jaxb;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.dto.AnimalList;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;
import org.solent.com504.factoryandfacade.model.dto.ReplyMessage;

/**
 *
 * @author gallenc
 */
public class ModelJaxbTest {

    @Test
    public void testAnimalListJaxb() {

        try {

            // test file we will write and read. 
            // Note in target folder so that will be deleted on each run and will not be saved to git
            File file = new File("target/testTransactionData.xml");
            System.out.println("writing test file to " + file.getAbsolutePath());

            // this contains a list of Jaxb annotated classes for the context to parse
            // NOTE you must also have a jaxb.index or ObjectFactory in the same classpath
            JAXBContext jaxbContext = JAXBContext.newInstance("org.solent.com504.factoryandfacade.model.dto");

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


            // test animal object
            Animal animal = new Animal();
            animal.setAddress("32 Windsor Gardens in Notting Hill");
            animal.setName("Paddington");
            AnimalType animalType = new AnimalType("Bear","Could I have some marmalade please");
            animal.setAnimalType(animalType);
            
                        // create an object to test
            AnimalList animalList = new AnimalList();
            animalList.getAnimals().add(animal);
            
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(200);
            replyMessage.setDebugMessage("OK");
            replyMessage.setAnimalList(animalList);
            
            List stringList = new ArrayList(Arrays.asList("hello","goodbye"));
            replyMessage.setStringList(stringList);

            // create XML from the object
            // marshal the object lists to system out, a file and a stringWriter
            jaxbMarshaller.marshal(replyMessage, System.out);
            jaxbMarshaller.marshal(replyMessage, file);

            // string writer is used to compare received object
            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(replyMessage, sw1);

            // having written the file we now read in the file for test
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            ReplyMessage receivedTransactionResult = (ReplyMessage) jaxbUnMarshaller.unmarshal(file);

            StringWriter sw2 = new StringWriter();
            jaxbMarshaller.marshal(receivedTransactionResult, sw2);

            // check transmitted and recieved messages are the same
            assertEquals(sw1.toString(), sw2.toString());

        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }
    }
}
```


