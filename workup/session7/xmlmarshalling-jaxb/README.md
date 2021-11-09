# Week 5 - Data Access Objects, JAXB and ReST

THis week we are taking the work we did on object factories and service facades last week and incorporating this into
 a web project which allows you to add and delete animals from a farm. 
We will also look at creating a ReST interface.
To do this we are also introducing a new Data Access Object (DAO) Layer.

## Simple DAO (dao-simple)

You will see in the new webfacadeexample2 project that we have introduced a Data Access Object Layer.

In the model you will see new DAO interaces  AnimalDao.java and AnimalTypeDao.java

These are implemented in the dao-simple project

The simple dao implements the required behaviour but keeps everything in memory in java List objects.

If you look at the ServiceObjectFactoryImpl.java, you will see that there are some lines 
which 'wire in' the simple dao to our ServiceFacade object.

You will also see that we can change which DAO implementation we will use by changeing a few lines.
Note that the rest of the code will work with either implementation because it is written to use interfaces and an object factory.

```
ServiceObjectFactoryImpl.java

        // UNCOMMENT THIS TO USE SIMPLE DAO AND COMMENT OUT AnimalDaoJaxbImpl
        // if you just want to use simple DAO do this
        AnimalDao animalDao = new AnimalDaoImpl();

        // UNCOMMENT THIS TO USE JAXB DAO AND COMMENT OUT AnimalDaoImpl()
        // NOTE THIS IS SAYING WHERE THE FILE GOES in TOMCAT
        //AnimalDao animalDao = new AnimalDaoJaxbImpl(jaxbFile);


```


## Logging

(We have covered this in previous exercises but now we are encorporating logging into our web application).

One of the important ways in which you can check that your program is running correctly is logging error and debug messages. 
This is often done using a logging framework - a library designed to help with the task.
Going forwards we will be using Log4j2 which is a widely used library.

Logging is implemented using a static factory which is invoked to create a logger for each class.
Once you have a logger for the class, you an log messages with different severities; error, info, debug, trace 
```
public class RestService {

    // SETS UP LOGGING 
    // note that log name will be org.solent.com504.factoryandfacade.impl.rest.RestService
    final static Logger LOG = LogManager.getLogger(RestService.class);
    
    ...
    LOG.debug("this is a debug message");
    LOG.error("this is a error message");
    
```
The output is determined by a log4j2.xml config file on the class path which is placed in the /src/main/resources folder
```
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="INFO">
    <!-- Logging Properties -->
    <Properties>
        <!-- this sets the output style properties for the log messages -->
        <Property name="LOG_PATTERN">%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n</Property>
        <!--<Property name="APP_LOG_ROOT" >target</Property>-->
        
        <!-- this creates log files in the catalina base directory -->
        <!-- e.g. in netbeans C:\Users\<your username>\AppData\Roaming\NetBeans\8.2\apache-tomcat-8.0.27.0_base -->
        <Property name="APP_LOG_ROOT" >${sys:catalina.base}/logs/app</Property>
    </Properties>
    <Appenders>
        
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="${LOG_PATTERN}"/>
        </Console>
        
        <!-- a roling file appender limits the size of log files -->
        <RollingFile name="appLog" fileName="${APP_LOG_ROOT}/app-perf.log"
                     filePattern="${APP_LOG_ROOT}/app-perf-%d{yyyy-MM-dd}-%i.log" >
            <PatternLayout pattern="${LOG_PATTERN}"/>
            <Policies>
                <SizeBasedTriggeringPolicy size="19500KB" />
            </Policies>
            <DefaultRolloverStrategy max="1"/>
        </RollingFile>
        
    </Appenders>
    <Loggers>
        
        <!-- The name org.solent specifies that log names beginning with this string are dealt with by the following appenders -->
        <!-- i.e. in our config, classes in package names beginnign org.solent -->
        <Logger name="org.solent"  additivity="false" level="DEBUG">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="appLog"/>
        </Logger>

        <!-- this logs to  the Consol (System.out) -->
        <Root level="DEBUG">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
```

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

# Exercises for week 5 Using JAXB for model persistance

## Exercise 1 webfacadeexample2

In this exercise you will look at how the facade and factory you created last week can be used as the back end of a web site. 

Try the  [webfacadeexample2](../week5/webfacadeexample2) exercises.

Initially this is using a simple dao but you change it by modifying ServiceObjectFactoryImpl.java to use the jaxb dao
The Jaxb DAO dao uses a jaxb context to store data in a file.

Can you modify the project to add an extra animal type?

## Exercise 2 ReST interface

You will see on the index.xml page that there are extra links for ReST end points.

Some of these links work and some dont.

Look at the RestServiceImpl.java class and see if you can fix the missing GET methods.

We will look more at this next week

