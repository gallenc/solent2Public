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
    
   private static ServiceObjectFactory m_serviceObjectFactory = null;
   

    /**
     * @return the serviceObjectFactory
     */
    public static ServiceObjectFactory getServiceObjectFactory() {
        if (m_serviceObjectFactory==null) synchronized(ServiceObjectFactory.class) {
            // creates a new factory if doesnt exist and not injected
            // could use spring here
            if (m_serviceObjectFactory==null){
                m_serviceObjectFactory= new MockServiceObjectFactoryImpl();
            }
            
            
            
            
        }
        return m_serviceObjectFactory;
    }

    /**
     * @param serviceObjectFactory the serviceObjectFactory to set
     */
    public static void setServiceObjectFactory(ServiceObjectFactory serviceObjectFactory) {
        m_serviceObjectFactory = serviceObjectFactory;
    }
            
}
