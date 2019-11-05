/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.service.rest.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.model.service.ServiceFacade;
import org.solent.com504.project.model.service.ServiceObjectFactory;

/**
 *
 * @author gallenc
 *//**
 *
 * @author gallenc
 *//**
 *
 * @author gallenc
 */
public class ClientObjectFactoryImpl implements ServiceObjectFactory {

    final static Logger LOG = LogManager.getLogger(ClientObjectFactoryImpl.class);
    
    private ServiceFacade serviceFacade = null;
    private String baseUrl = "http://localhost:8084/projectfacadeweb/rest/appointmentService";
    
    @Override
    public ServiceFacade getServiceFacade() {
        
        if (serviceFacade == null) {
            LOG.debug("creating new ServiceRestClientImpl for baseUrl=" + baseUrl);
            synchronized (this) {
                if (serviceFacade == null) {
                    serviceFacade = new ServiceRestClientImpl(baseUrl);
                }
            }
        }
        
        return serviceFacade;
    }

    @Override
    public void shutDown() {
        // do nothing
    }
    
}
