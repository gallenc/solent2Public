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
public class Car extends Vehicle{

    @Override
    public Double calculateFee() {
        Double Fee = 5.0;
        if(this.getWeight() > 1590){
            Double weightOver = this.getWeight() - 1590;
            Double extraFees = (int)(weightOver / 100) * 0.1;
            Fee = Fee + extraFees;
        }
        return Fee;
    }
    
}
