# Week 8 - Web Front End For Bank Card project (From week 3)

Last week (week7) we introduced a DAO for the accounts in our Bank Card project. 
We also started designing the BankApi service.
This week we will work on the JSP's for bank tellers to update their accounts

# Exercise 1 merge your work and test the web browser

Merge your repository with upstream as usual. 

The New Week8 folder has changes to the bankcard-parent pom.xml and the card-web project.

Copy YOUR work from last week into myPracticeCourseWork/Week8

Copy then changes in MY bankcard-parent pom.xml and card-web projects into your bankcard-parent project.

Some additional methods have also been completed in the BankApiImpl.java class to allow the JSP's to work. 
Look at this class and copy the changes into your own class.

Compile the whole project and test the web browser using 
```
cd bankcard-parent

mvn clean install

cd card-web

mvn jetty:run
```
browse to http://localhost:8680/accountList.jsp

Try adding and deleting a few accounts


# Exercise 2 Review the code in the provided JSP's and complete your robustness diagrams

Some additional methods have been completed in the BankApiImpl.java class to allow the JSP's to work.
Look at this class and copy the changes into your own class of you have not already done so.

Understand how accountList.jsp and createOrModifyAccount.jsp work

Note how each JSP gets it's BankApi service objects using the 
```
solent.ac.uk.ood.examples.cardvalidator.cardservice.web.WebObjectFactory
```
Complete your robustness diagrams for the provided JSP's. Does the functionality they provide match your use case?

# Exercise 3 Implement the createCard.jsp

a) Complete the robustness diagrams for creating a card.

b) Add the remaining BankApiImpl.java methods to allow you to create a card

c) Complete the createCard.jsp using the BankApi and show you can create new cards for each account.


# Exercise 4 Create the TransactionApi Rest web service and client

The TransactionApi facade will allow us to verify credit cards and update accounts following successful credit card transactions.

You are provided with a skeleton implementation classes.

TransactionApiImpl.java implements TransactionApi which is used to move money between credit card accounts 

Complete the design before you start implementing the interfaces and tests.
