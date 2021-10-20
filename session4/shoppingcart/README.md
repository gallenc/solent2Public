# Shopping Cart example

This exercise shows you how you can start building a shopping cart application.

In a previous example, we looked at how a list of names could be held in the session object.
This meant that each user accessing the application through a different web browser had access to a different list of names in their own session.

This shopping cart example takes this further by storing a new ShoppingCart object in each user session. 
Every user will have their own individual shopping cart which they can fill with goods in order to make a purchase.

There is also a common ShoppingService which provides the list of available goods which can be added to the shopping cart.
The ShoppingService is called a 'singleton' because there is only ever one ShoppingService used by the whole application.

You have been provided with a partially complete project which you should use as your starting point [webApplicationExercise2](../shoppingcart/webApplicationExercise2 )

An answer is also provided but please try and complete the exercise before you are tempted to look at the answer [webApplicationExercise2-answer](../shoppingcart/webApplicationExercise2-answer )


