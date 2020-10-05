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
import org.solent.com504.project.model.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.solent.com504.project.model.dao.springdata.PersonDAOSpringData;

/**
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
public class PersonDAOSpringDataTest {

    final static Logger LOG = LogManager.getLogger(PersonDAOSpringDataTest.class);

    @Autowired
    private PersonDAOSpringData personDAOspring = null;

    @Before
    public void before() {
        LOG.debug("before test running");
        assertNotNull(personDAOspring);
        LOG.debug("before test complete");
    }

    
    // see https://www.baeldung.com/spring-data-jpa-save-saveandflush 
    // https://www.marcobehler.com/2014/06/25/should-my-tests-be-transactional 
    //@Transactional
    @Test
    public void test1() {
        LOG.debug("start of test1");

        Person person1 = new Person();
        person1 = personDAOspring.save(person1);
        System.out.println("person1=" + person1);

        Long id = person1.getId();
        Person person2 = personDAOspring.getOne(id);
        System.out.println("person2=" + person2);
        LOG.debug("end of test1");
    }
}
