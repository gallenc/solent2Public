/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.jaxb.tests;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.factoryandfacade.impl.dao.jaxb.AnimalDaoJaxbImpl;
import org.solent.com504.factoryandfacade.impl.dao.simple.AnimalDaoImpl;
import org.solent.com504.factoryandfacade.impl.dao.simple.AnimalTypeDaoImpl;
import org.solent.com504.factoryandfacade.impl.dao.simple.tests.AnimalDaoTest;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;

/**
 *
 * @author gallenc
 */
public class AnimalDaoJaxbTest {

    AnimalDao animalDao = null;
    AnimalTypeDao animalTypeDao = null;
    AnimalDaoTest animalDaoTest = null;

    @Before
    public void init() {
        String testFilePath = "./target/testDaoFile.txt";
        animalDao = new AnimalDaoJaxbImpl(testFilePath);
        animalTypeDao = new AnimalTypeDaoImpl();
        animalDaoTest = new AnimalDaoTest();
        animalDaoTest.setAnimalDao(animalDao);
        animalDaoTest.setAnimalTypeDao(animalTypeDao);
    }

    @Test
    public void testDao() {
        System.out.println("start of testAnimalDaoJaxb");

        animalDaoTest.testDao();
        

        System.out.println("end of testAnimalDaoJaxb");
    }
}
