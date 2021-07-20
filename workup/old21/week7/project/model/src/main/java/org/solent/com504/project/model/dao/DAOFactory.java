package org.solent.com504.project.model.dao;

public interface DAOFactory {

    public PersonDAO getPersonDAO();

    public AppointmentDAO getAppointmentDAO();

    public void shutDown();
    
}
