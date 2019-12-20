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
public class ThingSingleClientLoaderTest {

    private static final Logger LOG = LoggerFactory.getLogger(ThingSingleClientLoaderTest.class);

    public static final String TEST_DATA_FILE_LOCATION = "target/testDaofile.xml";

    public static final String TEST_BASE_URL = "http://localhost:8084/";

    @Test
    public void testSingleClientLoader() {

        // delete test file at start of test
        File file = new File(TEST_DATA_FILE_LOCATION);
        file.delete();
        assertFalse(file.exists());

        // create dao
        ThingDAO thingDAO = new ThingDAOJaxbImpl(TEST_DATA_FILE_LOCATION);
        assertTrue(file.exists());

        List<Thing> list = thingDAO.retrieveAllThings();
        assertTrue(list.isEmpty());

        String baseUrl = "http://localhost:8084/";

        ThingClientLoader thingClientLoader = new ThingClientLoader(thingDAO, baseUrl);

        // try to load from service
        boolean success = thingClientLoader.restClientRetrieveAll();
        assertTrue(success);

        list = thingDAO.retrieveAllThings();
        LOG.debug("retrieved enties = " + list.size());

    }

}
