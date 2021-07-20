# Week 4 Exercises

## Set up
1. remember to set up maven as we did in week1
2. create a new 'week4' folder under your myPracticeCouseWork folder.
3. copy the contents of week4 into this folder and modify or add to the code here as needed. (NB only change any code in myPracticeCouseWork).

## solution for webApplicationExercise2

The example contains the answer to

- remove an item from the shopping cart 

- calculate a total and display the total of all items in the shopping cart

- change the quantities  of each item and calculate a total (needs a little thought);

The following have not been done in the example - still for you to do :)

- See if you can add comprehensive tests to the service layer

- See if you can add log messages whenever items are added to the cart or a cart is purchased.

- See if you can change the model so that the service (ShoppingService.java and ShoppingServiceImpl.java) can create an invoice
```
public boolean purchaseItems(ShoppingCart shoppingCart);

to

public Invoice purchaseItems(ShoppingCart shoppingCart);

```

# New Exercises for week 4 - Farm Factory and Facade Exercise


## Exercise 1 factoryAndFacade

In this exercise you will look at Java Collections and Generics, complete a simple factory method and implement and create tests for a simple facade interface. 

You will then look at how to generate skeleton code from UML class diagrams in Netbeans.

Finally you will look at a more complex example which completely seperates the client from the implementation classes.

Try the  [factoryandfacade](../week4/factoryandfacade) exercises.



