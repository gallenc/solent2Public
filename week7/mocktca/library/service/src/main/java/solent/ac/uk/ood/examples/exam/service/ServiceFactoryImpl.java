/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.exam.service;

import solent.ac.uk.ood.examples.exam.dao.jaxbimpl.BookDAOJaxbImpl;
import solent.ac.uk.ood.examples.exam.model.BookDAO;
import solent.ac.uk.ood.examples.exam.model.ServiceFacade;
import solent.ac.uk.ood.examples.exam.model.ServiceFactory;

/**
 *
 * @author cgallen
 */
public class ServiceFactoryImpl implements ServiceFactory {

    ServiceFacade serviceFacade = null;

    String dataFileUri = null;

    public ServiceFactoryImpl(String dataFileUri) {
        if (dataFileUri == null) {
            throw new IllegalArgumentException("dataFileUri must not be null");
        }
        
        BookDAO bookDao = new BookDAOJaxbImpl(dataFileUri);
        ServiceFacadeImpl serviceFacadeImpl = new ServiceFacadeImpl();
        serviceFacadeImpl.setBookDAO(bookDao);
        serviceFacade = serviceFacadeImpl;
        
    }

    @Override
    public ServiceFacade getServiceFacade() {
        return serviceFacade;
    }

}
