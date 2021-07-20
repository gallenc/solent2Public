/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.project.impl.service.rest.client.test.manual;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com528.project.impl.service.rest.client.ClientObjectFactoryImpl;
import org.solent.com528.project.impl.service.rest.client.ConfigurationPoller;
import org.solent.com528.project.model.service.ServiceFacade;
import org.solent.com528.project.model.service.ServiceObjectFactory;

/**
 *
 * @author gallenc
 */
public class ConfigurationPollerTest {

    final static Logger LOG = LogManager.getLogger(ConfigurationPollerTest.class);

    ServiceObjectFactory serviceObjectFactory = null;
    ServiceFacade serviceFacade = null;

    @Before
    public void loadFactory() {
        serviceObjectFactory = new ClientObjectFactoryImpl();
        assertNotNull(serviceObjectFactory);
        serviceFacade = serviceObjectFactory.getServiceFacade();
        assertNotNull(serviceFacade);
    }

    @Test
    public void testConfigurationPollerTest() {
        LOG.debug("start of ConfigurationPollerTest()");

        String uuid = UUID.randomUUID().toString();
        ConfigurationPoller configurationPoller = new ConfigurationPoller(serviceFacade);
        configurationPoller.setTicketMachineUuid(uuid);
        
        long initialDelay=0;
        long delay=5; // every 5 seconds
        configurationPoller.init( initialDelay, delay);

        LOG.debug("ConfigurationPollerTest() sleeping ");
        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            configurationPoller.shutdown();
        }
        LOG.debug("ConfigurationPollerTest() sleep interrupted ");

        LOG.debug("end of ConfigurationPollerTest()");
    }

}
