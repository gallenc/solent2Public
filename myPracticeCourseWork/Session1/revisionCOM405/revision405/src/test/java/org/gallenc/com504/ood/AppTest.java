package org.gallenc.com504.ood;

import org.gallenc.com504.ood.Models.Bridge.Bridge;
import org.gallenc.com504.ood.Models.Vehicle.Car;
import org.gallenc.com504.ood.Models.Vehicle.Vehicle;
 import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    //Bridge Tests
    
    @Test
    public void shouldNotAdd21Items(){
        Bridge bridge = new Bridge();
        addInitialVehicles(20, bridge);
        
        Vehicle vehicle = new Car();
        vehicle.setRegNumber("regFail");
        vehicle.setWeight(Double.valueOf(50));
        assertFalse(bridge.requestAddVehicle(vehicle));
    }
    
    @Test
    public void shouldNotExceedWeight(){
        Bridge bridge = new Bridge();
        addInitialVehicles(19, bridge);
        
        Vehicle vehicle = new Car();
        vehicle.setRegNumber("regFail");
        vehicle.setWeight(Double.valueOf(50000));
        assertFalse(bridge.requestAddVehicle(vehicle));
    }
    
    @Test
    public void testCalculateFee() {
        Bridge bridge = new Bridge();
        addInitialVehicles(10, bridge);
        assertEquals(50.0, bridge.calcTotalCost(), 0.0);
    }
    
    @Test
    public void removeVehicle(){
        Bridge bridge = new Bridge();
        addInitialVehicles(10, bridge);
        
        bridge.removeVehicle("reg1");
        bridge.removeVehicle("reg2");
    }
    
    private void addInitialVehicles(int number, Bridge bridge){
        for(int i =0; i < number; i++){
            Vehicle vehicle = new Car();
            vehicle.setRegNumber("reg" + i);
            vehicle.setWeight(Double.valueOf(i * 100));
            assertTrue(bridge.requestAddVehicle(vehicle));
        }
    }
}
