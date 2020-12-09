/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.solent.com504.project.model.dao.AppointmentDAO;
import org.solent.com504.project.model.dao.DAOFactory;
import org.solent.com504.project.model.dao.PersonDAO;
import org.solent.com528.project.impl.dao.jpa.DAOFactoryJPAImpl;

/**
 * THIS CLASS IS WRITTEN TO USE THE SAME ENTITY MANAGER
 * THIS CAN BE REMOVED ONCE HE COM504 CLASSES NO LONGER NEEDED
 * @author cgallen
 */
public class DAOFactoryCom504JPAImpl extends DAOFactoryJPAImpl implements DAOFactory {

    // this class inherits the entity manager from DAOFactoryJPAImpl
    private static PersonDAO personDAO;
    private static AppointmentDAO appointmentDAO;

    static {
        // note it is important that all DAO's share same entity manager
        personDAO = new PersonDAOJpaImpl(em);
        appointmentDAO = new AppointmentDAOJpaImpl(em);
    }

    @Override
    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    @Override
    public AppointmentDAO getAppointmentDAO() {
        return appointmentDAO;
    }

    @Override
    public void shutDown() {
        super.shutDown();
        
//        if (em!=null) synchronized (this){
//            if (em!=null){
//                em.close();
//            }
//        }
    }

}
