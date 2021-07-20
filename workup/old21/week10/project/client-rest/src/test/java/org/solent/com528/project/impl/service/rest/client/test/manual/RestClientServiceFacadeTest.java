/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.project.impl.service.rest.client.test.manual;


import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com528.project.impl.service.rest.client.ClientObjectFactoryImpl;;
import org.solent.com528.project.model.dto.TicketMachineConfig;
import org.solent.com528.project.model.service.ServiceFacade;
import org.solent.com528.project.model.service.ServiceObjectFactory;

/**
 *
 * @author gallenc
 */
public class RestClientServiceFacadeTest {

    final static Logger LOG = LogManager.getLogger(RestClientServiceFacadeTest.class);

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
    public void testRestClient() {
        LOG.debug("start of testRestClient()");
        String ticketMachineUuid = UUID.randomUUID().toString();

        TicketMachineConfig config = serviceFacade.getTicketMachineConfig(ticketMachineUuid);

        System.out.println(config);
        assertNotNull(config);

        LOG.debug("end of testRestClient()");
    }

}
