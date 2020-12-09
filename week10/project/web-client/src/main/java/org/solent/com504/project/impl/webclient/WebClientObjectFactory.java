/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.webclient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.factoryandfacade.impl.service.rest.client.ClientObjectFactoryImpl;
import org.solent.com504.project.model.service.ServiceFacade;
import org.solent.com504.project.model.service.ServiceObjectFactory;

/**
 *
 * @author gallenc
 */
public class WebClientObjectFactory {
    // SETS UP LOGGING
    final static Logger LOG = LogManager.getLogger(WebClientObjectFactory.class);

    private static ServiceFacade serviceFacade = null;

    public static ServiceFacade getServiceFacade() {
        if (serviceFacade == null) {
            synchronized (WebClientObjectFactory.class) {
                if (serviceFacade == null) {
                    LOG.debug("client web application starting");
                    ServiceObjectFactory clientObjectFactory = new ClientObjectFactoryImpl();
                    serviceFacade = clientObjectFactory.getServiceFacade();
                }
            }
        }
        return serviceFacade;
    }

}
