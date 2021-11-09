/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.json.tests;

import java.io.File;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.factoryandfacade.impl.dao.json.AnimalDaoJsonImpl;
import org.solent.com504.factoryandfacade.impl.dao.simple.AnimalTypeDaoImpl;
import org.solent.com504.factoryandfacade.impl.dao.simple.tests.AnimalDaoTest;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;
import org.solent.com504.factoryandfacade.model.dto.Animal;

/**
 *
 * @author gallenc
 */
public class AnimalDaoJsonTest {

    AnimalDao animalDao = null;
    AnimalTypeDao animalTypeDao = null;
    AnimalDaoTest animalDaoTest = null;
    String testFilePath = "./target/testDaoFile.json";

    @Before
    public void init() {
       
        // remove test file if exists
        File testFile = new File(testFilePath);
        if (testFile.exists()){
            System.out.println("deleting file at start of test: "+testFile.getAbsolutePath());
            testFile.delete();
        }
        
        animalDao = new AnimalDaoJsonImpl(testFilePath);
        animalTypeDao = new AnimalTypeDaoImpl();
        animalDaoTest = new AnimalDaoTest();
        animalDaoTest.setAnimalDao(animalDao);
        animalDaoTest.setAnimalTypeDao(animalTypeDao);
    }

    @Test
    public void testDao() {
        System.out.println("start of testAnimalDaoJson");

        animalDaoTest.testDao();
        
        File testFile = new File(testFilePath);
        assertTrue(testFile.exists());
        
        // now test we can read the file and the file is not empty
        AnimalDao animalDao2 = new AnimalDaoJsonImpl(testFilePath);
        List<Animal> animalList2 = animalDao2.retrieveAll();
        assertTrue(! animalList2.isEmpty());

        System.out.println("end of testAnimalDaoJson");
    }
}
