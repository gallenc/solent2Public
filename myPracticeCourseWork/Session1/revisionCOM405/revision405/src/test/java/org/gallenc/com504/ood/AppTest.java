package org.gallenc.com504.ood;

import org.gallenc.com504.ood.Models.Bridge.Bridge;
import org.gallenc.com504.ood.Models.Vehicle.Car;
import org.gallenc.com504.ood.Models.Vehicle.Lorry;
import org.gallenc.com504.ood.Models.Vehicle.Motorbike;
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
        
        assertTrue(bridge.removeVehicle("reg1"));
        assertTrue(bridge.removeVehicle("reg2"));
        
        addInitialVehicles(12, bridge);
    }
    
    private void addInitialVehicles(int number, Bridge bridge){
        for(int i =0; i < number; i++){
            Vehicle vehicle = new Car();
            vehicle.setRegNumber("reg" + i);
            vehicle.setWeight(Double.valueOf(i * 100));
            assertTrue(bridge.requestAddVehicle(vehicle));
        }
    }
    
    //Vehicle Tests:
    //Test Reg - Get & Set
    //Test Get Weight
    //Test Calculate Fee
    @Test
    public void testCarAndReg(){
        Car car = new Car();
        car.setRegNumber("ABC123");
        assertEquals("ABC123", car.getRegNumber());
        
        car.setWeight(1590.0);
        assertEquals(5.00, car.calculateFee(), 0.00);
        
        car.setWeight(1680.0);
        assertEquals(5.00, car.calculateFee(), 0.00);
        
        car.setWeight(1690.0);
        assertEquals(5.10, car.calculateFee(), 0.00);
        
        car.setWeight(2590.0);
        assertEquals(6.0, car.calculateFee(), 0.00);
    }
    
    @Test
    public void testLorry(){
        Lorry lorry = new Lorry();
        
        lorry.setWeight(7500.0);
        assertEquals(10.00, lorry.calculateFee(), 0.00);
        
        lorry.setWeight(8000.0);
        assertEquals(10.00, lorry.calculateFee(), 0.00);
        
        lorry.setWeight(8001.0);
        assertEquals(15, lorry.calculateFee(), 0.00);
        
        lorry.setWeight(9000.0);
        assertEquals(15, lorry.calculateFee(), 0.00);
    }
    
        @Test
    public void testBike(){
        Motorbike bike = new Motorbike();
        
        bike.setWeight(0.0);
        assertEquals(3.00, bike.calculateFee(), 0.00);
        
        bike.setWeight(8000.0);
        assertEquals(3.00, bike.calculateFee(), 0.00);
        
        bike.setWeight(8001.0);
        assertEquals(3, bike.calculateFee(), 0.00);
        
        bike.setWeight(9000.0);
        assertEquals(3, bike.calculateFee(), 0.00);
    }
}
