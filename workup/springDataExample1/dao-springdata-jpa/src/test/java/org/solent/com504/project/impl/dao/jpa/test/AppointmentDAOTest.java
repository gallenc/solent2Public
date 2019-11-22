/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.jpa.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.project.impl.dao.jpa.DAOFactoryJPAImpl;
import org.solent.com504.project.model.dao.AppointmentDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author cgallen
 */
public class AppointmentDAOTest {

    final static Logger LOG = LogManager.getLogger(AppointmentDAOTest.class);

//    private AppointmentDAO appointmentDao = null;
//
//    private DAOFactoryJPAImpl daoFactory = new DAOFactoryJPAImpl();
//
//    @Before
//    public void before() {
//        appointmentDao = daoFactory.getAppointmentDAO();
//        assertNotNull(appointmentDao);
//    }
//
//    @Test
//    public void createAppointmentDAOTest() {
//        LOG.debug("start of createAppointmentDAOTest(");
//        // this test simply runs the before method
//        LOG.debug("end of createAppointmentDAOTest(");
//    }
//    
    @Test
    public void test1() {
        LOG.debug("start of test1");
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring.xml");
        LOG.debug("end of test1");
    }

}
