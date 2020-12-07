package org.solent.com528.project.model.web;

import org.solent.com528.project.model.service.ServiceFacade;

public interface WebObjectFactory {

    public ServiceFacade getServiceFacade();

    public void shutDown();
}
