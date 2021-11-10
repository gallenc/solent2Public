/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.jpaexample1.impl.dao.jpa.test;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.jpaexample1.impl.dao.jpa.DAOFactoryJPAImpl;
import org.solent.com504.jpaexample1.model.dao.AppointmentDAO;
import org.solent.com504.jpaexample1.model.dao.PersonDAO;
import org.solent.com504.jpaexample1.model.dto.Appointment;
import org.solent.com504.jpaexample1.model.dto.Person;


/**
 *
 * @author cgallen
 */
public class AppointmentDAOTest {

    final static Logger LOG = LogManager.getLogger(AppointmentDAOTest.class);

    private AppointmentDAO appointmentDao = null;    
    private PersonDAO personDao = null;


    private DAOFactoryJPAImpl daoFactory = new DAOFactoryJPAImpl();

    private Person personA = new Person();    
    private Person personB = new Person();

    
    @Before
    public void before() {
        appointmentDao = daoFactory.getAppointmentDAO();        
        personDao = daoFactory.getPersonDAO();

        assertNotNull(appointmentDao);

        personA.setFirstName("Ryan");  
        personB.setFirstName("Bob");
        personA = personDao.save(personA);
        personB = personDao.save(personB);
        appointmentDao.deleteAll();

    }

    @Test
    public void createAppointmentDAOTest() {
        LOG.debug("start of createAppointmentDAOTest(");
        // this test simply runs the before method
        Appointment a = new Appointment();
        a.setPersonA(personA);
        a.setPersonB(personB);
        a.setDescripton("Test");
        Appointment b = appointmentDao.save(a);
        
        List<Appointment> allAppointments = appointmentDao.findAll();
        Assert.assertEquals(1, allAppointments.size());
        LOG.debug("end of createAppointmentDAOTest(");
    }
    
    
    
    
    
    
    
}
