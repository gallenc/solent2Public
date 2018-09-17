/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardcheck.service.rest;

import solent.ac.uk.ood.examples.cardcheck.service.ServiceObjectFactory;

/**
 *
 * @author cgallen
 */
public class AppObjectFactory {
    
   private  ServiceObjectFactory serviceObjectFactory = null;

    /**
     * @return the serviceObjectFactory
     */
    public ServiceObjectFactory getServiceObjectFactory() {
        return serviceObjectFactory;
    }

    /**
     * @param serviceObjectFactory the serviceObjectFactory to set
     */
    public void setServiceObjectFactory(ServiceObjectFactory serviceObjectFactory) {
        this.serviceObjectFactory = serviceObjectFactory;
    }
            
}
