/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.simplepropertiesdaowebapp.dao.test;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.ood.simplepropertiesdaowebapp.dao.PropertiesDao;

/**
 *
 * @author cgallen
 */
public class PropertiesDaoTest {

    public final String TEST_PROPERTIES_FILE = "./target/test/propertiesDaoTest.properties";

    @Before
    public void before() {
        // make sue file doesnt exist
        File propertiesFile = new File(TEST_PROPERTIES_FILE);
        if (propertiesFile.exists()) {
            propertiesFile.delete();
        }
    }

    @Test
    public void testPropertiesDao() {
        PropertiesDao propertiesDao = new PropertiesDao(TEST_PROPERTIES_FILE);

        propertiesDao.setProperty("org.solent.ood.simplepropertiesdaowebapp.testPropertiesFile", TEST_PROPERTIES_FILE);
        propertiesDao.setProperty("org.solent.ood.simplepropertiesdaowebapp.username", "testUserName");
        propertiesDao.setProperty("org.solent.ood.simplepropertiesdaowebapp.password", "testPassword");
        propertiesDao.setProperty("org.solent.ood.simplepropertiesdaowebapp.url", "http://google.com");

        String url = propertiesDao.getProperty("org.solent.ood.simplepropertiesdaowebapp.url");
        assertEquals("http://google.com", url);

        // test with different dao (normally there wiill only be one dao for the file)
        PropertiesDao propertiesDao2 = new PropertiesDao(TEST_PROPERTIES_FILE);
        String url2 = propertiesDao2.getProperty("org.solent.ood.simplepropertiesdaowebapp.url");
        assertEquals("http://google.com", url2);
        
        // note that the file will contain org.solent.ood.simplepropertiesdaowebapp.url=http\://google.com
        // with an extra | escape characters. see discussion here
        // https://stackoverflow.com/questions/21711562/java-properties-class-adding-characters-when-url-is-entered
    }
}
