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
public abstract class Vehicle {
    //Reg Number
    private String regNumber;
    //Getter
    public String getRegNumber(){
        return regNumber;
    }
    //Setter
    public void setRegNumber(String newReg){
        this.regNumber = newReg;
    }
    
    //Weight
    private Double weight;
    //Getter
    public Double getWeight(){
        return weight;
    }
    //Setter
    public void setWeight(Double newWeight){
        this.weight = newWeight;
    }
    
    public abstract Double calculateFee();
}
