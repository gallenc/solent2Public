/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.simplepropertiesdaowebapp.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author cgallen
 */
public class PropertiesDao {

    final static Logger LOG = LogManager.getLogger(PropertiesDao.class);

    private File propertiesFile;

    private Properties properties = new Properties();

    public PropertiesDao(String propertiesFileLocation) {
        try {
            propertiesFile = new File(propertiesFileLocation);
            if (!propertiesFile.exists()) {
                LOG.info("properties file does not exist: creating new file: " + propertiesFile.getAbsolutePath());
                propertiesFile.getParentFile().mkdirs();
                propertiesFile.createNewFile();
                saveProperties();
            }
            loadProperties();
        } catch (IOException ex) {
            LOG.error("cannot load properties", ex);
        }
    }

    // synchronized ensures changes are not made by another thread while reading
    public synchronized String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    public synchronized void setProperty(String propertyKey, String propertyValue) {
        properties.setProperty(propertyKey, propertyValue);
        saveProperties();
    }

    private void saveProperties() {
        OutputStream output = null;
        try {
            LOG.debug("saving properties to: " + propertiesFile.getAbsolutePath());

            output = new FileOutputStream(propertiesFile);
            String comments = "# properties example file";
            properties.store(output, comments);

        } catch (IOException ex) {
            LOG.error("cannot save properties", ex);
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException ex) {
            }
        }
    }

    private void loadProperties() {
        InputStream input = null;
        try {
            LOG.debug("loading properties from: " + propertiesFile.getAbsolutePath());
            input = new FileInputStream(propertiesFile);
            properties.load(input);
        } catch (IOException ex) {
            LOG.error("cannot load properties", ex);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
            }
        }
    }

}
