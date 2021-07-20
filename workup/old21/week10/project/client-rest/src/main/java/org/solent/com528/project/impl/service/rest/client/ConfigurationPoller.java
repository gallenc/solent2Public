/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.project.impl.service.rest.client;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.solent.com528.project.impl.service.rest.client.ClientObjectFactoryImpl.LOG;
import org.solent.com528.project.model.dao.PriceCalculatorDAO;
import org.solent.com528.project.model.dao.StationDAO;
import org.solent.com528.project.model.dao.TicketMachineDAO;
import org.solent.com528.project.model.dto.PricingDetails;
import org.solent.com528.project.model.dto.Station;
import org.solent.com528.project.model.dto.TicketMachineConfig;
import org.solent.com528.project.model.service.ServiceFacade;

/**
 *
 * @author cgallen
 */
public class ConfigurationPoller {

    final static Logger LOG = LogManager.getLogger(ConfigurationPoller.class);

    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private Runnable task = null;

    private ServiceFacade clientServiceFacade = null;

    private PriceCalculatorDAO priceCalculatorDAO = null;

    private StationDAO stationDAO = null;

    private String stationName = null;

    private Integer stationZone = null;

    private Date lastUpdateTime = null;

    private Date lastUpdateAttempt = null;

    // will start up with a random uuid - can be changed as string references are atomic
    private String ticketMachineUuid = UUID.randomUUID().toString();

    public String getStationName() {
        return stationName;
    }

    public Integer getStationZone() {
        return stationZone;
    }

    public String getTicketMachineUuid() {
        return ticketMachineUuid;
    }

    public void setTicketMachineUuid(String ticketMachineUuid) {
        this.ticketMachineUuid = ticketMachineUuid;
    }

    public Date getLastClientUpdateTime() {
        return this.lastUpdateTime;
    }

    public Date getLastClientUpdateAttempt() {
        return this.lastUpdateAttempt;
    }

    public ConfigurationPoller(ServiceFacade clientServiceFacade) {
        LOG.debug("created ConfigurationPoller");
        this.clientServiceFacade = clientServiceFacade;
        this.priceCalculatorDAO = clientServiceFacade.getPriceCalculatorDAO();
        this.stationDAO = clientServiceFacade.getStationDAO();
    }

    public void init(long initialDelay, long delay) {
        LOG.debug("init called ticketMachineUuid=" + ticketMachineUuid
                + ", initialDelay=" + initialDelay
                + ", delay=" + delay);

        if (task != null) {
            LOG.debug("init called when task already initialised");
        } else synchronized (this) {
            if (task == null) {
                LOG.debug("initialising configuration ticketMachineUuid=" + ticketMachineUuid
                        + " download timer (seconds) initialDelay=" + initialDelay
                        + " delay=" + delay);

                task = new Runnable() {
                    public void run() {
                        try {
                            lastUpdateAttempt = new Date();
                            LOG.debug("attempting to download configuration at "+lastUpdateAttempt.toString());
                            TicketMachineConfig ticketMachineConfig = clientServiceFacade.getTicketMachineConfig(ticketMachineUuid);
                            
                            lastUpdateTime= lastUpdateAttempt;
                            LOG.debug("received ticketMachineConfig at "+lastUpdateTime.toString()
                                    + " ticketMachineConfig=\n" + ticketMachineConfig);

                            stationName = ticketMachineConfig.getStationName();
                            stationZone = ticketMachineConfig.getStationZone();

                            List<Station> stationList = ticketMachineConfig.getStationList();
                            stationDAO.deleteAll();
                            stationDAO.saveAll(stationList);

                            PricingDetails pricingDetails = ticketMachineConfig.getPricingDetails();
                            priceCalculatorDAO.deleteAll();
                            priceCalculatorDAO.savePricingDetails(pricingDetails);

                        } catch (Exception ex) {
                            LOG.error("problem when attempting to download configuration", ex);
                        }
                    }
                };

                scheduler.scheduleAtFixedRate(task, initialDelay, delay, TimeUnit.SECONDS);

                // Belt and braces shutdown 
//                Runtime.getRuntime().addShutdownHook(new Thread() {
//                    public void run() {
//                        shutdown();
//                    }
//                });
            }

        }
    }

    public void shutdown() {
        LOG.debug("shutting down Configuration Poller");
        if (scheduler != null) {
            LOG.debug("shutting down scheduller");
            scheduler.shutdownNow();
        }

    }

}
