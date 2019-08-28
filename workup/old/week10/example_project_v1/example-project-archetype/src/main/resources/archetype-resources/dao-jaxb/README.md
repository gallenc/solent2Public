# card-main

In this exercise you will extend and complete the classes in card-main so that the whole project builds.

## Exercise 1.

build the entire project by running in the bankcard-parent project
```
mvn clean install
```
You will see that a number of tests fail. 
Look first at 
```
CreditCardFactoryAndValidatorImplTest.java
```
The tests in this Junit  test fails because of an UnsupportedOperationException in the following method in CreditCardFactoryAndValidator.java

```
    @Override
    public CardOrganisation getCardOrganisation(CreditCard card) {
        //TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

   public boolean cardNumberLunnIsValid(CreditCard card) {
        //TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }
```
Examine the tests in the card-checker project in order to understand how you can use that library to implement these methods. 
Once implemented the project tests should compile.

## Exercise 2.

Look at the test in CreateAndVerifyNewCreditCardTest.java

Here we can see that we are choosing which CreditCardFactoryAndValidatorImpl to use depending upon the card's issuerIdentifierNumber 

We need to implement a different cvv strategy for each bank and modify the CardFactoryDAOImpl.java to use different strategy implementations depending on the card's issuerIdentifierNumber. 

a) Before proceeding further, see if you can implement 5 new strategies and modify the TestCvvAlgorythimStrategies.java file to test each of these.
Each strategy should use all of the cards fields to calculate a unique 3 digit number.

b) once you have created  and tested your 5 strategies, see if you can modify the CardFactoryDAOImpl.java factory to use these strategies in your tests.

### Exercise 3.
for the more ambitious, once you have completed the above exercises, 
Could you implement CardFactoryDAOImpl.java using spring?




