/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.simple.tests;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.factoryandfacade.impl.dao.simple.AnimalDaoImpl;
import org.solent.com504.factoryandfacade.impl.dao.simple.AnimalTypeDaoImpl;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;

/**
 *
 * @author gallenc
 */
public class AnimalDaoTest {

    @Test
    public void testDao() {
        System.out.println("start of testAnimalDao");
        AnimalDao animalDao = new AnimalDaoImpl();
        AnimalTypeDao animalTypeDao = new AnimalTypeDaoImpl();

        // create one of each animal type and test you can retreive same
        for (AnimalType type : animalTypeDao.getSupportedAnimalTypes()) {
            Animal newAnimal = animalDao.create(type);
            assertNull(newAnimal.getId());

            Animal savedAnimal = animalDao.updateOrSave(newAnimal);
            assertNotNull(savedAnimal);
            assertNotNull(savedAnimal.getId());

            // add name and address
            savedAnimal.setName("name_" + newAnimal.getId());
            savedAnimal.setAddress("address_" + newAnimal.getId());
            savedAnimal = animalDao.updateOrSave(newAnimal);

            System.out.println("created new animal=" + savedAnimal);
            Animal retrievedAnimal = animalDao.retrieve(savedAnimal.getId());
            System.out.println("retreived new animal=" + retrievedAnimal);
            assertTrue(retrievedAnimal.toString().equals(savedAnimal.toString()));
        }

        // test we can retreive all animals
        List<Animal> animals = animalDao.retrieveAll();
        assertEquals(animalTypeDao.getSupportedAnimalTypes().size(), animals.size());

        // retreive animal id = 2
        Animal animal2 = animalDao.retrieve(2L);
        System.out.println("\nretreived animal2=" + animal2);
        assertTrue(animal2.getAddress().equals("address_2"));

        AnimalType type = animalTypeDao.getSupportedAnimalTypes().get(0);
        System.out.println("\ncreating animal for type=" + type);
        Animal templateAnimal = animalDao.create(type);

        System.out.println("searching for animal" + templateAnimal);
        List<Animal> animallist = animalDao.retrieve(templateAnimal);

        assertEquals(1, animallist.size());
        for (Animal animal : animallist) {
            System.out.println("list animal=" + animal);
        }

    }
}
