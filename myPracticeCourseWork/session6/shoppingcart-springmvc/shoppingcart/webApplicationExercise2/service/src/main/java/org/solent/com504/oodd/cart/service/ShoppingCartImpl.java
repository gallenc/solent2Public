/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;

/**
 *
 * @author cgallen
 */
public class ShoppingCartImpl implements ShoppingCart {

    private static final Logger logger = LogManager.getLogger(ShoppingCartImpl.class);
    private HashMap<String, ShoppingItem> itemMap = new HashMap<String, ShoppingItem>();

    @Override
    public List<ShoppingItem> getShoppingCartItems() {
        List<ShoppingItem> itemlist = new ArrayList();
        for (String itemUUID : itemMap.keySet()) {
            ShoppingItem shoppingCartItem = itemMap.get(itemUUID);
            itemlist.add(shoppingCartItem);
        }
        return itemlist;
    }

    @Override
    public void addItemToCart(ShoppingItem shoppingItem) {
        for(ShoppingItem item:itemMap.values())
        {
          if(item.getName().equals(shoppingItem.getName())){
              logger.info("Adding exisisting item " + shoppingItem.getName() + ". New Quantity: " + (item.getQuantity() + 1));
              item.setQuantity(item.getQuantity() + 1);
              return;
          }
        }
        logger.info("Adding new item " + shoppingItem.getName());
        itemMap.put(shoppingItem.getUuid(), shoppingItem);
    }

    @Override
    public void removeItemFromCart(String itemUuid) {
        ShoppingItem item = itemMap.get(itemUuid);
        if(item.getQuantity() > 1){
            item.setQuantity(item.getQuantity() - 1);
        }
        else{
            itemMap.remove(itemUuid);
        }
    }

    @Override
    public double getTotal() {
        double total = 0.0;
        if(itemMap.size() > 0){
            for(Map.Entry<String, ShoppingItem> entry : itemMap.entrySet()) {
            String key = entry.getKey();
            ShoppingItem item = entry.getValue();

            double valueToAdd = item.getPrice() * item.getQuantity();
            total = total + valueToAdd;
            }
        }
        return total;
    }

}
