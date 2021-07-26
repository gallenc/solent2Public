
# Using the Java Command Line

The aim of this exercise is to help you understand how build tools and Integrated Development Environments (IDE's) help with java development. 

## Compiling and running a class from the command line

These these instructions assume that java and javac and other java tools are on the command windows class path. 
If this is not the case, run the script in the  maven-setup instructions project.
(these instructions are based on windows)

1. Create and edit a file called MyTestClass.java using notepad and add the following content:

```
/*
 * This is not javadoc - usually the licence goes here
 */

/**
 * This is a javadoc comment on the Main class
 *
 */
public class MyTestClass {

   public static void main(String[] args) {
      
       // this is a local comment which doesn't go in javadoc
       MyTestClass myTestClass = new MyTestClass();
       myTestClass.writeAboutMe();
        
   }

 /**
  * This is a javadoc comment on writeAboutMe
  *
  */
  public void writeAboutMe() {
         System.out.println("I am running the following java class: "+MyTestClass.class);
  }

}

```

2. Open a command prompt in the same working directory and type
```
javac MyTestClass.java
```
This will compile your class to a file called MyTestClass.class

3. to run the class with the jre type the following command and you will get the following output
```
java MyTestClass
I am running the following java class: class MyTestClass
```

## Using external libraries with your class
Now we are going to include external libraries in the little program.
The library we are going to use is called log4j which is a logging framework.
We need the following java archive (jar) files.
```
log4j-api-2.11.0.jar
log4j-core-2.11.0.jar
```

1. download the following jar libraries into your directory from the central maven repository

Details: https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api/2.11.0

Download: https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-api/2.11.0/log4j-api-2.11.0.jar

Details: https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core/2.11.0

Download: https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-core/2.11.0/log4j-core-2.11.0.jar

2. create the following file MyTestClassLog4j.java and add the content:

```
/*
 * This is not javadoc - usually licence goes here
 */

/**
 * This is a javadoc comment on the Main class
 *
 */

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class MyTestClassLog4j {
   public static Logger logger = LogManager.getLogger(MyTestClassLog4j.class);
		
   public static void main(String[] args) {
       
       // this is a local comment which doesnt go in javadoc
       MyTestClassLog4j myTestClassLog4j = new MyTestClassLog4j();
       myTestClassLog4j.writeAboutMe();
        
   }

 /**
  * This is a javadoc comment on writeAboutMe
  *
  */
  public void writeAboutMe() {
         System.out.println("This is a system out message from : "+ MyTestClassLog4j.class);
         logger.error("This is a log4j message from : "+ MyTestClassLog4j.class);
  }

}

```

2. Now to run your program you need to include the new jars in the class path

```
javac -classpath log4j-core-2.11.0.jar;log4j-api-2.11.0.jar MyTestClassLog4j.java

java -classpath log4j-core-2.11.0.jar;log4j-api-2.11.0.jar;. MyTestClassLog4j

( or java -cp log4j-core-2.11.0.jar;log4j-api-2.11.0.jar;. MyTestClassLog4j )

This is the expected output which shows that (ignore the no log4j config file found)

ERROR StatusLogger No Log4j 2 configuration file found. Using default configuration (logging only errors to the console), or user programmatically provided configurations. Set system property 'log4j2.debug' to show Log4j 2 internal initialization logging. See https://logging.apache.org/log4j/2.x/manual/configuration.html for instructions on how to configure Log4j 2
I am running the following java class: class MyTestClassLog4j
14:12:18.597 [main] ERROR MyTestClassLog4j - I am logging this message
```
The last line is the output from the logger

NOTE if you are using power-shell instead of cmd to run the exercies, you may need to change the commands to have quotes around the class path:

```
 javac -classpath "log4j-core-2.11.0.jar;log4j-api-2.11.0.jar" MyTestClassLog4j.java
 java -classpath "log4j-core-2.11.0.jar;log4j-api-2.11.0.jar;." MyTestClassLog4j
 
```

If you are using linux and possibly on a Mac (I tested on centos 8) try using a colon : instead of ;
```
javac -classpath "log4j-core-2.11.0.jar:log4j-api-2.11.0.jar" MyTestClassLog4j.java
java -classpath "log4j-core-2.11.0.jar:log4j-api-2.11.0.jar:." MyTestClassLog4j
```
The resulting directory structure is
```
week1/command-line-exercise/src/java
log4j-api-2.11.0.jar
log4j-core-2.11.0.jar
MyTestClass.class
MyTestClass.java
MyTestClassLog4j.class
MyTestClassLog4j.java

```


## Creating your own jar
Jar files are simply zip files which contain class files, other resources and MANIFEST files.
Open one of the downloaded jars with an archive viewer to see what is inside.

To create a jar file containing your class files you can use the following command
```
jar -cvf MyJarFile.jar *.class
```
Having packaged your jar you can now include it in the class path and run using
```
java -cp log4j-core-2.11.0.jar;log4j-api-2.11.0.jar;MyJarFile.jar MyTestClass
or
java -cp log4j-core-2.11.0.jar;log4j-api-2.11.0.jar;MyJarFile.jar MyTestClassLog4j
```

## Creating javadoc documentation for your code
javadoc is the official tool for automatically generating documentation from java classes. 
You can see that the two test classes contain in code documentation bounded by /** */.
To turn this into html documentation you can use the javadoc tool
```
javadoc -d doc *.java
generates javadoc in doc folder
```
run this command and then open the generated /doc/index.html file in a browser to see the javadoc format


## Summary
Hopefully this little exercise will help you appreciate that if you were constrained to just using the command line tools, writing documenting and packaging java code would be a tedious exercise.
This sets us up for beginning to use maven to help with java builds

