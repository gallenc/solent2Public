package org.solent.com528.project.model.dao;

public interface DAOFactory {

    public TicketMachineDAO getTicketMachineDAO();

    public StationDAO getStationDAO();
    
    public PriceCalculatorDAO getPriceCalculatorDAO();

    public void shutDown();
}
