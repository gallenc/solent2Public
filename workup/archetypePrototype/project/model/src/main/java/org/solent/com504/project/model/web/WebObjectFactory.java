package org.solent.com504.project.model.web;

import org.solent.com504.project.model.service.ServiceFacade;

public interface WebObjectFactory {

    public ServiceFacade getServiceFacade();
    
    public void shutDown();
}
