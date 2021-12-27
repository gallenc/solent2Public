/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.simplepropertiesdaowebapp.dao;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author cgallen
 */
public class WebObjectFactory {

    final static Logger LOG = LogManager.getLogger(WebObjectFactory.class);

    private static PropertiesDao propertiesDao = null;

    public static PropertiesDao getPropertiesDao() {
        if (propertiesDao == null) {
            synchronized (WebObjectFactory.class) {
                if (propertiesDao == null) {
                    // creates a single instance of the dao
                    String TEMP_DIR = System.getProperty("java.io.tmpdir");
                    File propertiesFile = new File(TEMP_DIR + "/application.properties");
                    LOG.debug("using system temp directory: " + TEMP_DIR);
                    LOG.debug("using application properties file : " + propertiesFile.getAbsolutePath());
                    propertiesDao = new PropertiesDao(propertiesFile.getAbsolutePath());
                }
            }
        }
        return propertiesDao;
    }

}
