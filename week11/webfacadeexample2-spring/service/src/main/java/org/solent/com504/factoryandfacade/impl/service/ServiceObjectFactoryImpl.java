package org.solent.com504.factoryandfacade.impl.service;

import org.solent.com504.factoryandfacade.impl.dao.jaxb.AnimalDaoJaxbImpl;
import org.solent.com504.factoryandfacade.impl.dao.simple.AnimalTypeDaoImpl;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;
import org.solent.com504.factoryandfacade.model.service.FarmFacade;
import org.solent.com504.factoryandfacade.model.service.ServiceObjectFactory;

// This is a hard coded implementation of the factory using the jaxb DAO 
// and could be replaced by Spring
public class ServiceObjectFactoryImpl implements ServiceObjectFactory {

    // get a temporary directory to store our dao xml file
    public static final String TMP_DIR = System.getProperty("java.io.tmpdir");

    // dao xml file location
    private static final String DAFAULT_JAXB_FILE = TMP_DIR + "/animallist.xml";

    private FarmFacadeImpl farmFacade = null;

    /**
     * Initialises farmFacade objectFactory
     */
    public ServiceObjectFactoryImpl() {
        this(DAFAULT_JAXB_FILE);
    }

    /**
     * Initialises farmFacade objectFactory with a given location for jaxb file
     */
    public ServiceObjectFactoryImpl(String jaxbFile) {

        farmFacade = new FarmFacadeImpl();
        AnimalTypeDao animalTypeDao = new AnimalTypeDaoImpl();

        // NOTE THIS IS SAYING WHERE THE FILE GOES in TOMCAT
        AnimalDao animalDao = new AnimalDaoJaxbImpl(jaxbFile);

        // If you just want to use simple DAO do this
        // AnimalDao animalDao = new AnimalDaoImpl();
        farmFacade.setAnimalDao(animalDao);
        farmFacade.setAnimalTypeDao(animalTypeDao);
    }

    public FarmFacade getFarmFacade() {
        return farmFacade;
    }

    @Override
    public void shutDown() {
        // do nothing
    }
}
