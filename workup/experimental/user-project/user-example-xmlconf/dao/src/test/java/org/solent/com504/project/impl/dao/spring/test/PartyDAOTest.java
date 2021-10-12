/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.spring.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.party.dto.PartyRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.solent.com504.project.model.party.dao.PartyDAO;
import org.solent.com504.project.model.party.dto.Address;
import org.solent.com504.project.model.user.dao.RoleDAO;
import org.solent.com504.project.model.user.dao.UserDAO;
import org.solent.com504.project.model.user.dto.User;

/**
 * NOTE tests cannot be @transactional if you are using the id of an entity which has not been saveAndFlush'ed this could be an eclipselink problem or a
 * database problem. In practice make tests non transactional and make sure the database is clean at start of tests.
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
@Transactional
public class PartyDAOTest {

    final static Logger LOG = LogManager.getLogger(PartyDAOTest.class);

    @Autowired
    private PartyDAO partyDao = null;

    @Autowired
    private UserDAO userDao = null;

    @Autowired
    private RoleDAO roleDao = null;

    @Before
    public void before() {
        assertNotNull(partyDao);
        assertNotNull(userDao);
    }

    // initialises database for each test
    private void init() {
        // delete all in database

        partyDao.deleteAll();
        userDao.deleteAll();

        for (int i = 1; i < 6; i++) {
            User user = new User();
            Address a = new Address();
            a.setAddressLine1("address_A_" + i);
            user.setAddress(a);
            user.setUsername("username_A_" + i);
            user.setFirstName("userfirstName_A_" + i);
            user.setSecondName("usersecondName_A_" + i);
            user = userDao.save(user);
            LOG.debug("created test user:"+user);
        }

        List<User> users = userDao.findAll();
        assertEquals(5, users.size());

        // add 5 buyer
        for (int i = 1; i < 6; i++) {
            Party party = new Party();
            Address a = new Address();
            a.setAddressLine1("address_A_" + i);
            party.setAddress(a);
            party.setFirstName("partyfirstName_A_" + i);
            party.setSecondName("partysecondName_A_" + i);
            party.setPartyRole(PartyRole.BUYER);

            Set<User> userset = new HashSet();
            userset.add(users.get(i-1));
            party.setUsers(userset);

            party = partyDao.save(party);
            LOG.debug("created test party:"+party);
        }
        // add 5 undefined
        for (int i = 1; i < 6; i++) {
            Party party = new Party();
            Address a = new Address();
            a.setAddressLine1("address_B_" + i);
            party.setAddress(a);
            party.setFirstName("partyfirstName_B_" + i);
            party.setSecondName("partysecondName_B_" + i);
            party.setPartyRole(PartyRole.UNDEFINED);

            Set<User> userset = new HashSet();
            userset.add(users.get(i-1));
            party.setUsers(userset);

            party = partyDao.save(party);
            LOG.debug("created test party:"+party);
        }
    }

    // see https://www.baeldung.com/spring-data-jpa-save-saveandflush 
    // https://www.marcobehler.com/2014/06/25/should-my-tests-be-transactional 
    //@Transactional
    @Test
    public void createPartyDAOTest() {
        LOG.debug("start of createPartyDAOTest");
        // this test simply runs the before method
        LOG.debug("end of createPartyDAOTest");
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
        List<Party> partyList = partyDao.findAll();
        assertNotNull(partyList);

        // init should insert 10 people
        assertEquals(10, partyList.size());

        // print out result
        String msg = "";
        for (Party party : partyList) {
            msg = msg + "\n   " + party.toString();
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
    public void findByPartyRoleTest() {
        LOG.debug("start of findByIdTest()");

        init();

        List<Party> partyList = null;
        partyList = partyDao.findAll();
        assertFalse(partyList.isEmpty());

        partyList = partyDao.findByPartyRole(PartyRole.BUYER);
        assertNotNull(partyList);
        assertEquals(5, partyList.size());

        partyList = partyDao.findByPartyRole(PartyRole.UNDEFINED);
        assertNotNull(partyList);
        assertEquals(5, partyList.size());

        LOG.debug("end of findByIdTest()");
    }

    // @Transactional
    @Test
    public void findByNameTest() {
        LOG.debug("start of findByNameTest()");

        init();
        List<Party> partyList = null;
        partyList = partyDao.findAll();
        assertFalse(partyList.isEmpty());

        // choose from partyList
        int i = partyList.size() / 2;
        Party party1 = partyList.get(i);
        LOG.debug("Selecting Party " + i + " party=" + party1);
        String firstName = party1.getFirstName();
        String secondName = party1.getSecondName();

        partyList = partyDao.findByName(firstName, secondName);
        assertFalse(partyList.isEmpty());

        Party party2 = partyList.get(0);
        LOG.debug("Finding party by name Party " + party2);

        assertTrue(party1.toString().equals(party2.toString()));

        LOG.debug("end of findByNameTest())");

    }
}
