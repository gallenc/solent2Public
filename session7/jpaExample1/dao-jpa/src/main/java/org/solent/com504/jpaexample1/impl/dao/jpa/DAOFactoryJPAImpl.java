/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.jpaexample1.impl.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.solent.com504.jpaexample1.model.dao.AppointmentDAO;
import org.solent.com504.jpaexample1.model.dao.DAOFactory;
import org.solent.com504.jpaexample1.model.dao.PersonDAO;

/**
 *
 * @author cgallen
 */
public class DAOFactoryJPAImpl implements DAOFactory {

    // THIS HAS TO MATCH THE persistance.xml
    private static final String PERSISTENCE_UNIT_NAME = "modelPersistence";
    private static EntityManagerFactory factory;
    private static EntityManager em;
    private static PersonDAO personDAO;
    private static AppointmentDAO appointmentDAO;

    static {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();

        // note it is important that all DAO's share same entity manafer
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

}
