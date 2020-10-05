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
import org.solent.com504.project.model.dto.Actor;
import org.solent.com504.project.model.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.solent.com504.project.model.dao.ActorDAO;
import org.solent.com504.project.model.dto.Address;

/**
 * NOTE tests cannot be @transactional if you are using the id of an entity which has not been saveAndFlush'ed this could be an eclipselink problem or a
 * database problem. In practice make tests non transactional and make sure the database is clean at start of tests.
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
public class ActorDAOTest {

    final static Logger LOG = LogManager.getLogger(ActorDAOTest.class);

    @Autowired
    private ActorDAO actorDao = null;

    @Before
    public void before() {
        assertNotNull(actorDao);
    }

    // initialises database for each test
    private void init() {
        // delete all in database

        actorDao.deleteAll();

        // add 5 admin
        for (int i = 1; i < 6; i++) {
            Actor p = new Actor();
            Address a = new Address();
            a.setAddressLine1("address_A_" + i);
            p.setAddress(a);
            p.setFirstName("firstName_A_" + i);
            p.setSecondName("secondName_A_" + i);
            p.setRole(Role.GLOBALADMIN);
            actorDao.save(p);
        }
        // add 5 anonymous
        for (int i = 1; i < 7; i++) {
            Actor p = new Actor();
            Address a = new Address();
            a.setAddressLine1("address_B_" + i);
            p.setAddress(a);
            p.setFirstName("firstName_B_" + i);
            p.setSecondName("secondName_B_" + i);
            p.setRole(Role.ANONYMOUS);
            actorDao.save(p);
        }
    }

    // see https://www.baeldung.com/spring-data-jpa-save-saveandflush 
    // https://www.marcobehler.com/2014/06/25/should-my-tests-be-transactional 
    //@Transactional
    @Test
    public void createActorDAOTest() {
        LOG.debug("start of createActorDAOTest");
        // this test simply runs the before method
        LOG.debug("end of createActorDAOTest");
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
        List<Actor> actorList = actorDao.findAll();
        assertNotNull(actorList);

        // init should insert 11 people
        assertEquals(11, actorList.size());

        // print out result
        String msg = "";
        for (Actor actor : actorList) {
            msg = msg + "\n   " + actor.toString();
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

        List<Actor> actorList = null;
        actorList = actorDao.findAll();
        assertFalse(actorList.isEmpty());

        actorList = actorDao.findByRole(Role.GLOBALADMIN);
        assertNotNull(actorList);
        assertEquals(5, actorList.size());

        actorList = actorDao.findByRole(Role.ANONYMOUS);
        assertNotNull(actorList);
        assertEquals(6, actorList.size());

        LOG.debug("end of findByIdTest()");
    }

    // @Transactional
    @Test
    public void findByNameTest() {
        LOG.debug("start of findByNameTest()");

        init();
        List<Actor> actorList = null;
        actorList = actorDao.findAll();
        assertFalse(actorList.isEmpty());

        // choose from actorList
        int i = actorList.size() / 2;
        Actor actor1 = actorList.get(i);
        LOG.debug("Selecting Actor " + i + " actor=" + actor1);
        String firstName = actor1.getFirstName();
        String secondName = actor1.getSecondName();

        actorList = actorDao.findByName(firstName, secondName);
        assertFalse(actorList.isEmpty());

        Actor actor2 = actorList.get(0);
        LOG.debug("Finding actor by name Actor " + actor2);

        assertTrue(actor1.toString().equals(actor2.toString()));

        LOG.debug("end of findByNameTest())");

    }
}
