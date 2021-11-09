/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.json;

import org.solent.com504.factoryandfacade.impl.dao.simple.AnimalTypeDaoImpl;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;

/**
 *
 * @author cgallen
 */
public class DAOFactoryJsonImpl {

    // get a temporary directory to store our dao xml file
    public static final String TMP_DIR = System.getProperty("java.io.tmpdir");

    // dao xml file location
    private static final String DEFAULT_JSON_FILE = TMP_DIR + "/animallist.json";

    private AnimalDao animalDao = null;

    private AnimalTypeDao animalTypeDao = null;

    /**
     * Initialises farmFacade objectFactory
     */
    public DAOFactoryJsonImpl() {
        this(DEFAULT_JSON_FILE);
    }

    /**
     * Initialises objectFactory with a given location for jaxb file
     */
    public DAOFactoryJsonImpl(String jsonFile) {

        AnimalTypeDao animalTypeDao = new AnimalTypeDaoImpl();

        // NOTE THIS IS SAYING WHERE THE FILE GOES in TOMCAT
        AnimalDao animalDao = new AnimalDaoJsonImpl(jsonFile);

    }

    public AnimalTypeDao getAnimalTypeDao() {
        return animalTypeDao;
    }

    public AnimalDao getAnimalDao() {
        return animalDao;
    }

}
