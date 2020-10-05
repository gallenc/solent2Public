/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.cardservice.web;

import solent.ac.uk.ood.examples.cardvalidator.accountdao.jaxbimpl.AccountDAOJaxbImpl;
import solent.ac.uk.ood.examples.cardvalidator.cardservice.ServiceObjectFactoryImpl;
import solent.ac.uk.ood.examples.cardvalidator.impl.CardFactoryDAOImpl;
import solent.ac.uk.ood.examples.cardvalidator.model.AccountDAO;
import solent.ac.uk.ood.examples.cardvalidator.model.CardFactoryDAO;
import solent.ac.uk.ood.examples.cardvalidator.model.ServiceObjectFactory;


/**
 * 
 *
 * @author cgallen
 */
public class WebObjectFactory {

    public static ServiceObjectFactory serviceObjectFactory = null;
    public static String DATA_FILE_LOCATION = "./accounts-data.xml";

    public static ServiceObjectFactory getServiceObjectFactory() {

        if (serviceObjectFactory == null) {
            synchronized (WebObjectFactory.class) {
                if (serviceObjectFactory == null) {
                    
                    CardFactoryDAO cardFactoryDAO = new CardFactoryDAOImpl();
                    
                    AccountDAO accountDAO = new AccountDAOJaxbImpl(DATA_FILE_LOCATION);

                    ServiceObjectFactoryImpl.setCardFactoryDao(cardFactoryDAO);

                    ServiceObjectFactoryImpl.setAccountDAO(accountDAO);

                    serviceObjectFactory = new ServiceObjectFactoryImpl();
                }
            }
        }
        return serviceObjectFactory;
    }

}
