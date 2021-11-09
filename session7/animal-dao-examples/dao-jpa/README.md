# JPA based DAO 

This module implements the AnimalDao using Java Persistence Architecture JPA

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