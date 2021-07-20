package org.solent.com528.project.model.service;

import org.solent.com528.project.model.dao.PriceCalculatorDAO;
import org.solent.com528.project.model.dao.StationDAO;
import org.solent.com528.project.model.dao.TicketMachineDAO;
import org.solent.com528.project.model.dto.TicketMachineConfig;

public interface ServiceFacade {

    public TicketMachineConfig getTicketMachineConfig(String ticketMachineUuid);

    public TicketMachineDAO getTicketMachineDAO();

    public StationDAO getStationDAO();

    public PriceCalculatorDAO getPriceCalculatorDAO();
}
