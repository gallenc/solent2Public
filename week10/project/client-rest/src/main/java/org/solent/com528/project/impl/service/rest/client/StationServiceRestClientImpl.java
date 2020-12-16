/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.project.impl.service.rest.client;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import org.solent.com528.project.model.dto.ReplyMessage;
import org.solent.com528.project.model.service.ServiceFacade;
import java.util.logging.Level;
import org.solent.com528.project.impl.dao.jaxb.PriceCalculatorDAOJaxbImpl;
import org.solent.com528.project.impl.dao.jaxb.StationDAOJaxbImpl;
import static org.solent.com528.project.impl.service.rest.client.ClientObjectFactoryImpl.LOG;
import org.solent.com528.project.model.dao.PriceCalculatorDAO;
import org.solent.com528.project.model.dao.StationDAO;
import org.solent.com528.project.model.dao.TicketMachineDAO;
import org.solent.com528.project.model.dto.TicketMachineConfig;

/**
 *
 * @author gallenc
 */
public class StationServiceRestClientImpl implements ServiceFacade {

    final static Logger LOG = LogManager.getLogger(StationServiceRestClientImpl.class);

    // java.io.tmpdir is hte temporary storege directory - differnt within tomcat or running simply on windows
    final static String TMP_DIR = System.getProperty("java.io.tmpdir");

    // + File.separator is system specific path seperator / or \ for windows or linux
    private String pricingDetailsFile = TMP_DIR + File.separator + "client" + File.separator + "pricingDetailsFile.xml";

    private String stationListFile = TMP_DIR + File.separator + "client" + File.separator + "stationListFile.xml";

    private String baseUrl = "http://localhost:8080/projectfacadeweb/rest/appointmentService";

    private StationDAO stationDAO;

    private PriceCalculatorDAO priceCalculatorDAO;

    public StationServiceRestClientImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * this is a ReST api to get configuration from parent machine http://localhost:8080/projectfacadeweb/rest/stationService/getTicketMachineConfig?uuid=xyz
     *
     * @param ticketMachineUuid
     * @return
     */
    @Override
    public TicketMachineConfig getTicketMachineConfig(String ticketMachineUuid) {
        LOG.debug("getTicketMachineConfig() Called ticketMachineUuid="+ticketMachineUuid);

        Client client = ClientBuilder.newClient(new ClientConfig().register(
                new LoggingFeature(java.util.logging.Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                        Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000)));

        WebTarget webTarget = client.target(baseUrl).path("getTicketMachineConfig").queryParam("uuid", ticketMachineUuid);

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();
        

        ReplyMessage replyMessage = response.readEntity(ReplyMessage.class);
        LOG.debug("Response status=" + response.getStatus() + " ReplyMessage: " + replyMessage);

        if (replyMessage == null || response.getStatus() !=200) {
            return null;
        }
        return replyMessage.getTicketMachineConfig();

    }

    // NOTE not used in client
    @Override
    public TicketMachineDAO getTicketMachineDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StationDAO getStationDAO() {
        if (stationDAO == null) {
            LOG.debug("creating new StationDAO ");
            synchronized (this) {
                if (stationDAO == null) {
                    stationDAO = new StationDAOJaxbImpl(stationListFile);
                }
            }
        }
        return stationDAO;
    }

    @Override
    public PriceCalculatorDAO getPriceCalculatorDAO() {
        if (priceCalculatorDAO == null) {
            LOG.debug("creating new priceCalculatorDAO ");
            synchronized (this) {
                if (priceCalculatorDAO == null) {
                    priceCalculatorDAO = new PriceCalculatorDAOJaxbImpl(pricingDetailsFile);
                }
            }
        }
        return priceCalculatorDAO;
    }

}
