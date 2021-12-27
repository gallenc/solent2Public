# JPA based DAO 

This module implements the AnimalDao using Java Persistence Architecture JPA

JPA builds on JDBC to create an 'Object Relational Mapper' which simplifies the process of mapping Java objects to relational database tables

Many tutorials on JPA are on line. For example https://www.baeldung.com/learn-jpa-hibernate

## standalone jpa archetype

The DAO described here is quite complex.

You may find this simpler example useful to understand a simple JPA usecases

https://github.com/lalyos/standalone-jpa-archetype

This is a maven archetype that will help creating standalone (no jee/webserver) JPA projects.

It's using the following technologies:
* EclipseLink for JPA implementation
* embedded DerbyDB for the underlying database

## usage

You can us it via maven:
```
mvn archetype:generate -DarchetypeGroupId=com.github.lalyos -DarchetypeArtifactId=standalone-jpa-eclipselink-archetype
```
