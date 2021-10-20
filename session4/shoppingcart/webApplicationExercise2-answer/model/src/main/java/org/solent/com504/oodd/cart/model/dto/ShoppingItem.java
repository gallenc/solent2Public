/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.model.dto;

import java.util.UUID;

/**
 *
 * @author cgallen
 */
public class ShoppingItem {
    
    private String uuid=null;
    private String name=null;
    private Integer quantity=0;
    private Double price=0.0;
    
    public ShoppingItem(){
        
    }

    public ShoppingItem(String name, Double price) {
        this.name = name;
        this.price = price;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuuid) {
        this.uuid = uuuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ShoppingItem{" + "uuuid=" + uuid + ", name=" + name + ", quantity=" + quantity + ", price=" + price + '}';
    }
    
            
    
}
