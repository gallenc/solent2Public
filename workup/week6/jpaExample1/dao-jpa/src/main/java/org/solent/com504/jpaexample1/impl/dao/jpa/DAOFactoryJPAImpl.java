package org.solent.com504.jpaexample1.impl.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.solent.com504.jpaexample1.model.dao.AnimalDao;
import org.solent.com504.jpaexample1.model.dao.AnimalTypeDao;
import org.solent.com504.jpaexample1.model.dao.DAOFactory;


public class DAOFactoryJPAImpl implements DAOFactory {
    
    // TODO FACTORY MAY WANT TO CLOSE ENTITY MANAGER AT END OF SESSION

    private static final String PERSISTENCE_UNIT_NAME = "farmPersistence";
    private static EntityManagerFactory factory;
    private static EntityManager em;
    private static AnimalDao animalDao;
    private static AnimalTypeDao animalTypeDao;
    
    static {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
        animalDao = new AnimalDaoJpaImpl(em);
        animalTypeDao = new AnimalTypeDaoImpl();
    }

    @Override
    public AnimalDao getAnimalDao() {
        return animalDao;
    }
    
    @Override
    public AnimalTypeDao getAnimalTypeDao() {
        return animalTypeDao;
    }



}
