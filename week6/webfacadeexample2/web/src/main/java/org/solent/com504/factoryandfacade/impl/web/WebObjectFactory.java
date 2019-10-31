/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.web;


import java.io.File;
import org.solent.com504.factoryandfacade.impl.service.ServiceObjectFactoryImpl;
import org.solent.com504.factoryandfacade.model.service.FarmFacade;
import org.solent.com504.factoryandfacade.model.service.ServiceObjectFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.factoryandfacade.impl.service.ServiceObjectFactoryJpaImpl;

/**
 *
 * @author gallenc
 */
public class WebObjectFactory {
    // SETS UP LOGGING
    final static Logger LOG = LogManager.getLogger(WebObjectFactory.class);

    final static String  TMP_DIR = System.getProperty("java.io.tmpdir"); 
    
    private static FarmFacade farmFacade = null;

    public static FarmFacade getServiceFacade() {
        if (farmFacade == null) {
            synchronized (WebObjectFactory.class) {
                if (farmFacade == null) {
                    LOG.debug("web application starting");
                    
                    // this is needed to allow Derby to work as in embedded server
                    String derbyHome=TMP_DIR+File.separator+"derby";
                    LOG.debug("setting derby.system.home="+derbyHome);
                    
                    System.setProperty("derby.system.home", derbyHome); 
                    // note we can choose which we use
                    // ServiceObjectFactory serviceObjectFactory = new ServiceObjectFactoryImpl();
                    ServiceObjectFactory serviceObjectFactory = new ServiceObjectFactoryJpaImpl();
                    farmFacade = serviceObjectFactory.getFarmFacade();
                }
            }
        }
        return farmFacade;
    }

}
