/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.project.impl.webclient;

import java.util.Date;
import java.util.UUID;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com528.project.impl.service.rest.client.ClientObjectFactoryImpl;
import org.solent.com528.project.impl.service.rest.client.ConfigurationPoller;
import org.solent.com528.project.model.service.ServiceFacade;
import org.solent.com528.project.model.service.ServiceObjectFactory;

/**
 *
 * @author gallenc
 */
@WebListener
public class WebClientObjectFactory implements ServletContextListener {

    // SETS UP LOGGING
    final static Logger LOG = LogManager.getLogger(WebClientObjectFactory.class);

    private static ServiceFacade serviceFacade = null;

    private static ServiceObjectFactory clientObjectFactory = null;

    private static ConfigurationPoller configurationPoller = null;

    public static String getTicketMachineUuid() {
        return configurationPoller.getTicketMachineUuid();
    }

    public static void setTicketMachineUuid(String ticketMachineUuid) {
        LOG.debug("setting ticketMachineUuid=" + ticketMachineUuid);
        configurationPoller.setTicketMachineUuid(ticketMachineUuid);
    }

    public static String getStationName() {
        return configurationPoller.getStationName();
    }

    public static Integer getStationZone() {
        return configurationPoller.getStationZone();
    }

    public static Date getLastClientUpdateTime() {
        return configurationPoller.getLastClientUpdateTime();
    }

    public static Date getLastClientUpdateAttempt() {
        return configurationPoller.getLastClientUpdateAttempt();
    }

    public static ServiceFacade getServiceFacade() {
        if (serviceFacade == null) {
            synchronized (WebClientObjectFactory.class) {
                if (serviceFacade == null) {

                    LOG.debug("client web application starting");
                    clientObjectFactory = new ClientObjectFactoryImpl();
                    serviceFacade = clientObjectFactory.getServiceFacade();

                    configurationPoller = new ConfigurationPoller(serviceFacade);
                    // initially random uuid - can be set
                    String ticketMachineUuid = UUID.randomUUID().toString();
                    configurationPoller.setTicketMachineUuid(ticketMachineUuid);
                    long initialDelay = 0;
                    long delay = 30; // every 30 seconds
                    LOG.debug("starting configuration poller initialDelay=" + initialDelay
                            + ", delay=" + delay
                            + ", ticketMachineUuid=" + ticketMachineUuid);
                    configurationPoller.init(initialDelay, delay);
                }
            }
        }
        return serviceFacade;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.debug("WEB CLIENT OBJECT FACTORY context initialised");
        // nothing to do
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.debug("WEB CLIENT OBJECT FACTORY shutting down context");
        if (clientObjectFactory != null) {
            synchronized (WebClientObjectFactory.class) {
                LOG.debug("WEB OBJECT FACTORY shutting down configurationPoller");
                configurationPoller.shutdown();
                LOG.debug("WEB CLIENT OBJECT FACTORY  shutting down clientObjectFactory");
                clientObjectFactory.shutDown();
            }

        }
    }

}
