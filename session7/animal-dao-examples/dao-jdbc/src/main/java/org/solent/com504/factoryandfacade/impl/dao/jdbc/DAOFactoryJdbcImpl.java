package org.solent.com504.factoryandfacade.impl.dao.jdbc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;
import org.solent.com504.factoryandfacade.model.dao.DAOFactory;

public class DAOFactoryJdbcImpl implements DAOFactory {

    final static Logger LOG = LogManager.getLogger(DAOFactoryJdbcImpl.class);

    private static AnimalDao animalDao = null;
    private static AnimalTypeDao animalTypeDao = null;

    static {
        ConnectionFactory cf = ConnectionFactory.getInstance();
        animalDao = new AnimalDaoJdbcImpl(cf);
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

    @Override
    public void shutDown() {
    }

 

}
