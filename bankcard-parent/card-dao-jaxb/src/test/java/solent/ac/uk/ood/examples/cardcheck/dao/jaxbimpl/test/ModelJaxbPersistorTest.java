/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardcheck.dao.jaxbimpl.test;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.cardcheck.dao.jaxbimpl.ModelJaxbPersistor;

/**
 *
 * @author cgallen
 */
public class ModelJaxbPersistorTest {

    private static final Logger LOG = LoggerFactory.getLogger(ModelJaxbPersistorTest.class);

    public static final String TEST_FILE_LOCATION = "target/testData.xml";

    public ModelJaxbPersistorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void test1() {
        File testFile = new File(TEST_FILE_LOCATION);
        LOG.debug("using test file:" + testFile.getAbsolutePath());
        if (testFile.exists()) {
            LOG.debug("deleting old test file");
            testFile.delete();
        }
        assertFalse(testFile.exists());
        ModelJaxbPersistor modelJaxbPersistorTest = new ModelJaxbPersistor(TEST_FILE_LOCATION);
        assertTrue(testFile.exists());
    }
}
