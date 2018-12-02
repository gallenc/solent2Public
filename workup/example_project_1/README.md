# Example Project Archetype 

This folder contains an example project and a maven archetype which will recreate the example project with a new groupId, artifactId, and version and redefining the package. 
Using the archetype will enable you to quickly build and test the skeliton of your project before you create your own design

## Using the Archetype

## Archetype Design

### Example Project
exampleproject-parent contains a multi module project which is the prototype used to create the archetype

### Archetype
example-project-archetype is the project to build the archetype

This archetype was originally created from the example project using 
```
mvn org.apache.maven.plugins:maven-archetype-plugin:3.0.1:create-from-project "-Darchetype.properties=../archetype.properties" 
```
where the archetype.properties file defines key parameters for generating the archetype.

However subsequent to generating the archetype, it has been modified to more correctly create the project example

