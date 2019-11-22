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
import org.solent.com504.project.impl.dao.jpa.DAOFactoryJPAImpl;
import org.solent.com504.project.impl.dao.springdata.AppointmentDAOSpring;
import org.solent.com504.project.model.dao.AppointmentDAO;
import org.solent.com504.project.model.dto.Appointment;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author cgallen
 */
public class AppointmentDAOTest {

    final static Logger LOG = LogManager.getLogger(AppointmentDAOTest.class);

    private AppointmentDAOSpring appointmentDAO = null;

//    private DAOFactoryJPAImpl daoFactory = new DAOFactoryJPAImpl();
    @Before
    public void before() {
        try {
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring.xml");
            String[] beannames = ctx.getBeanDefinitionNames();

            String msg = "beans " + beannames.length
                    + " names: ";
            for (String b : Arrays.asList(beannames)) {
                msg = msg + b + ", ";
            }
            LOG.debug(msg);
            // appointmentDAO = (AppointmentDAOSpring) ctx.getBean("appointmentDAOSpring");
            // assertNotNull(appointmentDAO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
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

        Appointment appt1 = new Appointment();
        appt1 = appointmentDAO.save(appt1);
        System.out.println("appt1=" + appt1);

        Long id = appt1.getId();
        Appointment appt2 = appointmentDAO.getOne(id);
        System.out.println("appt2=" + appt2);

        LOG.debug("end of test1");
    }

}
