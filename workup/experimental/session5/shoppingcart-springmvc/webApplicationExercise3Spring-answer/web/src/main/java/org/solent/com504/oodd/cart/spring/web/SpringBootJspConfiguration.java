package org.solent.com504.oodd.cart.spring.web;

import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.solent.com504.oodd.cart.service.ServiceObjectFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SpringBootJspConfiguration {

    @Bean
    ShoppingService getShoppingService() {
        return ServiceObjectFactory.getShoppingService();
    }

    @Bean
    @Scope(value = "session")
    public ShoppingCart getNewShoppingCart() {
        return ServiceObjectFactory.getNewShoppingCart();
    }
}
