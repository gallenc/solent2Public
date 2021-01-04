# Week 3 Exercises

## webApplicationExercise2

### getting started
Follow the instructions on the main page of the repository to merge my new work with your repository

### merge and refactor your new code from last weeks example

This week we are looking at a more complete version of the web application we looked at last week. 

You will see we have split the project into seperate modules; model, service and web and also have a supporting UML model UMLmodel

Each layer has object actories which the higher layer can use to access the library.

See if you can merge the changes you made to last weeks web application into this version.

### You should have already found solutions to

- See if you can remove an item from the shopping cart - at the moment this throws a notImplemented exception in one of the classes.

- See if you can calculate a total and display the total of all items in the shopping cart

- See if you can work out how to change the quantities  of each item and calculate a total (needs a little thought);

### New work:

- See if you can add comprehensive tests to the service layer

- See if you can add log messages whenever items are added to the cart or a cart is purchased.

- See if you can change the model so that the service (ShoppingService.java and ShoppingServiceImpl.java) can create an invoice
```
public boolean purchaseItems(ShoppingCart shoppingCart);

to

public Invoice purchaseItems(ShoppingCart shoppingCart);

```



