/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.spring.test;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
public class AnimalDaoImplSpringTest {

    final static Logger LOG = LogManager.getLogger(AnimalDaoImplSpringTest.class);

    @Autowired
    private AnimalDao animalDao = null;

    @Before
    public void before() {
        assertNotNull(animalDao);
    }

    // initialise database
    private void init() {
        LOG.debug("initialising database for test");
        // delete all animals
        animalDao.deleteAll();
        // create a number of test animals

        // create animalTypes
        AnimalType animalTypeCat = new AnimalType();
        animalTypeCat.setType("Cat");
        animalTypeCat.setSound("Meoww");

        AnimalType animalTypeDog = new AnimalType();
        animalTypeDog.setType("Dog");
        animalTypeDog.setSound("Woof");

        AnimalType animalTypeCow = new AnimalType();
        animalTypeCow.setType("Cow");
        animalTypeCow.setSound("Moo");

        // create 3 animals of each type and add to database
        for (int i = 0; i < 3; i++) {
            Animal animal1 = animalDao.create(animalTypeCat);
            animal1.setName("Cat_" + i);
            animal1.setAddress("Cat_" + i + "_Address");
            assertNotNull(animal1);
            //assertNull(animal1.getId());

            // add animal1 to database
            animal1 = animalDao.updateOrSave(animal1);
            assertNotNull(animal1.getId());

            Animal animal2 = animalDao.create(animalTypeDog);
            animal2.setName("Dog1_" + i);
            animal1.setAddress("Dog_" + i + "_Address");
            assertNotNull(animal2);
            //assertNull(animal2.getId());

            // add animal1 to database
            animal1 = animalDao.updateOrSave(animal2);
            assertNotNull(animal2.getId());

            Animal animal3 = animalDao.create(animalTypeDog);
            animal3.setName("Cow1_" + i);
            animal3.setAddress("Cow_" + i + "_Address");
            assertNotNull(animal3);
           // assertNull(animal3.getId());

            // add animal1 to database
            animal1 = animalDao.updateOrSave(animal3);
            assertNotNull(animal3.getId());
        }

        // check we have added 9 animals
        List<Animal> animals = animalDao.retrieveAll();
        assertNotNull(animals);
        assertEquals(9, animals.size());
        LOG.debug("before test created animals:");
        for (Animal a : animals) {
            LOG.debug("\n   " + a);
        }

    }

    @Test
    public void createTest() {
        LOG.debug("start of createTest");
        AnimalType animalType = new AnimalType();
        animalType.setType("Cat");
        animalType.setSound("Meoww");
        Animal animal = animalDao.create(animalType);
        assertNotNull(animal);
        LOG.debug("end of createTest");
    }

    @Test
    public void updateOrSaveTest() {
        LOG.debug("start of updateOrSaveTest");

        assertNotNull(animalDao);
        init(); // initialise database

        List<Animal> animals = animalDao.retrieveAll();
        assertFalse(animals.isEmpty());

        // get animal in middle of index
        int index = animals.size() / 2;
        Animal animal = animals.get(index);
        LOG.debug("initial animal index:" + index + " " + animal);

        // change values
        animal.setAddress("new address");
        animal.setName("new name");
        LOG.debug("new animal details " + animal);

        // save animal
        animalDao.updateOrSave(animal);
        Long id = animal.getId();

        // retrieve another copy of the animal
        Animal newAnimal = animalDao.retrieve(id);
        LOG.debug("retrieved  details " + newAnimal);
        // quick and dirty equals
        assertTrue(animal.toString().equals(newAnimal.toString()));
        LOG.debug("end of updateOrSaveTest");
    }

    @Test
    public void retrieveTest() {
        LOG.debug("start of retrieveTest()");
        assertNotNull(animalDao);
        init(); // initialise database

        // create an animal template
        AnimalType animalType = new AnimalType();
        animalType.setType("Cat");
        animalType.setSound("Meoww");
        Animal animalTemplate = animalDao.create(animalType);
        assertNotNull(animalTemplate);

        // retrieve all animals
        animalTemplate.setAnimalType(null);
        animalTemplate.setName(null);
        LOG.debug("search using animalTemplate = :" + animalTemplate);

        List<Animal> animalsRetrieved = animalDao.retrieve(animalTemplate);
        LOG.debug(animalsRetrieved.size() + " animalsRetrieved:");
        for (Animal a : animalsRetrieved) {
            LOG.debug("\n   " + a);
        }

        // retrieve all with name
        animalTemplate.setAnimalType(null);
        animalTemplate.setName("Cat_2");
        LOG.debug("search using animalTemplate = :" + animalTemplate);

        animalsRetrieved = animalDao.retrieve(animalTemplate);
        LOG.debug(animalsRetrieved.size() + " animalsRetrieved:");
        for (Animal a : animalsRetrieved) {
            LOG.debug("\n   " + a);
        }

        // retrieve only cats 
        animalTemplate.setAnimalType(animalType);
        animalTemplate.setName(null);
        LOG.debug("animalTemplate = :");

        animalsRetrieved = animalDao.retrieve(animalTemplate);
        LOG.debug(animalsRetrieved.size() + " animalsRetrieved:");
        for (Animal a : animalsRetrieved) {
            LOG.debug("\n   " + a);
        }

        // retrieve cats named
        animalTemplate.setAnimalType(animalType);
        animalTemplate.setName("Cat_2");
        LOG.debug("search using animalTemplate = :" + animalTemplate);

        animalsRetrieved = animalDao.retrieve(animalTemplate);
        LOG.debug(animalsRetrieved.size() + " animalsRetrieved:");
        for (Animal a : animalsRetrieved) {
            LOG.debug("\n   " + a);
        }
        assertEquals(1, animalsRetrieved.size());

        //animalDao.retrieve(animalTemplate);
        LOG.debug("end of retrieveTest()");
    }

    @Test
    public void deleteTest() {
        LOG.debug("start of deleteTest()");
        assertNotNull(animalDao);
        init(); // initialise database

        List<Animal> animals = animalDao.retrieveAll();
        assertFalse(animals.isEmpty());

        Animal a = animals.get(0);
        LOG.debug("deleting " + a);
        Long id = a.getId();

        animalDao.delete(id);

        Animal a2 = animalDao.retrieve(id);
        assertNull(a2);

        LOG.debug("end of deleteTest()");
    }

    @Test
    public void retrieveAllTest() {
        LOG.debug("start of retrieveAllTest()");
        assertNotNull(animalDao);
        init(); // initialise database
        List<Animal> animalsRetrieved = animalDao.retrieveAll();

        LOG.debug(animalsRetrieved.size() + " animalsRetrieved:");
        for (Animal a : animalsRetrieved) {
            LOG.debug("\n   " + a);
        }

        LOG.debug("end of retrieveAllTest()");
    }

}
