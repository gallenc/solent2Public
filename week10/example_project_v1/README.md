# Example Project Archetype 

This folder contains an example project which provides a simple example of all of the object orientated design done in class.

It also contains a maven archetype which will recreate the example project with a new groupId, artifactId, and version
The archetype will also allow you to redefinine the package of all java classes. 
Using this archetype will enable you to quickly build and test the working skeleton of a new project before you create your own design

## Using the Archetype
The archetype is not included in maven central so to use the archetype, you must first build and install it locally
```
cd example-project-archetype
mvn clean install
```

Now you can use the archetype locally. 
Note that for the archetype to work properly you need to invoke the maven-archetype-plugin version 3.0.1 or greater.

Open a new folder where you want to create your new project and run the following command.

```
mvn org.apache.maven.plugins:maven-archetype-plugin:3.0.1:generate "-DarchetypeCatalog=local" "-DarchetypeArtifactId=example-project-archetype" "-DarchetypeGroupId=solent.ac.uk.ood.examples" "-DarchetypeVersion=1.0-SNAPSHOT"
```
You will then be asked for a new groupId, archetypeId, version and package
```
Define value for property 'groupId': org.solent.test.project
Define value for property 'artifactId': entityService
Define value for property 'version' 1.0-SNAPSHOT: :
Define value for property 'package' org.solent.test.project: :
Confirm properties configuration:
groupId: org.solent.test.project
artifactId: entityService
version: 1.0-SNAPSHOT
package: org.solent.test.project
```
Once the archetype has completed, a new folder will exist named after the artifactId you gave the plugin.
You can run the example in this folder.
```
cd 'artifactID'
mvn clean install
```
You can then import the multimodule project into netbeans.

You can also run the web app.
```
cd web
mvn jetty:run
``` 
browse to http:\\localhost:8680

## Archetype Design
If you are intersted in how the archetype as designed see [Create an archetype from a multi-module project](https://maven.apache.org/archetype/maven-archetype-plugin/examples/create-multi-module-project.html) 

exampleproject-parent contains a multi module project which is the prototype used to create the archetype.

example-project-archetype is the project to build the archetype

This archetype was originally created from the exampleproject-parent using the create-from-project goal with the archetype.properties file.  
```
mvn org.apache.maven.plugins:maven-archetype-plugin:3.0.1:create-from-project "-Darchetype.properties=../archetype.properties" 
```
where the archetype.properties file defines key parameters for generating the archetype.

However subsequent to generating the initial archetype, it has been modified to more correctly create the project example

Once built the erdhetype can be invoked in an empty folder using
```
mvn org.apache.maven.plugins:maven-archetype-plugin:3.0.1:generate "-DarchetypeCatalog=local" "-DarchetypeArtifactId=example-project-archetype" "-DarchetypeGroupId=solent.ac.uk.ood.examples" "-DarchetypeVersion=1.0-SNAPSHOT"
```


