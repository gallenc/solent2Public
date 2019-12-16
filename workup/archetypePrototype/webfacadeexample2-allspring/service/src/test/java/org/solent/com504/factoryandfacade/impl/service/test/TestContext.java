/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.service.test;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.solent.com504.factoryandfacade.impl.dao.springdata.AnimalDAOSpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author gallenc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class TestContext {

    final static Logger LOG = LogManager.getLogger(TestContext.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void applicationContextTest() {
        LOG.debug("start of applicationContextTest");
        // this test simply prints out the contents of the applicationContext
        String[] beannames = applicationContext.getBeanDefinitionNames();

        String msg = "******* beans " + beannames.length + " names: ";
        for (String b : Arrays.asList(beannames)) {
            msg = msg + "\n " + b + ", ";
        }
        LOG.debug(msg);

        LOG.debug("end of applicationContextTest");
    }

}
