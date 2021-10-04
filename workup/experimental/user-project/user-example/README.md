
# base project with Party and User objects

updated for java 11 dependencies

This is a project with DAO's based on spring MVC, jpa and springdata jpa with Hibernate and HSQLDB

The project provides user and role management (crud) and the ability to associate users with parties

## todo
* get  ReST interface working with spring security
* make USER crud service - create, retrieve, update, delete user assign roles to user, change password
* make USER pages for create and modify user and assign user to roles
* make PARTY pages to create and modify parties and assign users to parties
* make PARTY Role service
* make ReST interface for users and party
* make properties file to allow different databases and passwords
* make user role service use DAO and not spring repository

## USER roles: 

* ANONYMOUS (basic access and possible registration_
* USER (can take basic user role for assigned party)
* PARTY_ADMIN (can take admin role but only for assigned parties)
* ADMIN (can do anything) 
* REST_USER

PARTY roles: bank, auction, grower, buyer, catalog
PARTY user roles: depends upon user


