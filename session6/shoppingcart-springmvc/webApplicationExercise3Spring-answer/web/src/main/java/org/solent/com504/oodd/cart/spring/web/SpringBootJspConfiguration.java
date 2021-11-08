package org.solent.com504.oodd.cart.spring.web;

import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.solent.com504.oodd.cart.service.ServiceObjectFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
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
