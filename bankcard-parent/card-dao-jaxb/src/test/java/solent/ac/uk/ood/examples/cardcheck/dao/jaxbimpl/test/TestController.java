/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardcheck.dao.jaxbimpl.test;

import java.io.File;
import static org.junit.Assert.assertFalse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.cardcheck.dao.DaoObjectFactory;
import solent.ac.uk.ood.examples.cardcheck.dao.jaxbimpl.DaoObjectFactoryJaxbImpl;
import solent.ac.uk.ood.examples.cardcheck.dao.jaxbimpl.ModelJaxbPersistor;
import static solent.ac.uk.ood.examples.cardcheck.dao.jaxbimpl.test.ModelJaxbPersistorTest.TEST_FILE_LOCATION;

/**
 *
 * @author cgallen
 */
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    public static final String TEST_FILE_LOCATION = "target/testData3.xml";

    private static ModelJaxbPersistor modelJaxbPersistor;

    private static DaoObjectFactory daoObjectFactory;

    public static DaoObjectFactory getDaoObjectFactory() {
        if(daoObjectFactory==null) throw new RuntimeException("you must run init() method before accessing objectFactory");
        return daoObjectFactory;
    }
    
    // delete test file and initialise
    public static void init(){
        LOG.debug("initialising TestController");
        // delete any old files and reinitialise persistor
        File testFile = new File(TEST_FILE_LOCATION);
        LOG.debug("using test file:" + testFile.getAbsolutePath());
        if (testFile.exists()) {
            LOG.debug("deleting old test file");
            testFile.delete();
        }
        modelJaxbPersistor = new ModelJaxbPersistor(TEST_FILE_LOCATION);
        daoObjectFactory = new DaoObjectFactoryJaxbImpl(modelJaxbPersistor);
    }

}
