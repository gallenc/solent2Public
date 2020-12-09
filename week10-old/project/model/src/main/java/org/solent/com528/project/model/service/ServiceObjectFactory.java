package org.solent.com528.project.model.service;

public interface ServiceObjectFactory {

    public void shutDown();

    public ServiceFacade getServiceFacade();
}
