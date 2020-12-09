package org.solent.com528.project.impl.service;

import java.util.List;

import org.solent.com528.project.model.dao.PriceCalculatorDAO;
import org.solent.com528.project.model.dao.StationDAO;
import org.solent.com528.project.model.dao.TicketMachineDAO;
import org.solent.com528.project.model.dto.PricingDetails;
import org.solent.com528.project.model.dto.Station;
import org.solent.com528.project.model.dto.TicketMachine;
import org.solent.com528.project.model.dto.TicketMachineConfig;
import org.solent.com528.project.model.service.ServiceFacade;

public class ServiceFacadeImpl implements ServiceFacade {

    private TicketMachineDAO ticketMachineDAO;

    private StationDAO stationDAO;

    private PriceCalculatorDAO priceCalculatorDAO;

    @Override
    public TicketMachineConfig getTicketMachineConfig(String ticketMachineUuid) {
        TicketMachineConfig ticketMachineConfig = new TicketMachineConfig();

        PricingDetails pricingDetails = priceCalculatorDAO.getPricingDetails();
        ticketMachineConfig.setPricingDetails(pricingDetails);

        List<Station> stationList = stationDAO.findAll();
        ticketMachineConfig.setStationList(stationList);

        TicketMachine ticketMachine = ticketMachineDAO.findByUuid(ticketMachineUuid);
        Station station = ticketMachine.getStation();
        
        String stationName = station.getName();
        ticketMachineConfig.setStationName(stationName);

        Integer zone = station.getZone();
        ticketMachineConfig.setStationZone(zone);

        return ticketMachineConfig;
    }

    @Override
    public TicketMachineDAO getTicketMachineDAO() {
        return ticketMachineDAO;
    }

    public void setTicketMachineDAO(TicketMachineDAO ticketMachineDAO) {
        this.ticketMachineDAO = ticketMachineDAO;
    }

    @Override
    public StationDAO getStationDAO() {
        return stationDAO;
    }

    public void setStationDAO(StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    public void setPriceCalculatorDAO(PriceCalculatorDAO priceCalculatorDAO) {
        this.priceCalculatorDAO = priceCalculatorDAO;
    }

    @Override
    public PriceCalculatorDAO getPriceCalculatorDAO() {
        return priceCalculatorDAO;
    }

}
