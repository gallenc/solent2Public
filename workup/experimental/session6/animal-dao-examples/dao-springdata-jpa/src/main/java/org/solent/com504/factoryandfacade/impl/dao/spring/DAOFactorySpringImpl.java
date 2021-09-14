package org.solent.com504.factoryandfacade.impl.dao.spring;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dao.AnimalTypeDao;
import org.solent.com504.factoryandfacade.model.dao.DAOFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DAOFactorySpringImpl implements DAOFactory {

    final static Logger LOG = LogManager.getLogger(DAOFactorySpringImpl.class);

    private static AnimalDao animalDao = null;
    private static AnimalTypeDao animalTypeDao = null;
    private static ApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext("spring.xml");
        animalDao = (AnimalDao) context.getBean("animalDaoImplSpring");
        animalTypeDao = (AnimalTypeDao) context.getBean("animalTypeDaoImpl");
    }

    @Override
    public AnimalDao getAnimalDao() {
        return animalDao;
    }

    @Override
    public AnimalTypeDao getAnimalTypeDao() {
        return animalTypeDao;
    }

    // shutdown method which should be used to clean up entity manager when application closes
    public void shutDown() {
        LOG.debug("DAO FACTORY SHUTTING DOWN ");
    }

}
