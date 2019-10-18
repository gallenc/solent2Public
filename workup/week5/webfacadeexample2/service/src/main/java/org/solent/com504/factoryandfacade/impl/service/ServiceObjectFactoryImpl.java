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
    
    FarmFacadeImpl farmFacade = null;
    
    public ServiceObjectFactoryImpl(){
        farmFacade = new  FarmFacadeImpl();
        AnimalTypeDao animalTypeDao = new AnimalTypeDaoImpl();
        
        AnimalDao animalDao = new AnimalDaoJaxbImpl("./animallist.xml");
        
        farmFacade.setAnimalDao(animalDao);
        farmFacade.setAnimalTypeDao(animalTypeDao);
    }

    public FarmFacade getFarmFacade() {
         return farmFacade;
    }
}
