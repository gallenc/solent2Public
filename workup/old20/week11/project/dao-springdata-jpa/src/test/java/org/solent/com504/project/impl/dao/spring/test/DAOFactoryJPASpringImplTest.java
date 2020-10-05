/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.spring.test;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.project.impl.dao.spring.DAOFactoryJPASpringImpl;
import org.solent.com504.project.model.dao.DAOFactory;

/**
 *
 * @author gallenc
 */
public class DAOFactoryJPASpringImplTest {

    @Test
    public void testFactory() {

        DAOFactory daoFactory = new DAOFactoryJPASpringImpl();
        assertNotNull(daoFactory.getAppointmentDAO());
        assertNotNull(daoFactory.getPersonDAO());
    }
}

//    @Test
//    public void applicationContextTest() {
//        LOG.debug("start of applicationContextTest");
//        // this test simply runs the before method and prints out the contents of the context
//        String[] beannames = applicationContext.getBeanDefinitionNames();
//
//        String msg = "******* beans " + beannames.length + " names: ";
//        for (String b : Arrays.asList(beannames)) {
//            msg = msg + "\n " + b + ", ";
//        }
//        LOG.debug(msg);
//
//        LOG.debug("end of applicationContextTest");
//    }
