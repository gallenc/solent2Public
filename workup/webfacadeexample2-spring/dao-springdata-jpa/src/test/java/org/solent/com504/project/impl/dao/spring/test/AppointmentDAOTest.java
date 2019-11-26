/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.spring.test;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.solent.com504.project.model.dao.AppointmentDAO;
import org.solent.com504.project.model.dao.PersonDAO;
import org.solent.com504.project.model.dto.Appointment;
import org.solent.com504.project.model.dto.Person;
import org.solent.com504.project.model.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
public class AppointmentDAOTest {

    final static Logger LOG = LogManager.getLogger(AppointmentDAOTest.class);

    Person testDentist = null;

    @Autowired
    private AppointmentDAO appointmentDao = null;

    @Autowired
    private PersonDAO personDao = null;

    @Before
    public void before() {
        assertNotNull(appointmentDao);
    }

    public void init() {
        LOG.debug("initialising data for test(");
        // delete all in database
        // delete appointments first
        appointmentDao.deleteAll();

        // delete persons after appointments
        personDao.deleteAll();

        // add 5 patients
        for (int i = 1; i < 6; i++) {
            Person p = new Person();
            p.setAddress("address_A_" + i);
            p.setFirstName("firstName_A_" + i);
            p.setSecondName("secondName_A_" + i);
            p.setRole(Role.PATIENT);
            personDao.save(p);
        }

        // add 5 dentists
        for (int i = 1; i < 6; i++) {
            Person p = new Person();
            p.setAddress("address_A_" + i);
            p.setFirstName("firstName_A_" + i);
            p.setSecondName("secondName_A_" + i);
            p.setRole(Role.DENTIST);
            personDao.save(p);
        }

        List<Person> patients = personDao.findByRole(Role.PATIENT);
        List<Person> dentists = personDao.findByRole(Role.DENTIST);
        testDentist = dentists.get(0);

        for (int i = 1; i < 6; i++) {
            Appointment appt = new Appointment();
            Person patient = patients.get(i - 1);
            appt.setPersonA(patient);
            Person dentist = dentists.get(i - 1);
            // 3 appointments with test dentist
            if (i < 4) {
                dentist = testDentist;
            }
            appt.setPersonB(dentist);
            appt.setDescripton("appointment no " + i);
            appt.setDurationMinutes(60);
            appt.setMth(i);
            appt.setYr(2000 + i);
            appt.setDayOfMonth(i);

            appt = appointmentDao.save(appt);
        }
        LOG.debug("initialising data for test( complete");

    }

    // see https://www.baeldung.com/spring-data-jpa-save-saveandflush 
    // https://www.marcobehler.com/2014/06/25/should-my-tests-be-transactional 
    //@Transactional
    @Test
    public void createAppointmentDAOTest() {
        LOG.debug("start of createAppointmentDAOTest(");
        init();
        List<Appointment> appointments = appointmentDao.findAll();
        LOG.debug("created appointments " + appointments.size());

        LOG.debug("end of createAppointmentDAOTest(");
    }

    //@Transactional
    @Test
    public void findAppointmentByPersonDAOTest() {
        LOG.debug("start of findAppointmentDAOTest");
        init();
        List<Appointment> appointments = appointmentDao.findByPersonB(testDentist);
        assertEquals(3,appointments.size());

        LOG.debug("end of findAppointmentDAOTest");
    }

}
