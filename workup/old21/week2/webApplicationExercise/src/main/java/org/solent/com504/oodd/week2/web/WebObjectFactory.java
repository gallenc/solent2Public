/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.week2.web;

import org.solent.com504.oodd.week2.model.ShoppingCart;
import org.solent.com504.oodd.week2.model.ShoppingService;

/**
 *
 * @author cgallen
 */
public class WebObjectFactory {
    
    static ShoppingService shoppingService = new ShoppingServiceImpl();
    
    // cannot instantiate
    private WebObjectFactory(){
        
    }
    
    public static ShoppingService getShoppingService(){
        return shoppingService;
    }
    
    public static ShoppingCart getNewShoppingCart(){
        return new ShoppingCartImpl();
    }
    
}
