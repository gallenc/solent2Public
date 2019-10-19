/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.service.test;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.factoryandfacade.impl.service.ServiceObjectFactoryImpl;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.service.FarmFacade;
import org.solent.com504.factoryandfacade.model.service.ServiceObjectFactory;

/**
 *
 * @author gallenc
 */
public class FarmFacadeTest {

    ServiceObjectFactory serviceObjectFactory = null;
    FarmFacade farmFacade = null;

    List<String> supportedAnimalTypes = null;

    @Before
    public void loadFactory() {
        serviceObjectFactory = new ServiceObjectFactoryImpl();
        farmFacade = serviceObjectFactory.getFarmFacade();

        // delete all previously added animals
        List<Animal> allAnimals = farmFacade.getAllAnimals();
        for (Animal animal : allAnimals) {
            farmFacade.removeAnimal(animal.getName());
        }
        allAnimals = farmFacade.getAllAnimals();
        assertTrue(allAnimals.isEmpty());
    }

    @Test
    public void testFactory() {
        System.out.println("start FarmFacadeTest testFactory");
        assertNotNull(farmFacade);

        supportedAnimalTypes = farmFacade.getSupportedAnimalTypes();
        assertNotNull(supportedAnimalTypes);
        assertEquals(3, supportedAnimalTypes.size());

        assertTrue(supportedAnimalTypes.contains("Cat"));
        assertTrue(supportedAnimalTypes.contains("Dog"));
        assertTrue(supportedAnimalTypes.contains("Cow"));

        System.out.println("end FarmFacadeTest testFactory");
    }

    @Test
    public void testaddDuplicateAnimal() {
        System.out.println("start FarmFacadeTest testaddDuplicateAnimal");

        assertNotNull(farmFacade);

        String type = farmFacade.getSupportedAnimalTypes().get(0);
        String name = "duplicateTestName";
        farmFacade.addAnimal(type, name);
        assertEquals(1, farmFacade.getAllAnimals().size());

        try {
            // adding duplicate name - should throw exception
            farmFacade.addAnimal(type, name);
            fail("adding duplicate animal did not throw IllegalArgumentException ");
        } catch (IllegalArgumentException e) {
        }

        assertEquals(1, farmFacade.getAllAnimals().size());

        System.out.println("end FarmFacadeTest testaddDuplicateAnimal");
    }

    @Test
    public void testFarmFacade() {
        System.out.println("start FarmFacadeTest testFarmFacade");

        assertNotNull(farmFacade);

        supportedAnimalTypes = farmFacade.getSupportedAnimalTypes();
        assertNotNull(supportedAnimalTypes);

        System.out.println("testing adding supported animals to facade:");
        // create 3 of every animal type
        for (int i = 0; i < 3; i++) {
            for (String animalType : supportedAnimalTypes) {
                String name = animalType + "_" + i;
                farmFacade.addAnimal(animalType, name);
            }
        }

        List<Animal> allAnimals = farmFacade.getAllAnimals();
        assertEquals(9, allAnimals.size());
        for (Animal animal : allAnimals) {
            System.out.println("facade Animal:" + animal);
        }

        // testwe can retreive only animals of certain kind (Cat)
        List<Animal> cats = farmFacade.getAnimalsOfType("Cat");
        assertEquals(3, cats.size());

        //remove all cats
        for (Animal cat : cats) {
            String name = cat.getName();
            farmFacade.removeAnimal(name);
        }

        // check all cats are removed
        assertEquals(0, farmFacade.getAnimalsOfType("Cat").size());

        // check that 3 animals ( cats0 hae been removed
        allAnimals = farmFacade.getAllAnimals();
        assertEquals(6, allAnimals.size());

        // pick 4th animal from list of all animals and check we can retrieve it by name
        Animal animal = allAnimals.get(4);
        System.out.println(animal);

        Animal animal2 = farmFacade.getAnimal(animal.getName());
        assertEquals(animal.getName(), animal2.getName());
        assertEquals(animal.getAnimalType(), animal2.getAnimalType());

        System.out.println("end FarmFacadeTest testFarmFacade");

    }
}
