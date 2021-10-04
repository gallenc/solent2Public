/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.party.springdata.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.solent.com504.project.model.party.dto.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.solent.com504.project.impl.dao.party.springdata.PartyRepository;
import org.solent.com504.project.impl.dao.spring.test.DAOTestConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the DAOTestConfiguration class
@ContextConfiguration(classes = DAOTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class PartyRepositoryTest {

    final static Logger LOG = LogManager.getLogger(PartyRepositoryTest.class);

    @Autowired
    private PartyRepository partyRepository = null;

    @Before
    public void before() {
        LOG.debug("before test running");
        assertNotNull(partyRepository);
        LOG.debug("before test complete");
    }

    
    @Transactional
    @Test
    public void test1() {
        LOG.debug("start of test1");

        Party party1 = new Party();
        party1 = partyRepository.save(party1);
        System.out.println("party1=" + party1);

        Long id = party1.getId();
        Party party2 = partyRepository.getOne(id);
        System.out.println("party2=" + party2);
        LOG.debug("end of test1");
    }
}
