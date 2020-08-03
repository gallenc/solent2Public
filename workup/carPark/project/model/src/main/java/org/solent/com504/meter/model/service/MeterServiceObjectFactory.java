package org.solent.com504.meter.model.service;

public interface MeterServiceObjectFactory {

    public void shutDown();

    public MeterServiceFacade getServiceFacade();
}
