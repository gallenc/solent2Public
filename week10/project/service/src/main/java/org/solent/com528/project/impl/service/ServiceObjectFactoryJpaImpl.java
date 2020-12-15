package org.solent.com528.project.impl.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com528.project.impl.dao.jpa.DAOFactoryJPAImpl;
import org.solent.com528.project.model.dao.DAOFactory;
import org.solent.com528.project.model.dao.PriceCalculatorDAO;
import org.solent.com528.project.model.dao.StationDAO;
import org.solent.com528.project.model.dao.TicketMachineDAO;
import org.solent.com528.project.model.service.ServiceFacade;
import org.solent.com528.project.model.service.ServiceObjectFactory;

// This is a hard coded implementation of the factory using the JPA DAO 
// and could be replaced by Spring
public class ServiceObjectFactoryJpaImpl implements ServiceObjectFactory {

    final static Logger LOG = LogManager.getLogger(ServiceObjectFactoryJpaImpl.class);

    private ServiceFacadeImpl serviceFacade = null;
    private DAOFactory daoFactory = null;
    private PriceCalculatorDAO priceCalculatorDAO=null;
    private StationDAO stationDAO;
    private TicketMachineDAO ticketMachineDAO;

    /**
     * Initialises farmFacade objectFactory
     */
    public ServiceObjectFactoryJpaImpl() {
        
        daoFactory = new DAOFactoryJPAImpl();
        //TODO
        //priceCalculatorDAO = daoFactory.getPriceCalculatorDAO();
        stationDAO = daoFactory.getStationDAO();
        ticketMachineDAO = daoFactory.getTicketMachineDAO();

        serviceFacade = new ServiceFacadeImpl();
        serviceFacade.setPriceCalculatorDAO(priceCalculatorDAO);
        serviceFacade.setStationDAO(stationDAO);
        serviceFacade.setTicketMachineDAO(ticketMachineDAO);

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
