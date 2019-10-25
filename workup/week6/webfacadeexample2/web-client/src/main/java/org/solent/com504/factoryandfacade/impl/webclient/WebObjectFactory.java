/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.webclient;

import org.solent.com504.factoryandfacade.model.service.FarmFacade;
import org.solent.com504.factoryandfacade.model.service.ServiceObjectFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.factoryandfacade.impl.service.rest.client.ClientObjectFactoryImpl;

/**
 *
 * @author gallenc
 */
public class WebObjectFactory {
    // SETS UP LOGGING
    final static Logger LOG = LogManager.getLogger(WebObjectFactory.class);

    private static FarmFacade farmFacade = null;

    public static FarmFacade getServiceFacade() {
        if (farmFacade == null) {
            synchronized (WebObjectFactory.class) {
                if (farmFacade == null) {
                    LOG.debug("client web application starting");
                    ServiceObjectFactory clientObjectFactory = new ClientObjectFactoryImpl();
                    farmFacade = clientObjectFactory.getFarmFacade();
                }
            }
        }
        return farmFacade;
    }

}
