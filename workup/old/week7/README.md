# Week 7 - Back to Bank Card project (From week 3)

Having learnt about JAXB and web services, we can now return to our original bank card project and complete the creation of a service which allows us to model end to end credit card transactions.

# Exercise 1 merge your work

Merge your repository with upstream as usual. 

Copy the new week7 folder into your myPracticeCourseWork folder

Check that the week7 bankcard-parent project builds using maven clean install.
(note that the card-web module is not yet included in the build)

The bankcard project has added a number of new features and classes and renamed some of the sub modules. 

The week 3 card-validator module has been renamed to card-validator-dao

You will remember you created a number of cvv generators and tests within the card-validator in week 3.

Merge your work from week 3 into the new card-validator-dao module and make sure your card validation code still works.

# Exercise 2 JAXB DAO

In computer software, a data access object (DAO) is an object that provides an abstract interface to some type of database or other persistence mechanism. 
By mapping application calls to the persistence layer, the DAO provides some specific data operations without exposing details of the database.

Data Access Objects typically perform CRUD (Create Retrieve, Update, Delete) operations on entity objects.

In our case we now have a DAO written using jaxb which can persist bank operations to a back end store which uses an xml file . 

In the future we could re-write this module to implement the same DAO operations using a database. (Using Java JDBC or JPA technologies)

Look at the code and tests in card-account-dao-jaxb to see an example of how to implement a DATA ACCESS OBJECT using jaxb. 

# Exercise 3 USE CASE

You have been provided with skeleton code for a service layer in the card-service module. 

This exposes 3 facade interfaces. 

The BankApi facade will use the underlying DAO's to create accounts and to create associated credit cards. 

The TransactionApi facade will allow us to verify credit cards and update accounts following successful credit card transactions.

You are provided with skeleton implementation classes.

BankApiImpl.java which implements BankApi for accessing accounts and issuing credit cards.

TransactionApiImpl.java implements TransactionApi which is used to move money between credit card accounts 

Both of these service objects are accessed through the ServiceObjectFactory facade which is used to access singleton instances of the above interfaces.

ServiceObjectFactoryImpl.java implements the ServiceObjectFactory

We will be creating JSP's and a web UI to allow a bank teller to access the BankApi for creating new accounts, new cards and adding money to an account.

We will be creating a ReST a web service to access the TransactionApi and perform credit card transactions.

Before we can do this, we need to construct a use case, data flow diagrams and  robustness diagrams to show what each of these API's will actually be doing.

Complete the design before you start implementing the interfaces and tests.

# Exercise 4 Implement the interfaces

a) Finish the BankApiImpl.java and provide tests to prove it works. 

b) Finish the TransactionApiImpl.java and provide tests.

In both cases use the ServiceObjectFactory to access this interface for your tests.

# Exercise 5 Implement the Web Module

The card-web module will be used to expose the interfaces as JSP's and ReST calls

a) Implement the JSP inteface for the Bank Teller

b) Implement the ReST inteface for credit card transactions with a client to prove it works.


