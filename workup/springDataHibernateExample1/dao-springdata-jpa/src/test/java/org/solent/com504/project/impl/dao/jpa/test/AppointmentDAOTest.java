/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.jpa.test;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.solent.com504.project.impl.dao.jpa.DAOFactoryJPAImpl;
import org.solent.com504.project.impl.dao.springdata.AppointmentDAOSpring;
import org.solent.com504.project.model.dao.AppointmentDAO;
import org.solent.com504.project.model.dto.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * http://cleancodejava.com/simple-spring-data-jpa-example/ Simple Spring Data
 * JPA Example
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

public class AppointmentDAOTest {

    final static Logger LOG = LogManager.getLogger(AppointmentDAOTest.class);

    @Autowired
    private AppointmentDAOSpring appointmentDAO = null;

    @Autowired
    private ApplicationContext applicationContext;

    @Before
    public void before() {
        LOG.debug("before test running");
        assertNotNull(appointmentDAO);
        LOG.debug("before test complete");
    }

    @Test
    public void applicationContextTest() {
        LOG.debug("start of applicationContextTest");
        // this test simply runs the before method and prints out the contents of the context
        String[] beannames = applicationContext.getBeanDefinitionNames();

        String msg = "******* beans " + beannames.length + " names: ";
        for (String b : Arrays.asList(beannames)) {
            msg = msg + "\n " + b + ", ";
        }
        LOG.debug(msg);

        LOG.debug("end of applicationContextTest");
    }

    @Transactional
    @Test
    public void test1() {
        LOG.debug("start of test1");

        Appointment appt1 = new Appointment();
        appt1 = appointmentDAO.save(appt1);
        System.out.println("appt1=" + appt1);

        Long id = appt1.getId();
        Appointment appt2 = appointmentDAO.getOne(id);
        System.out.println("appt2=" + appt2);
        LOG.debug("end of test1");
    }

}
