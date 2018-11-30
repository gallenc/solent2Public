/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.ticketkiosk.service.test;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import solent.ac.uk.ood.examples.ticketkiosk.model.Ticket;
import solent.ac.uk.ood.examples.ticketkiosk.model.TicketItem;
import solent.ac.uk.ood.examples.ticketkiosk.service.ServiceFactory;
import solent.ac.uk.ood.examples.ticketkiosk.model.TicketMachineFacade;

/**
 *
 * @author cgallen
 */
public class TicketMachineFacadeImplTest {

    public static final String TEST_DATA_FILE = "./target/testfile.xml";

    @Test
    public void createTicketTest() {

        // use service factory to get access to service
        ServiceFactory serviceFactory = new ServiceFactory(TEST_DATA_FILE);
        assertNotNull(serviceFactory);

        TicketMachineFacade ticketMachineFacade = serviceFactory.getTicketMachineFacade();

        TicketItem ticketItem1 = new TicketItem();
        ticketItem1.setDestination("Crew");
        ticketItem1.setPrice(54.99);
        String scheduleTime1Start = "09:55";
        ticketItem1.setStart(scheduleTime1Start);
        String scheduleTime1End = "10:20";
        ticketItem1.setEnd(scheduleTime1End);
        
        ticketItem1.setRailCardAllowed(Boolean.FALSE);
        boolean railCardUsed = true;

        //TODO CREATE TWO TICKETS AND TEST TICKETID NOT EQUAL for tickets as they are created 

        Ticket ticket1 = ticketMachineFacade.createTicket(ticketItem1, railCardUsed);
        // TODO UNCOMMENT AND MAKE THIS TEST PASS
        // assertNotNull(ticket1);

        Ticket ticket2 = ticketMachineFacade.createTicket(ticketItem1, railCardUsed);
        // TODO UNCOMMENT AND MAKE THIS TEST PASS
        //assertNotNull(ticket2);

        // TODO UNCOMMENT AND MAKE THIS TEST PASS
        // assertNotEquals(ticket1.getTicketNumber(), ticket2.getTicketNumber());
        
        // TODO WRITE TEST TO to check not applied if railcard allowed and railcard not used
        
        // TODO WRITE TEST TO to check discount applied  if railcard allowed and railcard used
        
        // TODO WRITE TEST to check discound not applied  if railcard not allowed and railcard used
    }

}
