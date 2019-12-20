/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.service.test;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import solent.ac.uk.ood.examples.model.Thing;
import solent.ac.uk.ood.examples.model.ServiceFacade;
import solent.ac.uk.ood.examples.model.ServiceFactory;
import solent.ac.uk.ood.examples.service.ServiceFactoryImpl;

/**
 *
 * @author cgallen
 */
public class ServiceFacadeImplTest {

    public static final String TEST_DATA_FILE = "./target/testfile.xml";

    // Only some basic tests as most tests already done in ThingDAO tests
    @Test
    public void simpleServiceFacadeTest() {

        // use service factory to get access to service
        ServiceFactory serviceFactory = new ServiceFactoryImpl(TEST_DATA_FILE);
        assertNotNull(serviceFactory);

        ServiceFacade serviceFacade = serviceFactory.getServiceFacade();
        assertNotNull(serviceFacade);
        
        // clear file before anything else
        serviceFacade.deleteAllThings();

        Thing thing = new Thing();
        thing.setField_A("testFieldA");

        serviceFacade.createThing(thing);
        List<Thing> retrievedThings = serviceFacade.retrieveMatchingThings(thing);

        assertEquals(1, retrievedThings.size());
    }
}
