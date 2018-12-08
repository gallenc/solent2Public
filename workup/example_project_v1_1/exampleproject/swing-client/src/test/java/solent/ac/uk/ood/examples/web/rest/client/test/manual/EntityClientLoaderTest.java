/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.web.rest.client.test.manual;

import java.io.File;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.dao.jaxbimpl.EntityDAOJaxbImpl;
import solent.ac.uk.ood.examples.model.Entity;
import solent.ac.uk.ood.examples.model.EntityDAO;
import solent.ac.uk.ood.examples.swingcient.EntityClientLoader;

/**
 *
 * @author cgallen
 */
public class EntityClientLoaderTest {

    private static final Logger LOG = LoggerFactory.getLogger(EntityClientLoaderTest.class);

    public final String TEST_DATA_FILE_LOCATION = "target/testDaofile.xml";

    @Test
    public void testClientLoader() {

        // delete test file at start of test
        File file = new File(TEST_DATA_FILE_LOCATION);
        file.delete();
        assertFalse(file.exists());

        // create dao
        EntityDAO entityDAO = new EntityDAOJaxbImpl(TEST_DATA_FILE_LOCATION);
        assertTrue(file.exists());
        
        List<Entity> list = entityDAO.retrieveAllEntities();
        assertTrue(list.isEmpty());
        

        String baseUrl = "http://localhost:8680/";

        EntityClientLoader entityClientLoader = new EntityClientLoader(entityDAO, baseUrl);
        
        // try to load from service
        entityClientLoader.restClientRetrieveAll();
        
        list = entityDAO.retrieveAllEntities();
        LOG.debug("retreived enties = "+ list.size());

    }
}
