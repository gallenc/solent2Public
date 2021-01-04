/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.service.rest.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.factoryandfacade.model.service.FarmFacade;
import org.solent.com504.factoryandfacade.model.service.ServiceObjectFactory;

/**
 *
 * @author gallenc
 */
public class ClientObjectFactoryImpl implements ServiceObjectFactory {

    final static Logger LOG = LogManager.getLogger(ClientObjectFactoryImpl.class);
    
    private FarmFacade farmFacade = null;
    private String baseUrl = "http://localhost:8084/basicfacadeweb/rest/farmService";
    
    @Override
    public FarmFacade getFarmFacade() {
        
        if (farmFacade == null) {
            LOG.debug("creating new FarmRestClientImpl for baseUrl=" + baseUrl);
            synchronized (this) {
                if (farmFacade == null) {
                    farmFacade = new FarmRestClientImpl(baseUrl);
                }
            }
        }
        
        return farmFacade;
    }
    
}
