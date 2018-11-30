/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.ticketkiosk.service;

import solent.ac.uk.ood.examples.ticketkiosk.model.DailyTimeTableDAO;
import solent.ac.uk.ood.examples.ticketkiosk.dao.jaxbimpl.DailyTimeTableDAOJaxbImpl;
import solent.ac.uk.ood.examples.ticketkiosk.model.TicketMachineFacade;

/**
 *
 * @author cgallen
 */
public class ServiceFactory {

    TicketMachineFacade ticketMachineFacade = null;

    String dataFileUri = null;

    public ServiceFactory(String dataFileUri) {
        if (dataFileUri == null) {
            throw new IllegalArgumentException("dataFileUri must not be null");
        }
        DailyTimeTableDAO dailyTimetableDAO = new DailyTimeTableDAOJaxbImpl(dataFileUri);
        TicketMachineFacadeImpl ticketMachineFacadeImpl = new TicketMachineFacadeImpl();
        ticketMachineFacadeImpl.setDailyTimetableDAO(dailyTimetableDAO);
        ticketMachineFacade = ticketMachineFacadeImpl;
    }

    public TicketMachineFacade getTicketMachineFacade() {
        return ticketMachineFacade;
    }

}
