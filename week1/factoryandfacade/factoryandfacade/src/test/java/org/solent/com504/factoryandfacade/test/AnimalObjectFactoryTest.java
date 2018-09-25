/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.factoryandfacade.Animal;
import org.solent.com504.factoryandfacade.AnimalObjectFactory;

/**
 *
 * @author gallenc
 */
public class AnimalObjectFactoryTest {

    @Test
    public void testCreateCat() {
        Animal animal = AnimalObjectFactory.createCat();

        String sound = animal.getSound();

        System.out.println(sound);

        assertEquals("Meoww", sound);

    }

    @Test
    public void testCreateDog() {
        Animal animal = AnimalObjectFactory.createDog();

        String sound = animal.getSound();

        System.out.println(sound);

        assertEquals("Woof", sound);

    }

    @Test
    public void testCreateCow() {
        Animal animal = AnimalObjectFactory.createCow();

        String sound = animal.getSound();

        System.out.println(sound);

        assertEquals("Moo", sound);

    }
}
