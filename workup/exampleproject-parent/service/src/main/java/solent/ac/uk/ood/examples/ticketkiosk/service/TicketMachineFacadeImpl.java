/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.ticketkiosk.service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.ticketkiosk.model.DailyTimeTableDAO;
import solent.ac.uk.ood.examples.ticketkiosk.model.Ticket;
import solent.ac.uk.ood.examples.ticketkiosk.model.TicketItem;
import solent.ac.uk.ood.examples.ticketkiosk.model.TicketMachineFacade;

/**
 *
 * @author cgallen
 */
public class TicketMachineFacadeImpl implements TicketMachineFacade {

    private static final Logger LOG = LoggerFactory.getLogger(TicketMachineFacadeImpl.class);
    private static final Logger TRANSACTION_LOG = LoggerFactory.getLogger("transaction-log");

    DailyTimeTableDAO dailyTimetableDAO = null;
    
    // atomic integer is used for ticket ID to avoid concurrent access exceptions
    // and always ensure we get a unique ticket ID
    AtomicInteger nextTicketNumber = new AtomicInteger(0);

    public void setDailyTimetableDAO(DailyTimeTableDAO dailyTimetableDAO) {
        this.dailyTimetableDAO = dailyTimetableDAO;
    }

    @Override
    public Set<String> getDestinations() {
        return dailyTimetableDAO.getDestinations();
    }

    @Override
    public List<TicketItem> getTicketItemsForDestination(String destination) {
        return dailyTimetableDAO.getTicketItemsForDestination(destination);
    }

    @Override
    public boolean deleteTicketItem(Integer ticketItemId) {
        return dailyTimetableDAO.deleteTicketItem(ticketItemId);
    }

    @Override
    public TicketItem createTicketItem(TicketItem ticketItem) {
        return dailyTimetableDAO.createTicketItem(ticketItem);
    }

    @Override
    public TicketItem updateTicketItem(TicketItem ticketItem) {
        return dailyTimetableDAO.updateTicketItem(ticketItem);
    }

    @Override
    public TicketItem getTicketItem(Integer ticketItemId) {
        return dailyTimetableDAO.getTicketItem(ticketItemId);
    }

    @Override
    public void addDestination(String destination) {
        dailyTimetableDAO.addDestination(destination);
    }

    @Override
    public boolean deleteDestination(String destination) {
        return dailyTimetableDAO.deleteDestination(destination);
    }
    
    @Override
    public Boolean desitinationExists(String destination) {
        return dailyTimetableDAO.desitinationExists(destination);
    }

    @Override
    public Ticket createTicket(TicketItem ticketItem, boolean railCardUsed) {
        LOG.debug("starting to create ticket");
        if (ticketItem == null) {
            throw new IllegalArgumentException("ticketItem must not be null");
        }
        // get and increment ticket number
        Integer ticketNumber = nextTicketNumber.addAndGet(1);
        
        Ticket ticket = null;
        
        // TODO ADD CODE TO CREATE TICKET

        // TODO ADD CODE RAILCARD USED

        // enter issued ticket info in transaction log
        TRANSACTION_LOG.info("CREATED NEW TICKET: "+ticket);
    
        return ticket;
    }


}
