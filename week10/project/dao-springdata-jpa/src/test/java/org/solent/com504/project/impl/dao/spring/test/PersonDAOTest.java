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
import org.solent.com504.project.model.dto.Person;
import org.solent.com504.project.model.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * NOTE tests cannot be @transactional if you are  using the id of an entity which has not been saveAndFlush'ed
 * this could be an eclipselink problem or a database problem.
 * In practice make tests non transactional and make sure the database is clean at start of tests.
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
public class PersonDAOTest {

    final static Logger LOG = LogManager.getLogger(PersonDAOTest.class);

    @Autowired
    private PersonDAO personDao = null;

    @Autowired
    private AppointmentDAO appointmentDao = null;

    @Before
    public void before() {
        assertNotNull(personDao);
    }

    // initialises database for each test
    private void init() {
        // delete all in database
        // delete appointments first
        appointmentDao.deleteAll();

        // delete persons after appointments
        personDao.deleteAll();

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
        for (int i = 1; i < 7; i++) {
            Person p = new Person();
            p.setAddress("address_B_" + i);
            p.setFirstName("firstName_B_" + i);
            p.setSecondName("secondName_B_" + i);
            p.setRole(Role.DENTIST);
            personDao.save(p);
        }
    }

    // see https://www.baeldung.com/spring-data-jpa-save-saveandflush 
    // https://www.marcobehler.com/2014/06/25/should-my-tests-be-transactional 
    //@Transactional
    @Test
    public void createPersonDAOTest() {
        LOG.debug("start of createPersonDAOTest");
        // this test simply runs the before method
        LOG.debug("end of createPersonDAOTest");
    }

    //@Transactional
    @Test
    public void findByIdTest() {
        LOG.debug("start of findByIdTest()");
        //TODO implement test
        LOG.debug("NOT IMPLEMENTED");
        LOG.debug("end of findByIdTest()");
    }

    //@Transactional
    @Test
    public void saveTest() {
        LOG.debug("start of saveTest()");
        //TODO implement test
        LOG.debug("NOT IMPLEMENTED");
        LOG.debug("end of saveTest()");
    }

    //@Transactional
    @Test
    public void findAllTest() {
        LOG.debug("start of findAllTest()");

        init();
        List<Person> personList = personDao.findAll();
        assertNotNull(personList);

        // init should insert 11 people
        assertEquals(11, personList.size());

        // print out result
        String msg = "";
        for (Person person : personList) {
            msg = msg + "\n   " + person.toString();
        }
        LOG.debug("findAllTest() retrieved:" + msg);

        LOG.debug("end of findAllTest()");
    }

    //@Transactional
    @Test
    public void deleteByIdTest() {
        LOG.debug("start of deleteByIdTest()");
        //TODO implement test
        LOG.debug("NOT IMPLEMENTED");
        LOG.debug("end of deleteByIdTest()");
    }

    //@Transactional
    @Test
    public void deleteTest() {
        LOG.debug("start of deleteTest()");
        //TODO implement test
        LOG.debug("NOT IMPLEMENTED");
        LOG.debug("end ofdeleteTest()");
    }

    //@Transactional
    @Test
    public void deleteAllTest() {
        LOG.debug("start of deleteAllTest())");
        //TODO implement test
        LOG.debug("NOT IMPLEMENTED");
        LOG.debug("end of deleteAllTest()");
    }

    //@Transactional
    @Test
    public void findByRoleTest() {
        LOG.debug("start of findByIdTest()");

        init();

        List<Person> personList = null;
        personList = personDao.findAll();
        assertFalse(personList.isEmpty());

        personList = personDao.findByRole(Role.PATIENT);
        assertNotNull(personList);
        assertEquals(5, personList.size());

        personList = personDao.findByRole(Role.DENTIST);
        assertNotNull(personList);
        assertEquals(6, personList.size());

        LOG.debug("end of findByIdTest()");
    }

    // @Transactional
    @Test
    public void findByNameTest() {
        LOG.debug("start of findByNameTest()");

        init();
        List<Person> personList = null;
        personList = personDao.findAll();
        assertFalse(personList.isEmpty());

        // choose from personList
        int i = personList.size() / 2;
        Person person1 = personList.get(i);
        LOG.debug("Selecting Person " + i + " person=" + person1);
        String firstName = person1.getFirstName();
        String secondName = person1.getSecondName();

        personList = personDao.findByName(firstName, secondName);
        assertFalse(personList.isEmpty());

        Person person2 = personList.get(0);
        LOG.debug("Finding person by name Person " + person2);

        assertTrue(person1.toString().equals(person2.toString()));

        LOG.debug("end of findByNameTest())");

    }
}
