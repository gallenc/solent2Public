package org.solent.com504.factoryandfacade.impl.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.factoryandfacade.impl.dao.spring.DAOFactorySpringImpl;

import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;
import org.solent.com504.factoryandfacade.model.dao.DAOFactory;
import org.solent.com504.factoryandfacade.model.service.FarmFacade;
import org.solent.com504.factoryandfacade.model.service.ServiceObjectFactory;

// This is a hard coded implementation of the factory using the jaxb DAO 
// and could be replaced by Spring
public class ServiceObjectFactorySpringImpl implements ServiceObjectFactory {
    final static Logger LOG = LogManager.getLogger(ServiceObjectFactorySpringImpl.class);

    private FarmFacadeImpl farmFacade = null;
    private AnimalTypeDao animalTypeDao = null;
    private AnimalDao animalDao = null;
    private DAOFactory daoFactory = null;

    /**
     * Initialises farmFacade objectFactory
     */
    public ServiceObjectFactorySpringImpl() {
            

        daoFactory = new DAOFactorySpringImpl();
        animalDao = daoFactory.getAnimalDao();
        animalTypeDao = daoFactory.getAnimalTypeDao();

        farmFacade = new FarmFacadeImpl();

        farmFacade.setAnimalDao(animalDao);
        farmFacade.setAnimalTypeDao(animalTypeDao);
    }

    public FarmFacade getFarmFacade() {
        return farmFacade;
    }

    @Override
    public void shutDown() {
        LOG.debug("SERVICE OBJECT FACTORY SHUTTING DOWN ");
        if (daoFactory != null) {
            daoFactory.shutDown();
        }
    }

}
