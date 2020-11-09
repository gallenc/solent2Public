/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.week2.model;

import java.util.List;

/**
 *
 * @author cgallen
 */
public interface ShoppingCart {

    public List<ShoppingItem> getShoppingCartItems();
    
    public void addItemToCart(ShoppingItem shoppingItem);
    
    public void removeItemFromCart(String itemUuid);
    
    public double getTotal();
    
}
