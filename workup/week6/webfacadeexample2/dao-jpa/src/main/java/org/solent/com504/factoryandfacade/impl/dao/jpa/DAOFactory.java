package org.solent.com504.factoryandfacade.impl.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;


public class DAOFactory {
    
    // TODO FACTORY MAY WANT TO CLOSE ENTITY MANAGER AT END OF SESSION

    private static final String PERSISTENCE_UNIT_NAME = "farmPersistence";
    private static EntityManagerFactory factory;
    private static EntityManager em;
    private static AnimalDao animalDao;


    
    static {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
        animalDao = new AnimalDaoJpaImpl(em);

    }

    public static AnimalDao getAnimalDao() {
        return animalDao;
    }

}
