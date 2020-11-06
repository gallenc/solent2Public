package solent.ac.uk.ood.examples.week10.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Bridge {
    
    private static final int MAX_VECHICLES = 20; // 20 vehicles
    private static final double MAX_WEIGHT = 30000; // 30000kg
            
    private Map<String,Vehicle> vehicles = new LinkedHashMap<String,Vehicle>();

    public Double calcTotalWeight() {
        double totalWeight = 0;
        for (Vehicle vehicle : vehicles.values()){
            totalWeight = totalWeight+ vehicle.getWeight();
        }
        return totalWeight;
    }

    public boolean requestAddVechicle(Vehicle vehicle) {
        if (vehicles.size() >=MAX_VECHICLES  ) return false;
        double totalWeight = calcTotalWeight();
        if (totalWeight+vehicle.getWeight()>MAX_WEIGHT) return false;
        vehicles.put(vehicle.getRegistration(),vehicle);
        return true;
    }

    public boolean requestRemoveVehicle(String registration) {
        if(vehicles.remove(registration)!=null){
            return true;
        } else return false;
    }
    
    public int numberOfVehicles(){
        return vehicles.size();
    }

}
