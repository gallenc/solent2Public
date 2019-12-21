/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.service.test;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.project.impl.service.ServiceObjectFactoryJpaImpl;
import org.solent.com504.project.model.dto.Person;
import org.solent.com504.project.model.dto.Role;
import org.solent.com504.project.model.service.ServiceFacade;
import org.solent.com504.project.model.service.ServiceObjectFactory;

/**
 *
 * @author gallenc
 */
public class ServiceFacadeJpaTest {

    ServiceObjectFactory serviceObjectFactory = null;
    ServiceFacade serviceFacade = null;

    @Before
    public void loadFactory() {

        serviceObjectFactory = new ServiceObjectFactoryJpaImpl();

        serviceFacade = serviceObjectFactory.getServiceFacade();

    }

    @Test
    public void testFactory() {
        System.out.println("start ServiceFacadeTest testFactory");
        assertNotNull(serviceFacade);

        System.out.println("end ServiceFacadeTest testFactory");
    }

    @Test
    public void testGetHeartbeat() {
        System.out.println("start ServiceFacadeTest testGetHeartbeat()");
        assertNotNull(serviceFacade);

        String heartbeat = serviceFacade.getHeartbeat();
        System.out.println("recieved heartbeat: " + heartbeat);
        assertNotNull(heartbeat);

        System.out.println("end FarmFacadeTest testGetHeartbeat()");
    }

    @Test
    public void testGetAllByRole() {

        // you may want to create people first but you need to adc this to the facade :)
        List<Person> personList = serviceFacade.findByRole(null);
        assertNotNull(personList);

        personList = serviceFacade.findByRole(Role.PATIENT);
        assertNotNull(personList);

    }

    // WHAT OTHER TESTS DO YOU NEED FOR THE SERVICE?
}
