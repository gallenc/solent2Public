package org.solent.com504.factoryandfacade.impl.service;

import org.solent.com504.factoryandfacade.impl.dao.simple.AnimalDaoImpl;
import org.solent.com504.factoryandfacade.impl.dao.simple.AnimalTypeDaoImpl;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;
import org.solent.com504.factoryandfacade.model.service.FarmFacade;
import org.solent.com504.factoryandfacade.model.service.ServiceObjectFactory;

// This is a har coded implementation of the factory using th simple DAO 
// and could be replaced by Spring
public class ServiceObjectFactoryImpl implements ServiceObjectFactory {
    
    FarmFacadeImpl farmFacade = null;
    
    public ServiceObjectFactoryImpl(){
        farmFacade = new  FarmFacadeImpl();
        AnimalTypeDao animalTypeDao = new AnimalTypeDaoImpl();
        AnimalDao animalDao = new AnimalDaoImpl();
        
        farmFacade.setAnimalDao(animalDao);
        farmFacade.setAnimalTypeDao(animalTypeDao);
    }

    public FarmFacade getFarmFacade() {
         return farmFacade;
    }
}
