/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.jaxb;

import org.solent.com504.factoryandfacade.impl.dao.simple.AnimalTypeDaoImpl;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;

/**
 *
 * @author cgallen
 */
public class DAOFactoryJaxbImpl {

    // get a temporary directory to store our dao xml file
    public static final String TMP_DIR = System.getProperty("java.io.tmpdir");

    // dao xml file location
    private static final String DAFAULT_JAXB_FILE = TMP_DIR + "/animallist.xml";

    private AnimalDao animalDao = null;

    private AnimalTypeDao animalTypeDao = null;

    /**
     * Initialises farmFacade objectFactory
     */
    public DAOFactoryJaxbImpl() {
        this(DAFAULT_JAXB_FILE);
    }

    /**
     * Initialises objectFactory with a given location for jaxb file
     */
    public DAOFactoryJaxbImpl(String jaxbFile) {

        AnimalTypeDao animalTypeDao = new AnimalTypeDaoImpl();

        // NOTE THIS IS SAYING WHERE THE FILE GOES in TOMCAT
        AnimalDao animalDao = new AnimalDaoJaxbImpl(jaxbFile);

    }

    public AnimalTypeDao getAnimalTypeDao() {
        return animalTypeDao;
    }

    public AnimalDao getAnimalDao() {
        return animalDao;
    }

}
