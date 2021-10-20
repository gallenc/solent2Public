/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;

/**
 *
 * @author rgaud
 */
public class Invoice {
    String invoiceNumber = "";
    LocalDate dateOfPurchase;
    double amountDue;
    List<ShoppingItem> purchasedItems = new ArrayList<ShoppingItem>();
    
    public Invoice(String invoiceNumber, double amount, List<ShoppingItem> purchasedItems){
        this.invoiceNumber = invoiceNumber;
        this.amountDue = amount;
        this.purchasedItems = purchasedItems;
        dateOfPurchase = LocalDate.now();
    }
    
    public String GetInvoiceAsString(){
        return "Invoice Number: " + invoiceNumber + " | Amount Due: " + amountDue + "| Date of Purchase: " + dateOfPurchase;
    }
    
    public List<ShoppingItem> GetItems(){
        return purchasedItems;
    }
}

