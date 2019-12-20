/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.swingcient.test.manual;

import java.io.File;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.dao.jaxbimpl.ThingDAOJaxbImpl;
import solent.ac.uk.ood.examples.model.Thing;
import solent.ac.uk.ood.examples.model.ThingDAO;
import solent.ac.uk.ood.examples.swingcient.ThingClientLoader;

/**
 *
 * @author cgallen
 */
public class ScheduledThingClientLoaderTest {

    private static final Logger LOG = LoggerFactory.getLogger(ThingSingleClientLoaderTest.class);

    public static final String TEST_DATA_FILE_LOCATION = "target/testDaofile.xml";

    public static final String TEST_BASE_URL = "http://localhost:8084/";

    @Test
    public void testScheduledClientLoader() {

        // delete test file at start of test
        File file = new File(TEST_DATA_FILE_LOCATION);
        file.delete();
        assertFalse(file.exists());

        // create dao
        ThingDAO thingDAO = new ThingDAOJaxbImpl(TEST_DATA_FILE_LOCATION);
        assertTrue(file.exists());

        ThingClientLoader thingClientLoader = new ThingClientLoader(thingDAO, TEST_BASE_URL);

        // schedule loading every minute (2 retrys interval 10 seconds every minute)
        int scheduleIntervalSeconds = 1 * 60; // 1 minutes
        int retryAttempts = 2;
        int retryIntervalSeconds = 10;
        thingClientLoader.scheduleLoadData(scheduleIntervalSeconds, retryAttempts, retryIntervalSeconds);

        
        try {
            // wait for 5 minutes before ending test
            Thread.sleep(1000 * scheduleIntervalSeconds * 5);
            
            //wait for 30 seconds before ending test
            //Thread.sleep(1000 * 30);
        } catch (InterruptedException ex) {
            LOG.debug("retrieving data interrupted");
        }
        
        thingClientLoader.cancelLoadDataSchedule();

        LOG.debug("Total Client Retrieve Tries = " + thingClientLoader.getTotalClientRetrieveTries());
        LOG.debug("Total Client Retrieve Successful = " + thingClientLoader.getTotalClientRetrieveSuccessful());

        List<Thing> list = thingDAO.retrieveAllThings();
        LOG.debug("Schedule retrieved entites = " + list.size());

    }
}
