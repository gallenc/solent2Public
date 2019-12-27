/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.service.rest.client.test.manual;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.solent.com504.project.model.service.ServiceFacade;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 *
 * @author gallenc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
public class RestClientServiceFacadeTest {

    final static Logger LOG = LogManager.getLogger(RestClientServiceFacadeTest.class);

    ServiceFacade serviceFacade = null;

    List<String> supportedAnimalTypes = null;

    @Before
    public void loadFactory() {
        assertNotNull(serviceFacade);
    }

    @Test
    public void testGetHeartbeat() {
        LOG.debug("start of testGetHeartbeat()");

        String heartbeat = serviceFacade.getHeartbeat();
        assertNotNull(heartbeat);
        LOG.debug("heartbeat received :" + heartbeat);

        LOG.debug("end of testGetHeartbeat()");
    }

}
