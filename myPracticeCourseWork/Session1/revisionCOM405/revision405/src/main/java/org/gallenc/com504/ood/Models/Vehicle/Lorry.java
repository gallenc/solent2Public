/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gallenc.com504.ood.Models.Vehicle;

/**
 *
 * @author rgaud
 */
public class Lorry extends Vehicle{

    @Override
    public Double calculateFee() {
        Double fee = 10.0;
        if(this.getWeight() > 8000){
            fee = fee + 5;
        }
        return fee;
    }
    
}
