/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.jpa.test;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.solent.com504.factoryandfacade.impl.dao.jpa.DAOFactory;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;

/**
 *
 * @author cgallen
 */
public class AnimalDaoJpaImplTest {

    private AnimalDao animalDao = null;

    @Before
    public void before() {
        animalDao = DAOFactory.getAnimalDao();
        assertNotNull(animalDao);
    }

    @Test
    public void createTest() {

        AnimalType animalType = new AnimalType();
        animalType.setType("Cat");
        animalType.setSound("Meoww");
        Animal animal = animalDao.create(animalType);
        assertNotNull(animal);
    }

    @Test
    public void updateOrSaveTest() {
        assertNotNull(animalDao);

        // create animal1
        AnimalType animalTypeCat = new AnimalType();
        animalTypeCat.setType("Cat");
        animalTypeCat.setSound("Meoww");

        AnimalType animalTypeDog = new AnimalType();
        animalTypeDog.setType("Dog");
        animalTypeDog.setSound("Woof");

        // create animal 1 and check it is given a new id
        Animal animal1 = animalDao.create(animalTypeCat);
        animal1.setName("Cat1");

        assertNotNull(animal1);
        assertNull(animal1.getId());

        // add animal1 to database
        animal1 = animalDao.updateOrSave(animal1);
        assertNotNull(animal1.getId());

        // create animal 1 and check it is given a new id
        Animal animal2 = animalDao.create(animalTypeCat);
        animal2.setName("Dog1");

        assertNotNull(animal2);
        assertNull(animal2.getId());

        // add animal1 to database
        animal1 = animalDao.updateOrSave(animal2);
        assertNotNull(animal2.getId());

    }

    @Test
    public void retrieveTest() {
        assertNotNull(animalDao);

        //animalDao.retrieve(animalTemplate);
    }

    @Test
    public void deleteTest() {
        assertNotNull(animalDao);

        //animalDao.delete(0);
    }

    @Test
    public void retrieveAllTest() {
        assertNotNull(animalDao);

        // animalDao.retrieveAll();
    }

}
