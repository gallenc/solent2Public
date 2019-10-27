/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.jpa.test;

import java.util.List;
import static org.junit.Assert.*;
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

    @Test
    public void testDAOFactory() {
        AnimalDao animalDao = DAOFactory.getAnimalDao();
        assertNotNull(animalDao);
    }

    @Test
    public void testDAOAddObject() {
        AnimalDao animalDao = DAOFactory.getAnimalDao();
        assertNotNull(animalDao);
    }

    @Test
    public void createTest() {
        AnimalDao animalDao = DAOFactory.getAnimalDao();
        assertNotNull(animalDao);
        AnimalType animalType = new AnimalType();
        animalType.setType("Cat");
        animalType.setSound("Meoww");
        Animal animal = animalDao.create(animalType);
        assertNotNull(animal);
    }

    @Test
    public void updateOrSaveTest() {
        AnimalDao animalDao = DAOFactory.getAnimalDao();
        assertNotNull(animalDao);
        AnimalType animalType = new AnimalType();
        animalType.setType("Cat");
        animalType.setSound("Meoww");
        Animal animal = animalDao.create(animalType);
        assertNotNull(animal);

        assertNull(animal.getId());

        animal = animalDao.updateOrSave(animal);
        assertNotNull(animal.getId());

    }

    @Test
    public void retrieveTest() {

    }

    @Test
    public void deleteTest() {

    }

    @Test
    public void retrieveAllTest() {

    }

}
