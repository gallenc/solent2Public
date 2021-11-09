package org.solent.com504.factoryandfacade.impl.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;
import org.solent.com504.factoryandfacade.model.dao.DAOFactory;

public class DAOFactoryJPAImpl implements DAOFactory {

    final static Logger LOG = LogManager.getLogger(DAOFactoryJPAImpl.class);
    // TODO FACTORY MAY WANT TO CLOSE ENTITY MANAGER AT END OF SESSION
    private static final String PERSISTENCE_UNIT_NAME = "modelPersistence";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    private static AnimalDao animalDao = null;
    private static AnimalTypeDao animalTypeDao = null;

    @Override
    public AnimalDao getAnimalDao() {
        if (animalDao == null) {
            synchronized (this) {
                if (animalDao == null) {
                    try {
                        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                        em = factory.createEntityManager();
                        animalDao = new AnimalDaoJpaImpl(em);
                    } catch (Exception ex) {
                        LOG.error("problem creating DAOFactoryJPAImpl ", ex);
                        throw new RuntimeException("problem creating AnimalDaoJpaImpl ", ex);
                    }
                }
            }
        }

        return animalDao;
    }

    @Override
    public AnimalTypeDao getAnimalTypeDao() {
        if (animalTypeDao == null) {
            synchronized (this) {
                if (animalTypeDao == null) {
                    try {
                        animalTypeDao = new AnimalTypeDaoImpl();
                    } catch (Exception ex) {
                        LOG.error("problem creating DAOFactoryJPAImpl ", ex);
                        throw new RuntimeException("problem creating AnimalTypeDaoImpl ", ex);
                    }
                }
            }
        }
        return animalTypeDao;
    }

    // shutdown method which should be used to clean up entity manager when application closes
    public void shutDown() {
        LOG.debug("DAO FACTORY SHUTTING DOWN ");
        if (em != null) {
            synchronized (this) {
                LOG.debug("closing entity manager ");
                if (em != null) {
                    em.close();
                    em = null;
                }
                animalDao = null;
                animalTypeDao = null;
            }

        }

    }

}
