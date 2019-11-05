/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.exam.web;

import solent.ac.uk.ood.examples.exam.model.ServiceFactory;
import solent.ac.uk.ood.examples.exam.service.ServiceFactoryImpl;

/**
 *
 * @author cgallen
 */
public class WebObjectFactory {

    final static String TMP_DIR = System.getProperty("java.io.tmpdir");
    
    private static final String DATA_FILE_LOCATION = TMP_DIR+"/library/app-data.xml";

    private static ServiceFactory serviceFactory = null;

    public static ServiceFactory getServiceFactory() {

        if (serviceFactory == null) {
            synchronized (WebObjectFactory.class) {
                if (serviceFactory == null) {
                    serviceFactory = new ServiceFactoryImpl(DATA_FILE_LOCATION);
                }
            }
        }
        return serviceFactory;
    }
}
