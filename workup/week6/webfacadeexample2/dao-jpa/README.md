# JPA based DAO 


## standalone jpa archetype

The DAO described her is quite complex.

You may find this simpler example useful to understand a simple JPA usecases

https://github.com/lalyos/standalone-jpa-archetype

This is a maven archetype that will help creating standalone (no jee/webserver) JPA projects.

It's using the following technologies:
* EclipseLink for JPA implementation
* embedded DerbyDB for the underlying database

## usage

To use this first clone the https://github.com/lalyos/standalone-jpa-archetype project from github. 
To build the archetype use
```
mvn clean install
```

After it is built locally you can use the archetype to build an example project a new folder via maven:
```
mvn archetype:generate -DarchetypeGroupId=com.github.lalyos -DarchetypeArtifactId=standalone-jpa-eclipselink-archetype
```
