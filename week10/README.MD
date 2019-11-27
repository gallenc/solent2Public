# Week 9 Using Spring MVC and Inversion of Control

## Spring Data

Last week we looked at SpringMVC which is intended to more clearly seperate the model view controller for our web layer.

This week we will look at SpringData which is designed to simplify and remove duplicate code when using JPA to create DAO'



A lot of tutorial material on Spring Data is available on line but most of it is based on spring boot and hibernete. I have provided two examples which can be integrated into your project. 

The
[springDataHibernateExample1](../week10/springDataHibernateExample1/ )

example uses the Hiberante JPA library with the HSQLDB imbedded database. This is differnt to the implementation we have used previously.


The 
[springDataEclipselinkExample1](../week10/springDataEclipselinkExample1/ )
example  uses the ecliselink JPA implementaion with the derby embedded database which we have been using in previous examples.


The 
[project](../week10/project/ )
example replaces the eclipseLink JPA based dao with the springData eclipseLink based dao. 
You could use this DAO instead of the JPA DAO but note that with ecliselink, it is neccessary to add the @OneToOne annotations to references to other objects in your model. 


# Exercise

Rew the Spring Data DAO's examples to see how much work is saved

