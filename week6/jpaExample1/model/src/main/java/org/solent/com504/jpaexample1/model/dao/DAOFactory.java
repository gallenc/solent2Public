package org.solent.com504.jpaexample1.model.dao;

public interface DAOFactory {

    public PersonDAO getPersonDAO();

    public AppointmentDAO getAppointmentDAO();
}
