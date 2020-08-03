package org.solent.com504.meter.model.web;

import org.solent.com504.meter.model.service.MeterServiceFacade;

public interface MeterWebObjectFactory {

    public MeterServiceFacade getServiceFacade();

    public void shutDown();
}
