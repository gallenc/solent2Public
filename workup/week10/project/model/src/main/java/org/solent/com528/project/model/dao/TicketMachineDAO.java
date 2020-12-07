package org.solent.com528.project.model.dao;

import org.solent.com528.project.model.dto.TicketMachine;

public interface TicketMachineDAO {

    public TicketMachine findById(Long id);

    public TicketMachine findByUuid(String uuid);

    public TicketMachine save(TicketMachine ticketMachine);

    public void deleteAll();

    public void deleteById(Long id);

    public TicketMachine delete(TicketMachine ticketMachine);

    public TicketMachine findByStationName(String stationName);
}
