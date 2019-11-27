package org.solent.com504.project.impl.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.impl.dao.spring.DAOFactoryJPASpringImpl;
//import org.solent.com504.project.impl.dao.jpa.DAOFactoryJPAImpl;
import org.solent.com504.project.model.dao.AppointmentDAO;
import org.solent.com504.project.model.dao.DAOFactory;
import org.solent.com504.project.model.dao.PersonDAO;
import org.solent.com504.project.model.service.ServiceFacade;
import org.solent.com504.project.model.service.ServiceObjectFactory;

// This is a hard coded implementation of the factory using the jaxb DAO 
// and could be replaced by Spring
public class ServiceObjectFactoryJpaImpl implements ServiceObjectFactory {

    final static Logger LOG = LogManager.getLogger(ServiceObjectFactoryJpaImpl.class);

    private ServiceFacadeImpl serviceFacade = null;
    AppointmentDAO appoitmentDao = null;
    PersonDAO personDao = null;
    private DAOFactory daoFactory = null;

    /**
     * Initialises farmFacade objectFactory
     */
    public ServiceObjectFactoryJpaImpl() {

        //daoFactory = new DAOFactoryJPAImpl();
        daoFactory = new DAOFactoryJPASpringImpl();
        personDao = daoFactory.getPersonDAO();
        appoitmentDao = daoFactory.getAppointmentDAO();

        serviceFacade = new ServiceFacadeImpl();
        serviceFacade.setAppointmentDao(appoitmentDao);
        serviceFacade.setPersonDao(personDao);
    }

    @Override
    public ServiceFacade getServiceFacade() {
        return serviceFacade;
    }

    @Override
    public void shutDown() {
        LOG.debug("SERVICE OBJECT FACTORY SHUTTING DOWN ");
        if (daoFactory != null) {
            daoFactory.shutDown();
        }
    }

}
