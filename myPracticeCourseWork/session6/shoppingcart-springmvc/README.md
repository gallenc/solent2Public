# Exercise 2 shoppingcart-springmvc

This exercise follows on from the work you did in  [Exercise 1 simple-springmvc](../../session6/simple-springmvc) 

In this exercise you will migrate the code from your shopping cart application to use SpringMVC

We will do this in two parts.

## Part 1
Migrate the simple shopping cart you created in the [session4 shopping cart exercise](../../session4/shoppingcart) 

In this case you only have to migrate one JSP with no bootstrap.

Following the steps in last [Exercise 1 simple-springmvc](../../session6/simple-springmvc) , you will need to

1. add an application.properties file to tell SpringMVC where the views are
2. move the home.jsp under WEB-INF/views
2. add a MVCController class and add a request method which calls the home.jsp (you can copy and modify the class from the previous example)
3. change the web references in the home.jsp forms to match the @RequestMapping(value in the request method
This should now give you a working app with the home.jsp as a view. 

You still have to migrate the code in the jsp:

4. add the @RequestParameter annotations to the requesting method
5. move the business logic out of the JSP and into the request method in the controller
6. make sure the return values needed by the page are now injected into the model
7. change the java code in the JSP to use JSTL and the model variables


An example answer is provided [webApplicationExercise2Spring-answer](../shoppingcart-springmvc/webApplicationExercise2Spring-answer/ )
 but dont look at this until you have tried it yourself.


## Part 2
In session 4 you enhanced the simple shopping cart application to have a navbar using bootstrap 

see if you can migrate [session4 shopping cart bootstrap exercise](../../session4/shoppingcart-bootstrap) to use spring mvc

In this case you need to 
1. start with the changes you did to migrate the single home.jsp page in Part 1
2. add the additional header.jsp and footer.jsp, about.jsp and contact.jsp to the view and change the home.jsp to use them
3. add additional methods to the controller for the new pages. Note header.jsp and footer.jsp do not need methods as they are imported by the other JSPs
4. You should be able to inject the 'selectedPage' using the model instead of code at the top of each jsp.

Again an example answer is provided [webApplicationExercise3Spring-answer](../shoppingcart-springmvc/webApplicationExercise3Spring-answer/ )
 but dont look at this until you have tried it yourself.
 
 Note that in this example answer, there is one additional change which has been made. 
 Instead of usign a static WebObjectFactory, we can use Spring to inject the objects that we need in the controller.
 Rather than storing data directly in the session object, we can create a bean which is session scoped and use this to store our shopping cart.
 The @Autowired annotation will inject these automatically.
 
 See [Answer MVCController.java](../shoppingcart-springmvc/webApplicationExercise3Spring-answer/web/src/main/java/org/solent/com504/oodd/cart/spring/web/MVCController.java )
 
 ```
     @Autowired
    ShoppingService shoppingService =null;
    
    // note that scope is session in configuration
    // so the shopping cart is unique for each web session
    @Autowired
    ShoppingCart shoppingCart = null;
 ```
In the configuration we then put the bean definitions.
 
 See [Answer SpringBootJspConfiguration.java](../shoppingcart-springmvc/webApplicationExercise3Spring-answer/web/src/main/java/org/solent/com504/oodd/cart/spring/web/SpringBootJspConfiguration.java  )

 ```
 package org.solent.com504.oodd.cart.spring.web;

import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.solent.com504.oodd.cart.service.ServiceObjectFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class SpringBootJspConfiguration {

    @Bean
    ShoppingService getShoppingService() {
        return ServiceObjectFactory.getShoppingService();
    }

    // see https://www.baeldung.com/spring-mvc-session-attributes
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ShoppingCart getNewShoppingCart() {
        return ServiceObjectFactory.getNewShoppingCart();
    }
}

 
 ```
 
 
 
