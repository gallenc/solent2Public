/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.springdata.test;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.solent.com504.factoryandfacade.impl.dao.springdata.AnimalDAOSpringData;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * http://cleancodejava.com/simple-spring-data-jpa-example/ Simple Spring Data
 * JPA Example
 *
 * @author cgallen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
public class AnimalDAOSpringDataTest {

    final static Logger LOG = LogManager.getLogger(AnimalDAOSpringDataTest.class);

    @Autowired
    private AnimalDAOSpringData animalDAOspringData = null;

    @Autowired
    private ApplicationContext applicationContext;

    @Before
    public void before() {
        LOG.debug("before test running");
        assertNotNull(animalDAOspringData);
        LOG.debug("before test complete");
    }

    @Test
    public void applicationContextTest() {
        LOG.debug("start of applicationContextTest");
        // this test simply runs the before method and prints out the contents of the context
        String[] beannames = applicationContext.getBeanDefinitionNames();

        String msg = "******* beans " + beannames.length + " names: ";
        for (String b : Arrays.asList(beannames)) {
            msg = msg + "\n " + b + ", ";
        }
        LOG.debug(msg);

        LOG.debug("end of applicationContextTest");
    }

    // see https://www.baeldung.com/spring-data-jpa-save-saveandflush 
    // https://www.marcobehler.com/2014/06/25/should-my-tests-be-transactional 
    //@Transactional
    @Test
    public void test1() {
        LOG.debug("start of test1");

        Animal appt1 = new Animal();

        appt1 = animalDAOspringData.save(appt1);
        System.out.println("appt1=" + appt1);

        Long id = appt1.getId();
        Animal appt2 = animalDAOspringData.getOne(id);
        System.out.println("appt2=" + appt2);
        LOG.debug("end of test1");
    }

}
