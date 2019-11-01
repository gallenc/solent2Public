package org.solent.com504.factoryandfacade.model.service;

/**
 * Entry point for accessing all of the services in the farm model
 * @author gallenc
 */
public interface ServiceObjectFactory {

    /**
     * Returns a singleton FarmFacade object. 
     * There is only ever one FarmFacade per ServiceObjectFactory
     * @return farmFacade
     */
    public FarmFacade getFarmFacade();
    
    /** 
     * shuts down all services created by factory gracefully
     */
    public void shutDown();
}
