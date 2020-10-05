/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.week10.model.test;

import org.junit.Test;
import static org.junit.Assert.*;
import solent.ac.uk.ood.examples.week10.model.Car;
import solent.ac.uk.ood.examples.week10.model.Lorry;
import solent.ac.uk.ood.examples.week10.model.Motorbike;
import solent.ac.uk.ood.examples.week10.model.Vehicle;

/**
 *
 * @author gallenc
 */
public class TestVehicles {

    @Test
    public void bikeTest1() {
        double TEST_VALUE = 1690.00;
        Vehicle bike = new Motorbike();
        bike.setWeight(TEST_VALUE);
        double price = bike.calculateFee();
        System.out.println("bike weight=" + TEST_VALUE + " price=" + price);
        assertEquals(3.00, price, 0);
    }

    @Test
    public void carTest1() {
        double TEST_VALUE = 1690.00;
        Vehicle car = new Car();
        car.setWeight(TEST_VALUE);
        double price = car.calculateFee();
        System.out.println("car weight=" + TEST_VALUE + " price=" + price);
        assertEquals(5.00, price, 0);
    }

    @Test
    public void carTest2() {
        double TEST_VALUE = 1695.00;
        Vehicle car = new Car();
        car.setWeight(TEST_VALUE);
        double price = car.calculateFee();
        System.out.println("car weight=" + TEST_VALUE + " price=" + price);
        assertEquals(5.00, price, 0);
    }

    @Test
    public void carTest3() {
        double TEST_VALUE = 1895.00;
        Vehicle car = new Car();
        car.setWeight(TEST_VALUE);
        double price = car.calculateFee();
        System.out.println("car weight=" + TEST_VALUE + " price=" + price);
        assertEquals(5.20, price, 0);
    }

    @Test
    public void lorryTest1() {
        double TEST_VALUE = 1690.00;
        Vehicle lorry = new Lorry();
        lorry.setWeight(TEST_VALUE);
        double price = lorry.calculateFee();
        System.out.println("lorry weight=" + TEST_VALUE + " price=" + price);
        assertEquals(10.00, price, 0);
    }

    @Test
    public void lorryTest2() {
        double TEST_VALUE = 9000;
        Vehicle lorry = new Lorry();
        lorry.setWeight(TEST_VALUE);
        double price = lorry.calculateFee();
        System.out.println("lorry weight=" + TEST_VALUE + " price=" + price);
        assertEquals(15.00, price, 0);
    }
}
