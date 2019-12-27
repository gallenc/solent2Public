/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.dao.springdata.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.solent.com504.project.model.dto.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.solent.com504.project.model.dao.springdata.ActorDAOSpringData;

/**
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
public class ActorDAOSpringDataTest {

    final static Logger LOG = LogManager.getLogger(ActorDAOSpringDataTest.class);

    @Autowired
    private ActorDAOSpringData actorDAOspring = null;

    @Before
    public void before() {
        LOG.debug("before test running");
        assertNotNull(actorDAOspring);
        LOG.debug("before test complete");
    }

    
    @Transactional
    @Test
    public void test1() {
        LOG.debug("start of test1");

        Actor actor1 = new Actor();
        actor1 = actorDAOspring.save(actor1);
        System.out.println("actor1=" + actor1);

        Long id = actor1.getId();
        Actor actor2 = actorDAOspring.getOne(id);
        System.out.println("actor2=" + actor2);
        LOG.debug("end of test1");
    }
}
