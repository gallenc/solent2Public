/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.week10.model.test;

import org.junit.Test;
import static org.junit.Assert.*;
import solent.ac.uk.ood.examples.week10.model.Bridge;
import solent.ac.uk.ood.examples.week10.model.Car;
import solent.ac.uk.ood.examples.week10.model.Lorry;

/**
 *
 * @author gallenc
 */
public class TestBridge {

    @Test
    public void testAdd21Cars() {
        Bridge bridge = new Bridge();
        for (int i = 0; i < 20; i++) {
            Car car = new Car();
            car.setRegistration("reg" + i);
            car.setWeight(1000.00);
            System.out.println(i + " bridge adding car to bridge:" + car
                    + " total weight=" + bridge.calcTotalWeight()
                    + " number of cars=" + bridge.numberOfVehicles());
            assertTrue(bridge.requestAddVechicle(car));
        }
        Car car = new Car();
        car.setRegistration("regxxx");
        car.setWeight(1590.00);
        assertFalse(bridge.requestAddVechicle(car));
    }

    @Test
    public void testAddTooMuchWeight() {
        Bridge bridge = new Bridge();
        Lorry lorry = new Lorry();
        lorry.setRegistration("regxxx");
        lorry.setWeight(1590.00);
        assertTrue(bridge.requestAddVechicle(lorry));

        Lorry lorry2 = new Lorry();
        lorry2.setRegistration("regxxx");
        lorry2.setWeight(29000.00);
        assertFalse(bridge.requestAddVechicle(lorry2));

    }
    
       @Test
        public void testCalculateFee() {
            
        }
}
