# Week 7 - Back to Bank Card project (From week 3)

Having learnt about JAXB and web services, we can now return to our original bank card project and complete the creation of a service which allows the use of credit cards with a set of back end.

# Exercise 1 merge your work

merge your repository from upstream.

Copy the new week 7 folder into your myPracticeCourseWork folder

The bankcard project has added a number of new features and classes and renamed some of the sub modules. 

The week 3 card-validator module has been renamed to card-validator-dao

You will remember you created a number of cvv generators and tests in week 3.

Merge your work from week 3 into the new card-validator module and make sure your card validation code still works.

# Exercise 2 JAXB DAO

In computer software, a data access object (DAO) is an object that provides an abstract interface to some type of database or other persistence mechanism. By mapping application calls to the persistence layer, the DAO provides some specific data operations without exposing details of the database.

Look at the code and tests in card-account-dao-jaxb to see an example of how to implement a DATA ACCESS OBJECT using jaxb. 

# Exercise 3 USE CASE

You have been provided with skeleton code for a service layer in the card-service module. This exposes 3 facade interfaces. These facades will use the underlying DAO's to create accounts and create and and verify credit cards and to update accouts following successful transactions.

BankApiImpl.java which implements BankApi for accessing accounts and issuing credit cards.

TransactionApiImpl.java implements TransactionApi which is used to move money between credit card accounts 

Both of these service objects are accessed through the

ServiceObjectFactoryImpl.java 	which is used to access singleton instances of the above interfaces.

We will be creating JSP's and a web UI to allow a bank teller to access the BankApi for creating new accounts, new cards and adding money to an account.

We will be creating a ReST a web service to access the TransactionApi and perform credit card transactions.

Before we can do this, we need to construct a use case, data flow diagrams and  robustness diagrams to show what each of these API's will actually be doing.

# Exercise 4 Implement the interfaces

a) finish the BankApiImpl.java and provide tests to prove it works. 

b) finish the TransactionApiImpl.java and provide tests.

In both cases use the ServiceObjectFactory to access this interface for your tests.

# Exercise 5 Implement the Web Module

The card-web module will be used to expose the interfaces as JSP's and ReST calls

a) Implement the JSP inteface for the Bank Teller

b) Implement the ReST inteface for credit card transactions with a client to prove it works.


