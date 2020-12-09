package org.solent.com528.project.model.dao;

import java.util.List;
import org.solent.com528.project.model.dto.TicketMachine;

public interface TicketMachineDAO {

    public TicketMachine findById(Long id);

    public TicketMachine findByUuid(String uuid);

    public TicketMachine save(TicketMachine ticketMachine);

    public void deleteAll();

    public void deleteById(Long id);

    public void delete(TicketMachine ticketMachine);

    public List<TicketMachine> findByStationName(String stationName);
    
    public List<TicketMachine> findAll();
}
