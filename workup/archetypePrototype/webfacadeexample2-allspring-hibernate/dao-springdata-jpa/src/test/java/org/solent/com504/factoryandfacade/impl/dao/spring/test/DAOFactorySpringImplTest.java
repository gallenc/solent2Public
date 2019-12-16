/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.spring.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.factoryandfacade.impl.dao.spring.DAOFactorySpringImpl;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;
import org.solent.com504.factoryandfacade.model.dao.DAOFactory;

/**
 *
 * @author gallenc
 */
public class DAOFactorySpringImplTest {

    @Test
    public void testDAOFActory() {
        DAOFactory daoFactory = new DAOFactorySpringImpl();
        AnimalDao animalDao = daoFactory.getAnimalDao();
        assertNotNull(animalDao);
        AnimalTypeDao animalTypeDao = daoFactory.getAnimalTypeDao();
        assertNotNull(animalTypeDao);

    }
}
