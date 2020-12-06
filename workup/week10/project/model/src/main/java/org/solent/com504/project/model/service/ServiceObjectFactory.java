package org.solent.com504.project.model.service;

public interface ServiceObjectFactory {

    public void shutDown();

    public ServiceFacade getServiceFacade();
}
