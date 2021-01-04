/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.week2.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.solent.com504.oodd.week2.model.ShoppingCart;
import org.solent.com504.oodd.week2.model.ShoppingItem;
import org.solent.com504.oodd.week2.model.ShoppingService;

/**
 *
 * @author cgallen
 */
public class ShoppingServiceImpl implements ShoppingService {
    
    private HashMap<String,ShoppingItem> itemMap = new HashMap<String,ShoppingItem>();
    
   
    private List<ShoppingItem> itemlist = Arrays.asList(new ShoppingItem("house", 20000.00),
                new ShoppingItem("hen", 5.00),
                new ShoppingItem("car", 5000.00),
                new ShoppingItem("pet alligator", 65.00)
                );
    
    public ShoppingServiceImpl(){
        
        // initialised the hashmap
        for(ShoppingItem item : itemlist){
            itemMap.put(item.getName(), item);
        }
    }

    @Override
    public List<ShoppingItem> getAvailableItems() {
        return itemlist;    
    }

    @Override
    public boolean purchaseItems(ShoppingCart shoppingCart) {
        System.out.println("purchased items");
        for(ShoppingItem shoppingItem : shoppingCart.getShoppingCartItems()){
            System.out.println(shoppingItem);
        }
        
        return true;
    }

    @Override
    public ShoppingItem getNewItemByName(String name) {
        ShoppingItem templateItem = itemMap.get(name);
        ShoppingItem item = new ShoppingItem();
        item.setName(name);
        item.setPrice(templateItem.getPrice());
        item.setQuantity(0);
        item.setUuuid(UUID.randomUUID().toString());
        return item;
    }
    
}
