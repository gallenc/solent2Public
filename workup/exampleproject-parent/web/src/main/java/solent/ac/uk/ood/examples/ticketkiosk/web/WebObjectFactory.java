package solent.ac.uk.ood.examples.ticketkiosk.web;

import solent.ac.uk.ood.examples.ticketkiosk.service.ServiceFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gallenc
 */
public class WebObjectFactory {

    public static ServiceFactory serviceFactory = null;
    public static String DATA_FILE_LOCATION = "./ticket-data.xml";

    public static ServiceFactory getServiceFactory() {

        if (serviceFactory == null) {
            synchronized (WebObjectFactory.class) {
                if (serviceFactory == null) {
                    serviceFactory = new ServiceFactory(DATA_FILE_LOCATION);
                }
            }
        }
        return serviceFactory;
    }
}
