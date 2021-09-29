/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gallenc.com504.ood.Models.Bridge;

import org.gallenc.com504.ood.Models.Vehicle.Vehicle;

/**
 *
 * @author rgaud
 */
public class Bridge {
    private Vehicle[] currentVehicles = new Vehicle[20];
    
    
    public Double calcTotalWeight(){
        Double totalWeight = 0.0;
        for(int i =0; i < currentVehicles.length; i++){
            if(currentVehicles[i] != null){
                totalWeight += currentVehicles[i].getWeight();
            }
        }
        return totalWeight;
    }
    
    public boolean requestAddVehicle(Vehicle vehicle){
        //Only 20 vehicles & Weight
        int arrayCounter = 0;
        for (int i = 0; i < currentVehicles.length; i ++)
            if (currentVehicles[i] != null)
                arrayCounter ++;
        if(arrayCounter < 20 && (calcTotalWeight() + vehicle.getWeight() < 30000)){
            for(int i =0; i < currentVehicles.length; i++){
                 if(currentVehicles[i] == null){
                     currentVehicles[i] = vehicle;
                     return true;
                 }
            }
            return true;
        }
        return false;
    }
    
    public boolean removeVehicle(String registration){
        for(int i = 0; i < currentVehicles.length; i++){
            if(currentVehicles[i] != null && 
                    currentVehicles[i].getRegNumber().equals(registration)){
                currentVehicles[i] = null;
                return true;
            }
        }
        return false;
    }
    
    public Double calcTotalCost(){
        //Of all cars on the bridge
        Double totalCost = 0.0;
        for(int i =0; i < currentVehicles.length; i++){
            if(currentVehicles[i] != null){
                totalCost += currentVehicles[i].calculateFee();
            }
        }
        return totalCost;
    }
}
