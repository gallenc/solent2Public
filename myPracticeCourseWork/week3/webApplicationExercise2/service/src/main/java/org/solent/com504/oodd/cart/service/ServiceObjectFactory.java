/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.service;

import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.service.ShoppingService;

/**
 *
 * @author cgallen
 */
public class ServiceObjectFactory {
    
    static ShoppingService shoppingService = new ShoppingServiceImpl();
    
    // cannot instantiate
    private ServiceObjectFactory(){
        
    }
    
    public static ShoppingService getShoppingService(){
        return shoppingService;
    }
    
    public static ShoppingCart getNewShoppingCart(){
        return new ShoppingCartImpl();
    }
    
}
