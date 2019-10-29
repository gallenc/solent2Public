package org.solent.com504.factoryandfacade.impl.service;

import org.solent.com504.factoryandfacade.impl.dao.jaxb.AnimalDaoJaxbImpl;
import org.solent.com504.factoryandfacade.impl.dao.simple.AnimalDaoImpl;
import org.solent.com504.factoryandfacade.impl.dao.jpa.DAOFactoryJPAImpl;

import org.solent.com504.factoryandfacade.impl.dao.simple.AnimalTypeDaoImpl;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;
import org.solent.com504.factoryandfacade.model.dao.DAOFactory;
import org.solent.com504.factoryandfacade.model.service.FarmFacade;
import org.solent.com504.factoryandfacade.model.service.ServiceObjectFactory;

// This is a hard coded implementation of the factory using the jaxb DAO 
// and could be replaced by Spring
public class ServiceObjectFactoryJpaImpl implements ServiceObjectFactory {


    private FarmFacadeImpl farmFacade = null;
    private AnimalTypeDao animalTypeDao = null;
    private AnimalDao animalDao=null;

    /**
     * Initialises farmFacade objectFactory
     */
    public ServiceObjectFactoryJpaImpl() {
        
        DAOFactory daoFactory = new DAOFactoryJPAImpl();
        animalDao = daoFactory.getAnimalDao();
        animalTypeDao = daoFactory.getAnimalTypeDao();
                
        farmFacade = new FarmFacadeImpl();
        
        farmFacade.setAnimalDao(animalDao);
        farmFacade.setAnimalTypeDao(animalTypeDao);
    }

    public FarmFacade getFarmFacade() {
        return farmFacade;
    }
}
