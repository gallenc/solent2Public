/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.week2.web;

import java.util.Arrays;
import java.util.List;
import org.solent.com504.oodd.week2.model.ShoppingCart;
import org.solent.com504.oodd.week2.model.ShoppingItem;
import org.solent.com504.oodd.week2.model.ShoppingService;

/**
 *
 * @author cgallen
 */
public class ShoppingServiceImpl implements ShoppingService {

    @Override
    public List<ShoppingItem> getAvailableItems() {
        return Arrays.asList(new ShoppingItem("house", 20000.00),
                new ShoppingItem("hen", 5.00),
                new ShoppingItem("car", 5000.00),
                new ShoppingItem("pet alligator", 65.00)
                );
    
    }

    @Override
    public boolean purchaseItems(ShoppingCart shoppingCart) {
        System.out.println("purchased items");
        for(ShoppingItem shoppingItem : shoppingCart.getShoppingCartItems()){
            System.out.println(shoppingItem);
        }
        
        return true;
    }
    
}
