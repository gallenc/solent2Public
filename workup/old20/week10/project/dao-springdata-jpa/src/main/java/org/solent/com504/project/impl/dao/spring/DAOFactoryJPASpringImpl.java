/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.spring;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.solent.com504.project.model.dao.AppointmentDAO;
import org.solent.com504.project.model.dao.DAOFactory;
import org.solent.com504.project.model.dao.PersonDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author cgallen
 */
public class DAOFactoryJPASpringImpl implements DAOFactory {

    private static PersonDAO personDAO;
    private static AppointmentDAO appointmentDAO;
    private static ApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext("spring.xml");
        personDAO = (PersonDAO) context.getBean("personDAOImplSpring");
        appointmentDAO = (AppointmentDAO) context.getBean("appointmentDAOImplSpring");
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

    }

}
