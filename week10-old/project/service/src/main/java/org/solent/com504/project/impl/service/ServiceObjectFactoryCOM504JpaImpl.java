package org.solent.com504.project.impl.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.impl.dao.jpa.DAOFactoryCom504JPAImpl;
import org.solent.com504.project.model.dao.AppointmentDAO;
import org.solent.com504.project.model.dao.DAOFactory;
import org.solent.com504.project.model.dao.PersonDAO;
import org.solent.com504.project.model.service.ServiceFacade;
import org.solent.com504.project.model.service.ServiceObjectFactory;

// This is a hard coded implementation of the factory using the jaxb DAO 
// and could be replaced by Spring
public class ServiceObjectFactoryCOM504JpaImpl implements ServiceObjectFactory {

    final static Logger LOG = LogManager.getLogger(ServiceObjectFactoryCOM504JpaImpl.class);

    private ServiceFacadeCOM504Impl serviceFacade = null;
    AppointmentDAO appoitmentDao = null;
    PersonDAO personDao = null;
    private DAOFactory daoFactory = null;

    /**
     * Initialises farmFacade objectFactory
     */
    public ServiceObjectFactoryCOM504JpaImpl() {

        daoFactory = new DAOFactoryCom504JPAImpl();
        personDao = daoFactory.getPersonDAO();
        appoitmentDao = daoFactory.getAppointmentDAO();

        serviceFacade = new ServiceFacadeCOM504Impl();
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
