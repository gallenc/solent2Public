/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.jpa.test;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.solent.com504.factoryandfacade.impl.dao.jpa.DAOFactory;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;

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
}
