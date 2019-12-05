# Week 9 Using Spring MVC and Inversion of Control

## Spring Data

Last week we looked at SpringMVC which is intended to more clearly seperate the model view controller for our web layer.

This week we will look at SpringData which is designed to simplify and remove duplicate code when using JPA to create DAO'.

A good tutorial on spring data is provided here (although it uses spring 4 and my exmaples use spring 5).
http://cleancodejava.com/simple-spring-data-jpa-example/

SpringData allows you to simply define a DAO using only an interface with some annotations. 
Spring automaticallay connstructs the DAO for you.
This significantly simplifys the code. 
This is done by extending a generic DAO which is defined usign hjava generics.

The interface we extend is 
https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html

Most of the methods are predefined but you can add methods using JSQL as shown in [PersonDAOSpringData.java](../week10/springDataEclipselinkExample1/dao-springdata-jpa/src/main/java/org/solent/com504/project/model/dao/springdata/PersonDAOSpringData.java )

You can also simply use the name of the method to define the code without any JSQL query as shown in 
[AppointmentDAOSpringData.java](../week10/springDataEclipselinkExample1/dao-springdata-jpa/src/main/java/org/solent/com504/project/model/dao/springdata/AppointmentDAOSpringData.java )

A lot of tutorial material on Spring Data is available on line but most of it is based on spring boot and hibernete. I have provided two examples one using [hibernate](https://hibernate.org/) and the other using [Eclipselink](https://www.eclipse.org/eclipselink/). The eclipselink one can be integrated into your project. 

The [springDataHibernateExample1](../week10/springDataHibernateExample1/ ) example uses the Hibernate JPA library with the HSQLDB imbedded database. 
This is different to the implementation we have used previously.
Note however that with hibernate, it is neccessary to add the @OneToOne annotations to references to single objects which are in a differnt table in your model. 
This is actually more correct use of JPA annotations.

[Appointment.java](../week10/springDataEclipselinkExample1/model/src/main/java/org/solent/com504/project/model/dto/Appointment.java )
```
    @OneToOne
    public Person getPersonA() {
        return personA;
    }
```
The [springDataEclipselinkExample1](../week10/springDataEclipselinkExample1/ ) example  uses the ecliselink JPA implementation with the derby embedded database which we have been using in previous examples.


The 
[project](../week10/project/ )
example replaces the eclipseLink JPA based dao with the springData eclipseLink based dao. 
You could use this DAO instead of the JPA DAO in the previous project examples. 
Note that you will need to update the dependencies in your POM files.


# Exercise

Review the Spring Data DAO's examples to see how much boilerplate code is ommitted

