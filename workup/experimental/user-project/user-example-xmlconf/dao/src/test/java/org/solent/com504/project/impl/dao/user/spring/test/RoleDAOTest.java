/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.user.spring.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.solent.com504.project.model.party.dao.PartyDAO;
import org.solent.com504.project.model.user.dao.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
public class RoleDAOTest {

    final static Logger LOG = LogManager.getLogger(RoleDAOTest.class);

    @Autowired
    private RoleDAO roleDao = null;

    @Before
    public void before() {
        assertNotNull(roleDao);
    }

    @Test
    public void testLoadcontext() {
        LOG.debug("start testLoadcontext");
        LOG.debug("end of testLoadcontext");
    }
    //TODO TEST
}
