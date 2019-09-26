/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.test;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.factoryandfacade.impl.FarmObjectFactoryImpl;
import org.solent.com504.factoryandfacade.model.Animal;
import org.solent.com504.factoryandfacade.model.Cat;
import org.solent.com504.factoryandfacade.model.Cow;
import org.solent.com504.factoryandfacade.model.Dog;
import org.solent.com504.factoryandfacade.model.FarmFacade;
import org.solent.com504.factoryandfacade.model.FarmObjectFactory;

/**
 *
 * @author gallenc
 */
public class FarmFacadeTest {

    FarmObjectFactory farmObjectFactory = null;
    List<String> supportedAnimalTypes = null;

    @Before
    public void loadFactory() {
        farmObjectFactory = new FarmObjectFactoryImpl();
        supportedAnimalTypes = farmObjectFactory.getSupportedAnimalTypes();
    }

    @Test
    public void testFactory() {

        assertNotNull(farmObjectFactory);

        assertNotNull(supportedAnimalTypes);
        assertEquals(3, supportedAnimalTypes.size());

        assertTrue(supportedAnimalTypes.contains(Cat.ANIMAL_TYPE));
        assertTrue(supportedAnimalTypes.contains(Dog.ANIMAL_TYPE));
        assertTrue(supportedAnimalTypes.contains(Cow.ANIMAL_TYPE));

        System.out.println("testing supported animal types:");
        for (String animalType : supportedAnimalTypes) {
            Animal animal = farmObjectFactory.createAnimal(animalType);
            assertNotNull(animal);
            System.out.println("created:" + animal);
        }
    }

    @Test
    public void testFarmFacade() {
        assertNotNull(farmObjectFactory);
        assertNotNull(supportedAnimalTypes);
        FarmFacade farmFacade = farmObjectFactory.createFarmFacade();

        System.out.println("testing adding animals to facade:");
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
            System.out.println("facade Animal:"+animal);
        }

        List<Animal> cats = farmFacade.getAnimalsOfType(Cat.ANIMAL_TYPE);
        assertEquals(3, cats.size());

        //remove all cats
        for (Animal cat : cats) {
            String name = cat.getName();
            farmFacade.removeAnimal(name);
        }
        assertEquals(0, farmFacade.getAnimalsOfType(Cat.ANIMAL_TYPE).size());

        allAnimals = farmFacade.getAllAnimals();
        assertEquals(6, allAnimals.size());

        Animal animal = allAnimals.get(4);
        System.out.println(animal);

        Animal animal2 = farmFacade.getAnimal(animal.getName());
        assertEquals(animal.getName(), animal2.getName());
        assertEquals(animal.getAnimalType(), animal2.getAnimalType());

    }
}
