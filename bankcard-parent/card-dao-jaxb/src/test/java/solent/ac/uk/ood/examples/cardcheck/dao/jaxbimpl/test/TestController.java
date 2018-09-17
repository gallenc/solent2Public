/* ***************************************************************************
 * Copyright 2018 Craig Gallen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ****************************************************************************/

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
