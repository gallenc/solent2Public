package org.solent.com504.factoryandfacade.springimpl.test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.solent.com504.factoryandfacade.model.Animal;
import org.solent.com504.factoryandfacade.model.FarmFacade;
import org.solent.com504.factoryandfacade.springimpl.AnimalObjectFactorySpring;

public class AnimalObjectFactorySpringTest {

    @Test
    public void testCreateCat() {
        Animal animal1 = AnimalObjectFactorySpring.createCat();
        assertNotNull(animal1);

        String sound = animal1.getSound();

        System.out.println(sound);

        assertEquals("Meoww", sound);

        animal1.setName("kitcat");

        Animal animal2 = AnimalObjectFactorySpring.createDog();
        assertNotNull(animal2);

        animal2.setName("kiki");

        assertNotEquals(animal1, animal2);
        assertNotEquals(animal1.getName(), animal2.getName());
        System.out.println("animal1.getName()=" + animal1.getName()
                + " animal2.getName()=" + animal2.getName());

    }

    @Test
    public void testCreateDog() {
        Animal animal1 = AnimalObjectFactorySpring.createDog();
        assertNotNull(animal1);

        String sound = animal1.getSound();

        System.out.println(sound);

        assertEquals("Woof", sound);

        animal1.setName("fido");

        Animal animal2 = AnimalObjectFactorySpring.createDog();
        assertNotNull(animal2);

        animal2.setName("bonzo");

        assertNotEquals(animal1, animal2);
        assertNotEquals(animal1.getName(), animal2.getName());
        System.out.println("animal1.getName()=" + animal1.getName()
                + " animal2.getName()=" + animal2.getName());
    }

    @Test
    public void testCreateCow() {
        Animal animal1 = AnimalObjectFactorySpring.createCow();
        assertNotNull(animal1);

        String sound = animal1.getSound();

        System.out.println(sound);

        assertEquals("Moo", sound);

        animal1.setName("ermantrude");

        Animal animal2 = AnimalObjectFactorySpring.createDog();
        assertNotNull(animal2);

        animal2.setName("milkme");

        assertNotEquals(animal1, animal2);

        assertNotEquals(animal1.getName(), animal2.getName());
        System.out.println("animal1.getName()=" + animal1.getName()
                + " animal2.getName()=" + animal2.getName());
    }

    @Test
    public void testCreateFarmFacade() {
        FarmFacade farmFacade1 = AnimalObjectFactorySpring.getFarmFacade();
        assertNotNull(farmFacade1);
        
        FarmFacade farmFacade2 = AnimalObjectFactorySpring.getFarmFacade();
        assertNotNull(farmFacade2);
        
        // both references should reference the same class as a singleton
        assertEquals(farmFacade2, farmFacade1);
        
        System.out.println("farmFacade1:"+farmFacade1);
        System.out.println("farmFacade2:"+farmFacade2);

    }
}
