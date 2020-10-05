/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.simple.tests;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.factoryandfacade.impl.dao.simple.AnimalTypeDaoImpl;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;

/**
 *
 * @author gallenc
 */
public class AnimalTypeDaoTest {

    @Test
    public void testAnimalTypeDao() {
        System.out.println("start of testAnimalTypeDao");
        AnimalTypeDao animalTypeDao = new AnimalTypeDaoImpl();

        List<AnimalType> supportedAnimalTypes = animalTypeDao.getSupportedAnimalTypes();
        for (AnimalType type : supportedAnimalTypes) {
            String typeName = type.getType();
            AnimalType rxType = animalTypeDao.getAnimalType(typeName);
            System.out.println("test type=" + type + " rxType=" + rxType);
            assertNotNull(rxType);
            assertEquals(type.getType(), rxType.getType());
            assertEquals(type.getSound(), rxType.getSound());
        }
        System.out.println("end of testAnimalTypeDao");
    }
}
